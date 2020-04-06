package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Claim;
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

    private ArrayList<Claim> claimList;
    private GlobalState globalState;

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

                startActivity(intent);
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

                    this.claimList = globalState.getClaimList();
                    this.claimList.add(new Claim(claimTitle, claimPlate, claimDate, claimDescription));
                    globalState.setListClaim(this.claimList);

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
