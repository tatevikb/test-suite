package ui;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageObjects.UserSettingsPage;
import ui.utils.RetryAnalyzer;

public class UploadImageTest {
    private UserSettingsPage userSettingsPage;
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void init() {
        driverThreadLocal.set(new DriverFactory().getDriver());
        userSettingsPage = new UserSettingsPage(driverThreadLocal.get());
        userSettingsPage.openPage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void uploadImageTest () {
        userSettingsPage.hoverOnTheUserAvatar();
        userSettingsPage.selectSettings();
        userSettingsPage.isDescriptionLabelDisplayed();
        userSettingsPage.uploadImage(getClass().getClassLoader().getResource("pisik.png").getPath());
        userSettingsPage.clickSaveBtn();
    }

    @AfterMethod
    public void cleanup(){
        driverThreadLocal.get().quit();
    }
}
