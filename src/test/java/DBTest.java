import org.junit.Test;
import ua.my.ukrnetdump.core.database.Control;
import ua.my.ukrnetdump.core.database.SqliteControl;
import ua.my.ukrnetdump.core.model.DataModel;
import ua.my.ukrnetdump.core.utill.Config;
import ua.my.ukrnetdump.core.utill.Consts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel-pc on 24.07.15.
 */
public class DBTest {


    @Test
    public void test_db_add(){

        Consts consts = new Config().getConsts();
        List<DataModel> testList=  new ArrayList<>();
        testList.add(new DataModel("Головне","Test News","http://google.com","25.07.2015","Test Test Test"));
        testList.add(new DataModel("Головне","Test News","http://google1.com","25.07.2015","Test Test Test"));
        testList.add(new DataModel("Головне","Test News News","http://google1.com","25.07.2015","Test Test Test"));
        try {
            Control sql = new SqliteControl(consts);
            sql.add(testList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_db_removeAll(){
        Consts consts = new Config().getConsts();
        try {
            Control sql = new SqliteControl(consts);
            sql.removeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
