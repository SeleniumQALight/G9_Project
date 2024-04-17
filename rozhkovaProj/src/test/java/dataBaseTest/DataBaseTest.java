package dataBaseTest;

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
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setMySQLDataBase() throws SQLException, ClassNotFoundException {//встановили з'єднання з базою даних
        mySQLDataBase = MySQL_Database.getDataBase();
        logger.info("MySQL Database was set up");

    }

    @After
    public void tearDownMySQLDataBase() throws SQLException {
        mySQLDataBase.quit();
        logger.info("MySQL Database was closed");

    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G9_tetiana_r";
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQLDataBase.selectTableAsMap("SELECT * FROM seleniumTable where id=665"); //можна конкретизувати запит тут
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap(String.format("SELECT * FROM seleniumTable where login='%s'", LOGIN));
        logger.info("Number of rows with login " + LOGIN + " is " + dataFromSeleniumTable.size());
        int numberOfInsertedRows = mySQLDataBase.changeTable("INSERT INTO seleniumTable VALUES (677, '%s', '%s')",LOGIN, "123456789");
        logger.info(numberOfInsertedRows + " rows were inserted");

        dataFromSeleniumTable = mySQLDataBase.selectTableAsMap(String.format("SELECT * FROM seleniumTable where login='%s'", LOGIN));
        logger.info("Number of rows with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQLDataBase.changeTable("DELETE FROM seleniumTable where login= '"+LOGIN+"'"); //можна конкретизувати запит тут
        logger.info(numberOfDeletedRows + " rows were deleted");

        logger.info("-----------------");
        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G8_taras_r"));

    }
}
