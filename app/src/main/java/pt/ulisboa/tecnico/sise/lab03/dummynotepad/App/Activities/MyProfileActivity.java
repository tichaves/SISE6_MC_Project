package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSCustomerInfo;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class MyProfileActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - MyProfileActivity";

    private Button _buttonMenu;
    private TextView _insurePolicyOutput;
    private TextView _clientNameOutput;
    private TextView _nifOutput;
    private TextView _addressOutput;
    private TextView _dateOfBirthOutput;

    private GlobalState _globalState;
    private int _sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        _globalState = (GlobalState) getApplicationContext();

        _buttonMenu         = (Button)   findViewById(R.id.settings_act_btn_menu);
        _insurePolicyOutput = (TextView) findViewById(R.id.myprofile_act_insure_policy_output);
        _clientNameOutput   = (TextView) findViewById(R.id.myprofile_act_name_output);
        _nifOutput          = (TextView) findViewById(R.id.myprofile_act_nif_output);
        _addressOutput      = (TextView) findViewById(R.id.myprofile_act_adress_output);
        _dateOfBirthOutput  = (TextView) findViewById(R.id.myprofile_act_birth_output);

        _sessionId = _globalState.get_sessionId();

        _buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            Customer customer = new WSCustomerInfo(_sessionId, MyProfileActivity.this).execute().get();
            _insurePolicyOutput.setText(String.valueOf(customer.getPolicyNumber()));
            _clientNameOutput.setText(customer.getName());
            _nifOutput.setText(String.valueOf(customer.getFiscalNumber()));
            _dateOfBirthOutput.setText(customer.getDateOfBirth());
            _addressOutput.setText(customer.getAddress());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }

}

