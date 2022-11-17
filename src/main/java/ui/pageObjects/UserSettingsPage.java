package ui.pageObjects;

import configurations.Configurations;
import helpers.AuthenticationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class UserSettingsPage {

    private Logger logger = LogManager.getLogger(UserSettingsPage.class);
    private final Wait<WebDriver> wait;

    private WebDriver driver;

    private final String pageUrl = Configurations.APP_BASE_URL + "/create";

    @FindBy(xpath="//*[@id=\"header-desktop\"]/div/div[3]/div[2]/span/span/span/picture/img")
    private WebElement userAvatar;

    @FindBy(linkText = "Settings")
    private WebElement settings;

    @FindBy(id = "upload-avatar")
    private WebElement uploadAvatarBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div/form/div[1]/div[2]/p")
    private  WebElement descriptionLabel;

    @FindBy(xpath = "//button[@data-test=\"save-changes-button\"]")
    private  WebElement saveChangesBtn;

    public UserSettingsPage(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(500))
                .pollingEvery(Duration.ofSeconds(100));

        PageFactory.initElements(this.driver, this);
    }

    public void openPage(){
        this.driver.manage().addCookie(new Cookie("user_key", AuthenticationHelper.getKey()));
        this.driver.get(pageUrl);
        logger.info("The page is opening");
    }

    public void hoverOnTheUserAvatar() {
        Actions actions = new Actions(driver);
        actions.moveToElement(userAvatar).perform();
        logger.info("The cursor is on the user avatar");
    }

    public void selectSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settings));
        settings.click();
        logger.info("Settings page is opened");
    }
    public void isDescriptionLabelDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(descriptionLabel));
        descriptionLabel.isDisplayed();
        logger.info("Message is displayed under the button");
    }

    public void uploadImage(String path) {
        uploadAvatarBtn.sendKeys(path);
        logger.info("Image is uploaded");
    }

    public void clickSaveBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesBtn));
        saveChangesBtn.submit();
        logger.info("The change is submitted");
    }
}
