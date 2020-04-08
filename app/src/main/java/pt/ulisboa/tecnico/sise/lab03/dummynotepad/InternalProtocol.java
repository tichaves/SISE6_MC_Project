package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

public class InternalProtocol {
    public static final String LOG        = "InSureApp";
    public static final String SESSION_ID = "SESSION_ID";

    // file cache names
   // public final static String CACHE_CUSTOMER = "inSureCacheCustomer";
  //  public final static String AUTO_INSURE = "AutoInSure";


    // relevant for the login activity
    public static final String KEY_NEW_CLAIM_TITLE = "CLAIM_TITLE";
    public static final String KEY_NEW_CLAIM_PLATE = "CLAIM_PLATE";
    public static final String KEY_NEW_CLAIM_DATE  = "CLAIM_DATE";
    public static final String KEY_NEW_CLAIM_DESCRIPTION  = "CLAIM_DESC";

    //relevant for privacy activity request
    public static final int MENU_REQUEST =4;


    // relevant for the read note activity
    public static final String READ_CLAIM_INDEX   = "CLAIM_INDEX";

    public static final int NEW_CLAIM_REQUEST         = 1;
    public static final int CLAIM_INFORMATION_REQUEST = 3;
    public static final int SETTINGS_REQUEST          = 2;
}
