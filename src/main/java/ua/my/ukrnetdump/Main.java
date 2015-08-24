package ua.my.ukrnetdump;


import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.*;
import ua.my.ukrnetdump.core.database.Control;
import ua.my.ukrnetdump.core.database.SqliteControl;
import ua.my.ukrnetdump.core.utill.Config;
import ua.my.ukrnetdump.core.utill.ConfigException;
import ua.my.ukrnetdump.core.utill.Consts;
import ua.my.ukrnetdump.core.Parse;
import ua.my.ukrnetdump.core.model.DataModel;
import ua.my.ukrnetdump.core.net.UkrNetConnection;
import ua.my.ukrnetdump.core.utill.DataBaseException;

public class Main {

    final public static long TIME = 10000L;
    final private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static boolean isRestart = false;
    public static void main(String[] args) {
        LOGGER.setLevel(Level.ALL);
        if (args.length==0 || args.length>1){
            System.out.println("Using : java -jar ukrnetdump.jar [remove|update|working]");
        }else {
           String mode = args[0];

            try {
                Handler logHandText = new FileHandler("core/logs/config.log",true);
                logHandText.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(logHandText);
                try {

                    Consts consts = new Config().getConsts();

                    LOGGER.removeHandler(logHandText);
                    logHandText.close();
                    switch (mode){
                        case "remove": remove(consts);break;
                        case "update":
                        case "working": work(consts,mode); break;
                        default: System.out.println("Using : java -jar ukrnetdump.jar [remove|update|working]");break;
                }


                }catch (ConfigException e) {
                    LOGGER.log(Level.ALL,null,e);
                }finally {
                    logHandText.close();
                }

            } catch (IOException e) {
                System.err.println("Handler config is NULL !");
            }
        }
    }
    private static void remove(Consts consts){
        try(Control database = new SqliteControl(consts)) {
            database.removeAll();
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, null, e);

        }
    }

    private static void work (Consts consts,String mode){
        boolean isWork = true;
        while (isWork){

            Handler logHandText = null;
            Handler logHandXml = null;
            try {
                logHandText = new FileHandler(consts.getFILE_LOG_GENERAL(),true);
                logHandXml = new FileHandler(consts.getFILE_LOG_GENERAL()+".xml");

                logHandText.setFormatter(new SimpleFormatter());
                logHandXml.setFormatter(new XMLFormatter());

                LOGGER.addHandler(logHandText);
                LOGGER.addHandler(logHandXml);

                try {
                    List<DataModel> arr = new Parse(new UkrNetConnection(consts).getHtml(),consts).getList();
                    try(Control database = new SqliteControl(consts)) {
                        database.add(arr);
                    }catch (SQLException | DataBaseException e){
                        LOGGER.log(Level.SEVERE, null, e);
                        break;
                    }
                	
		}catch (UncheckedIOException e){
                    LOGGER.log(Level.WARNING, "IO Error", e);
                }
                try {
                    logHandText.close();
                    logHandXml.close();
                }catch (NullPointerException e){
                    System.err.println("Handler is NULL Mess:"+ e.getMessage() );
                
		}
                if (mode.equals("update")){
            		isWork = false;
            		break;
		}
		Thread.sleep(consts.getTIMEUPDATE()*TIME);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE,"Fatal error",e);

                if (isRestart)
                    break;

                isRestart = true;
                work(consts,mode);

            
            }
        }
    }
}