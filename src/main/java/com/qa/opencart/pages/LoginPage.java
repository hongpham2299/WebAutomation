package com.qa.opencart.pages;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private By forgotPasswordHyperText = By.linkText("Forgotten Password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By registerLink = By.linkText("Register");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the login page title")
    public String getLoginPageTitle(){
        String pageTitle = elementUtil.waitForTitleIsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
        System.out.println("Login page title: " + pageTitle);
        return pageTitle;
    }

    @Step("Get the login page URL")
    public String getLoginPageURL(){
        String url = elementUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
        System.out.println("Current login page URL: " + url);
        return url;
    }

    @Step("Get the forgot password link")
    public boolean isForgotPasswordLinkExist(){
        return elementUtil.waitForElementPresence(forgotPasswordHyperText, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }

    /**
     * Chaining across pages/page objects
     */
    @Step("Login with valid username: {0} and valid password: {1}. It will leads to the Accounts Page if the login is success")
    public AccountsPage doLogin(String inputEmail, String inputPassword){
        elementUtil.waitForElementPresence(email, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(inputEmail);
        elementUtil.doSendKeys(password, inputPassword);
        elementUtil.doClick(loginButton);
        return new AccountsPage(driver);
    }

    @Step("Navigate to the Register link on the login page")
    public RegisterPage clickToRegisterLink(){
        elementUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }


}
