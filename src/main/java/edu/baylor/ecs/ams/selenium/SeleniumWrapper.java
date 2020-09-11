package edu.baylor.ecs.ams.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

@Getter
public abstract class SeleniumWrapper {

    protected WebDriver webDriver;

    public SeleniumWrapper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SeleniumWrapper(String downloadPath) {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        this.webDriver = new ChromeDriver(cap);
    }

    public SeleniumWrapper pause(long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
        return this;
    }

    public void quit() {
        webDriver.quit();
    }

    public abstract SeleniumWrapper toSite();

    public abstract SeleniumWrapper sendQuery(String query);

    public abstract SeleniumWrapper filterResults();

    public abstract SeleniumWrapper exportResults();
}
