package ua.my.ukrnetdump.core.utill;

/**
 * Created by pavel-pc on 17.07.15.
 */
public class Consts {

    final private String USERAGENT = "Opera/9.80 (J2ME/MIDP; Opera Mini/9.80 (J2ME/23.377; U; en) Presto/2.5.25 Version/10.54";
    final private String UKRNET_URL = "http://ukr.net";
    final private int TIMEOUT;
    final private String PATH_DB;

    public Consts(String PATH_DB) {
        this.PATH_DB = PATH_DB;
        TIMEOUT = 5000;
    }

    public Consts(int TIMEOUT, String PATH_DB) {
        this.TIMEOUT = TIMEOUT;
        this.PATH_DB = PATH_DB;
    }


    public String getPATH_DB() {
        return PATH_DB;
    }

    public int getTIMEOUT() {
        return TIMEOUT;
    }

    public String getUKRNET_URL() {
        return UKRNET_URL;
    }

    public String getUSERAGENT() {
        return USERAGENT;
    }

}
