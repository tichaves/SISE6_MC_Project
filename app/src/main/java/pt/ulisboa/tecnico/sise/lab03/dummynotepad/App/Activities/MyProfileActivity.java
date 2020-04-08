package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSCustomerInfo;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class MyProfileActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - MyProfileActivity";

    private Button buttonMenu;
    private TextView insurePolicyOutput;
    private TextView clientNameOutput;
    private TextView nifOutput;
    private TextView adressOutput;
    private TextView dateOfBirthOutput;
    private int sessionId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        final GlobalState globalState = (GlobalState) getApplicationContext();

        buttonMenu = (Button) findViewById(R.id.settings_act_btn_menu);
        insurePolicyOutput = (TextView)findViewById(R.id.myprofile_act_insure_policy_output);
        clientNameOutput= (TextView)findViewById(R.id.myprofile_act_name_output);
        nifOutput= (TextView)findViewById(R.id.myprofile_act_nif_output);
        adressOutput= (TextView)findViewById(R.id.myprofile_act_adress_output);
        dateOfBirthOutput= (TextView)findViewById(R.id.myprofile_act_birth_output);
        this.sessionId = globalState.get_sessionId();

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

    }
// exemplo do prof para ir buscar as coisas

     @Override
    protected void onStart() {
         super.onStart();
         try {
             Customer customer = new WSCustomerInfo(this.sessionId).execute().get();
             insurePolicyOutput.setText(String.valueOf(customer.getPolicyNumber()));
             clientNameOutput.setText(customer.getName());
             nifOutput.setText(String.valueOf(customer.getFiscalNumber()));
             dateOfBirthOutput.setText(customer.getDateOfBirth());
             adressOutput.setText(customer.getAddress());
         } catch (Exception e) {
//             e.printStackTrace();
         }

     }
    }

