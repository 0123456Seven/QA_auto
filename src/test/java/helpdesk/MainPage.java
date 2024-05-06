package helpdesk;

import core.BaseSeleniumPage;
import core.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import read_properties.ConfigProvider;

public class MainPage extends BaseSeleniumPage {
//    private final By queueList = By.id("id_queue");//получение локатора по айди
//    //private final By queueList2 = By.xpath("//select[@id='id_queue']");//получение локатора через xpath
//    private WebElement queueList3 = driver.findElement(queueList);


    @FindBy(xpath = "//select[@id = 'id_queue']")
    private WebElement queueList;
    @FindBy(xpath = "//select[@id = 'id_queue']//option[@value = '1']")
    private WebElement queueValue;
    @FindBy(id="id_title")
    private WebElement title;
    @FindBy(id="id_body")
    private WebElement body;
    @FindBy(id="id_priority")
    private WebElement priorityList;
    @FindBy(xpath = "//select[@id = 'id_priority']//option[@value = '1']")
    private WebElement priorityValue;
    @FindBy(id = "id_due_date")
    private WebElement dateField;
    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']//a[text()='6']")
    private WebElement dateValue;
    @FindBy(id = "id_submitter_email")
    private WebElement email;
    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg btn-block']")
    private WebElement submitButton;
    @FindBy(id = "userDropDown")
    private WebElement logInButton;

    public MainPage(){
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }
    public MainPage createTicket(String titleValue, String bodyValue, String emailValue){
        queueList.click();
        queueValue.click();
        title.sendKeys(titleValue);
        body.sendKeys(bodyValue);
        dateField.click();
        dateValue.click();
        email.sendKeys(emailValue);
        submitButton.click();
        return this;
    }
    public LoginPage openLoginPage(){
        logInButton.click();
        return new LoginPage();
    }

}
