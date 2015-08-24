package ua.my.ukrnetdump.core.utill;

/**
 * Created by pavel-pc on 24.07.15.
 */
public class DataBaseException extends RuntimeException {

    String message;
    public DataBaseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
         return new String ("DataBaseException["+message+"]");
    }
}