package ru.yandex.market.computers.notebooks;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import ru.yandex.market.computers.notebooks.headersearch.PageSearch;
import ru.yandex.market.computers.notebooks.searchfilter.LimitPrice;
import ru.yandex.market.computers.notebooks.searchfilter.Manufacturer;
import ru.yandex.market.computers.notebooks.searchpager.ShowItemsOption;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexMarketNotebooksPage {

	private static final String URL = "https://market.yandex.ru/catalog--noutbuki/";

	private final ElementsCollection ITEMS = $$(By.xpath("//*[@data-zone-name = 'title']/a"));

	public int getItemsCount() {
		return ITEMS.size();
	}

	@Step
	public void checkItemsCount(int count) {
		ITEMS.shouldHaveSize(count);
		assertEquals(getItemsCount(), count);
	}

	@Step
	public String getItemNameByIndex(int index) {
		assertTrue(isIndexInDisplayedItemsCount(index));
		//Screenshots.takeScreenShot($(By.xpath("//body"))).createNewFile();
		return ITEMS.get(index).text();
	}

	public static YandexMarketNotebooksPage openYandexMarketNotebooksCatalog() {
		open(URL);
		return new YandexMarketNotebooksPage();
	}

	@Step
	public YandexMarketNotebooksPage setMinimumOfPrice(int minValue) {
		LimitPrice.min.setValue(String.valueOf(minValue));
		return this;
	}

	@Step
	public YandexMarketNotebooksPage setMaximoumOfPrice(int maxValue) {
		LimitPrice.max.setValue(String.valueOf(maxValue));
		return this;
	}

	@Step
	public YandexMarketNotebooksPage setLimitOfPrice(int minValue, int maxValue) {
		LimitPrice.min.setValue(String.valueOf(minValue));
		LimitPrice.max.setValue(String.valueOf(maxValue));
		return this;
	}

	@Step
	public YandexMarketNotebooksPage chooseManufacturer(Manufacturer manufacturer) {
		manufacturer.set();
		return this;
	}

	@Step
	public YandexMarketNotebooksPage chooseManufacturer(Manufacturer ...manufacturers) {
		for (Manufacturer manufacturer : manufacturers)
			manufacturer.set();
		return this;
	}

	private ShowItemsOption displayedItemsCount = ShowItemsOption.showFortyEight;

	@Step
	public YandexMarketNotebooksPage showTwelveItems() {
		ShowItemsOption.showTwelve.select();
		checkItemsCount(12);
		displayedItemsCount = ShowItemsOption.showTwelve;
		return this;
	}

	@Step
	public YandexMarketNotebooksPage showFortyEightItems() {
		ShowItemsOption.showFortyEight.select();
		checkItemsCount(48);
		displayedItemsCount = ShowItemsOption.showFortyEight;
		return this;
	}

	@Step
	public YandexMarketNotebooksPage search(@NotNull String request) {
		PageSearch.request(request);
		return this;
	}

	private boolean isIndexInDisplayedItemsCount(int index) {
		return (index >= 0 && index < displayedItemsCount.getCount());
	}

	@Step
	public YandexMarketNotebooksPage checkIndexItemDisplayedOnPositionAfterSearchIt(int index, int position) {
		assertTrue(isIndexInDisplayedItemsCount(index) && isIndexInDisplayedItemsCount(position));
		final String ITEM_NAME = getItemNameByIndex(index);
		assertEquals(ITEM_NAME, search(ITEM_NAME).getItemNameByIndex(position));
		return this;
	}
}
