package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.pages.AccountsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Map;

@Epic("EPIC - 102: Design product information for Open Cart App")
@Story("US-Account: 102: design product information page features for Open Cart")
public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productPageSetup(){
        accountsPage = new AccountsPage(driver);
    }

    @DataProvider
    public Object[][] getProductKeywordData(){
        return new Object[][] {
                {"Macbook"},
                {"iMac"},
                {"Apple"},
                {"Samsung"}
        };
    }

    @Description("Validate the number of products after enter keyword for searching. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getProductKeywordData")
    public void validateSearchProductCountTest(String searchKey){
        searchResultPage = accountsPage.performSearch(searchKey);
        int numberOfSearchProducts = searchResultPage.getTotalSearchProductsCount();
        Assert.assertTrue(numberOfSearchProducts > 0);
        System.out.println("---------------------");
    }

    @DataProvider
    public Object[][] getProductData(){
        return new Object[][] {
                {"Macbook", "MacBook Pro"},
                {"Macbook", "MacBook Air"},
                {"iMac", "iMac"},
                {"Apple", "Apple Cinema 30\""},
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Samsung", "Samsung Galaxy Tab 10.1"}
        };
    }

    @Description("Validate products display after entering keyword for searching. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getProductData")
    public void validateSearchProductTest(String searchKey, String productName){
        searchResultPage = accountsPage.performSearch(searchKey);

        if(searchResultPage.getTotalSearchProductsCount()>0) {
            productInfoPage = searchResultPage.selectProduct(productName);
            String actualProductH1Header = productInfoPage.getProductInfoH1Header();
            Assert.assertEquals(actualProductH1Header, productName);
            System.out.println("---------------------");
        }
    }

    @DataProvider
    public Object[][] getProductImagesData(){
        return new Object[][] {
            {"Macbook", "MacBook Pro", 4},
            {"iMac", "iMac", 3},
            {"Apple", "Apple Cinema 30\"", 6},
            {"Samsung", "Samsung SyncMaster 941BW", 1},
    };
}

    @Description("Validate product images display on each product info. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getProductImagesData")
    public void validateNumberOfImageProductsTest(String searchKey, String productName, int imagesCount){

        searchResultPage = accountsPage.performSearch(searchKey);
        productInfoPage = searchResultPage.selectProduct(productName);
        int actualNumber = productInfoPage.getProductImagesCount();
        Assert.assertEquals(actualNumber, imagesCount);
        System.out.println("---------------------");
    }

    @DataProvider
    public Object[][] getProductTestData() {
        return new Object[][] {
                {"Macbook", "MacBook Pro"},
        };
    }

    @Description("Validate the products information display on the product info page. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getProductTestData")
    public void validateProductInfoTest(String searchKeyword, String productName){

        searchResultPage = accountsPage.performSearch(searchKeyword);
        productInfoPage = searchResultPage.selectProduct(productName);
        Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();

        softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_BRAND_KEY), OpenCartConstants.PRODUCT_BRAND);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_CODE_KEY), OpenCartConstants.PRODUCT_CODE);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_REWARD_POINTS_KEY), OpenCartConstants.PRODUCT_REWARD_POINTS);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_AVAILABILITY_KEY), OpenCartConstants.PRODUCT_AVAILABILITY);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_NAME_KEY), OpenCartConstants.PRODUCT_NAME);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_PRICE_KEY), OpenCartConstants.PRODUCT_PRICE);
        softAssert.assertEquals(actualProductInfoMap.get(OpenCartConstants.PRODUCT_EX_TAX_KEY), OpenCartConstants.PRODUCT_EX_TAX);

        softAssert.assertAll();
        System.out.println("---------------------");
    }

    @DataProvider
    public Object[][] getCartTestData() {
        return new Object[][] {
                {"Macbook", "MacBook Pro", 1},
                {"iMac", "iMac", 2},
        };
    }

    @Description("Validate the product is added to the cart. Tester: Hong Pham Diaz")
    @Test(dataProvider = "getCartTestData")
    public void validateProductAddToCartSuccess(String searchKey, String productName, int quantity){
        searchResultPage = accountsPage.performSearch(searchKey);
        productInfoPage = searchResultPage.selectProduct(productName);
        productInfoPage.enterQuantity(quantity);
        String actualSuccessMessage = productInfoPage.addProductToCart();

        softAssert = new SoftAssert();
        softAssert.assertTrue(actualSuccessMessage.contains(productName));
        softAssert.assertEquals(actualSuccessMessage, OpenCartConstants.FIRST_PORTION_OF_SUCCESS_MESSAGE + productName
                                                                        + OpenCartConstants.LAST_PORTION_OF_SUCCESS_MESSAGE);
        System.out.println("---------------------");
    }




}
