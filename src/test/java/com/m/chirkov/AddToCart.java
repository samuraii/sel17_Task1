package com.m.chirkov;

import com.m.chirkov.PageObjects.CartPageObject;
import com.m.chirkov.PageObjects.CornerCartObject;
import com.m.chirkov.PageObjects.MainPageObject;
import com.m.chirkov.PageObjects.ProductPageObject;
import org.junit.Test;

public class AddToCart extends Login {

    MainPageObject mainPage = new MainPageObject();
    CartPageObject cartPage = new CartPageObject();
    ProductPageObject product = new ProductPageObject();
    CornerCartObject cornerCart = new CornerCartObject();

    @Test
    public void AddToCart() {

        int count = 0;

        while (count < 3) {
            mainPage.selectProduct(0);
            product.addToCart("Small");
            cornerCart.waitForProductCount(count += 1);
            product.breadCrumbsHomeClick();
        }

        cornerCart.clickCheckout();

        while (true) {

            if (cartPage.empty()) {
                break;
            } else {
                cartPage.removeProductFromCart();
            }

        }
    }
}
