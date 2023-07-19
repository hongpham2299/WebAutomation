package com.qa.opencart.pages;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By logoutHyperText = By.linkText("Logout");
    private By accountHeadersH2 = By.xpath("//div[@id='content']//h2");
    private By searchField = By.name("search");
    private By searchIcon = By.cssSelector("#search button");

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the accounts page title")
    public String getAccPageTitle() {
        String title = elementUtil.waitForTitleIsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.ACCOUNTS_PAGE_TITLE_VALUE);
        System.out.println("Account page title: " + title);
        return title;
    }

    @Step("Get the current accounts page URL")
    public String getCurrentAccPageURL() {
        String url = elementUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
        System.out.println("Account page URL: " + url);
        return url;
    }

    @Step("Confirm the logout link exists on the accounts page")
    public boolean isLogoutLinkExist() {
        return elementUtil.waitForElementVisible(logoutHyperText, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
    }

    @Step("Confirm the search field exists on the accounts page")
    public boolean isSearchFieldExist() {
        return elementUtil.waitForElementVisible(searchField, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
    }

    @Step("Get all the H2 Headers in accounts page")
    public List<String> getAccountPageHeadersH2List() {

        List<WebElement> headersH2List = elementUtil.waitForElementsVisible(accountHeadersH2, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
        List<String> h2Headers = new ArrayList<>();

        for (WebElement header : headersH2List) {
            String headerText = header.getText();
            h2Headers.add(headerText);
        }
        return h2Headers;
    }

    @Step("Navigate to the search field and perform the search action on the accounts page. It will leads to the Search Result Page")
    public SearchResultPage performSearch(String inputSearchKey) {
        if (isSearchFieldExist()) {
            elementUtil.doSendKeys(searchField, inputSearchKey);
            elementUtil.doClick(searchIcon);
            return new SearchResultPage(driver);
        } else {
            System.out.println("Search field is not presented on the page");
            return null;
        }

    }
}
