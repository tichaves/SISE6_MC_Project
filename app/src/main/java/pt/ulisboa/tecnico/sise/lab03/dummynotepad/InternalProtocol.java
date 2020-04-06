package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

public class InternalProtocol {
    public static final String LOG                = "InSureApp";
    public static int SESSIONID;

    // relevant for the login activity
    public static final int    NEW_NOTE_REQUEST   = 4;
    public static final String KEY_NEW_NOTE_TITLE = "NOTE_TITLE";
    public static final String KEY_NEW_NOTE_BODY  = "NOTE_BODY";

    public static final int    NEW_CLAIM_REQUEST   = 1;
    public static final String KEY_NEW_CLAIM_TITLE = "CLAIM_TITLE";
    public static final String KEY_NEW_CLAIM_PLATE = "CLAIM_PLATE";
    public static final String KEY_NEW_CLAIM_DATE  = "CLAIM_DATE";
    public static final String KEY_NEW_CLAIM_DESCRIPTION  = "CLAIM_DESC";

    public static String KEY_USER     = "USER_APP";
    public static String KEY_PASSWORD = "USER_PASSWORD";

    // relevant for the read note activity
    public static final String READ_NOTE_INDEX    = "NOTE_INDEX";
    public static final String READ_CLAIM_INDEX   = "CLAIM_INDEX";

    public static final int CLAIM_INFORMATION_REQUEST   = 3;
}
