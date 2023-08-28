package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import ui.pages.LoginPage;
import ui.pages.ProductsPage;

import static org.testng.Assert.assertEquals;

@Feature("App Cart: add product to cart")
public class CartTest extends UiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCartAndReset() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart("Sauce Labs Backpack");
        var cartSize = productsPage.getCartItemsNumber();
        assertEquals(cartSize, 1);

        productsPage.resetAppState();
        cartSize = productsPage.getCartItemsNumber();
        assertEquals(cartSize, 0);
    }
}
