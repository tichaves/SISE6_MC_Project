package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;

public class WSMyClaims extends AsyncTask<Void, String, Void> {
    public final static String TAG = "WSMyClaims";
    private int sessionId;

    public WSMyClaims(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        /*
         * Test method call invocation: listClaims
         */
        publishProgress("Testing method call listClaims...");
        try {
            List<ClaimItem> claimItemList = WSHelper.listClaims(sessionId);
            if (claimItemList != null) {
                String m = claimItemList.size() > 0 ? "" : "empty array";
                for (ClaimItem claimItem : claimItemList ) {
                    m += " ("+ claimItem.toString() + ")";
                }
                Log.d(TAG, "List claim item result => " + m);
            } else {
                Log.d(TAG, "List claim item result => null.");
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }

        return null;
    }
}