package pt.ulisboa.tecnico.sise.lab03.dummynotepad.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Note;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class ListNotesActivity extends AppCompatActivity {
    private static final String LOG_TAG = "SISE - ListNotes";
    private ListView _listView;
    private ArrayList<Note> _noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        // place the note list in the application domain
        _noteList = new ArrayList<Note>();
        GlobalState globalState = (GlobalState) getApplicationContext();
        globalState.setNoteList(_noteList);

        // assign adapter to list view
        _listView = (ListView) findViewById(R.id.list_notes_list);
        ArrayAdapter<Note> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, _noteList);
        _listView.setAdapter(adapter);

        // attach click listener to list view items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the read note activity, passing to it the index position as parameter
                Log.d("position", position+"");
                Intent intent = new Intent(ListNotesActivity.this, ReadNoteActivity.class);
                intent.putExtra(InternalProtocol.READ_NOTE_INDEX, position);
                startActivity(intent);

                // if instead of string, we pass a list with notes, we can retrieve the original Note object this way
                //Note note = (Note)parent.getItemAtPosition(position);
            }
        });

        // set up the button listener for the "New Note" button
        Button newNoteButton = (Button) findViewById(R.id.list_notes_btn_new_note);
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListNotesActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, InternalProtocol.NEW_NOTE_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case InternalProtocol.NEW_NOTE_REQUEST:

                if (resultCode == Activity.RESULT_OK) {

                    // retrieve the title and body of the note from the intent
                    String noteTitle = data.getStringExtra(InternalProtocol.KEY_NEW_NOTE_TITLE);
                    String noteBody = data.getStringExtra(InternalProtocol.KEY_NEW_NOTE_BODY);
                    Log.d(InternalProtocol.LOG, "New note:" + noteTitle + "," + noteBody);

                    // update the domain data structures
                    _noteList.add(new Note(noteTitle, noteBody));

                    // refresh the list on screen
                    _listView.setAdapter(new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, _noteList));

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.d(InternalProtocol.LOG, "Cancel pressed.");
                } else {
                    Log.d(InternalProtocol.LOG, "Internal error: unknown result code.");
                }
                break;
            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");
        }
    }
}