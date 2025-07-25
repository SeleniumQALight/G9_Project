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

    private Database mySqlDataBase;

    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySqlDataBase() throws SQLException, ClassNotFoundException {
        mySqlDataBase = MySQL_Database.getDataBase();
        logger.info("MySQL database was set up");

    }

    @After
    public void tearDownMySqlDataBase() throws SQLException {
        mySqlDataBase.quit();
        logger.info("MySQL database was closed");

    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException{
        final String LOGIN = "G9_ira_h";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap("SELECT * FROM seleniumTable where id=665");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap(
                        String.format("SELECT * FROM seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of records for login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertedRows =
                mySqlDataBase.changeTable("INSERT INTO seleniumTable VALUES('600', '%s', '%s')", LOGIN, "12356");
        logger.info(numberOfInsertedRows + "rows were inserted");

        dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap(String.format("SELECT * FROM seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of records with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfDeletedRows =
                mySqlDataBase.changeTable("Delete FROM seleniumTable where login = '"+LOGIN+"'");
        logger.info(numberOfDeletedRows + " rows were deleted");


        logger.info("------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G8_taras_r"));
    }



}
