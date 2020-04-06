package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

public class WSLogin extends AsyncTask<String, String, Integer> {
    public final static String TAG = "WSLogin";
    /*private TextView _textView;

    public WSLogin(TextView textView) {
        _textView = textView;
    }*/

    private String userId;
    private String password;

    public WSLogin(String user, String password) {
        this.userId = user;
        this.password = password;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        int sessionId = -1;

        /*
         * Test method call invocation: login
         */
        publishProgress("Testing method call login wrong...");
        try {

            sessionId = WSHelper.login(this.userId, this.password);        // username doesn't exist
            Log.d(TAG, "Login result => " + sessionId);
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        publishProgress("Testing method call login wrong2...");
        return sessionId;
    }
}
