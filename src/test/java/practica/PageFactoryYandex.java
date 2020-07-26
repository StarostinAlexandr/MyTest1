package practica;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactoryYandex {
    private WebDriver chromeDriver;

    @FindBy(how = How.ID, id="text")
    WebElement searchField;

    @FindBy(how= How.CSS, css=".mini-suggest__button")
    WebElement searchButton;

    @FindAll(@FindBy(how = How.XPATH, using="//li[@class=\"serp-item\"]//h2"))
    List<WebElement> listResult;

    public List<WebElement> getListResult() {
        return listResult;
    }

    public PageFactoryYandex(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }

    public void find(String wordFind){
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
    }


}
