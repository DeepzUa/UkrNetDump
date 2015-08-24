import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.my.ukrnetdump.core.utill.Config;
import ua.my.ukrnetdump.core.utill.ConfigException;
import ua.my.ukrnetdump.core.utill.Consts;


/**
 * Created by pavel-pc on 17.07.15.
 */
public class ConfigTest {


    @Test
    public void test_config_value(){


        try {
            Consts consts =  new Config().getConsts();
        }catch (ConfigException e) {
           e.printStackTrace();
        }
    }
}
