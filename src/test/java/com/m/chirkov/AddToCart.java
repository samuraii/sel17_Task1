package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddToCart extends Login {

    @Test
    public void AddToCart() {


        int count = 0;

        while (count < 3) {
            // Добавляю первый элемент в корзину
            List<WebElement> goods = driver.findElements(By.cssSelector(".product"));
            WebElement first_good_item = goods.get(0);
            first_good_item.click();
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product")));
            WebElement add_to_cart = driver.findElement(By.name("add_cart_product"));
            try {
                // Если встречается элемент с размерми
                new Select(driver.findElement(By.name("options[Size]"))).selectByVisibleText("Small");
            } catch (Exception e) {

            }
            add_to_cart.click();
            WebElement back_home = driver.findElement(By.linkText("Home"));
            count += 1;
            wait.until(ExpectedConditions.textToBe(By.cssSelector("#cart-wrapper .quantity"), String.valueOf(count)));
            back_home.click();
        }

        WebElement checkout = driver.findElement(By.partialLinkText("Checkout"));
        checkout.click();

        // Удаляем пока не исчезнет элемент Remove
        while (true) {
            WebElement remove_buttomn = driver.findElement(By.name("remove_cart_item"));
            remove_buttomn.click();
            try {
                WebDriverWait wait = new WebDriverWait(driver, 3);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("remove_cart_item")));
            } catch (org.openqa.selenium.TimeoutException e) {
                break;
            }
        }
    }
}
