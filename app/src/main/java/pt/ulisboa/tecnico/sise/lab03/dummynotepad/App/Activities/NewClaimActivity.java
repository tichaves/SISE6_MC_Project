package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSListPlates;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class NewClaimActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - NewClaim";
    private Button _buttonSubmit;
    private Button _buttonCancel;
    private EditText _editTextTitle;
    private Spinner _spinnerPlate;
    private EditText _editTextDate;
    private EditText _editTextDesc;
    private List<String> _plateList = null;
    private String _claimPlate;

    private GlobalState _globalState;
    private int _sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _globalState = (GlobalState) getApplicationContext();
        _sessionId = _globalState.get_sessionId();

        try {
            _plateList = new WSListPlates(_sessionId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);
        Log.d(LOG_TAG, "NewClaim Activity Created.");

        _buttonSubmit  = (Button)  findViewById(R.id.new_claim_btn_submit);
        _buttonCancel  = (Button)  findViewById(R.id.new_claim_btn_cancel);
        _editTextTitle = (EditText)findViewById(R.id.new_claim_title_input);
        _spinnerPlate = (Spinner) findViewById(R.id.new_claim_plate_input);
        _editTextDate  = (EditText)findViewById(R.id.new_claim_date_input);
        _editTextDesc  = (EditText)findViewById(R.id.new_claim_description_input);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, _plateList);
        _spinnerPlate.setAdapter(adapter);
        _spinnerPlate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String claimPlate=(String) parent.getItemAtPosition(position);
                _claimPlate = claimPlate;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        _buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "Submit claim debug message!");
                Log.d(LOG_TAG, _editTextTitle.getText().toString());
                String claimTitle = _editTextTitle.getText().toString();
                //String claimPlate = _spinnerPlate.getText().toString();
                String claimDate = _editTextDate.getText().toString();
                String claimDesc = _editTextDesc.getText().toString();

                // check the title
                if (claimTitle.equals("") || _claimPlate.equals("") || claimDate.equals("") || claimDesc.equals("")) {
                    Toast.makeText(v.getContext(), "Missing information!", Toast.LENGTH_LONG).show();
                    return;
                }

                // return an intent containing the title and body of the new note
                Intent resultIntent = new Intent();
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE, claimTitle);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE, _claimPlate);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DATE, claimDate);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION, claimDesc);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        _buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // return the return code only; no intent message is required
                setResult(Activity.RESULT_CANCELED);
                // write a toast message
                Toast.makeText(v.getContext(), "Canceled!", Toast.LENGTH_SHORT).show();
                // finish activity
                finish();
            }
        });
    }
}
