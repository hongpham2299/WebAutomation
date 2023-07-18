package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ExcelUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EPIC - 103: Design register for Open Cart App")
@Story("US-Account: 103: design register page features for Open Cart")
public class RegisterPageTest extends BaseTest {

    @Description("Require to click on the register link first in order to perform the registration")
    @BeforeClass
    public void preconditionRegisterPageSetup(){
        registerPage = loginPage.clickToRegisterLink();
    }

    @DataProvider
    public Object[][] getRegTestData() {
        Object regData[][] = ExcelUtil.getTestData(OpenCartConstants.REGISTER_SHEET_NAME);
        return regData;
    }

    @Description("Validate the users register successfully. The users info will pull from the .xlsx sheet. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getRegTestData")
    public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        boolean result = registerPage.registerUser(firstName, lastName, registerPage.getRandomEmail(), telephone, password, subscribe);
        Assert.assertTrue(result);
    }

}
