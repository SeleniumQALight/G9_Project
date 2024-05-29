package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps{
    public LoginPageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void i_open_login_page(){
        pageProvider.getLoginPage().openLoginPage();

    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @When("I click on button SignIn  in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.getLoginPage().checkTextInAlertMessageInCenter(message);
    }



    @When("I enter {string} into input Username in Registration form")
    public void i_enter_into_input_username_in_registration_form(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }

    @When("I enter {string} into input Email in Registration form")
    public void i_enter_into_input_email_in_registration_form(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @When("I enter {string} into input Password in Registration form")
    public void i_enter_into_input_password_in_registration_form(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @When("I click on button SignUp in Registration form")
    public void i_click_on_button_sign_up_in_registration_form() {
        pageProvider.getLoginPage().clickOnSignUpButton();
    }

    @Then("I see the following {string}")
    public void i_see_the_following(String errorMessage) {
        pageProvider.getLoginPage().checkErrorsMessages(errorMessage);
    }
}
