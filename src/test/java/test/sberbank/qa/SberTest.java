package test.sberbank.qa;

import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SberTest {
	public static WebDriver webDriver;
	private final DisplayedItemsOption displayedItemsOption = DisplayedItemsOption.showTwelve;

	@BeforeAll
	static void beforeAll() {
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	void sberTest() {
				MarketYandexRuSubcategoryNotebookPage page =
				openYandexRu()
						.clickOnMarketYandexLink()
						.clickOnCategoryComputers()
						.clickOnSubcategoryNotebook()
						.setLimitOfPrice(10000, 30000)
						.chooseManufacturer("HP")
						.chooseManufacturer(NotebookManufacturer.Lenovo)
						.setDisplayedItemsOnPage(displayedItemsOption)
						.checkDisplayedItemsOnPage();
		final String itemTitle = page.getItemTitleByIndex(1);
		page.searchByItemTitle(itemTitle)
				.checkIndexItemTitleEqualsText(1, itemTitle);
	}

	@Test
	public YandexRuPage openYandexRu() {
		webDriver.get("http://yandex.ru");
		return new YandexRuPage();
	}

	@AfterAll
	static void afterAll() {
		webDriver.quit();
	}

	private class YandexRuPage extends WebPage {

		@FindBy(xpath = "//a[@data-id = 'market' and contains(@class,'home-link') and ./div[contains(@class,'item-title')] = 'Маркет']")
		private WebElement marketYandexRuLink;

		public YandexRuPage() {
			super(SberTest.webDriver);
		}

		@Test
		public MarketYandexRuPage clickOnMarketYandexLink() {
			final String originalWindow = webDriver.getWindowHandle();
			marketYandexRuLink.click();
			for (String windowHandle : webDriver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					webDriver.switchTo().window(windowHandle);
					break;
				}
			}
			return new MarketYandexRuPage();
		}
	}

	private class MarketYandexRuPage extends WebPage {

		@FindBy(xpath = "//a[contains(@href, '/catalog--kompiuternaia-tekhnika') or contains(./*, 'Компьютеры')]")
		private WebElement CategoryComputerLink;

		public MarketYandexRuPage() {
			super(SberTest.webDriver);
		}

		@Test
		public MarketYandexRuCategoryComputerPage clickOnCategoryComputers() {
			CategoryComputerLink.click();
			return new MarketYandexRuCategoryComputerPage();
		}

	}

	private class MarketYandexRuCategoryComputerPage extends WebPage {

		@FindBy(xpath = "//a[contains(@href, 'https://market.yandex.page.ru/catalog--noutbuki') or contains(./*, 'Ноутбуки')]")
		private WebElement notebookCategoryLink;

		public MarketYandexRuCategoryComputerPage() {
			super(SberTest.webDriver);
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage clickOnSubcategoryNotebook() {
			notebookCategoryLink.click();
			return new MarketYandexRuSubcategoryNotebookPage();
		}

	}

	private class MarketYandexRuSubcategoryNotebookPage extends WebPage {

		@FindBy(xpath = "//*[contains(./*, 'Цена, ₽')]//input[../*[contains(@aria-label, 'Цена от')] = 'от' or (@type = 'text' and contains(@name, 'Цена от'))]")
		private WebElement minPriceInput;

		@FindBy(xpath = "//*[contains(./*, 'Цена, ₽')]//input[../*[contains(@aria-label, 'Цена до')] = 'до' or (@type = 'text' and contains(@name, 'Цена до'))]")
		private WebElement maxPriceInput;

		@FindBy(xpath = "//button[.= 'Показать всё' and ancestor::fieldset/legend = 'Производитель']")
		private WebElement showAllManufacturersButton;

		@FindBy(xpath = "//input[@type = 'text' and @name = 'Поле поиска']")
		private WebElement manufacturerSearchInput;

		@FindBy(xpath = "//div[./button = 'Показать ещё']/div[./button[@aria-expanded = 'false']]/button")
		private WebElement selectorOfDisplayedItems;

		@FindBy(id = "header-search")
		private WebElement searchItemInput;

		@FindBy(xpath = "//form[@action = '/search' and @role = 'search' and @aria-label = 'Искать']//button[@type = 'submit' and ./*[contains(., 'Найти')]]")
		private WebElement searchItemButton;

		public MarketYandexRuSubcategoryNotebookPage() {
			super(SberTest.webDriver);
		}

		private void setInputValue(WebElement input, int value) {
			if (!input.isSelected())
				input.click();
			if (value <= 0)
				input.clear();
			else
				input.sendKeys(String.valueOf(value));
		}

		private void setInputValue(WebElement input, String value) {
			if (!input.isSelected())
				input.click();
			if (value == null)
				input.clear();
			else
				input.sendKeys(value);
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage setLimitOfPrice(int minPrice, int maxPrice) {
			if (!minPriceInput.isDisplayed())
				webDriver.manage().window().setPosition(minPriceInput.getLocation());
			setInputValue(minPriceInput, minPrice);
			setInputValue(maxPriceInput, maxPrice);
			return this;
		}

		private void findAndChooseManufacturer(NotebookManufacturer manufacturer) {
			try {
				if (!manufacturerSearchInput.isDisplayed())
					webDriver.manage().window().setPosition(manufacturerSearchInput.getLocation());
			} catch (NoSuchElementException exception) {
				webDriver.manage().window().setPosition(showAllManufacturersButton.getLocation());
				showAllManufacturersButton.click();
				exception.addSuppressed(exception);
			}
			webDriver.manage().window().maximize();
			setInputValue(manufacturerSearchInput, null);
			setInputValue(manufacturerSearchInput, manufacturer.toString());
			WebElement checkbox = webDriver.findElement(By.xpath(String.format(
					"//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель %s']]//div[./span]",
					manufacturer)));
			webDriver.manage().window().setPosition(checkbox.getLocation());
			checkbox.click();
			webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage chooseManufacturer(NotebookManufacturer manufacturer) {
			findAndChooseManufacturer(manufacturer);
			return this;
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage chooseManufacturer(@NotNull String manufacturer) {
			try {
				findAndChooseManufacturer(NotebookManufacturer.valueOf(manufacturer));
			} catch (IllegalArgumentException exception) {
				exception.addSuppressed(exception);
				return this;
			}
			return this;
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage setDisplayedItemsOnPage(DisplayedItemsOption option) {
			webDriver.manage().window().maximize();
			Actions action = new Actions(webDriver);
			JavascriptExecutor executor = (JavascriptExecutor)webDriver;
			executor.executeScript("arguments[0].click()", selectorOfDisplayedItems);
			/*action.moveToElement(selectorOfDisplayedItems).click().build().perform();
			*//*if (!selectorOfDisplayedItems.isDisplayed())
				webDriver.manage().window().setPosition(selectorOfDisplayedItems.getLocation());
			selectorOfDisplayedItems.click();*/
			/*webDriver.findElement(By.xpath(String.format(
					"//button[contains(./*,'Показывать по')]/..//button[.='%s']",
					DISPLAYED_ITEMS_OPTION_STRING[option.ordinal()]))).click();*/
			action.moveToElement(webDriver.findElement(By.xpath(String.format(
					"//div[./button = 'Показать ещё']//div[//button[@aria-expanded]]//div[@aria-hidden = 'false']//button[.='%s']",
					option.toString())))).build().perform();
			webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			return this;
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage checkDisplayedItemsOnPage() {
			assert webDriver.findElements(By.xpath("//article")).toArray().length
					!= (displayedItemsOption == DisplayedItemsOption.showTwelve ? 12 : 48);
			return this;
		}

		public WebElement getItemByIndex(int index) {
			return webDriver.findElement(By.xpath(String.format("//article[%d]", index)));
		}

		@Test
		public String getItemTitleByIndex(int index) {
			assert index >= 0;
			return getItemByIndex(index).findElement(By.xpath(
					("//a[../*[@data-zone-name = 'title']]")))
					.getAttribute("title");
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage searchByItemTitle(@NotNull String itemTitle) {
			if (!searchItemInput.isDisplayed())
				webDriver.manage().window().setPosition(searchItemInput.getLocation());
			if (!searchItemInput.isSelected())
				searchItemInput.click();
			searchItemInput.sendKeys(itemTitle);
			searchItemButton.click();
			return this;
		}

		@Test
		public MarketYandexRuSubcategoryNotebookPage checkIndexItemTitleEqualsText(int index, @NotNull String text) {
			assert index >= 0;
			assert getItemTitleByIndex(index).contains(text);
			return this;
		}
	}
}
