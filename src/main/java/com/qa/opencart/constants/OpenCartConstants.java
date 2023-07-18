package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class OpenCartConstants {

    public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
    public static final int DEFAULT_SHORT_TIME_OUT = 5;
    public static final int DEFAULT_LONG_TIME_OUT = 20;

    //------------------Login Page----------------
    public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
    public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";

    //------------------Accounts Page----------------
    public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";
    public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE = "route=account/account";
    public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
    public static final List<String> EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
                                                                            "My Affiliate Account", "Newsletter");

    //------------------Product Info Page----------------
    public static final String PRODUCT_BRAND = "Apple";
    public static final String PRODUCT_CODE = "Product 18";
    public static final String PRODUCT_REWARD_POINTS = "800";
    public static final String PRODUCT_AVAILABILITY = "937";
    public static final String PRODUCT_NAME = "MacBook Pro";
    public static final String PRODUCT_PRICE = "$2,000.00";
    public static final String PRODUCT_EX_TAX = "$2,000.00";
    public static final String PRODUCT_NAME_KEY = "productName";
    public static final String PRODUCT_BRAND_KEY = "brands";
    public static final String PRODUCT_CODE_KEY = "productCode";
    public static final String PRODUCT_REWARD_POINTS_KEY = "rewardPoints";
    public static final String PRODUCT_AVAILABILITY_KEY = "availability";
    public static final String PRODUCT_PRICE_KEY = "productPrice";
    public static final String PRODUCT_EX_TAX_KEY = "exTax";
    public static final String FIRST_PORTION_OF_SUCCESS_MESSAGE = "Success: You have added ";
    public static final String LAST_PORTION_OF_SUCCESS_MESSAGE = " to your shopping cart!";

    //------------------Register Page---------------
    public static final String USER_REG_SUCCESS_MESSG = "Congratulations! Your new account has been successfully created!";
    public static final String REGISTER_SHEET_NAME = "register";


}
