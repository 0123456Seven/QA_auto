package read_properties;

import hh.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReadPropertiesTest extends BaseTest {
    @Test
    public void readPropertiesTest() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("URL");
        System.out.println(urlFromProperty);
    }

    public void readFromConf(){

    }
}
