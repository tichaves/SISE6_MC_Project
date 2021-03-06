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
import android.widget.ListView;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.InternalProtocol;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.R;

public class MyClaimsActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "InSureApp - My Claims";
    private ListView _listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);

        // place the note list in the application domain
        GlobalState globalState = (GlobalState) getApplicationContext();
        List<ClaimItem> claimList = globalState.get_ClaimList();

        // assign adapter to list view
        _listView = (ListView) findViewById(R.id.my_claims_list_list);
        ArrayAdapter<ClaimItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, claimList);
        _listView.setAdapter(adapter);

        // attach click listener to list view items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the read note activity, passing to it the index position as parameter
                Log.d("position", position+"");
                Intent intent = new Intent(MyClaimsActivity.this, ClaimDescriptionActivity.class);
                intent.putExtra(InternalProtocol.READ_CLAIM_INDEX, position);
                startActivityForResult(intent, InternalProtocol.CLAIM_INFORMATION_REQUEST);

                // if instead of string, we pass a list with notes, we can retrieve the original Note object this way
                // Claim claim = (Claim) parent.getItemAtPosition(position);
            }
        });

        // set up the button listener for the "New Note" button
        Button menuButton = (Button) findViewById(R.id.my_claims_menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case InternalProtocol.CLAIM_INFORMATION_REQUEST:
                if (resultCode == Activity.RESULT_CANCELED){
                    finish();
                }
        }
    }
}
