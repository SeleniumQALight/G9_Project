package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps {
    public LoginPageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage() {
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
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

    @When("I click on Sign In button in Login page")
    public void i_click_on_sign_in_button_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.getLoginPage().checkTextInAlertMesage(message);
    }

    @When("I enter {string} into Username registration input")
    public void iEnterIntoUsernameRegistrationInput(String username) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUsernameField(username);
    }

    @And("I enter {string} into Email registration input")
    public void iEnterIntoEmailRegistrationInput(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into Password registration input")
    public void iEnterIntoPasswordRegistrationInput(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @And("I click on Sign Up button")
    public void iClickOnSignUpButton() {
        pageProvider.getLoginPage().clickOnButtonRegistration();
    }

    @Then("I see {string} message")
    public void iSeeMessage(String message) {
        pageProvider.getLoginPage().checkErrorsMessagesTexts(message);
    }
}
