package practica;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Tests extends WebDriverSettings {

    @Test
    public void testPOYandexResultMoreThanThree(){
        chromeDriver.get("https://www.yandex.ru/");
        PageObjectYandex pageObjectYandex = new PageObjectYandex(chromeDriver);
        pageObjectYandex.find("гладиолус");
        System.out.println(pageObjectYandex.getListResult().size());
        pageObjectYandex.getListResult().stream().forEach(x-> System.out.println(x.getText()));
        Assertions.assertTrue(
                pageObjectYandex.getListResult().size() > 3
                , "Список с результатом поиска имеет размер не более трёх элементов"
        );
    }

    @Test
    public void testPOYandexResultMoreThanThreeNegative(){
        chromeDriver.get("https://www.yandex.ru/");
        PageObjectYandex pageObjectYandex = new PageObjectYandex(chromeDriver);
        pageObjectYandex.find("");
        System.out.println(pageObjectYandex.getListResult().size());
        pageObjectYandex.getListResult().stream().forEach(x-> System.out.println(x.getText()));
        Assertions.assertFalse(
                pageObjectYandex.getListResult().size() > 3
                , "Список с результатом поиска имеет размер не должен быть более трёх элементов"
        );
    }

    @Test
    public void testPOYandexResultContains(){
        chromeDriver.get("https://www.yandex.ru");
        PageObjectYandex pageObject = new PageObjectYandex(chromeDriver);
        pageObject.find("гладиолус");
        System.out.println(pageObject.getListResult().size());
        Assertions.assertTrue(
                pageObject.getListResult().stream().anyMatch(x->x.getText().contains("Гладиолус - Википедия"))
                , "Заданный текст не найден"
        );
    }

    @Test
    public void testPOYandexResultContainsNegative(){
        chromeDriver.get("https://www.yandex.ru");
        PageObjectYandex pageObject = new PageObjectYandex(chromeDriver);
        pageObject.find("гладиолус");
        System.out.println(pageObject.getListResult().size());
        Assertions.assertFalse(
                pageObject.getListResult().stream().anyMatch(x->x.getText().contains("Гладиолус - Википедия"))
                , "Заданный текст не должен быть в результате поиска"
        );
    }

    @Test
    public void testPFYandexResultMoreThanThree(){
        chromeDriver.get("https://yandex.ru");
        PageFactoryYandex pageFactory = PageFactory.initElements(chromeDriver,PageFactoryYandex.class);
        pageFactory.find("гладиолус");
        System.out.println(pageFactory.getListResult().size());
        Assertions.assertTrue(
                pageFactory.getListResult().size() > 3
                , "Список с результатом поиска имеет размер не более трёх элементов"
        );
    }

    @Test
    public void testPFYandexResultMoreThanThreeNegative(){
        chromeDriver.get("https://yandex.ru");
        PageFactoryYandex pageFactory = PageFactory.initElements(chromeDriver,PageFactoryYandex.class);
        pageFactory.find("");
        System.out.println(pageFactory.getListResult().size());
        Assertions.assertFalse(
                pageFactory.getListResult().size() > 3
                , "Список с результатом поиска имеет размер не должен быть более трёх элементов"
        );
    }

    @Test
    public void testPFYandexResultContains(){
        chromeDriver.get("https://yandex.ru");
        PageFactoryYandex pageFactory = PageFactory.initElements(chromeDriver,PageFactoryYandex.class);
        pageFactory.find("гладиолус");
        Assertions.assertTrue(
                pageFactory.getListResult().stream().anyMatch(x->x.getText().contains("Гладиолус - Википедия"))
                , "Заданный текст не найден"
        );
    }

    @Test
    public void testPFYandexResultContainsNegative(){
        chromeDriver.get("https://yandex.ru");
        PageFactoryYandex pageFactory = PageFactory.initElements(chromeDriver,PageFactoryYandex.class);
        pageFactory.find("гладиолус");
        Assertions.assertFalse(
                pageFactory.getListResult().stream().anyMatch(x->x.getText().contains("Гладиолус - Википедия"))
                , "Заданный текст не должен быть найден в списке результата поиска"
        );
    }
}
