package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckStickers extends Login {

    @Test
    public void CheckStickers() {
        List<WebElement> goods = driver.findElements(By.className("product"));
        int goods_amount = goods.size();

        for (int i = 0; i < goods_amount; i++) {
            WebElement sticker = goods.get(i).findElement(By.className("sticker"));
            assert sticker.isDisplayed();
        }

    }

}
