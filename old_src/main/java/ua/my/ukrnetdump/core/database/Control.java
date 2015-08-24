package ua.my.ukrnetdump.core.database;

import ua.my.ukrnetdump.core.model.DataModel;

import java.util.List;

/**
 * Created by pavel-pc on 16.07.15.
 */
public interface Control {

    public int add(List<DataModel> listNews);
    public void removeAll();
}
