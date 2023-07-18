package com.qa.opencart.pages;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private By h1Header = By.xpath("(//h1)[last()]");
    private By productImages = By.xpath("//ul[@class='thumbnails']//img");
    private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul)[1]/li");
    private By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul)[2]/li");
    private By quantityField = By.id("input-quantity");
    private By addToCartButton = By.id("button-cart");
    private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");

    private WebDriver driver;
    private ElementUtil elementUtil;
    private Map<String, String> productInfoMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Get the product H1 header on the product page")
    public String getProductInfoH1Header(){
        String h1HeaderText = elementUtil.doElementGetText(h1Header);
        System.out.println("Product H1 Header: " + h1HeaderText);
        return h1HeaderText;
    }

    @Step("Get all the product images count on product page")
    public int getProductImagesCount(){
        int numberOfImages = elementUtil.waitForElementsVisible(productImages, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        System.out.println("Number of Product Images: " + numberOfImages);
        return numberOfImages;
    }

    @Step("Get all the product information such as product meta data and product prices on the product page")
    public Map<String, String> getProductInfo() {
        productInfoMap = new LinkedHashMap<>();
        productInfoMap.put(OpenCartConstants.PRODUCT_NAME_KEY, getProductInfoH1Header());//Feting the product name
        getProductMetaData();
        getProductPriceData();
        System.out.println(productInfoMap);
        return productInfoMap;
    }

    /*  Fetching meta-data
        Brands Apple
        Product Code: Product 18
        Reward Points: 800
        Availability: 937
     */
    private void getProductMetaData() {
        // meta data:
        List<WebElement> metaProductList = elementUtil.getElements(productMetaData);
        String brandValue = metaProductList.get(0).getText().split(" ")[1];
        String productCodeValue = metaProductList.get(1).getText().split(":")[1].trim();
        String rewardPointsValue = metaProductList.get(2).getText().split(":")[1].trim();
        String availabilityValue = metaProductList.get(3).getText().split(":")[1].trim();

        productInfoMap.put(OpenCartConstants.PRODUCT_BRAND_KEY, brandValue);
        productInfoMap.put(OpenCartConstants.PRODUCT_CODE_KEY, productCodeValue);
        productInfoMap.put(OpenCartConstants.PRODUCT_REWARD_POINTS_KEY, rewardPointsValue);
        productInfoMap.put(OpenCartConstants.PRODUCT_AVAILABILITY_KEY, availabilityValue);
    }

    /* Fetching pricing data
       $2,000.00
       Ex Tax: $2,000.00
     */
    private void getProductPriceData() {
        // price:
        List<WebElement> metaPriceList = elementUtil.getElements(productPriceData);
        String priceValue = metaPriceList.get(0).getText();
        String exTax = metaPriceList.get(1).getText();
        String exTaxValue = exTax.split(":")[1].trim();

        productInfoMap.put(OpenCartConstants.PRODUCT_PRICE_KEY, priceValue);
        productInfoMap.put(OpenCartConstants.PRODUCT_EX_TAX_KEY, exTaxValue);
    }

    @Step("Apply the quantity of product")
    public void enterQuantity(int inputQuantity){
        elementUtil.doSendKeys(quantityField, String.valueOf(inputQuantity));
        System.out.println("Product quantity: " + inputQuantity);
    }

    @Step("Add the product to cart")
    public String addProductToCart(){
        elementUtil.doClick(addToCartButton);
        String successMessage = elementUtil.waitForElementVisible(cartSuccessMessage, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).getText();
        System.out.println("Cart success message -> " + successMessage);
        return successMessage;
    }


}
