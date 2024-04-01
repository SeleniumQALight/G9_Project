package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

// описуємо елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[contains(text(),'Create Post')]")
    private WebElement buttonCreatePost;

    // myProfile button
    @FindBy(xpath = ".//a[@class='mr-2']")
    private WebElement myProfileButton;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut, "Button Sign Out");
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

}
