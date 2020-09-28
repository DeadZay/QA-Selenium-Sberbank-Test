package ru.yandex.market.computers.notebooks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import ru.yandex.market.computers.notebooks.searchfilter.LimitPrice;
import ru.yandex.market.computers.notebooks.searchfilter.Manufacturer;
import ru.yandex.market.computers.notebooks.searchpager.ShowItemsOption;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class realize functionality of web page https://market.yandex.ru/catalog--noutbuki/
 */
public class YandexMarketNotebooksPage {

	private static final SelenideElement searchInput = $(By.id("header-search"));
	private static final SelenideElement searchButton = $(By.xpath(
			"//div[@data-apiary-widget-name = '@MarketNode/HeaderSearch']/form//button[@type = 'submit' and ./div = 'Найти']"));

	private static final String URL = "https://market.yandex.ru/catalog--noutbuki/";

	private final ElementsCollection ITEMS = $$(By.xpath("//*[@data-zone-name = 'title']/a"));

	public int getItemsCount() {
		return ITEMS.size();
	}

	/**
	 * A function that check is expected count of items
	 * equals current count items on page
	 *
	 * @param count of items on page
	 */
	@Step
	public void checkItemsCount(int count) {
		ITEMS.shouldHaveSize(count);
		assertEquals(getItemsCount(), count);
	}

	/**
	 * A function copy title name of item on page and return it
	 *
	 * @param index of item on page
	 * @return title name of item
	 */
	@Step
	public String getItemNameByIndex(int index) {
		assertTrue(isIndexInDisplayedItemsCount(index));
		//Screenshots.takeScreenShot($(By.xpath("//body"))).createNewFile();
		return ITEMS.get(index).text();
	}

	/**
	 * A function open yandex market in category 'notebooks' and return
	 * associated with that page class
	 *
	 * @return associated with page class to control the page
	 */
	public static YandexMarketNotebooksPage openYandexMarketNotebooksCatalog() {
		open(URL);
		return new YandexMarketNotebooksPage();
	}

	/**
	 *
	 * A function set search parameter minimum price
	 *
	 * @param minValue is a minimum price in search filter on the page
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage setMinimumOfPrice(int minValue) {
		LimitPrice.min.setValue(String.valueOf(minValue));
		return this;
	}

	/**
	 *
	 * A function set search parameter maximum price
	 *
	 * @param maxValue is a maximum price in search filter on the page
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage setMaximumOfPrice(int maxValue) {
		LimitPrice.max.setValue(String.valueOf(maxValue));
		return this;
	}

	/**
	 *
	 * A function set search parameter minimum and maximum price
	 *
	 * @param minValue is a minimum price in search filter on the page
	 * @param maxValue is a maximum price in search filter on the page
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage setLimitOfPrice(int minValue, int maxValue) {
		LimitPrice.min.setValue(String.valueOf(minValue));
		LimitPrice.max.setValue(String.valueOf(maxValue));
		return this;
	}

	/**
	 * A function choose concrete manufacturer given as argument
	 * in search filter on the page
	 *
	 * @param manufacturer to be set or unset in search filter on the page
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage chooseManufacturer(Manufacturer manufacturer) {
		manufacturer.set();
		return this;
	}

	/**
	 *
	 * A function choose some manufacturers given as argument
	 * in search filter on the page
	 *
	 * @param manufacturers to be set or unset in search filter on the page
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage chooseManufacturer(Manufacturer ...manufacturers) {
		for (Manufacturer manufacturer : manufacturers)
			manufacturer.set();
		return this;
	}

	private ShowItemsOption displayedItemsCount = ShowItemsOption.showFortyEight;

	/**
	 * A function set 12 items to be show on the page
	 *
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage showTwelveItems() {
		ShowItemsOption.showTwelve.select();
		checkItemsCount(12);
		displayedItemsCount = ShowItemsOption.showTwelve;
		return this;
	}

	/**
	 * A function set 48 items to be show on the page
	 *
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage showFortyEightItems() {
		ShowItemsOption.showFortyEight.select();
		checkItemsCount(48);
		displayedItemsCount = ShowItemsOption.showFortyEight;
		return this;
	}

	/**
	 * A function use search input in header of page to search items by name
	 *
	 * @param request to be search
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage search(@NotNull String request) {
		searchInput.shouldBe(Condition.visible).clear();
		searchInput.sendKeys(request);
		searchButton.click();
		SelenideWait wait = new SelenideWait(searchButton.getWrappedDriver(), 5000, 2500);
		wait.withTimeout(Duration.ofSeconds(5));
		return this;
	}

	/**
	 * A function check, that index contains in shown items on the page
	 *
	 * @param index to be checked
	 * @return true if index >= 0 and < displayedItemsCount else false
	 */
	private boolean isIndexInDisplayedItemsCount(int index) {
		return (index >= 0 && index < displayedItemsCount.getCount());
	}

	/**
	 * A function save title name of item by index, search it, and check item on position equals saved item
	 *
	 * @param index of item to be save and check after search
	 * @param position of item to be check for equal saved item
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage checkIndexItemDisplayedOnPositionAfterSearchIt(int index, int position) {
		assertTrue(isIndexInDisplayedItemsCount(index) && isIndexInDisplayedItemsCount(position));
		final String ITEM_NAME = getItemNameByIndex(index);
		assertEquals(ITEM_NAME, search(ITEM_NAME).getItemNameByIndex(position));
		return this;
	}
}
