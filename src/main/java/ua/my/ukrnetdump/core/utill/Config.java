package ua.my.ukrnetdump.core.utill;

import java.io.*;
import java.util.Properties;

/**
 * Created by pavel-pc on 17.07.15.
 */
public class Config {

    final private Properties configFile;
    final private String configFileNamePath = "core/config.properties";
    private Consts consts;
    public Config() {
        configFile = new Properties();
        initFile();
    }


    private void initFile(){
        try (InputStream read = this.getClass().getClassLoader().getResourceAsStream(configFileNamePath)){
            configFile.load(read);
            String timeOut = configFile.getProperty("timeOut");
            String pathDb = configFile.getProperty("pathDatabase");
            String timeUpdate = configFile.getProperty("timeUpdate");
            chekProperty(timeOut,pathDb,timeUpdate);
            consts = new Consts(Integer.parseInt(timeOut),pathDb,Integer.parseInt(timeUpdate));
        } catch (IOException e) {
            throw new  ConfigException("Cannot read config file");
        } catch (NumberFormatException e){
            throw new  ConfigException("Error parse type");
        }

    }
    private void chekProperty(String ... parms)
    {
        for (int i=0;i<parms.length;++i) {
            if (parms[i]==null)
                throw new  ConfigException("Property not found");
        }
    }

    public Consts getConsts() {
        return consts;
    }
}