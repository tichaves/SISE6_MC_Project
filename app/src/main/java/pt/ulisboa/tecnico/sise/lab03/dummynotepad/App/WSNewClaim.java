package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities.HomePageActivity;

public class WSNewClaim extends AsyncTask<String, Void, Boolean> {
    public final static String TAG = "ListPlates";
    private Integer _sessionId;
    private String _claimTitle;
    private String _claimPlate;
    private String _claimDate;
    private String _claimDescription;
//    private Context context;
//    private Boolean exceptionCaught = false;

//    public WSNewClaim (Integer sessionId, Context context) {
//        this.context = context;
//        this.sessionId = sessionId;
//    }

    public WSNewClaim(int sessionId, String claimTitle, String claimPlate, String claimDate, String claimDescription) {
        _sessionId = sessionId;
        _claimTitle = claimTitle;
        _claimPlate = claimPlate;
       _claimDate = claimDate;
        _claimDescription = claimDescription;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            Log.d(TAG,"Claim details: " + params[0] + ", " + params[1] + ", " + params[2]);
            boolean r = WSHelper.submitNewClaim(_sessionId, params[0], params[1], params[2], params[3]);

            Log.d(TAG, "Submit new claim => " + r);
            return r;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            //exceptionCaught = true;
        }
        return false;
    }

    /*@Override
    protected void onPostExecute(Boolean result) {
        Log.d(TAG,"Submit new claim => " + result + ", and Exception Triggered => " + exceptionCaught);

        String toastMsg;
        if (result && !exceptionCaught) {

            Intent intent = new Intent(context, HomePageActivity.class);
            context.startActivity(intent);
            ((Activity)context).finish();
            toastMsg = "Your Claim has been submitted with success!";

        } else if (exceptionCaught) {
            toastMsg = "Server Error! Please try again later!";
        } else {
            toastMsg = "Error: Invalid parameters!";
        }

        Toast.makeText(context.getApplicationContext(),
                toastMsg,
                Toast.LENGTH_LONG)
                .show();

    }*/

}
