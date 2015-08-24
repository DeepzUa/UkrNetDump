import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.my.ukrnetdump.core.Parse;
import ua.my.ukrnetdump.core.net.UkrNetConnection;
import ua.my.ukrnetdump.core.utill.Consts;

/**
 * Created by pavel-pc on 17.07.15.
 */
public class URKNETConnectTest {

    private Consts consts;
    @Before
    public void init(){
        consts = new Consts(5000,"newe.db");
    }
    @Test
    public void test_connect(){
        UkrNetConnection connection = new UkrNetConnection(consts);
        new Parse(connection.getHtml());
    }


}