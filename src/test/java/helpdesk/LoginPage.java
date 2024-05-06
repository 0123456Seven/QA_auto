package helpdesk;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import read_properties.ConfigProvider;

public class LoginPage extends BaseSeleniumPage {
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(xpath = "//input[@class='btn btn-lg btn-primary btn-block']")
    private WebElement loginButton;
    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
    public TicketsPage auth(String usernameValue, String passwordValue){
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue);
        loginButton.click();
        return new TicketsPage();
    }
}
