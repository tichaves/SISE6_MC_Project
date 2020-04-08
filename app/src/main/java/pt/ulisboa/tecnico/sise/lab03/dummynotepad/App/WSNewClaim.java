package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities.HomePageActivity;

public class WSNewClaim extends AsyncTask<String, String, Boolean> {
    public final static String TAG = "ListPlates";
    private int _sessionId;
    private String _claimTitle;
    private String _claimPlate;
    private String _claimDate;
    private String _claimDescription;
    private HomePageActivity _homePageActivity;
//    private Context context;

//    public WSNewClaim (Integer sessionId, Context context) {
//        this.context = context;
//        this.sessionId = sessionId;
//    }

    public WSNewClaim(int sessionId, String claimTitle, String claimPlate, String claimDate,
                      String claimDescription, HomePageActivity homePageActivity) {
        _sessionId = sessionId;
        _claimTitle = claimTitle;
        _claimPlate = claimPlate;
        _claimDate = claimDate;
        _claimDescription = claimDescription;
        _homePageActivity = homePageActivity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        try {
            Log.d(TAG,"Claim details: " + _claimTitle + ", " + _claimDate + ", " + _claimPlate);
            boolean r = WSHelper.submitNewClaim(_sessionId, _claimTitle, _claimDate, _claimPlate, _claimDescription);
            return r;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return false;
    }

}
