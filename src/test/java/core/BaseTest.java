package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract public class BaseTest {

//    /**
//     * инициализация selenide с настройками
//     */
    public void setUp(){

        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 200000;
    }

//    /**
//     * выполнение метода перед каждым запуском тестов
//     */
    @BeforeEach
    public void init(){
        setUp();
    }

//    /**
//     * выполнение метода после каждого закрытия тестов
//     */
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
