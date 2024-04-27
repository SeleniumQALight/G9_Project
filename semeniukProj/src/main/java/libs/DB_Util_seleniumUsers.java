package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util_seleniumUsers {
    private Database seleniumUsers;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        seleniumUsers = MySQL_Database.getDataBase();
        logger.info("-*-*-*- Connected to DB -*-*-*-");

        String pass = seleniumUsers.selectValue(
                String.format("select passWord from seleniumUsers where login = '%s'", login)
        );
        seleniumUsers.quit();
        logger.info("-*-*-*- Disconnected from DB -*-*-*-");
        return pass;
    }

}
