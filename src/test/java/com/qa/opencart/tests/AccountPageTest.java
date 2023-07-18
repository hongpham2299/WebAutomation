package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@Epic("EPIC - 100: Design accounts for Open Cart App")
@Story("US-Account: 100: design accounts page features for Open Cart")
public class AccountPageTest extends BaseTest {

    @Description("Perform the login action first in order to test the accounts page. Tester: Hong Pham Diaz")
    @BeforeClass
    public void preconditionForAccPageSetup(){
        String username = properties.getProperty("username").trim();
        String password = properties.getProperty("password").trim();
        accountsPage = loginPage.doLogin(username, password);
    }

    @Description("Validate the account page title. Tester: Hong Pham Diaz")
    @Test
    public void validateAccPageTitleTest(){
        String actualTitle = accountsPage.getAccPageTitle();
        Assert.assertEquals(actualTitle, OpenCartConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

    @Description("Validate the account page URL. Tester: Hong Pham Diaz")
    @Test
    public void validateAccPageURLTest(){
        String actualURL = accountsPage.getCurrentAccPageURL();
        Assert.assertTrue(actualURL.contains(OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
    }

    @Description("Validate the logout link exists on the accounts page. Tester: Hong Pham Diaz")
    @Test
    public void validateLogoutLinkExistTest(){
        boolean result = accountsPage.isLogoutLinkExist();
        Assert.assertTrue(result);
    }

    @Description("Validate the search field exists on the accounts page. Tester: Hong Pham Diaz")
    @Test
    public void validateTheSearchFieldExistTest(){
        boolean result = accountsPage.isSearchFieldExist();
        Assert.assertTrue(result);
    }

    @Description("Validate the total number of headers on accounts page to make sure the test passes. Tester: Hong Pham Diaz")
    @Test
    public void validateAccPageH2HeadersCountTest(){
        List<String> actualHeaderList = accountsPage.getAccountPageHeadersH2List();
        int actualNumberOfHeaders = actualHeaderList.size();
        System.out.println("Total count of headers: " + actualNumberOfHeaders);
        Assert.assertEquals(actualNumberOfHeaders, OpenCartConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
    }

    @Description("Validate the all text value of headers on accounts page to make sure the test passes. Tester: Hong Pham Diaz")
    @Test
    public void validateAccPageH2HeadersValueTest(){
        List<String> actualHeaderList = accountsPage.getAccountPageHeadersH2List();
        System.out.println("Account page headers list: " + actualHeaderList);
        Assert.assertEquals(actualHeaderList, OpenCartConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
    }
}
