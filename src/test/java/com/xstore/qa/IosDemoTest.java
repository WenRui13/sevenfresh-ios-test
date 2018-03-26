package com.xstore.qa;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IosDemoTest {

    private IOSDriver driver;
    private WebDriverWait wait;
    @Parameters({ "platformVersion", "deviceName","udid" })
    @BeforeClass
    public void setUp(String platformVersion,String deviceName,String udid) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("platformVersion",platformVersion);
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("browserName","");
        capabilities.setCapability("udid",udid);
        capabilities.setCapability("bundleId","com.xstore.sevenFresh");

//        capabilities.setCapability("useNewWDA","true");
//        capabilities.setCapability("clearSystemFiles","true");
//        capabilities.setCapability("shouldUseSingletonTestManager","false");
//        capabilities.setCapability("shouldWaitForQuiescence","false");
//        capabilities.setCapability("waitForQuiescence","false");
//        capabilities.setCapability("wdaStartupRetryInterval","1000");

        capabilities.setCapability("noReset","true");
        capabilities.setCapability("unicodeKeyboard","true");
        capabilities.setCapability("showXcodeLog","true");
//        capabilities.setCapability("keychainPath","/usr/local/***");
//        capabilities.setCapability("keychainPassword","****");



        driver = new IOSDriver(new URL("http://169.254.25.214:4444/wd/hub"),capabilities);
//        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void test1() throws Exception {
        driver.findElementByAccessibilityId("个人中心").click();
        Assert.assertTrue(driver.findElementByAccessibilityId("ceshibu5").isDisplayed());

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

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
