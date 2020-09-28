package qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.HeaderServiceLink;
import ru.yandex.market.computers.navigationtree.NavTreeSubLink;
import ru.yandex.market.computers.notebooks.YandexMarketNotebooksPage;
import ru.yandex.market.computers.notebooks.headersearch.PageSearch;
import ru.yandex.market.computers.notebooks.searchfilter.LimitPrice;
import ru.yandex.market.computers.notebooks.searchfilter.Manufacturer;
import ru.yandex.market.computers.notebooks.searchpager.ShowItemsOption;
import ru.yandex.market.headerstab.TabLink;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class SberTest {

	@BeforeEach
	void beforeEach() {
		Configuration.startMaximized = true;
		open("https://yandex.ru");
	}

	@Test
	void skeletonTest() {
		$(By.xpath("//a[@data-id = 'market']")).click();
		switchTo().window(1);
		$(By.xpath("//div[@data-zone-name = 'category-link']//a[./span = 'Компьютеры']")).click();
		$(By.xpath("//ul[@data-autotest-id = 'subItems']//a[.='Ноутбуки']")).click();
		$(By.xpath("//fieldset[./legend = 'Цена, ₽']//*[./label[@aria-label = 'Цена от']]//input[@type = 'text']"))
				.setValue("10000");
		$(By.xpath("//fieldset[./legend = 'Цена, ₽']//*[./label[@aria-label = 'Цена до']]//input[@type = 'text']"))
				.setValue("30000");
		$(By.xpath("//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель HP']]//div[./span = 'HP']"))
				.click();
		$(By.xpath("//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель Lenovo']]//div[./span = 'Lenovo']"))
				.click();
		$(By.xpath("//div[./button = 'Показать ещё']/div[./button[@aria-expanded = 'false']]/button")).click();
		$(By.xpath("//div[./button = 'Показать ещё']//div[//button[@aria-expanded]]//div[@aria-hidden = 'false']//button[.='Показывать по 12']")).click();
		$$(By.xpath("//article")).shouldHaveSize(12);
		final String FIRST_ARTICLE_TITLE = $(By.xpath("//*[@data-zone-name = 'title']/a")).getAttribute("title");
		$("#header-search").sendKeys(FIRST_ARTICLE_TITLE);
		$(By.xpath("//form[//input[@id = 'header-search']]//button")).click();
		assertEquals(FIRST_ARTICLE_TITLE, $(By.xpath("//*[@data-zone-name = 'title']/a")).text(),
				"First article not equal first article before search");
	}

	@Test
	void basicTest() throws IOException {
		HeaderServiceLink.market.click();
		switchTo().window(1);
		TabLink.computers.click();
		NavTreeSubLink.notebook.click();
		LimitPrice.min.setValue("10000");
		LimitPrice.max.setValue("30000");
		Manufacturer.HP.set();
		Manufacturer.Lenovo.set();
		ShowItemsOption.showTwelve.select();
		YandexMarketNotebooksPage.checkItemsCount(12);
		final String ITEM_NAME = YandexMarketNotebooksPage.getItemName(0);
		PageSearch.request(ITEM_NAME);
		assertEquals(ITEM_NAME, YandexMarketNotebooksPage.getItemName(0),
				"Item name not equal item name before search");
	}

	@AfterEach
	void afterEach() {
		closeWebDriver();
	}
}
