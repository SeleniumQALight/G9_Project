package loginTests;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    final String USER_NAME = "qaauto";
    @Test
    public  void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displyed" ,
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());

        Assert.assertTrue("Button Create Post is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostDisplayed());
        Assert.assertTrue("Button My Profile is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonMyProfileDisplayed());
        Assert.assertTrue("User name is not displayed", pageProvider.getHomePage().getHeaderElement().checkIsUsernameIsPresent(USER_NAME));
        Assert.assertFalse("Input UserName is displayed", pageProvider.getLoginPage().checkIsInputUserNameDisplayed());
        Assert.assertFalse("Input Password is displayed", pageProvider.getLoginPage().checkIsInputPasswordDisplayed());


    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("NotValidLogin");
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();


        Assert.assertTrue("Button Sign In  in is not visible", pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertFalse("Button Sign Out Sign out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Massage 'Invalid username/password' is not visible", pageProvider.getLoginPage().isInvalidUsernamePasswordDisplayed());
    }




}


//1. додати перевірки в тест на валідний логін:
//- що після того як залогінилися, ми бачимо кнопки Create Post, MyProfile, імʼя юзера
//- і не бачимо інпутів куди ми вводили логін та пароль