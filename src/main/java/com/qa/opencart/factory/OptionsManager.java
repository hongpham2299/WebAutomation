package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties properties;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    public OptionsManager(Properties properties) {
        this.properties = properties;
    }

    public ChromeOptions getChromeOptions(){
        chromeOptions = new ChromeOptions();
        if(Boolean.parseBoolean(properties.getProperty("incognito").trim())){
            System.out.println("Running under chrome incognito");
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFireFoxOptions(){
        firefoxOptions = new FirefoxOptions();
        if(Boolean.parseBoolean(properties.getProperty("incognito").trim())){
            System.out.println("Running under firefox incognito");
            firefoxOptions.addArguments("--incognito");
        }
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions(){
        edgeOptions = new EdgeOptions();
        if(Boolean.parseBoolean(properties.getProperty("incognito").trim())){
            System.out.println("Running under edge incognito");
            edgeOptions.addArguments("--incognito");
        }
        return edgeOptions;
    }
}
