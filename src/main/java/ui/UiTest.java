package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import ui.listeners.ScreenshotTestListener;


@Listeners({ScreenshotTestListener.class})
public abstract class UiTest {

    @BeforeSuite(description = "Configure Selenide before running tests")
    @Parameters("browser")
    public void setupSelenide(@Optional("firefox") String browser) {
        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null) {
            System.out.println(remoteUrl);
            Configuration.remote = remoteUrl;
        }
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.browser = browser;
        Configuration.reportsFolder = "target/test-results/reports";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
