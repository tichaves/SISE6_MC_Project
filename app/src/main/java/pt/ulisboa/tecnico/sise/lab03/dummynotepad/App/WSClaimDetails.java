package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;

public class WSClaimDetails extends AsyncTask<Integer, String, ClaimRecord> {
    public final static String TAG = "WSClaimInfo";
    private int _sessionId;
    private int _claimId;

    public WSClaimDetails(int sessionId, int claimId) {
        _sessionId = sessionId;
        _claimId = claimId;
    }

    @Override
    protected ClaimRecord doInBackground(Integer... integers) {

        /*
         * method call invocation: getClaimInfo
         */
        try {
            ClaimRecord claimRecord = WSHelper.getClaimInfo(_sessionId, _claimId);
            if (claimRecord != null) {
                Log.d(TAG, "Get Claim Info result claimId " + _claimId + " => " + claimRecord.toString());
                return claimRecord;
            } else {
                Log.d(TAG, "Get Claim Info result claimId " + _claimId + " => null.");
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
