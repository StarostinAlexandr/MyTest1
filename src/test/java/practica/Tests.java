package practica;

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

    @Test
    public void gladiolusGoogle(){
//        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        chromeDriver.get("https://www.google.ru/");
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
        WebElement searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
        searchField.click();
        searchField.sendKeys("Гладиолус");
        searchButton.click();

//        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"));
        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@id=\"res\"]//*[@class=\"g\"]"));

        System.out.println(news.size());

        news.stream().forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(
                news.stream().anyMatch(x->x.getText().contains("Шпажник — Википедия"))
                , "Заданные новости не найдены"
        );
    }

    @Test
    public void testPOGoogleResultMoreThanThree(){
        chromeDriver.get("https://www.google.ru/");
//        PageObjectBell bellPO = new PageObjectBell(chromeDriver);
        PageObjectGoogle pageObjectGoogle = new PageObjectGoogle(chromeDriver);
        pageObjectGoogle.find("гладиолус");
        System.out.println(pageObjectGoogle.getListResult().size());
        pageObjectGoogle.getListResult().stream().forEach(x-> System.out.println(x.getText()));
        Assertions.assertTrue(
                pageObjectGoogle.getListResult().size() > 3
                , "Список с результатом поиска по слову гладиолус имеет размер не больше двух"
        );

    }

    @Test
    public void testPOGoogleResultContainsFrase(){
        chromeDriver.get("https://www.google.ru");
//        PageObjectBell bellPO = new PageObjectBell(chromeDriver);
        PageObjectGoogle pageObjectGoogle = new PageObjectGoogle(chromeDriver);
        pageObjectGoogle.find("гладиолус");
        System.out.println(pageObjectGoogle.getListResult().size());
        Assertions.assertTrue(
                pageObjectGoogle.getListResult().stream().anyMatch(x->x.getText().contains("Шпажник — Википедия"))
                , "Заданные фраза не найдена"
        );

    }

    @Test
    public void gladiolusYandex(){
//        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        chromeDriver.get("https://www.yandex.ru/");
//        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@class=\"input__control input__input mini-suggest__input\"]"));
//        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@class=\"input__ahead\"]//*[@id=\"text\"]"));
//        WebElement searchField = chromeDriver.findElement(By.name("text"));
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"text\"]"));
        searchField.click();
        searchField.sendKeys("Гладиолус");
//        WebElement searchButton = chromeDriver.findElement(By.xpath("/html/body/header/div/div/div[3]/form/div[2]/button/div"));
        WebElement searchButton = chromeDriver.findElement(By.cssSelector(".mini-suggest__button"));
        System.out.println(searchButton.getText());

        searchButton.click();

//        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"));
//        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@id=\"res\"]//*[@class=\"g\"]"));
//        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@class=\"organic typo typo_text_m typo_line_s i-bem\"]"));
        List<WebElement> news = chromeDriver.findElements(By.xpath("//*[@class=\"organic__title-wrapper typo typo_text_l typo_line_m\"]"));

        System.out.println(news.size());

        news.stream().forEach(x-> System.out.println(x.getText()));

        Assertions.assertTrue(
                news.stream().anyMatch(x->x.getText().contains("Шпажник — Википедия"))
                , "Заданная фраза не найдена"
        );
    }

    @Test
    public void testPOYandexResultMoreThanThree(){
        chromeDriver.get("https://www.yandex.ru/");
        PageObjectYandex pageObjectYandex = new PageObjectYandex(chromeDriver);
        pageObjectYandex.find("гладиолус");
        System.out.println(pageObjectYandex.getListResult().size());
        pageObjectYandex.getListResult().stream().forEach(x-> System.out.println(x.getText()));
        Assertions.assertTrue(
                pageObjectYandex.getListResult().size() > 3
                , "Список с результатом поиска по слову гладиолус имеет размер не более трёх элементов"
        );

    }

    @Test
    public void testPOYandexResultContainsFrase(){
        chromeDriver.get("https://www.yandex.ru");
//        PageObjectBell bellPO = new PageObjectBell(chromeDriver);
        PageObjectYandex pageObject = new PageObjectYandex(chromeDriver);
        pageObject.find("гладиолус");
        System.out.println(pageObject.getListResult().size());
        Assertions.assertTrue(
                pageObject.getListResult().stream().anyMatch(x->x.getText().contains("Шпажник — Википедия"))
                , "Заданная фраза не найдена"
        );

    }
}
