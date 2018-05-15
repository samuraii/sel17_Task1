package com.m.chirkov.PageObjects;

import com.m.chirkov.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPageObject extends Login {

    public WebElement breadCrumbs() {
        return driver.findElement(By.cssSelector("ul.list-horizontal"));
    }

    public void breadCrumbsHomeClick() {
        this.breadCrumbs().findElements(By.cssSelector("li")).get(0).click();
    }

    public void addToCart(String size) {
        try {
            // Если встречается элемент с размерми
            new Select(driver.findElement(By.name("options[Size]"))).selectByVisibleText(size);
        } catch (Exception e) {
        }

        driver.findElement(By.name("add_cart_product")).click();
    }

}
