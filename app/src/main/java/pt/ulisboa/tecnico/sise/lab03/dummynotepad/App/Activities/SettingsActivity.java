package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class SettingsActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Settings";
    private Button buttonLogout;
    private Button buttonMenu;
    private Button buttonTermsAndConditions;
    private Button buttonPrivacyPolicy;
    private Button buttonTecnicalSupport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonMenu = (Button) findViewById(R.id.settings_act_btn_menu);
        buttonLogout = (Button) findViewById(R.id.settings_logout_btn);
        buttonTermsAndConditions = (Button) findViewById(R.id.terms_support_button);
        buttonPrivacyPolicy = (Button) findViewById(R.id.privacy_policy_btn);
        buttonTecnicalSupport = (Button) findViewById(R.id.tecnical_support_btn);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        buttonTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, TermsPolicyActivity.class);
                startActivity(intent);
            }

        });
        buttonPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        buttonTecnicalSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, TecnicalSupportActivity.class);
               startActivity(intent);
            }
        });


    }
}
