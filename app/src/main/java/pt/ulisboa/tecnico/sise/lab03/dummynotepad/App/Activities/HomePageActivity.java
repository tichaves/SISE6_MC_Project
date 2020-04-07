package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSMyClaims;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSNewClaim;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class HomePageActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Home Page";
    private Button buttonMyProfile;
    private Button buttonMyClaims;
    private Button buttonSettings;
    private Button buttonNewClaim;
    private Button buttonHelp;

    private List<ClaimItem> claimList;
    private GlobalState globalState;
    private int sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        globalState = (GlobalState) getApplicationContext();

        buttonMyProfile = (Button)  findViewById(R.id.home_page_my_profile_btn);
        buttonMyClaims  = (Button) findViewById(R.id.home_page_claim_history_btn);
        buttonSettings  = (Button) findViewById(R.id.home_page_settings_btn);
        buttonNewClaim  = (Button)  findViewById(R.id.home_page_new_claim_btn);
        buttonHelp      = (Button)  findViewById(R.id.home_page_help_btn);

        this.sessionId = globalState.getSessionId();

        try {
            this.claimList = new WSMyClaims(this.sessionId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, MyProfileActivity.class);

                startActivity(intent);
            }
        });

        buttonMyClaims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyClaims debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, MyClaimsActivity.class);

                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);

                startActivity(intent);
            }
        });

        buttonNewClaim.setOnClickListener(new View.OnClickListener() {
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
        Log.d(LOG_TAG, "No Claims:" + globalState.getClaimList().size());
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
                        boolean success = new WSNewClaim(this.sessionId, claimTitle, claimPlate, claimDate, claimDescription).execute().get();
                        if (success){
                            Toast.makeText(getApplicationContext(), "Claim submitted successfully!", Toast.LENGTH_SHORT).show();
                            this.claimList = new WSMyClaims(sessionId).execute().get();
                            globalState.setListClaim(this.claimList);
                        } else {
                            Toast.makeText(getApplicationContext(), "Claim not submitted!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                Log.d(LOG_TAG, "No Claims:" + globalState.getClaimList().size());
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
            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");
        }
        Log.d(LOG_TAG, "No Claims: " + claimList.size());
    }

}
