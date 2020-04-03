package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class NewClaimActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "SISE - NewNote";
    private Button buttonSubmit;
    private Button buttonCancel;
    EditText editTextTitle;
    EditText editTextPlate;
    EditText editTextDate;
    EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);

        buttonSubmit  = (Button)  findViewById(R.id.new_claim_btn_submit);
        buttonCancel  = (Button)  findViewById(R.id.new_note_btn_cncl);
        editTextTitle = (EditText)findViewById(R.id.new_claim_title_input);
        editTextPlate = (EditText)findViewById(R.id.new_claim_plate_input);
        editTextDate  = (EditText)findViewById(R.id.new_claim_date_input);
        editTextDesc  = (EditText)findViewById(R.id.new_claim_desciption_input);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "Submit claim debug message!");
                Log.d(LOG_TAG, editTextTitle.getText().toString());
                String noteTitle = editTextTitle.getText().toString();
                String noteBody = editTextDesc.getText().toString();

                // check the title
                if (noteTitle.equals("")) {
                    Toast.makeText(v.getContext(), "Write a note title", Toast.LENGTH_LONG).show();
                    return;
                }

                // return an intent containing the title and body of the new note
                Intent resultIntent = new Intent();
                resultIntent.putExtra(InternalProtocol.KEY_NEW_NOTE_TITLE, noteTitle);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_NOTE_BODY, noteBody);
                setResult(Activity.RESULT_OK, resultIntent);
                // write a toast message
                Toast.makeText(v.getContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        /*buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // return the return code only; no intent message is required
                setResult(Activity.RESULT_CANCELED);
                // write a toast message
                Toast.makeText(v.getContext(), "Changed button title!", Toast.LENGTH_SHORT).show();
                // finish activity
                finish();
            }
        });*/
    }
}
