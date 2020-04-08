package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;

public class WSCustomerInfo extends AsyncTask<String, String, Customer> {
    public final static String TAG = "WSCustomerInfo";

    private int _sessionId;
    private GlobalState _globalState;

    public WSCustomerInfo(int sessionId) {
        _sessionId = sessionId;
    }



    @Override
    protected Customer doInBackground (String... String) {
//        /*
//         * Test method call invocation: getCustomerInfo
//         */
//        publishProgress("Testing method call getCustomerInfo...");
//        try {
//            Customer customer = WSHelper.getCustomerInfo(_sessionId);
//            if (customer == null) {
//                Log.d(TAG, "Get customer info result => null");
//            } else {
//                Log.d(TAG, "Get customer info result => " + customer.toString());
//            }
//
//            // NS: Testing json codec for the customer data structure
//            String customerFileName = "customer.json";
//
//            String customerJson = JsonCodec.encodeCustomerInfo(customer);
//            Log.d(TAG, "customerInfo: customerJson - " + customerJson);
//
//            JsonFileManager.jsonWriteToFile(_globalState.getApplicationContext(), customerFileName, customerJson);
//            Log.d(TAG, "customerInfo: written to - " + customerFileName);
//
//            customerJson = JsonFileManager.jsonReadFromFile(_globalState.getApplicationContext(), customerFileName);
//            Log.d(TAG, "customerInfo: read from - " + customerFileName);
//
//            Customer jsonCustomer = JsonCodec.decodeCustomerInfo(customerJson);
//            Log.d(TAG, "customerInfo: jsonCustomer - " + jsonCustomer);
//
//            publishProgress("ok.\n");
//        } catch (Exception e) {
//            Log.d(TAG, e.toString());
//            publishProgress("failed.\n");
//        }

        publishProgress("Testing method call getCustomerInfo...");

        try {
            Customer customer = WSHelper.getCustomerInfo(_sessionId);

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

