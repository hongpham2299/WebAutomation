package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    private DriverFactory driverFactory;
    protected Properties properties;
    protected WebDriver driver;

    //We keep these pages here, just in case other classed want to use these pages. They can get it from here since they already extended from the BaseTest.
    protected LoginPage loginPage;
    protected AccountsPage accountsPage;
    protected SearchResultPage searchResultPage;
    protected ProductInfoPage productInfoPage;
    protected SoftAssert softAssert;
    protected RegisterPage registerPage;


    @BeforeTest
    public void setup(){
        driverFactory = new DriverFactory();
        properties = driverFactory.initProperty();
        driver = driverFactory.initDriver(properties);
        //softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
