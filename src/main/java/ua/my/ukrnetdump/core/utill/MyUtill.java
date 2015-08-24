package ua.my.ukrnetdump.core.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pavel-pc on 16.07.15.
 */
public class MyUtill {

    public static String getDateNow(){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    public static void cleanStringBuilder(StringBuilder str){
        str.delete(0, str.length());
    }
}
