package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Claim;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Note;

public class GlobalState extends Application {
/*    private ArrayList<Note> _noteList;

    public void setNoteList(ArrayList<Note> noteList) {
        _noteList = noteList;
    }

    public ArrayList<Note> getNoteList() {
        return _noteList;
    }*/

    private List<Claim> claimList = new ArrayList<Claim>();

    public void setListClaim(ArrayList<Claim> claimList) {
        this.claimList = claimList;
    }

    public List<Claim> getClaimList() {
        return this.claimList;
    }
}
