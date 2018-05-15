package com.m.chirkov.PageObjects;

import com.m.chirkov.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject extends Login {

    public List<WebElement> getAllProducts() {
        return driver.findElements(By.cssSelector(".product"));
    }

    public void selectProduct(int i) {
        this.getAllProducts().get(i).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product")));
    }

}
