package dataBaseTests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.libs.DB_Util_seleniumTable;
import pages.libs.Database;
import pages.libs.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class DataBaseTest {
    private Database mySqlDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySqlDataBase() throws SQLException, ClassNotFoundException {
        mySqlDataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");
    }

    @After
    public void tearDownMySqlDataBase() throws SQLException {
        mySqlDataBase.quit();
        logger.info("--- Disconnected from DB -------");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G9_mariia_b";
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySqlDataBase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id=665");
        logger.info("Data from seleniumTable: " + dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable = mySqlDataBase.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login= '%s'", LOGIN));
        logger.info("Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertedRows = mySqlDataBase.changeTable("INSERT INTO seleniumTable VALUES('800', '%s', '%s')", LOGIN, "qwerty");
        logger.info(numberOfInsertedRows + " rows were inserted into seleniumTable");

        dataFromSeleniumTable = mySqlDataBase.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login= '%s'", LOGIN));
        logger.info("Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySqlDataBase.changeTable("DELETE FROM seleniumTable WHERE login = '" + LOGIN + "'");
        logger.info(numberOfDeletedRows + " rows were deleted from seleniumTable");

        logger.info("---------------------------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G8_taras_r"));
    }

}
