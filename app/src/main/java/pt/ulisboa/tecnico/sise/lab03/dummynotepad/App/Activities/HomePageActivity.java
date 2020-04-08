package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSLogout;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSMyClaims;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSNewClaim;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class HomePageActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Home Page";
    private Button _buttonMyProfile;
    private Button _buttonMyClaims;
    private Button _buttonSettings;
    private Button _buttonNewClaim;

    private List<ClaimItem> _claimList;
    private GlobalState _globalState;
    private int _sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Log.d(LOG_TAG, "HomePage Activity Created.");

        _globalState = (GlobalState) getApplicationContext();
        _sessionId = _globalState.get_sessionId();

        try {
            _claimList = new WSMyClaims(_sessionId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        _globalState.set_ListClaim(_claimList);

        _buttonMyProfile = (Button)  findViewById(R.id.home_page_my_profile_btn);
        _buttonMyClaims  = (Button) findViewById(R.id.home_page_claim_history_btn);
        _buttonSettings  = (Button) findViewById(R.id.home_page_settings_btn);
        _buttonNewClaim  = (Button)  findViewById(R.id.home_page_new_claim_btn);

        _buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, MyProfileActivity.class);

                startActivity(intent);
            }
        });

        _buttonMyClaims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyClaims debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, MyClaimsActivity.class);

                startActivity(intent);
            }
        });

        _buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);

                startActivityForResult(intent, InternalProtocol.SETTINGS_REQUEST);
            }
        });

        _buttonNewClaim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, NewClaimActivity.class);

                startActivityForResult(intent, InternalProtocol.NEW_CLAIM_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case InternalProtocol.NEW_CLAIM_REQUEST:

                if (resultCode == Activity.RESULT_OK) {

                    // retrieve the title and body of the note from the intent
                    String claimTitle = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE);
                    String claimPlate = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE);
                    String claimDate = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DATE);
                    String claimDescription = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION);

                    // update the domain data structures
                    try {
                        boolean success = new WSNewClaim(_sessionId, claimTitle, claimPlate, claimDate, claimDescription).execute().get();
                        if (success){
                            Toast.makeText(getApplicationContext(), "Claim submitted successfully!", Toast.LENGTH_SHORT).show();
                            _claimList = new WSMyClaims(_sessionId).execute().get();
                            _globalState.set_ListClaim(_claimList);
                        } else {
                            Toast.makeText(getApplicationContext(), "Claim not submitted!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                Log.d(LOG_TAG, "No Claims:" + _globalState.get_ClaimList().size());
/*
                // refresh the list on screen
                _listView.setAdapter(new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, _noteList));*/

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.d(InternalProtocol.LOG, "Cancel pressed.");
                } else {
                    Log.d(InternalProtocol.LOG, "Internal error: unknown result code.");
                }
                break;

            case InternalProtocol.SETTINGS_REQUEST:
                if (resultCode == Activity.RESULT_CANCELED){
                    try {
                        boolean successLogout = new WSLogout(_sessionId).execute().get();
                        if(successLogout){
                            Toast.makeText(getApplicationContext(), "See you soon!", Toast.LENGTH_SHORT).show();
                            _globalState.set_sessionId(0);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Logout failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");
        }
    }

}
