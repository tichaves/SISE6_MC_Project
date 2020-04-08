package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class SettingsActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Settings";
    private Button _buttonLogout;
    private Button _buttonMenu;
    private Button _buttonTermsAndConditions;
    private Button _buttonPrivacyPolicy;
    private Button _buttonTechnicalSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(LOG_TAG, "Settings Activity Created.");

        _buttonMenu = (Button) findViewById(R.id.settings_act_btn_menu);
        _buttonLogout = (Button) findViewById(R.id.settings_logout_btn);
        _buttonTermsAndConditions = (Button) findViewById(R.id.terms_support_button);
        _buttonPrivacyPolicy = (Button) findViewById(R.id.privacy_policy_btn);
        _buttonTechnicalSupport = (Button) findViewById(R.id.tecnical_support_btn);

        _buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        _buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        _buttonTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, TermsPolicyActivity.class);
                startActivity(intent);
            }

        });
        _buttonPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        _buttonTechnicalSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, TecnicalSupportActivity.class);
               startActivity(intent);
            }
        });


    }
}
