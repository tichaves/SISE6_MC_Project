package pt.ulisboa.tecnico.sise.lab03.dummynotepad;

public class InternalProtocol {
    public static final String LOG                = "InSureApp";

    // relevant for the login activity
    public static final int    NEW_NOTE_REQUEST   = 1;
    public static final String KEY_NEW_NOTE_TITLE = "NOTE_TITLE";
    public static final String KEY_NEW_NOTE_BODY  = "NOTE_BODY";

    public static final int    NEW_CLAIM_REQUEST   = 1;
    public static final String KEY_NEW_CLAIM_TITLE = "NOTE_TITLE";
    public static final String KEY_NEW_CLAIM_PLATE = "NOTE_PLATE";
    public static final String KEY_NEW_CLAIM_DATE  = "NOTE_DATE";
    public static final String KEY_NEW_CLAIM_DESCRIPTION  = "NOTE_DESC";

    public static final String KEY_USER = "NOTE_TITLE";

    // relevant for the read note activity
    public static final String READ_NOTE_INDEX    = "NOTE_INDEX";
}
