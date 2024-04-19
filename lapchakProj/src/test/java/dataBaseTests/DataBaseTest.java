package dataBaseTests;

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
       logger.info("MySQL database is connected");
    }

    @After
    public void tearDownMySqlDataBase() throws SQLException {
        mySqlDataBase.quit();
        logger.info("MySQL database was closed");
    }

@Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G9_liubomyr_01";

    ArrayList<Map<String, String>> dataFromSeleniumTable = mySqlDataBase.selectTableAsMap("select * FROM seleniumTable Where id = 665");
    logger.info(dataFromSeleniumTable);
    logger.info(dataFromSeleniumTable.size());

    dataFromSeleniumTable = mySqlDataBase.selectTableAsMap(String.format("Select * FROM seleniumTable Where login = '%s'", LOGIN));
    logger.info("Data from seleniumTable where login = " + LOGIN + " is " + dataFromSeleniumTable.size());

    int numberOfInsertedRows = mySqlDataBase.changeTable("INSERT INTO seleniumTable VALUES (658, '%s', '%s')", LOGIN, "1234");
    logger.info(numberOfInsertedRows + " Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());

    dataFromSeleniumTable = mySqlDataBase.selectTableAsMap(String.format("Select * FROM seleniumTable Where login = '%s'", LOGIN));
    logger.info("Data from seleniumTable where login = " + LOGIN + " is " + dataFromSeleniumTable.size());

    int numberOfDeletedRows = mySqlDataBase.changeTable("DELETE FROM seleniumTable WHERE login = '"+ LOGIN + "'");
    logger.info(numberOfDeletedRows + " rows were deleted ");


    logger.info("--------");
    DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
    logger.info(dbUtilSeleniumTable.getPassForLogin("G9_liubomyr_01"));
}



}
