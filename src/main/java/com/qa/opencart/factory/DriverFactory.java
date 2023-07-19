package com.qa.opencart.factory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DriverFactory {

    public WebDriver driver;
    public Properties properties;
    public FileInputStream fileInputStream;
    public OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private String prodConfigFile = "src/test/resources/config/prod.config.properties";
    private String qaConfigFile = "src/test/resources/config/qa.config.properties";
    private String devConfigFile = "src/test/resources/config/dev.config.properties";
    private String stageConfigFile = "src/test/resources/config/stage.config.properties";

    /**
     * This method is initializing the driver on the basis of given browser name
     * @param prop
     * @return
     */
    public WebDriver initDriver(Properties prop){

        optionsManager = new OptionsManager(prop);

        highlight = prop.getProperty("highlight");

        String browserName = prop.getProperty("browser").toLowerCase().trim();

        System.out.println("Browser name:" + browserName);

        switch (browserName){
            case "chrome":
                //driver = new ChromeDriver(optionsManager.getChromeOptions());
                webDriverThreadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "firefox":
                //driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
                webDriverThreadLocal.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
                break;
            case "safari":
                //driver = new SafariDriver();
                webDriverThreadLocal.set(new SafariDriver());
                break;
            case "edge":
                //driver = new EdgeDriver(optionsManager.getEdgeOptions());
                webDriverThreadLocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            default:
                System.out.println("Please pass a correct browser");
                break;
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    /*
     * Get the local thread copy of the driver
     */
    public synchronized static WebDriver getDriver(){
        return webDriverThreadLocal.get();
    }

    /**
     * This method is reading the properties from the properties file
     * @return
     */
    public Properties initProperty(){

        properties = new Properties();
        String envName = System.getProperty("env");//mvn clean install -Denv="qa"
        System.out.println("Running test cases on Env: " + envName);

        try {
            if (envName == null) {
                System.out.println("no env is passed....Running tests on QA env...");
                fileInputStream = new FileInputStream(qaConfigFile);
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fileInputStream = new FileInputStream(qaConfigFile);
                        break;
                    case "stage":
                        fileInputStream = new FileInputStream(stageConfigFile);
                        break;
                    case "dev":
                        fileInputStream = new FileInputStream(devConfigFile);
                        break;
                    case "prod":
                        fileInputStream = new FileInputStream(prodConfigFile);
                        break;

                    default:
                        System.out.println("Wrong env is passed. No need to run the test cases");
                        throw new RuntimeException("Exception coming -> WRONG ENV....");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * take screenshot
     */
    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destinationFile = new File(path);
        try {
            FileHandler.copy(srcFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
