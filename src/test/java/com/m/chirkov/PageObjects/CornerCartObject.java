package com.m.chirkov.PageObjects;

import com.m.chirkov.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CornerCartObject extends Login {

    public void waitForProductCount(int count) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#cart-wrapper .quantity"), String.valueOf(count)));
    }

    public void clickCheckout() {
        driver.findElement(By.partialLinkText("Checkout")).click();
    }

}
