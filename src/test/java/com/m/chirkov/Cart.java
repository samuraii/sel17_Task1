package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart extends Login {
    @Test
    public void CartTest() {
        List<WebElement> goods = driver.findElements(By.cssSelector(".product a.link"));
        goods.get(0).click();
        WebElement add_to_cart = driver.findElement(By.name("add_cart_product"));
        add_to_cart.click();
    }
}
