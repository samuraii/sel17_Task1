package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;

public class CreateGood extends Login {

    protected String RandId() {
        Random rand = new Random();
        int randomNum = rand.nextInt((9999999 - 1) + 1) + 1;
        return String.valueOf(randomNum);
    }

    @Test
    public void CreateGoodItem() {
        loginAsAdmin();

        // General
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.cssSelector("#doc-catalog .name")).click();
        driver.findElement(By.partialLinkText("Add New Product")).click();
        String name = "Goood" + RandId();
        driver.findElement(By.name("name[en]")).sendKeys(name);
        driver.findElement(By.name("code")).sendKeys(RandId());
        driver.findElement(By.name("quantity")).sendKeys("11");
        driver.findElement(By.name("date_valid_from")).sendKeys("21112018");
        driver.findElement(By.name("date_valid_to")).sendKeys("21112020");
        String filePath = System.getProperty("user.dir") + File.separator +"img" + File.separator + "cat.jpg";
        driver.findElement(By.name("new_images[]")).sendKeys(filePath);

        //Information
        driver.findElement(By.linkText("Information")).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("keywords")));
        driver.findElement(By.name("keywords")).sendKeys("keywords");
        driver.findElement(By.name("short_description[en]")).sendKeys("short description");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Tra la la la la la loooo");


        //Prices
        driver.findElement(By.linkText("Prices")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("purchase_price")));
        driver.findElement(By.name("purchase_price")).sendKeys(RandId());
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys(RandId());
        driver.findElement(By.name("prices[EUR]")).sendKeys(RandId());

        // Сохраняем
        driver.findElement(By.name("save")).click();

        //Проверяем что товар появился
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(name)));
    }
}
