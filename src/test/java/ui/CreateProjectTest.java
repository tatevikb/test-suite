package ui;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageObjects.EditorPage;
import ui.pageObjects.NewProjectPage;
import ui.utils.RetryAnalyzer;


public class CreateProjectTest {

    private NewProjectPage newProjectPage;
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void init() {
        driverThreadLocal.set(new DriverFactory().getDriver());
        newProjectPage = new NewProjectPage(driverThreadLocal.get());
        newProjectPage.openPage();
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createNewProjectTest () {
        newProjectPage.checkNewProjectLoaded();
        EditorPage editorPage = newProjectPage.clickNewProjectBtn();
        editorPage.checkEditorLoaded();
    }

    @AfterMethod
    public void cleanup() {
        driverThreadLocal.get().quit();
    }
}
