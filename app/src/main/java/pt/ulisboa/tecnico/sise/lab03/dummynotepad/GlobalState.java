package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.ClaimItem;
import pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel.Customer;

public class GlobalState extends Application {
    private int _sessionId;
    private Customer _customer;
    private List<ClaimItem> _claimList;

    public void set_ListClaim(List<ClaimItem> claimList) {
        _claimList = claimList;
    }

    public List<ClaimItem> get_ClaimList() {
        return _claimList;
    }

    public void set_sessionId(int id) {
        _sessionId = id;
    }

    public int get_sessionId() {
        return _sessionId;
    }

//    public void setCustomerLicensePlates(List<String> plates) {
//        //this.customer.setPlateList(plates);
//    }

  //  public void writeCustomerInCache(Customer c) {
       // writeObjectInFile(c, InternalProtocol.CACHE_CUSTOMER);
   // }
//
//    public void writeObjectInFile(Object o, String filename) {
//
//        try {
//            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
//            ObjectOutputStream out = new ObjectOutputStream(fos);
//            out.writeObject(o);
//
//            out.close();
//            fos.close();
//
//            Log.d(filename, filename + " - Object successfully written");
//        } catch (IOException e) {
//            Log.d(filename, filename + " - WRITE ERROR");
//        }
//
//    }
//
//    public Object readObjectInFile(String filename) {
//
//        Object o = null;
//        try {
//            FileInputStream fis = openFileInput(filename);
//            ObjectInputStream in = new ObjectInputStream(fis);
//            o = (Object) in.readObject();
//
//            in.close();
//            fis.close();
//
//            Log.d(filename, filename + " - Object successfully read");
//
//        } catch (IOException | ClassNotFoundException e) {
//            Log.d(filename, filename + " - read failed: not in cache");
//        }
//
//        return o;
//    }
//
//    public void destroyFileInCache(Context context, String filename) {
//        try {
//            context.deleteFile(filename);
//            Log.d("DESTROY_CACHE_FILE", filename + " was deleted");
//        } catch (Exception e) {
//            // e.printStackTrace();
//            Log.d("DESTROY_CACHE_FILE", filename + " does not exist");
//        }
//    }

    public void set_Customer(Customer customer) {
        _customer = customer;
    }

    public Customer get_Customer() { return _customer; }
}
