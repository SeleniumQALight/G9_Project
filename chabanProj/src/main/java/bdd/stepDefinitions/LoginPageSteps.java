package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import data.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps {

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
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @When("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }


    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.getLoginPage().checkTextInElement(message);
    }

    @When("I enter {string} into input userName on Login page at Registration area")
    public void iEnterUsernameIntoInputUserNameOnLoginPageAtRegistrationArea(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }

    @And("I enter {string} into input Email on Login page at Registration area")
    public void iEnterEmailIntoInputEmailOnLoginPageAtRegistrationArea(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input Password on Login page at Registration area")
    public void iEnterPasswordIntoInputPasswordOnLoginPageAtRegistrationArea(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @And("I click on button Sign Up on Login page at Registration area")
    public void iClickOnButtonSignUpOnLoginPageAtRegistrationArea() {
        pageProvider.getLoginPage().clickOnButtonSignUp();

    }

    @Then("I see message with text {string}")
    public void iSeeMessageWithTextValidation_text(String validation_text) {
        pageProvider.getLoginPage().checkErrorsMessagesText(validation_text);
    }
}
