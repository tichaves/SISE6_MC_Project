package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;

public class WSCustomerInfo extends AsyncTask<String, String, Void> {
    public final static String TAG = "WSCustomerInfo";

    private TextView _customerName;
    private TextView _fiscalNumber;
    private TextView _address;
    private TextView _dateOfBirth;
    private TextView _policyNumber;
    private TextView _license_plates;
    private TextView _sessionID;

    private GlobalState globalState;

    /*private TextView _textView;

    public WSLogin(TextView textView) {
        _textView = textView;
    }*/

    public WSCustomerInfo(TextView customerName, TextView fiscalNumber, TextView address,
                          TextView dateOfBirth, TextView policyNumber, TextView license_plates, TextView sessionID) {

        _customerName = customerName;
        _fiscalNumber = fiscalNumber;
        _address = address;
        _dateOfBirth = dateOfBirth;
        _policyNumber = policyNumber;
        _license_plates = license_plates;
        _sessionID = sessionID;
    }

    @Override
    protected Void doInBackground (String... String) {
        int sessionId = -1;

        publishProgress("Testing method call getCustomerInfo...");

        try {
            Customer customer = WSHelper.getCustomerInfo(sessionId);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}