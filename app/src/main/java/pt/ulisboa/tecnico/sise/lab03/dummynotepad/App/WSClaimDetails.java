package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities.ClaimDescriptionActivity;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;

public class WSClaimDetails extends AsyncTask<Integer, String, ClaimRecord> {
    public final static String TAG = "WSClaimInfo";
    private int _sessionId;
    private int _claimId;
    private ClaimDescriptionActivity _claimDescriptionActivity;

    public WSClaimDetails(int sessionId, int claimId, ClaimDescriptionActivity claimDescriptionActivity) {
        _sessionId = sessionId;
        _claimId = claimId;
        _claimDescriptionActivity = claimDescriptionActivity;
    }

    @Override
    protected ClaimRecord doInBackground(Integer... integers) {
        ClaimRecord claimRecord;
        String claimRecordFileName = "claimRecord"+ _claimId + ".json";
        /*
         * method call invocation: getClaimInfo
         */
        try {
            claimRecord = WSHelper.getClaimInfo(_sessionId, _claimId);
            if (claimRecord != null) {
                Log.d(TAG, "Get Claim Info result claimId " + _claimId + " => " + claimRecord.toString());
            } else {
                Log.d(TAG, "Get Claim Info result claimId " + _claimId + " => null.");
            }
            publishProgress("ok.\n");

            String claimRecordJson = JsonCodec.encodeClaimRecord(claimRecord);
            Log.d(TAG, "customerInfo: customerJson - " + claimRecordJson);

            JsonFileManager.jsonWriteToFile(_claimDescriptionActivity.getApplicationContext(), claimRecordFileName, claimRecordJson);
            Log.d(TAG, "customerInfo: written to - " + claimRecordFileName);

        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");


            String claimRecordJson = JsonFileManager.jsonReadFromFile(_claimDescriptionActivity.getApplicationContext(), claimRecordFileName);
            Log.d(TAG, "customerInfo: read from - " + claimRecordFileName);

            ClaimRecord jsonClaimRecord = JsonCodec.decodeClaimRecord(claimRecordJson);

            claimRecord = jsonClaimRecord;
            Log.d(TAG, "customerInfo: jsonCustomer - " + jsonClaimRecord);
        }

        return claimRecord;
    }
}
