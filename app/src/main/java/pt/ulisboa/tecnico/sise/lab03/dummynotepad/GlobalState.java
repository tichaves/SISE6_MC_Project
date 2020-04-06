package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;

public class GlobalState extends Application {

    private int sessionId;
    private Customer customer;
    private ArrayList<ClaimRecord> claimList = new ArrayList<ClaimRecord>();
    public void setListClaim(ArrayList<ClaimRecord> claimList) {
        this.claimList = claimList;
    }
    public ArrayList<ClaimRecord> getClaimList() {
        return this.claimList;
    }
    public void setSessionId(int id) {
        this.sessionId = id;
    }
    public Customer getCustomer() {
        return customer;
    }

}

