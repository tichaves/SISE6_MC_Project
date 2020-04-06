package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Claim;

public class GlobalState extends Application {
    private int sessionId;

    private ArrayList<Claim> claimList = new ArrayList<Claim>();

    public void setListClaim(ArrayList<Claim> claimList) {
        this.claimList = claimList;
    }

    public ArrayList<Claim> getClaimList() {
        return this.claimList;
    }

    public void setSessionId(int id) {
        this.sessionId = id;
    }
}
