package ua.my.ukrnetdump.core.utill;

/**
 * Created by pavel-pc on 18.07.15.
 */
public class ConfigException extends RuntimeException {

    private String message;

    public ConfigException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String toString() {
        return new String ("ConfigException["+message+"]");
    }
}
