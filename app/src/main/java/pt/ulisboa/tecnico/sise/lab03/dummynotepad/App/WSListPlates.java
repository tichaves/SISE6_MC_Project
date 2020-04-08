package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.App.Activities.NewClaimActivity;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;

public class WSListPlates extends AsyncTask<Integer, Void, List<String>> {
    public final static String TAG = "InSureApp - ListPlates";
    private int _sessionId;
    private NewClaimActivity _newClaimActivity;

    public WSListPlates(int sessionId, NewClaimActivity newClaimActivity) {
        _sessionId = sessionId;
        _newClaimActivity = newClaimActivity;
    }

    @Override
    protected List<String> doInBackground(Integer... params) {
        String platesFileName = "plates.json";
        List<String> plateList;
        /*
         * Test method call invocation: listPlates
         */
        try {
            plateList = WSHelper.listPlates(_sessionId);
            if (plateList != null) {
                String m = plateList.size() > 0 ? "" : "empty array";
                for (String plate : plateList) {
                    m += " (" + plate + ")";
                }
                Log.d(TAG, "List plates result => " + m);
            } else {
                Log.d(TAG, "List plates result => null.");
            }

            String platesJson = JsonCodec.encodePlateList(plateList);
            Log.d(TAG, "customerInfo: customerJson - " + platesJson);

            JsonFileManager.jsonWriteToFile(_newClaimActivity.getApplicationContext(), platesFileName, platesJson);
            Log.d(TAG, "customerInfo: written to - " + platesFileName);
        } catch (Exception e) {
            Log.d(TAG, e.toString());

            String plateListJson = JsonFileManager.jsonReadFromFile(_newClaimActivity.getApplicationContext(), platesFileName);
            Log.d(TAG, "customerInfo: read from - " + platesFileName);

            List<String> jsonPlateList = JsonCodec.decodePlateList(plateListJson);

            plateList = jsonPlateList;
            Log.d(TAG, "customerInfo: jsonCustomer - " + jsonPlateList);
        }

        return plateList;
    }

    /*private Spinner spinner;
    private GlobalState gs;

    public WSListPlates(Spinner spinner, Context context) {
        this.spinner = spinner;
        this.gs = (GlobalState)context.getApplicationContext();

    }

    @Override
    protected List<String> doInBackground(Integer... params) {
        Integer sessionId = params[0];
        List<String> result = null;
        try {
            result = WSHelper.listPlates(sessionId);
        } catch (Exception e) {
            try {
                result = gs.getCustomer().getPlateList();
            } catch (NullPointerException el) {
                Log.d(TAG, el.toString());

            }

        }
        return result;
    }

    /*
    @Override
    protected void onPostExecute(List<String> result) {
        Boolean isLoadSuccessful = result != null && result.size() != 0;
        Log.d(TAG,"List Plates Load Successful => " + isLoadSuccessful);

        if (isLoadSuccessful) {
            if (spinner != null) {
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(gs, android.R.layout.simple_spinner_dropdown_item, result);
                spinner.setAdapter(spinnerAdapter);
                Log.d(TAG,"List Plates loaded to spinner");
            }

            gs.setCustomerLicensePlates(result);
            gs.writeCustomerInCache(gs.getCustomer());

        } else {
            String toastMsg = "Server Error and Plates not stored in cache!\nPlease try again later!";

            Toast.makeText(gs.getApplicationContext(),
                    toastMsg,
                    Toast.LENGTH_LONG)
                    .show();

            Log.d(TAG,toastMsg);
        }


    }*/

}

