package ru.bellintegrator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Tests extends WebDriverSettings {

    @Test
    public void firstTest(){

    }

    @Test
    public void checkTitle(){
        chromeDriver.get("http://www.bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"),"Тайтл не корректен");
    }

    @Test
    public void secondTest(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
        WebElement searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"button-search\"]"));

        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();

        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"));

        System.out.println(news.size());

        news.stream().forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(
                news.stream().anyMatch(x->x.getText().contains("Кирилл Филенков"))
                , "Заданные новости не найдены"
        );
    }

    @Test
    public void testPO(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        PageObjectBell bellPO = new PageObjectBell(chromeDriver);
        bellPO.find("RPA");
        Assertions.assertTrue(
                bellPO.getNews().stream().anyMatch(x->x.getText().contains("Кирилл Филенков"))
                , "Заданные новости не найдены"
        );
    }

    @Test
    public void testPF(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        PageFactoryBell bellPF = PageFactory.initElements(chromeDriver,PageFactoryBell.class);
        bellPF.find("RPA");
        Assertions.assertTrue(
                bellPF.getNews().stream().anyMatch(x->x.getText().contains("Кирилл Филенков"))
                , "Заданные новости не найдены"
        );
    }

    @Test
    public void testPFnEGA(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        PageFactoryBell bellPF = PageFactory.initElements(chromeDriver,PageFactoryBell.class);
        bellPF.find("RPA");
        Assertions.assertFalse(
                bellPF.getNews().stream().anyMatch(x->x.getText().contains("fdhssfdghsfghjsfghfg"))
                , "Заданные новости не найдены"
        );
    }

}
