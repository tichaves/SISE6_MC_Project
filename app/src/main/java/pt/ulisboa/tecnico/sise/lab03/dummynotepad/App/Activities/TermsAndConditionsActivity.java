package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

import static pt.ulisboa.tecnico.sise.lab03.dummynotepad.R.layout.activity_terms_conditions;

public class TermsAndConditionsActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - TermsPolicyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_terms_conditions);


        // set up the listener of the back button
        final Button buttonBack = (Button) findViewById(R.id.terms_conditions_back_butn);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }

        });

        // set up the listener of the done button
        final Button buttonMenu = (Button) findViewById(R.id.terms_conditions_menu_butn);

        buttonMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}