package com.m.chirkov;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;


public class Login {

    protected static WebDriver driver;

    protected static String host = "http://localhost/shop";

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(host);
        WebDriverWait wait = new WebDriverWait(driver, 3);
    }

    protected static void loginAsAdmin() {
        driver.get(host + "/admin");
        WebElement loginField = driver.findElement(By.name("username"));
        loginField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin");
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-apps-menu")));
    }

    protected static void logoutAdmin() {
        driver.get(host + "admin");
        WebElement lgout = driver.findElement(By.cssSelector("a[title=\"Logout\"]"));
        lgout.click();
    }

    public void SwitchToTab(int n) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(n));
    }

    public void CloseTab() {
        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript("window.close()");
    }

    public void OpenNewTab(String url) {
        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        SwitchToTab(1);
        driver.get(url);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
