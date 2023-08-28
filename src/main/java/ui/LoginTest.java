package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.LoginPage;
import ui.pages.ProductsPage;

import static com.codeborne.selenide.Condition.*;

@Feature("Login to application: this is feature desc")
public class LoginTest extends UiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isLoaded());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertFalse(productsPage.isLoaded());
        loginPage.getError().shouldHave(text("Epic sadface: Sorry, this user has been locked out."));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void specificallyFailedTest() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(productsPage.isLoaded());
    }
}
