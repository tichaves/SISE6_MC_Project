package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class SettingsActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
