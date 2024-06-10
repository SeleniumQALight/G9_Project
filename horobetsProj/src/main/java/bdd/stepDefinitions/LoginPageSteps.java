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
    public void i_open_login_page() {
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
    public void i_enter_into_input_password_in_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }
    @When("I click on button SignIn In Login page")
    public void i_click_on_button_sign_in_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.getLoginPage().checkTextInAlertMessageInCenter(message);

    }

    @When("I enter {string} into input Username in Registration form on Login page")
    public void iEnterIntoInputUsernameInRegistrationFormOnLoginPage(String userName) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName);
    }

    @And("I enter {string} into input Email in Registration form on Login page")
    public void iEnterIntoInputEmailInRegistrationFormOnLoginPage(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input Password in Registration form on Login page")
    public void iEnterIntoInputPasswordInRegistrationFormOnLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @And("I click on button SignUp in Registration form on Login page")
    public void iClickOnButtonSignUpInRegistrationFormOnLoginPage() {
        pageProvider.getLoginPage().clickOnSignUpButton();
    }

    @Then("I see error messages {string}")
    public void iSeeErrorMessages(String errorMessage) {
        pageProvider.getLoginPage().checkErrorsMessages(errorMessage);
    }
}
