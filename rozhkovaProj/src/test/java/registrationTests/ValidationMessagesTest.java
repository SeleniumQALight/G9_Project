package registrationTests;

import baseTests.BaseTest;
import categories.SmokeTestFilter;
import data.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static data.RegistrationValidationMessages.*; //* всі одразу заімпортували з RegistrationValidationMessages
import static data.User.*;
import static libs.StringUtils.deleteSomeSymbols;
import static data.RegistrationValidationMessages.*;
import static data.User.SHORT_PASSWORD_NOT_VALID;

@RunWith(JUnitParamsRunner.class) //яка вказує, що даний клас ми хочемо запускати з параметрами

@Category(SmokeTestFilter.class) //тільки тести без параметрів. Якщо треба з параметрами, то треба категорію навісити на клас

public class ValidationMessagesTest extends BaseTest {
    /*final String ERROR_USERNAME = "Username must be at least 3 characters."; //заімпортувати з RegistrationValidationMessages
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
*/

    @Test
    @Parameters(method = "parametersForValidationMessagesTest") //вказуємо метод (щоб повязати метод і тест), який буде відповідати за параметризацію
    public void TC023_validationMessagesTest_validationMessagesTest(String tcName, User user, String expectedMessages) {
        logger.info(tcName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(user.getUserName())
                .enterTextIntoRegistrationEmailField(user.getEmail())
                .enterTextIntoRegistrationPasswordField(user.getPassword())
                .checkErrorsMessages(expectedMessages);
    }

    @Test
    //Тест на перевірку еррор меседжів при регістраціі за допомогою кнопок (ТАБ і Ентер)
    //    Steps
    //     1. Open login page
    //     2. Tabom доклікати до поля User Name в Реєстраційній формі
    //     3. Ввести не валідне значення в поле User Name
    //     4. Tabom доклікати до поля Email в Реєстраційній формі
    //     5. Ввести не валідне значення в поле Email
    //     6. Tabom доклікати до поля Password в Реєстраційній формі
    //     7. Ввести не валідне значення в поле Password
    //     8. Натиснути кнопку Enter
    //     9. Перевірити що відобразилися три еррор меседжа

    public void TC_002_validationMessagesTest_validationMessagesTestUsingTabAndEnter() {//addition 2 to HW6
        pageProvider.getLoginPage().openLoginPage();
       pageProvider.getLoginPage().pressTabKey(5);
        pageProvider.getLoginPage().enterTextIntoField("ta");
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField("ta");
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField("ta");
        pageProvider.getLoginPage().pressEnterKey();
        pageProvider.getLoginPage().checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);
    }



//для того, щоб не чіпати тест, а параметри в окремому методі прописати (відокремити дані від тесту)
    public Object[][] parametersForValidationMessagesTest() {//метод, дії не відрізняються при заповненню полів, то можемо використати параметризацію. якщо поле не затрагуємо, то інший тесткейс
        return new Object[][]{
                {"Login - valid, Email - NOT valid, Password - NOT valid"//технічне інфо по тест данним, що буде виводитися в консолі, де назва виконуваного тесту
                        ,new User("TC023").updateEmail(User.SHORT_EMAIL_NOT_VALID).updatePassword(User.SHORT_PASSWORD_NOT_VALID) //ств юзернайм і проапдейтили невалідним мейлом і пароль
                        ,ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email - valid, password - NOT valid", new User("TC023").updatePassword(SHORT_PASSWORD_NOT_VALID)
                        , ERROR_PASSWORD}, //automation bug -- ERROR_EMAIL + SEMICOLON + --
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(deleteSomeSymbols(USER_NAME_MIN_LENGTH, 1), SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD }, // bug app ❤️
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email -  NOT valid, password - valid", new User(USER_NAME_MAX_LENGTH, SHORT_EMAIL_NOT_VALID, PASSWORD_MIN_LENGTH)
                        , ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password - valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH + "2".repeat(2))
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_LONG_PASSWORD}


               // {"taras11", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                //{"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

        };
    }


}


