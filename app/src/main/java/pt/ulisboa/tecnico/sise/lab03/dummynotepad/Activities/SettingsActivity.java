package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonMenu = (Button) findViewById(R.id.settings_act_btn_menu);
        buttonLogout = (Button) findViewById(R.id.settings_logout_btn);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
