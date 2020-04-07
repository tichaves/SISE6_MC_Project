package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;

public class WSCustomerInfo extends AsyncTask<String, String, Customer> {
    public final static String TAG = "WSCustomerInfo";

    private int _sessionID;

    private GlobalState globalState;

    public WSCustomerInfo(int sessionID) {
        _sessionID = sessionID;
    }

    @Override
    protected Customer doInBackground (String... String) {
        publishProgress("Testing method call getCustomerInfo...");

        try {
            Customer customer = WSHelper.getCustomerInfo(_sessionID);

//          Customer.setPlateList(WSHelper.listPlates(session.ID));
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
            }
            publishProgress("ok.\n");
            return customer;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}

