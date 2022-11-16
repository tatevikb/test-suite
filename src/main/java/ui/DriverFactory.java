package ui;

import configurations.Configurations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        this.driver.manage().window().maximize();
        driver.get(Configurations.APP_BASE_URL);
        return this.driver;
    }

    public void quitDriver(){
        driver.quit();
    }
}
