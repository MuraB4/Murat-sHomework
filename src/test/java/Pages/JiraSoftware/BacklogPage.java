package Pages.JiraSoftware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BacklogPage {

    public BacklogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//span[@class='aui-icon aui-icon-large agile-icon-plan aui-iconfont-backlog']")
    public WebElement backLogButton;

    @FindBy(xpath = "//span[contains(text(),'Story test')]")
    public List<WebElement> stories;

    @FindBy(xpath = "//span[contains(text(),'Bug test')]")
    public List<WebElement> bugs;
}
