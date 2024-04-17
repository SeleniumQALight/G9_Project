package databaseTests;

import libs.DB_Util_seleniumTable;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {
    private Database mySQL_DataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySQL_DataBase() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("MySQL_DataBase was set up");

    }

    @After
    public void tearDownMySQL_DataBase() throws SQLException {
        mySQL_DataBase.quit();
        logger.info("MySQL_DataBase was closed");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G9_Karhina_Olena";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable where id = 665");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap(
                String.format("SELECT * FROM seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of records with login " + LOGIN + " is " +  dataFromSeleniumTable.size());
        int numberOfInsertRows = mySQL_DataBase.changeTable(
                "INSERT INTO seleniumTable VALUES ('1211', '%s', '%s')", LOGIN, "12334");
        logger.info("Number of inserted rows is " + numberOfInsertRows);

        dataFromSeleniumTable =
                mySQL_DataBase.selectTableAsMap(
                        String.format("SELECT * FROM seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of records with login " + LOGIN + " is " +  dataFromSeleniumTable);

        int numberOfDeleteRows = mySQL_DataBase.changeTable(
                "Delete FROM seleniumTable where login = '" + LOGIN + "'");
        logger.info("Number of deleted rows is " + numberOfDeleteRows);


        logger.info("-----------------");
        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info(db_util_seleniumTable.getPassForLogin("G8_taras_r"));
    }
}
