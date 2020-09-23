import io.qameta.allure.Step;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MarketYandexRuNotebooksWebPage extends WebPage {
	private final WebElement minPriceInputText,
			maxPriceInputText,
			manufacturerHpCheckbox,
			manufacturerLenovoCheckbox,
			manufacturerShowAllButton,
			manufacturerSearchInputText,
			selectorOfDisplayedProductsOnPageButton,
			displayTwelveProductsOnPageButton,
			firstProductTitleOnPage,
			searchInputText,
			searchButtonSubmit;

	public MarketYandexRuNotebooksWebPage(WebDriver webDriver) {
		super(webDriver);
		minPriceInputText = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("minPriceInputText")));
		maxPriceInputText = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("maxPriceInputText")));
		manufacturerHpCheckbox = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("manufacturerHpCheckbox")));
		manufacturerLenovoCheckbox = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("manufacturerLenovoCheckbox")));
		manufacturerShowAllButton = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("manufacturerShowAllButton")));
		manufacturerSearchInputText = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("manufacturerSearchInputText")));
		selectorOfDisplayedProductsOnPageButton = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("selectorOfDisplayedProductsOnPageButton")));
		displayTwelveProductsOnPageButton = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("displayTwelveProductsOnPageButton")));
		firstProductTitleOnPage = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("firstProductTitleOnPage")));
		searchInputText = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("searchInputText")));
		searchButtonSubmit = webDriver.findElement(
				By.xpath(XpathProperties.getProperty("searchButtonSubmit")));
	}

	@Step
	@ParameterizedTest
	@ValueSource(ints = {10000, 30000})
	public MarketYandexRuNotebooksWebPage setLimitOfPrice(int minPrice, int maxPrice)
	{
		if (minPrice == 0)
			minPriceInputText.clear();
		else
			minPriceInputText.sendKeys(Integer.toString(minPrice));
		if (maxPrice == 0)
			maxPriceInputText.clear();
		else
			maxPriceInputText.sendKeys(Integer.toString(maxPrice));
		return this;
	}
}
