package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait_10, webDriverWait_15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує всі елементи описані @FindBy
        webDriverWait_10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait_15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void cleanAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state){
                logger.info(getElementName(webElement) + " Element is displayed");
            } else{
                logger.info(getElementName(webElement) + " Element is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }
    protected boolean isElementDisplayed(WebElement webElement, String elementName){
        try{
            boolean state = webElement.isDisplayed();
            if (state){
                logger.info(elementName + " Element is displayed");
            } else{
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

// select text in dropdown by visible text
    protected void selectTextInDropdownByVisibleText(WebElement dropdown, String text){
        try{
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown " + getElementName(dropdown));
        }catch (Exception e){
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


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement){
        try {
        return webElement.getAccessibleName();
        } catch (Exception e){
            return "";
        }
    }
}
