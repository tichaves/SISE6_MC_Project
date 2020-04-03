package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class HomePageActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Home Page";
    private Button buttonMyProfile;
    private Button buttonMyClaims;
    private Button buttonSettings;
    private Button buttonNewClaim;
    private Button buttonHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        buttonMyProfile = (Button)  findViewById(R.id.home_page_my_profile_btn);
        buttonMyClaims  = (Button) findViewById(R.id.home_page_claim_history_btn);
        buttonSettings  = (Button) findViewById(R.id.home_page_settings_btn);
        buttonNewClaim  = (Button)  findViewById(R.id.home_page_new_claim_btn);
        buttonHelp      = (Button)  findViewById(R.id.home_page_help_btn);

        /*buttonMyProfile.setOnClickListener(new View.OnClickListener() {
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
        });*/

        buttonNewClaim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "MyProfile debug message!");

                // return an intent containing the title and body of the new note
                Intent intent = new Intent(HomePageActivity.this, NewClaimActivity.class);

                startActivity(intent);
            }
        });
    }

}
