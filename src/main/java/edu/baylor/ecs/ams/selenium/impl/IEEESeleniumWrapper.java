package edu.baylor.ecs.ams.selenium.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import edu.baylor.ecs.ams.selenium.SeleniumWrapper;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IEEESeleniumWrapper extends SeleniumWrapper {

    private static String IEEE_URL = "https://ieeexplore.ieee.org/Xplore/home.jsp";
    private static String IEEE_DOWNLOAD_PATH = "downloads" + File.separator + "exports" + File.separator + "ieee";

    private static String getDownloadPath() {
        String currentWorkingDir = System.getProperty("user.dir");
        System.out.println(currentWorkingDir);
        Path rootPath = Paths.get(currentWorkingDir);
        Path fullPath = rootPath.resolve(IEEE_DOWNLOAD_PATH);
        System.out.println(fullPath.toAbsolutePath().toString());
        return fullPath.toAbsolutePath().toString();
    }

    public IEEESeleniumWrapper() {
        super(getDownloadPath());
    }

    @Override
    public SeleniumWrapper toSite() {

        // Navigate to URL
        this.webDriver.get(IEEE_URL);

        // Wait until search bar is loaded
        new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/xpl-header/div/div[4]/xpl-search-bar-migr/div/form/div[2]/div/div/xpl-typeahead-migr/div/input")));

        return this;
    }

    @Override
    public SeleniumWrapper sendQuery(String query) {

        // Apply query to search bar
        WebElement searchBox = this.webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/xpl-header/div/div[4]/xpl-search-bar-migr/div/form/div[2]/div/div/xpl-typeahead-migr/div/input"));
        searchBox.sendKeys(query);

        // Submit query
        WebElement submitButton = this.webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/xpl-header/div/div[4]/xpl-search-bar-migr/div/form/div[3]/button"));
        submitButton.click();

        // Wait until results load
        new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[2]/xpl-search-dashboard/section/div/div[1]")));

        return this;
    }

    @Override
    public SeleniumWrapper filterResults() {

        // Filter to only conferences
        WebElement conferenceButton = this.webDriver.findElement(By.id("refinement-ContentType:Conferences"));
        conferenceButton.click();

        // Filter to only journals
        WebElement journalButton = this.webDriver.findElement(By.id("refinement-ContentType:Journals"));
        journalButton.click();

        // Apply the filters
        WebElement applyButton = new WebDriverWait(this.webDriver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[2]/xpl-search-dashboard/section/div/div[2]/xpl-facet-content-type-migr/div/div[2]/button")));
        applyButton.click();

        // Wait until results load again
        new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[2]/xpl-search-dashboard/section/div/div[1]")));

        return this;
    }

    @Override
    public SeleniumWrapper exportResults() {

        // /html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[1]/ul/li[3]/xpl-export-search-results/button

        // select only first page of results
//        WebElement selectPageButton = this.webDriver.findElement(By.className("results-actions-selectall-checkbox"));
//        selectPageButton.click();

        // /html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[1]/ul/li[2]/xpl-export-search-results/button
        // //*[@id="xplMainContent"]/div[1]/div[1]/ul/li[2]/xpl-export-search-results/button
        // Expand export options
        // WebElement exportButton = this.webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[1]/ul/li[3]/xpl-export-search-results/button"));
        WebElement exportButton = this.webDriver.findElement(By.tagName("xpl-export-search-results"));
        exportButton.click();

        // Get the download button and click
//        WebElement downloadButton = new WebDriverWait(this.webDriver, 10)
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[4]/div/xpl-root/div/xpl-search-results/main/div[1]/div[1]/ul/li[3]/xpl-export-search-results/ngb-tooltip-window/div[2]/ngb-tabset/div/div/div/div/form/div/button")));
        WebElement downloadButton = new WebDriverWait(this.webDriver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.className("stats-SearchResults_Download")));
        downloadButton.click();

        // Wait for download window to open
        new WebDriverWait(this.webDriver, 10).until((webDriver -> webDriver.getWindowHandles().size() == 2));

        // Wait for download window to close
        new WebDriverWait(this.webDriver, 30).until((webDriver -> webDriver.getWindowHandles().size() == 1));

        return this;
    }
}
