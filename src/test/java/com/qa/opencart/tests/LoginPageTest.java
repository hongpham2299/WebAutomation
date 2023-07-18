package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("EPIC - 101: Design login for Open Cart App")
@Story("US-Login: 101: design log in page features for Open Cart")
public class LoginPageTest extends BaseTest {

    @Description("Validate the title of the page. Tester: Hong Pham Diaz")
    @Test(priority = 1)
    public void validateLoginPageTitleTest(){
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
    }

    @Description("Validate the login page URL. Tester: Hong Pham Diaz")
    @Test(priority = 2)
    public void validateLoginPageURLTest(){
        String actualURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actualURL.contains(OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }

    @Description("Validate the forgot password link in the login form. Tester: Hong Pham Diaz")
    @Test(priority = 3)
    public void validateForgotPasswordHyperTextExistTest(){
        boolean actualResult = loginPage.isForgotPasswordLinkExist();
        Assert.assertTrue(actualResult);
    }

    @Description("Validate the user is able to login with valid username and pass. Tester: Hong Pham Diaz")
    @Test(priority = 4)
    public void validateLoginFormTest(){
        String username = properties.getProperty("username").trim();
        String password = properties.getProperty("password").trim();

        accountsPage = loginPage.doLogin(username, password);
        boolean result = accountsPage.isLogoutLinkExist();
        Assert.assertTrue(result);
    }

}
