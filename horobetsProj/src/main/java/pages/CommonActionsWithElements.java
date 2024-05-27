package pages;

import libs.ConfigProvider;
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
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує всі  елементи описані @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void cleanAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + " Element is displayed");
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
                logger.info(elementName + " Element is displayed");
            } else {
                logger.info(elementName + " Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }


    // select text in dropdown by visible text
    protected void selectTextInDropdownByVisibleText(WebElement dropdown, String text) {
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

    protected void selectCheckbox(WebElement checkboxElement) {
        try {
            if (!checkboxElement.isSelected()) {
                checkboxElement.click();
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    protected void unselectCheckbox(WebElement checkboxElement) {
        try {
            if (checkboxElement.isSelected()) {
                checkboxElement.click();
                logger.info("Checkbox was unselected");
            } else {
                logger.info("Checkbox is already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxState(WebElement checkboxElement, String state) {
        if (state.equals("check")) {
            selectCheckbox(checkboxElement);
            // logger.info("Checkbox was selected");
        } else if (state.equals("uncheck")) {
            unselectCheckbox(checkboxElement);
            // logger.info("Checkbox was unselected");
        } else {
            logger.info("Сheckbox state is not selected.");
        }
    }

    protected void checkElementsDisplayed(WebElement webElement, String elementName) {
        Assert.assertTrue(elementName + " is not displayed", isElementDisplayed(webElement, elementName));
    }


    protected void checkElementsNotDisplayed(WebElement webElement, String elementName) {
        Assert.assertFalse(elementName + " is displayed", isElementDisplayed(webElement, elementName));
    }

    protected void checkTextInElement(WebElement webElement, String expectedText){
//        try {
            Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
            String textFromElement = webElement.getText();
            Assert.assertEquals("Text in element not matched"
                    , expectedText, textFromElement);
//        }catch (Exception e){
//            printErrorAndStopTest(e);
//        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    public void openNewTab() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
            switchToNewTab();
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void switchToNewTab() {
        try {
            for (String windowHandle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(windowHandle);
            }
            logger.info("Switched to new tab");
        } catch (Exception e) {
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

    public void closeNewTabAndSwitchToMainTab() {
        try {
            webDriver.close();
            logger.info("Close new tab and switch to main tab");
            switchToMainTab();
        } catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }
}
