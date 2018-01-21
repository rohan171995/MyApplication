package com.example.rohan.myapplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class FirstTest {

    WebDriver driver;

    WebDriverWait wait;

    String AppURL = "http://www.seleniumeasy.com";

    @BeforeTest
    public void setUp() throws MalformedURLException {

        // Create an object for Desired Capabilities

        DesiredCapabilities capabilities = new DesiredCapabilities();


        // Name of mobile web browser to automate. ‘Safari’ for iOS and ‘Chrome’

        // or ‘Browser’ for Android

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "XT1562");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "8.0.0");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Java package of the Android app you want to run- Ex:

        // com.example.android.myApp

        capabilities.setCapability("appPackage", "com.android.chrome");


        // Activity name for the Android activity you want to launch from your

        // package

        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");


        // Initialize the driver object with the URL to Appium Server and

        // passing the capabilities

        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        //driver = new RemoteWebDriver(new URL("http://10.167.69.83/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, 10000);
    }

    @org.testng.annotations.Test
    public void testSearchAppium() {


        driver.get(AppURL);

        driver.manage().timeouts().implicitlyWait(100000, TimeUnit.SECONDS);

        WebElement titleElement = driver.findElement(By.cssSelector("#site-name>a"));

        //By titleElement = By.cssSelector("#site-name>a");

        //wait.until(ExpectedConditions.presenceOfElementLocated(titleElement));

        // String homePageTitle = ((WebElement) titleElement).getText();

        String homePageTitle = titleElement.getText();

        System.out.println(homePageTitle);

        Assert.assertEquals(homePageTitle, "Selenium Easy");


        WebElement searchElement = driver.findElement(By.name("search_block_form"));

        searchElement.sendKeys("Appium Tutorials");


        WebElement searchBtnEle = driver.findElement(By.id("edit-submit"));

        searchBtnEle.click();


        By byPageTitle = By.id("page-title");

        wait.until(ExpectedConditions.presenceOfElementLocated(byPageTitle));


        String searchPageTitle = driver.findElement(byPageTitle).getText();

        Assert.assertEquals(searchPageTitle, "Search");

    }
}
