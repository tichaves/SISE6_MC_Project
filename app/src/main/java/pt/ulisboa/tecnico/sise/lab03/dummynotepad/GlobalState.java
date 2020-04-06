package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimMessage;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimRecord;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;

public class GlobalState extends Application {
    private int sessionId = -1;
    private Customer customer;
    private Map<Integer, Integer> _nbrAutoInsureMsgsByClaimId;
    public final static Random RANDOM = new Random();

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer newCustomer) {
        // if a customer already existed then it is necessary to clone the
        // claimRecords and plateLists here
        Customer oldCustomer = customer;
        this.customer = newCustomer;

        if (oldCustomer != null) {
            this.customer.setClaimItemList(oldCustomer.getClaimItemList());
            this.customer.setClaimRecordList(oldCustomer.getClaimRecordList());
        }
    }

    public void writeCustomerInCache(Customer c) {
        writeObjectInFile(c, InternalProtocol.CACHE_CUSTOMER);
    }

    public void writeObjectInFile(Object o, String filename) {

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(o);

            out.close();
            fos.close();

            Log.d(filename, filename + " - Object successfully written");
        } catch (IOException e) {
            Log.d(filename, filename + " - WRITE ERROR");
        }

    }

    public Object readObjectInFile(String filename) {

        Object o = null;
        try {
            FileInputStream fis = openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            o = (Object) in.readObject();

            in.close();
            fis.close();

            Log.d(filename, filename + " - Object successfully read");

        } catch (IOException | ClassNotFoundException e) {
            Log.d(filename, filename + " - read failed: not in cache");
        }

        return o;
    }

    public void destroyFileInCache(Context context, String filename) {
        try {
            context.deleteFile(filename);
            Log.d("DESTROY_CACHE_FILE", filename + " was deleted");
        } catch (Exception e) {
            // e.printStackTrace();
            Log.d("DESTROY_CACHE_FILE", filename + " does not exist");
        }
    }

    public Map<Integer, Integer> getNbrAutoInsureMsgsByClaimId() {
        return _nbrAutoInsureMsgsByClaimId;
    }

    public void setNbrAutoInsureMsgsByClaimId(Map<Integer, Integer> nbrAutoInsureMsgsByClaimId) {
        this._nbrAutoInsureMsgsByClaimId = nbrAutoInsureMsgsByClaimId;
    }

    public void setCustomerLicensePlates(List<String> plates) {
        this.customer.setPlateList(plates);
    }

    public void setCustomerClaimItemList(List<ClaimItem> claimItemList) {
        this.customer.setClaimItemList(claimItemList);
    }

    public void addClaimRecordToCustomer(ClaimRecord claimRecord) {
        ClaimRecord cr = this.getCustomerClaimRecord(claimRecord.getId());
        if (cr == null) {
            this.customer.addClaim(claimRecord);
        }
    }

    public void setCustomerClaimItsClaimMessageList(int claimId, List<ClaimMessage> claimMessages) {
        ClaimRecord cr = getCustomerClaimRecord(claimId);
        if (cr != null) {
            cr.setClaimMessageList(claimMessages);
        }
    }

    public ClaimRecord getCustomerClaimRecord(int claimId) {
        List<ClaimRecord> claimRecords = null;
        claimRecords = this.customer.getClaimRecordList();

        ClaimRecord claimRecord = null;

        if (claimRecords != null) {
            for (ClaimRecord cr : claimRecords) {
                if (cr.getId() == claimId) {
                    claimRecord = cr;
                    break;
                }
            }
        }
        return claimRecord;
    }

    public void clearCustomer() {
        customer.setSessionId(-2);
        customer.setName("");
        customer.setPolicyNumber(-1);
        customer.setAddress("");
        customer.setDateOfBirth("");
        customer.setUsername("");
        customer.setPassword("");
        customer.getClaimRecordList().clear();
        customer.getPlateList().clear();
        if (customer.getClaimItemList() != null) {
            customer.setClaimItemList(null);
        }

        customer = null;
    }
}
