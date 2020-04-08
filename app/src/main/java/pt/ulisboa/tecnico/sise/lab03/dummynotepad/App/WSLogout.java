package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

public class WSLogout  extends AsyncTask<String, String, Boolean> {
    public final static String TAG = "WSLogin";

    private int _sessionId;

    public WSLogout(int sessionId) {
        _sessionId = sessionId;
    }

    /*
     * Test method call invocation: logout
     */
    @Override
    protected Boolean doInBackground(String... strings) {

        publishProgress("Testing method call logout...");
        try {
            boolean result = WSHelper.logout(_sessionId);
            Log.d(TAG, "Logout result => " + result);
            return result;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return false;
    }
}
