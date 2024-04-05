package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

// описує елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    // myProfile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;



    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);

    }
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

}
