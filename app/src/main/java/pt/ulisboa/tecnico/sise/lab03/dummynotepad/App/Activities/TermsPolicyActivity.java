package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

import static pt.ulisboa.tecnico.sise.lab03.dummynotepad.R.layout.terms_conditions;

public class TermsPolicyActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - TermsPolicyActivity";

    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(terms_conditions);

        buttonMenu = (Button) findViewById(R.id.terms_menu_butn);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermsPolicyActivity.this, HomePageActivity.class);
                startActivity(intent);
            }

        });

    }
}

