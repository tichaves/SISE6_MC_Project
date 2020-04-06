package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.os.AsyncTask;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;

public class WSCustomerInfo extends AsyncTask <String, String, String> {
    public final static String TAG = "CustomerInfo";

    private TextView _name;
    private TextView _insure_policy_number;
    private TextView _nif;
    private TextView _birthday;
    private TextView _license_plates;
    private GlobalState globalState;


    public WSCustomerInfo(  )

}
