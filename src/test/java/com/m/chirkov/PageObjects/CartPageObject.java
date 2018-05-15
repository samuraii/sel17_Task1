package com.m.chirkov.PageObjects;

import com.m.chirkov.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPageObject extends Login {

    public void removeProductFromCart() {
        driver.findElement(By.name("remove_cart_item")).click();
    }

    public Boolean empty() {
        try {
            WebElement back = driver.findElement(By.partialLinkText("Back"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

}
