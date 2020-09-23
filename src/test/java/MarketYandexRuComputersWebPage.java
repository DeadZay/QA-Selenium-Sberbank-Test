import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MarketYandexRuComputersWebPage extends WebPage {
	private final WebElement notebookSubcategoryLink;

	public MarketYandexRuComputersWebPage(WebDriver webDriver) {
		super(webDriver);
		notebookSubcategoryLink = webDriver.findElement(By.xpath(
				XpathProperties.getProperty("notebookSubcategoryLink")));
	}

	@Step
	public MarketYandexRuNotebooksWebPage clickOnSubcategoryNotebooks() {
		notebookSubcategoryLink.click();
		return new MarketYandexRuNotebooksWebPage(webDriver);
	}
}
