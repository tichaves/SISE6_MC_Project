package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

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

public class ReadNoteActivity extends AppCompatActivity {
    private static final String LOG_TAG = "SISE - ReadNote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        // set up the listener of the done button
        final Button buttonDone = (Button) findViewById(R.id.read_note_btn_done);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });

        // display the title and body of the note identified by the index parameter
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.d(InternalProtocol.LOG, "Internal error: Index cannot be null.");
            finish();
            return;
        }
        int index = extras.getInt(InternalProtocol.READ_NOTE_INDEX);
        Log.d(InternalProtocol.LOG, "Index:" + index);

        // obtain a reference to the note's data structure
        GlobalState context = (GlobalState) getApplicationContext();
        Note note = context.getNoteList().get(index);

        // update the UI
        TextView noteTextView = (TextView) findViewById(R.id.read_note_title);
        noteTextView.setText(note.getTitle());
        TextView noteBodyTextView = (TextView) findViewById(R.id.read_note_text);
        noteBodyTextView.setText(note.getBody());
    }
}