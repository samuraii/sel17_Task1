package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PanelMenu extends Login {

    @Test
    public void clickMenuItems() {
        loginAsAdmin();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("#box-apps-menu > li[id='app-'] > a"));
        int top_level_links_amount = menuItems.size();

        for (int i = 0; i < top_level_links_amount; i++) {
            menuItems = driver.findElements(By.cssSelector("#box-apps-menu > li[id='app-'] > a"));
            menuItems.get(i).click();

            try {
                List<WebElement> sub_menu = driver.findElements(By.className("docs"));
                List<WebElement> sub_menu_items = sub_menu.get(0).findElements(By.cssSelector("li > a"));
                int sub_menu_size = sub_menu_items.size();

                for (int j = 0; j < sub_menu_size; j++) {
                    sub_menu = driver.findElements(By.className("docs"));
                    sub_menu_items = sub_menu.get(0).findElements(By.cssSelector("li > a"));
                    sub_menu_items.get(j).click();
                    WebElement content = driver.findElement(By.id("content"));
                    WebElement content_h1 = content.findElement(By.tagName("h1"));
                    assert content_h1.isDisplayed();
                }

            } catch (IndexOutOfBoundsException ex) {
                WebElement content = driver.findElement(By.id("content"));
                WebElement content_h1 = content.findElement(By.tagName("h1"));
                assert content_h1.isDisplayed();
            }
        }
    }

}
