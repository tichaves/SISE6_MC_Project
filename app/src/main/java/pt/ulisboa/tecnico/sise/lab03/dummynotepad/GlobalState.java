package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Claim;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Note;

public class GlobalState extends Application {
    private ArrayList<Note> _noteList;

    public void setNoteList(ArrayList<Note> noteList) {
        _noteList = noteList;
    }

    public ArrayList<Note> getNoteList() {
        return _noteList;
    }

    private ArrayList<Claim> claimList;

    public void setClaimList(ArrayList<Claim> claimList) {
        this.claimList = claimList;
    }

    public ArrayList<Claim> getClaimList() {
        return this.claimList;
    }
}
