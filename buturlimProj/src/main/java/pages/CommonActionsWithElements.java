package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує всі елементи описані @FindBy
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void cleanAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    // Select text in dropdown by visible text
    protected void selectTextInDropdownByVisibleText(WebElement dropdown, String textForSelect) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckbox(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox is selected");
            } else {
                logger.info("Checkbox was already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void unsetCheckbox(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox is unselected");
            } else {
                logger.info("Checkbox was already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxState(WebElement checkbox, String check) {
        try {
            if (check.equals("checked")) {
                setCheckbox(checkbox);
            } else if (check.equals("unchecked")) {
                unsetCheckbox(checkbox);
            } else {
                logger.info("Wrong parameter for checkbox");
            }
        } catch (Exception e) {
            logger.info("Can not work with checkbox");
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
