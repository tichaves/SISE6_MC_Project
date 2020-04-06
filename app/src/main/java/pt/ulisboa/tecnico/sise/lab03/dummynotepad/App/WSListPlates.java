package pt.ulisboa.tecnico.sise.lab03.dummynotepad.App;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.GlobalState;

public class WSListPlates extends AsyncTask<Integer, Void, List<String>> {
    public final static String TAG = "ListPlates";

    private Spinner spinner;
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
            }catch (NullPointerException el) {
                Log.d(TAG, el.toString());

            }

        }
        return null;


    }

}
