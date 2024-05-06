package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import hh.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends BaseTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";
    @Test
    public void openAllHref(){
        Selenide.open(URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        //способы получения ссылок href из коллекции элементов
        //1
//        for(int i = 0;i<hrefs.size();i++){
//            links.add(hrefs.get(i).getAttribute("href"));
//        }

        //2
//        for(SelenideElement element : hrefs){
//            links.add(element.getAttribute("href"));
//        }
//        int i = 0;

        //3


        hrefs.asDynamicIterable().stream().forEach(element -> links.add(element.getAttribute("href")));


        //Способы перебора значения из полученных ссылок
        //1
//        links.forEach(Selenide::open);
        //2
//        for(int i = 0 ;i<links.size();i++){
//            String linksUrl = links.get(i);
//            Selenide.open(linksUrl);
//            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
//            Assert.assertEquals(currentUrl, linksUrl);
//        }
//        Random random = new Random();
//        while(links.size()>0){
//            int randomNumber = random.nextInt(links.size());
//            Selenide.open(links.get(randomNumber));
//            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
//
//        }

        List<Integer> linksLenght = hrefs.asDynamicIterable().stream().map(element->element.getAttribute("href").length()).collect(Collectors.toList());


    }
}
