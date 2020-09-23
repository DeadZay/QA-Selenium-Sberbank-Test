import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MarketYandexRuWebPage extends WebPage {
	private final WebElement computerCategoryLink;

	public MarketYandexRuWebPage(WebDriver webDriver) {
		super(webDriver);
		computerCategoryLink = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("computerCategoryLink")));
	}

	@Step
	public MarketYandexRuComputersWebPage clickOnComputerCategory() {
		computerCategoryLink.click();
		return new MarketYandexRuComputersWebPage(webDriver);
	}
}
