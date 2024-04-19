package databaseTest;

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
    private Database mySqlDatabase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySqlDatabase() throws SQLException, ClassNotFoundException {
        mySqlDatabase = MySQL_Database.getDataBase();
        logger.info("MySQL database was set up");
    }

    @After
    public void tearDownMySQLDataBase() throws SQLException {
        mySqlDatabase.quit();
        logger.info("MySQL database was closed");
    }
    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G9_roman_r";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySqlDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE id=665");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable = mySqlDatabase.selectTableAsMap(
                String.format("SELECT * FROM seleniumTable WHERE login='%s'", LOGIN));
        logger.info("Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertRows =
                mySqlDatabase.changeTable("INSERT INTO seleniumTable VALUES ('666', '%s', '%s')", LOGIN, "11223");
        logger.info(numberOfInsertRows + " rows were inserted");

        dataFromSeleniumTable = mySqlDatabase.selectTableAsMap(
                String.format("SELECT * FROM seleniumTable WHERE login='%s'", LOGIN));
        logger.info("Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfDeleteRows =
                mySqlDatabase.changeTable("DELETE FROM seleniumTable WHERE login='"+LOGIN+"'");
        logger.info(numberOfDeleteRows + " rows were deleted");

        logger.info("--------");
        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G8_taras_r"));

    }

}
