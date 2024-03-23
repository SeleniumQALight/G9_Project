package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e); //іде в консоль і в логфайл
        Assert.fail("Can not work with element " + e); // іде в репорт
    }
}
