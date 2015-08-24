package ua.my.ukrnetdump.core.database;

import sun.font.StandardTextSource;
import ua.my.ukrnetdump.core.model.DataModel;
import ua.my.ukrnetdump.core.utill.Consts;
import ua.my.ukrnetdump.core.utill.DataBaseException;
import ua.my.ukrnetdump.core.utill.SQLConst;

import java.sql.*;
import java.util.List;

public class SqliteControl implements Control {

    final private Connection connection;
    public SqliteControl(Consts consts ) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+consts.getPATH_DB());
        } catch (ClassNotFoundException e) {
            throw new DataBaseException("Cannot load driver");
        }



    }
    @Override
    public void add(List<DataModel> listNews) {
        String sqlCommand = "INSERT OR IGNORE INTO "+ SQLConst.NEWS_TABLENAME + "(tagNews,titleNews,titleNewsUrl,dataNews,newsText) VALUES (?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            for (DataModel data : listNews){
                preparedStatement.setString(1,data.getTagNews());
                preparedStatement.setString(2,data.getTitleNews());
                preparedStatement.setString(3,data.getTitleNewsUrl());
                preparedStatement.setString(4,data.getDataNews());
                preparedStatement.setString(5,data.getNewsText());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw  new DataBaseException("Error add table");
        }
    }

    @Override
    public void removeAll() {
        String dropTable = "DROP TABLE IF EXISTS "+SQLConst.NEWS_TABLENAME;
        String sqlCreateTable ="CREATE TABLE "+SQLConst.NEWS_TABLENAME+" (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE, tagNews TEXT NOT NULL ,titleNews TEXT NOT NULL  UNIQUE ,titleNewsUrl TEXT NOT NULL  UNIQUE , dataNews DATETIME NOT NULL ,newsText TEXT NOT NULL )";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(dropTable);
            statement.executeUpdate(sqlCreateTable);
            System.out.println("Table  deleted in given database...");		
	} catch (SQLException e) {
            throw  new DataBaseException("Error drop table");
        }

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
