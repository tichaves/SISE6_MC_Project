package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class ClaimDescriptionActivity extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - ClaimDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_description);

        // set up the listener of the back button
        final Button buttonBack = (Button) findViewById(R.id.claim_description_back_btn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        // set up the listener of the done button
        final Button buttonMenu = (Button) findViewById(R.id.claim_description_menu_btn);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });

        // display the claim items identified by the index parameter
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.d(InternalProtocol.LOG, "Internal error: Index cannot be null.");
            finish();
            return;
        }
        int index = extras.getInt(InternalProtocol.READ_CLAIM_INDEX);
        Log.d(InternalProtocol.LOG, "Index:" + index);

        // obtain a reference to the note's data structure
        GlobalState context = (GlobalState) getApplicationContext();
        ClaimRecord claim = context.getClaimList().get(index);

        /*// update the UI
        TextView claimTitle = (TextView) findViewById(R.id.read_note_title);
        noteTextView.setText(note.getTitle());
        TextView noteBodyTextView = (TextView) findViewById(R.id.read_note_text);
        noteBodyTextView.setText(note.getBody());*/
    }
}

