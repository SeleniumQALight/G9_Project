package registrationTests;

import baseTests.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class) //яка вказує, що даний клас ми хочемо запускати з параметрами

@Category(SmokeTestFilter.class) //тільки тести без параметрів. Якщо треба з параметрами, то треба категорію навісити на клас

public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTest") //вказуємо метод (щоб повязати метод і тест), який буде відповідати за параметризацію
    public void TC_001_validationMessagesTest_validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
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
                {"taras11", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

        };
    }


}


