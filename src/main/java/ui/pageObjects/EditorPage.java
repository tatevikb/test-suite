package ui.pageObjects;

import configurations.Configurations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ui.DriverFactory;

import java.time.Duration;

public class EditorPage extends DriverFactory {

    private WebDriver driver;
    private final Wait<WebDriver> wait;
    private final String pageUrl = Configurations.APP_BASE_URL + "/create/editor";

    @FindBy(xpath = "//button[@data-testid=\"layout-category\"]")
    private  WebElement layoutBtn;

    public EditorPage(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait<WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(500))
                .pollingEvery(Duration.ofSeconds(100));
        PageFactory.initElements(this.driver, this);
    }

    public void checkEditorLoaded(){
        wait.until(ExpectedConditions.urlContains("?category=layout&customSize=1080x1080&unit=px"));
        layoutBtn.isSelected();
    }

}
