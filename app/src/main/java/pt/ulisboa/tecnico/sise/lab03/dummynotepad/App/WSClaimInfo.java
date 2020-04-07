package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;

public class WSClaimInfo extends AsyncTask<Integer, String, Void> {
    public final static String TAG = "WSClaimInfo";
    private int sessionId;
    private int claimId;

    public WSClaimInfo(int sessionId, int claimId) {
        this.sessionId = sessionId;
        this.claimId = claimId;
    }

    @Override
    protected Void doInBackground(Integer... integers) {

        /*
         * Test method call invocation: getClaimInfo
         */
        publishProgress("Testing method call getClaimInfo...");
        try {
            //int claimId = 1;
            ClaimRecord claimRecord = WSHelper.getClaimInfo(sessionId, claimId);
            if (claimRecord != null) {
                Log.d(TAG, "Get Claim Info result claimId " + claimId + " => " + claimRecord.toString());
            } else {
                Log.d(TAG, "Get Claim Info result claimId " + claimId + " => null.");
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
