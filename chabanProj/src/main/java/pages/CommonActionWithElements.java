package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionWithElements {
   protected WebDriver webDriver;
   protected Logger logger = Logger.getLogger(getClass());
   protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує всі елементи описані в @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }
    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);

        }
    }

    protected void cleanAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        }catch (Exception e) {
          printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed ");
            } else {
                logger.info(getElementName(webElement) + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }


    protected boolean isElementDisplayed(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(elementName + " Element is displayed ");
            } else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }

    }

    public void checkElementIsDisplayed(WebElement webElement){
            Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        }

    public void checkElementIsNotDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is displayed", !isElementDisplayed(webElement));
    }


    // select text in dropdown by visible text
    protected void selectValueInDropdownByVisibleText(WebElement dropdown, String text) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void selectValueInDropdown(WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown " + getElementName(dropdown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //press Enter key using Action class

    public void pressEnterKey(){
        try{
            Actions actions = new Actions(webDriver);
            actions.sendKeys(org.openqa.selenium.Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected void checkTextInElement(WebElement webElement, String ExpectedText){
//        try {
            Assert.assertTrue("Element is not displayed",isElementDisplayed(webElement));
            String  textFromElement = webElement.getText();
            Assert.assertEquals("Text in element not matched", ExpectedText, textFromElement);
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }
    public void openNewTab(){
        try{
            ((org.openqa.selenium.JavascriptExecutor)webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
            switchToNewTab();
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void switchToNewTab(){
        try{
            for(String windowHandle : webDriver.getWindowHandles()){
                webDriver.switchTo().window(windowHandle);
            }
            logger.info("Switched to new tab");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void switchToMainTab() {
        try {
            webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[0]);
            logger.info("Switched to main tab");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    public void closeCurrentTabAndSwitchToMain(){
        try{
            webDriver.close();
            logger.info("Current tab was closed");
            switchToMainTab();
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

}
