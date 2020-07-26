package practica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectYandex {
    private WebDriver chromeDriver;
    private List<WebElement> listResult;
    WebElement searchField;
    WebElement searchButton;

    public List<WebElement> getListResult() {
        return listResult;
    }

    PageObjectYandex(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"text\"]"));
        searchButton = chromeDriver.findElement(By.cssSelector(".mini-suggest__button"));

    }

    public void find(String wordFind) {
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
//        listResult = chromeDriver.findElements(By.xpath("//*[@class=\"organic__title-wrapper typo typo_text_l typo_line_m\"]"));
        listResult = chromeDriver.findElements(By.xpath("//li[@class=\"serp-item\"]//h2"));
    }

}
