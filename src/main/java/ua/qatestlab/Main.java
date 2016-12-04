package ua.qatestlab;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by Микитенко on 03.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        String fs = System.getProperty("file.separator");
        String query = "automation";
        String geckoDriverPath = System.getProperty("user.dir") +
                fs + "drivers" + fs + "geckodriver.exe";

        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        WebDriver firefoxDriver = new FirefoxDriver();

        firefoxDriver.get("https://www.bing.com");
        WebElement queryInput = firefoxDriver.findElement(By.id("sb_form_q"));
        queryInput.sendKeys(query);
        queryInput.submit();

        WebDriverWait wait = new WebDriverWait(firefoxDriver, 10);
        WebElement resultList = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("b_results")));

        WebElement searchResultsTitle = firefoxDriver.findElement(By.tagName("title"));
        System.out.println(searchResultsTitle.getText());

        List<WebElement> searchResults = resultList.findElements(By.className("b_algo"));
        searchResults.forEach(sr -> System.out.println(sr.findElement(By.tagName("h2")).getText()));

        firefoxDriver.quit();
    }
}
