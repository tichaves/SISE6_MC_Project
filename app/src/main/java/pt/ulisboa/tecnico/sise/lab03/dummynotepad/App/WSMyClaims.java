package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities.HomePageActivity;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;

public class WSMyClaims extends AsyncTask<Integer, String, List<ClaimItem>> {
    public final static String TAG = "WSMyClaims";
    private HomePageActivity _homePageActivity;
    private int _sessionId;

    public WSMyClaims(int sessionId, HomePageActivity homePageActivity) {
        _sessionId = sessionId;
        _homePageActivity = homePageActivity;
    }

    @Override
    protected List<ClaimItem> doInBackground(Integer... integers) {
        String claimsFileName = "claims.json";
        List<ClaimItem> claimItemList;
                /*
         * Test method call invocation: listClaims
         */
        publishProgress("Testing method call listClaims...");
        try {
            claimItemList = WSHelper.listClaims(_sessionId);
            if (claimItemList != null) {
                String m = claimItemList.size() > 0 ? "" : "empty array";
                for (ClaimItem claimItem : claimItemList ) {
                    m += " ("+ claimItem.toString() + ")";
                }
                Log.d(TAG, "List claim item result => " + m);
            } else {
                Log.d(TAG, "List claim item result => null.");
            }

            // NS: Testing json codec for the customer data structure
            //String claimsFileName = "claims.json";

            String claimsJson = JsonCodec.encodeClaimList(claimItemList);
            Log.d(TAG, "customerInfo: customerJson - " + claimsJson);

            JsonFileManager.jsonWriteToFile(_homePageActivity.getApplicationContext(), claimsFileName, claimsJson);
            Log.d(TAG, "customerInfo: written to - " + claimsFileName);

            return claimItemList;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");

            String claimsJson = JsonFileManager.jsonReadFromFile(_homePageActivity.getApplicationContext(), claimsFileName);
            Log.d(TAG, "customerInfo: read from - " + claimsFileName);

            List<ClaimItem> jsonClaims = JsonCodec.decodeClaimList(claimsJson);

            claimItemList = jsonClaims;
            Log.d(TAG, "customerInfo: jsonCustomer - " + jsonClaims);

        }
        return claimItemList;
    }
}
