package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage extends Page {

    @Step("Get a page secondary title")
    public String getTitle() {
        return $(By.cssSelector(".header_secondary_container .title")).text();
    }

    @Step("Verify if Product page loaded")
    public boolean isLoaded() {
        return $(By.id("shopping_cart_container")).isDisplayed();
    }

    @Step("Add product to cart")
    public void addProductToCart(String name) {
        var element = getProductElement(name);
        element.$(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    private SelenideElement getProductElement(String name) {
        return $$(By.cssSelector(".inventory_item")).stream()
                .filter(e -> e.find(By.cssSelector(".inventory_item_name")).text().equals(name))
                .findFirst()
                .orElseThrow();
    }

    @Step(value = "Get Cart items number")
    public int getCartItemsNumber() {
        try {
            return Integer.parseInt($(By.cssSelector(".shopping_cart_link .shopping_cart_badge"))
                    .text());
        } catch (ElementNotFound e) {
            return 0;
        }
    }

    @Step("Open burger menu and reset app state")
    public void resetAppState() {
        $("#react-burger-menu-btn").click();
        $("#reset_sidebar_link").shouldBe(Condition.exist, Condition.enabled).click();
        $("#react-burger-cross-btn").click();
    }
}
