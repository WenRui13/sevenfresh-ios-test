package com.xstore.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DemoTest extends UserBaseTest {

    private AppiumDriver<MobileElement>  driver;
    private WebDriverWait wait;

    @BeforeTest
    public void prepairDriver() throws Exception {
        driver = getDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void testSummitOrder() throws Exception {
        driver.findElementByAccessibilityId("searchicon").click();
//        driver.findElementByClassName("XCUIElementTypeTextField").clear();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("虾");
        driver.getKeyboard().sendKeys(Keys.RETURN);
        driver.findElementByAccessibilityId("每日优选及分类热销每日新品列表加车").click();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("shoppingcart").click();
//        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("购物车")));

//        driver.findElementByXPath("//XCUIElementTypeButton[contains(@name,'去结算')]").click();
        (new TouchAction(driver)).tap(363, 708).perform();



        driver.findElementByAccessibilityId("提交订单").click();
        Assert.assertTrue(driver.findElementByAccessibilityId("支付").isDisplayed());
    }

//    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
