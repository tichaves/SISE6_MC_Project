package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Note;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class MyProfileActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - MyProfileActivity";
    private Button buttonMenu;
    private TextView name;
    private TextView insure_policy_number;
    private TextView nif;
    private TextView birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        buttonMenu = (Button) findViewById(R.id.settings_act_btn_menu);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

 
    }
}
