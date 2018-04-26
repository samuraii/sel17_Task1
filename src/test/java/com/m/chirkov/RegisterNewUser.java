package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class RegisterNewUser extends Login {

    protected String RandMail(int max, int min) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNum) + "email@test.ru";
    }

    protected String RandPhone() {
        Random rand = new Random();
        int randomNum = rand.nextInt((99999999 - 1) + 1) + 1;
        return "+" + String.valueOf(randomNum);
    }

    @Test
    public void RegisterNew() {
        WebElement login_form = driver.findElement(By.id("box-account-login"));
        WebElement reg_new_button = login_form.findElement(By.linkText("New customers click here"));
        reg_new_button.click();
        //Заполняю поля формы
        WebElement customer_form = driver.findElement(By.cssSelector("form[name=\"customer_form\"]"));
        customer_form.findElement(By.name("firstname")).sendKeys("testname");
        customer_form.findElement(By.name("lastname")).sendKeys("testlastname");
        customer_form.findElement(By.name("address1")).sendKeys("testaddress");
        customer_form.findElement(By.name("city")).sendKeys("testcity");
        String email = RandMail(999999, 1);
        System.out.println("User email " + email);
        customer_form.findElement(By.name("email")).sendKeys(email);
        customer_form.findElement(By.name("phone")).sendKeys(RandPhone());
        customer_form.findElement(By.name("password")).sendKeys("Password1");
        customer_form.findElement(By.name("confirmed_password")).sendKeys("Password1");
        customer_form.findElement(By.name("create_account")).click();
        //Разлогиниваемя на главной
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        driver.findElement(By.linkText("Logout")).click();
        // Логинимся под созданной записью
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box-account-login")));
        login_form = driver.findElement(By.id("box-account-login"));
        login_form.findElement(By.name("email")).sendKeys(email);
        login_form.findElement(By.name("password")).sendKeys("Password1");
        login_form.findElement(By.name("login")).click();
        //Разлогиниваемя на главной
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        driver.findElement(By.linkText("Logout")).click();
    }
}
