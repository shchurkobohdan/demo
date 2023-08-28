package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends Page {
    private SelenideElement username = $(By.id("user-name"));
    private SelenideElement password = $(By.id("password"));

    public LoginPage() {
        open("/");
    }

    @Step("Login with username and password")
    public ProductsPage login(String username, String password) {
        this.username.val(username);
        this.password.val(password);
        $(By.id("login-button")).click();

        return new ProductsPage();
    }

    @Step("Get login form error message")
    public SelenideElement getError() {
        return $(By.cssSelector("div[class*=error]"));
    }
}
