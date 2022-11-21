package ui.pageObjects;

import configurations.Configurations;
import helpers.AuthenticationHelper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ui.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.Duration;
import java.util.ArrayList;

public class NewProjectPage extends DriverFactory {

    private Logger logger = LogManager.getLogger(NewProjectPage.class);
    private Wait<WebDriver> wait;
    private WebDriver driver;
    private final String pageUrl = Configurations.APP_BASE_URL + "/create";

    @FindBy(xpath = "//button[@data-testid=\"create-search-button\"]")
    private  WebElement newProjectBtn;

    @FindBy(xpath = "//button[@data-testid=\"create-upload-button\"]")
    private  WebElement uploadBtn;

    @FindBy(xpath = "//div[@data-testid=\"create-wrapper\"]")
    private  WebElement createWrapper;


    public NewProjectPage(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait<WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(250));

        PageFactory.initElements(this.driver, this);
    }

    public void openPage(){
        this.driver.manage().addCookie(new Cookie("user_key", AuthenticationHelper.getKey()));
        this.driver.get(pageUrl);
        logger.info("User is opening main page");
    }

    public EditorPage clickNewProjectBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(newProjectBtn));
        newProjectBtn.click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        logger.info("The system switched to Editor");
        return new EditorPage(driver);
    }

    public void checkNewProjectLoaded(){
        wait.until(ExpectedConditions.elementToBeClickable(newProjectBtn));
        wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));
        wait.until(ExpectedConditions.elementToBeClickable(createWrapper));
    }
}
