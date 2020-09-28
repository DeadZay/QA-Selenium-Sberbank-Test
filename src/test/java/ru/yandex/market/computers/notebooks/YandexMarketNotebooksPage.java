package ru.yandex.market.computers.notebooks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexMarketNotebooksPage {

	private static final ElementsCollection ITEMS = $$(By.xpath("//*[@data-zone-name = 'title']/a"));

	public static int getItemsCount() {
		return ITEMS.size();
	}

	public static void checkItemsCount(int count) {
		ITEMS.shouldHaveSize(count);
		assertEquals(getItemsCount(), count);
	}

	public static String getItemName(int index) throws IOException {
		assertTrue(index >= 0 && index <= getItemsCount());
		//Screenshots.takeScreenShot($(By.xpath("//body"))).createNewFile();
		return ITEMS.get(index).text();
	}
}
