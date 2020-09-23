import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexRuWebPage extends WebPage {
	private final WebElement yandexMarketLink;

	public YandexRuWebPage(WebDriver webDriver) {
		super(webDriver);
		yandexMarketLink = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("yandexMarketLink")));
	}

	@Step
	public MarketYandexRuWebPage clickOnYandexMarketLink() {
		yandexMarketLink.click();
		return new MarketYandexRuWebPage(webDriver);
	}
}
