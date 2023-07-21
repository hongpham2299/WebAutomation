package com.qa.opencart.pages;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
    private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
    private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
    private By registerSuccessMessage = By.xpath("//p[contains(text(), 'Congratulations!')]");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Register with firstName: {0}, lastName: {1}, email: {2}, telephone: {3}, password: {4}, subscribe: {5}")
    public boolean registerUser(String inputFirstName, String inputLastName, String inputEmail, String inputTelephone,
                                                                            String inputPassword, String subscribe){
        elementUtil.waitForElementVisible(firstName, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(inputFirstName);
        elementUtil.doSendKeys(lastName, inputLastName);
        elementUtil.doSendKeys(email, inputEmail);
        elementUtil.doSendKeys(telephone, inputTelephone);
        elementUtil.doSendKeys(password, inputPassword);
        elementUtil.doSendKeys(confirmPassword, inputPassword);

        if(subscribe.equalsIgnoreCase("yes")) {
            elementUtil.doClick(subscribeYes);
        }
        else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doActionsClick(agreeCheckBox);
        elementUtil.doClick(continueButton);

        String successMesg = elementUtil.waitForElementVisible(registerSuccessMessage, OpenCartConstants.DEFAULT_LONG_TIME_OUT).getText();
        System.out.println("User register success message: " + successMesg);

        if(successMesg.contains(OpenCartConstants.USER_REG_SUCCESS_MESSG)) {
            elementUtil.doClick(logoutLink);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }

    @Step("Generate the random email for register since email must be unique")
    public String getRandomEmail(){
        String randomEmail = "automation" + System.currentTimeMillis() + "@gmail.com";
        return randomEmail;
    }


}
