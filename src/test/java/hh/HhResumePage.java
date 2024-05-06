package hh;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

public class HhResumePage {
    private final static String URL = "https://ufa.hh.ru/resume/037ccbafff0c0a61e00039ed1f314a32647942";

    @Test
    public void checkAttributesHashMap(){
        Selenide.open(URL);
    }
}
