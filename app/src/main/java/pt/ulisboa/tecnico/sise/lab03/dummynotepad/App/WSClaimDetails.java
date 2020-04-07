package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;

public class WSClaimDetails extends AsyncTask<Integer, String, ClaimRecord> {
    public final static String TAG = "WSClaimInfo";
    private int sessionId;
    private int claimId;

    public WSClaimDetails(int sessionId, int claimId) {
        this.sessionId = sessionId;
        this.claimId = claimId;
    }

    @Override
    protected ClaimRecord doInBackground(Integer... integers) {

        /*
         * method call invocation: getClaimInfo
         */
        try {
            ClaimRecord claimRecord = WSHelper.getClaimInfo(sessionId, claimId);
            if (claimRecord != null) {
                Log.d(TAG, "Get Claim Info result claimId " + claimId + " => " + claimRecord.toString());
                return claimRecord;
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
