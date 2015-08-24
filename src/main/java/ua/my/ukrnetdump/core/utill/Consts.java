package ua.my.ukrnetdump.core.utill;

/**
 * Created by pavel-pc on 17.07.15.
 */
public class Consts {

    final private String USERAGENT = "Opera/9.80 (J2ME/MIDP; Opera Mini/9.80 (J2ME/23.377; U; en) Presto/2.5.25 Version/10.54";
    final private String UKRNET_URL = "http://ukr.net";
    final private String FILE_LOG_PARSER = "core/logs/parse.log";
    final private String FILE_LOG_GENERAL = "core/logs/general.log";
    final private int TIMEOUT;
    final private String PATH_DB;
    final private int TIMEUPDATE;


    public Consts(String PATH_DB) {
        this.PATH_DB = PATH_DB;
        TIMEOUT = 5000;
        TIMEUPDATE = 1800;
    }

    public Consts(int TIMEOUT, String PATH_DB,int TIMEUPDATE) {
        this.TIMEOUT = TIMEOUT;
        this.PATH_DB = PATH_DB;
        this.TIMEUPDATE = TIMEUPDATE;
    }
    
    public String getFILE_LOG_PARSER() {
        return FILE_LOG_PARSER;
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

    public String getFILE_LOG_GENERAL() {
        return FILE_LOG_GENERAL;
    }

    public int getTIMEUPDATE() {
        return TIMEUPDATE;
    }
}
