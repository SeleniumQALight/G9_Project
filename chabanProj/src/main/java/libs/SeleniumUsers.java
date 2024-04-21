package libs;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class SeleniumUsers {
    private Database seleniumUsers;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        seleniumUsers = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = seleniumUsers.selectValue(
                String.format("select passWord from seleniumUsers where login = '%s'", login)
        );
        seleniumUsers.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }
    @Before
    public void setUp() throws Exception {
        seleniumUsers = MySQL_Database.getDataBase();
        logger.info("Selenium Users was set up");
    }

    @After
    public void tearDown() throws Exception {
        seleniumUsers.quit();
        logger.info("MySqlDataBase was closed");
    }

    @Test
    public void gotPass() throws Exception, ClassNotFoundException{
        final String login = "newqaauto";

        SeleniumUsers seleniumUsersTable = new SeleniumUsers();
        logger.info(seleniumUsersTable.getPassForLogin(login));


    }

}

