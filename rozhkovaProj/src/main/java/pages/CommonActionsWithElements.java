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
        PageFactory.initElements(webDriver,this); //будуть проініціалізовані всі елементи описані в @FindBy
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void cleanAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element ");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed (WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            if (state){
                logger.info("Element is displayed");
            }else{
                logger.info("Element is not displayed"); //елемент є на сторінці, але не показаний візуально
            }
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed"); //елементу немає на сторінці
            return false;
        }
    }

    // select text in dropdown by visible text
    protected void selectTextInDropdownByVisibleText(WebElement dropDown, String text){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropdown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectCheckbox(WebElement checkbox){
        try{
            if (!checkbox.isSelected()){
                checkbox.click();
                logger.info("Checkbox was selected");
            }else{
                logger.info("Checkbox was already selected");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void unselectCheckbox(WebElement checkbox){
        try{
            if (checkbox.isSelected()){
                checkbox.click();
                logger.info("Checkbox was unselected");
            }else{
                logger.info("Checkbox was already unselected");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxState(WebElement checkbox, String neededState) {
        if (neededState.equals("Check")) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox was already selected");
            }
        } else if (neededState.equals("Uncheck")) {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was unselected");
            } else {
                logger.info("Checkbox was already unselected");
            }
        }
    }

            private void printErrorAndStopTest (Exception e){
                logger.error("Can not work with element " + e); //іде в консоль і в логфайл
                Assert.fail("Can not work with element " + e); // іде в репорт
            }
        }
