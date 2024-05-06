package helpdesk;

import core.BaseSeleniumTest;
import org.junit.jupiter.api.Test;
import read_properties.ConfigProvider;

public class HelpDeskTest extends BaseSeleniumTest {
    @Test
    public void checkTicket(){
        String title = "234234";
        String body = "43534";
        String email = "mefefef@bk.ru";
        MainPage mainPage = new MainPage();
        mainPage.createTicket(title, body, email)
                .openLoginPage()
                .auth(ConfigProvider.USER_LOGIN, ConfigProvider.USER_PASSWORD);


    }



}
