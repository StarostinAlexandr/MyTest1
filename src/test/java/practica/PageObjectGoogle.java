package practica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectGoogle {
    private WebDriver chromeDriver;
    WebElement searchField;
    WebElement searchButton;
    List<WebElement> listResult;

    public List<WebElement> getListResult() {
//        return chromeDriver.findElements(By.xpath("//*[@id=\"res\"]//*[@class=\"g\"]"));
        return listResult;

//        return chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"));
    }

    PageObjectGoogle(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
//        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
//        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"button-search\"]"));
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
//        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/button/div/span/svg"));
//        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[1]/div/span/svg"));
//        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));

    }

    public void find(String wordFind) {
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
        listResult = chromeDriver.findElements(By.xpath("//*[@id=\"res\"]//*[@class=\"g\"]"));
    }

}
