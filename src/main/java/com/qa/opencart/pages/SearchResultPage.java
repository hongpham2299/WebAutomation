package com.qa.opencart.pages;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By searchProductResults = By.cssSelector("div#content div.product-layout");


    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the number of total search products")
    public int getTotalSearchProductsCount(){
        int count = elementUtil.waitForElementsVisible(searchProductResults, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        System.out.println("Total of search products: " + count);
        return count;
    }

    @Step("Select the product from the search result. It will lead to the Product Info page")
    public ProductInfoPage selectProduct(String productName){
        By productLocator = By.linkText(productName);
        elementUtil.waitForElementVisible(productLocator, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).click();
        return new ProductInfoPage(driver);
    }
}
