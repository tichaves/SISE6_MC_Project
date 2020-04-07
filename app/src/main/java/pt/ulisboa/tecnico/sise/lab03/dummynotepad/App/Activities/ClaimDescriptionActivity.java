package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.WSClaimDetails;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
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
                setResult(Activity.RESULT_CANCELED);
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
        ClaimItem claim = context.getClaimList().get(index);
        try {
            ClaimRecord claimRecord = new WSClaimDetails(context.getSessionId(), claim.getId()).execute().get();
            // update the UI
            TextView claimTitle = (TextView) findViewById(R.id.claim_description_title_data);
            claimTitle.setText(claimRecord.getTitle());
            TextView claimPlate = (TextView) findViewById(R.id.claim_description_plate_data);
            claimPlate.setText(claimRecord.getPlate());
            TextView claimDate = (TextView) findViewById(R.id.claim_description_date_data);
            claimDate.setText(claimRecord.getOccurrenceDate());
            TextView claimDesc = (TextView) findViewById(R.id.claim_description_body_data);
            claimDesc.setText(claimRecord.getDescription());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

