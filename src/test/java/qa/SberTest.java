package qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import ru.yandex.HeaderServiceLink;
import ru.yandex.market.computers.navigationtree.NavTreeLink;
import ru.yandex.market.computers.navigationtree.NavTreePcSubLink;
import ru.yandex.market.computers.notebooks.YandexMarketNotebooksPage;
import ru.yandex.market.computers.notebooks.searchfilter.LimitPrice;
import ru.yandex.market.computers.notebooks.searchpager.ShowItemsOption;
import ru.yandex.market.headerstab.TabLink;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.YandexRuPage.openYandexRuPage;
import static ru.yandex.market.computers.notebooks.searchfilter.Manufacturer.*;

/**
 * A class that realize functionality, needed by Sberbank HR, to give me offer
 *
 * @author fcodi (dmitriy.maksimov@yahoo.com)
 */
public class SberTest {

	public static int window = 0;

	/**
	 * beforeEach() set parameters before start every single test
	 */
	@BeforeEach
	void beforeEach() {
		Configuration.startMaximized = true;
	}

	/**
	 * a function that test service links on page yandex.ru in header on the top of search field
	 *
	 * @param link - is a HeaderServiceLink that contain link to Yandex service;
	 */
	@ParameterizedTest
	@EnumSource(HeaderServiceLink.class)
	void clickOnHeaderServiceAndCheckUrl(HeaderServiceLink link) {
		open("https://yandex.ru");
		link.click();
	}

	/**
	 * @param name - is a String representation (title of link) of service link
	 *             on page yandex.ru in header on the top of search field
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"market", "Авто.ру", "tv", "Картинки"})
	void getHeaderServiceLinkByNameAndClick(String name) {
		open("https://yandex.ru");
		HeaderServiceLink.valueOf(name).click();
	}

	/**
	 *
	 * a function that test category links on header before market search field
	 *
	 * @param link - is link to category page of market.yandex.ru
	 */
	@ParameterizedTest
	@EnumSource(TabLink.class)
	private void clickTabLink(TabLink link) {
		open("https://market.yandex.ru");
		link.click();
	}

	/**
	 *
	 * a function that test category links in NavigationTree on the left side
	 *
	 * @param link - is link in @MarketNode/NavigationTree to category items
	 */
	@ParameterizedTest
	@EnumSource(NavTreeLink.class)
	void clickOnNavTreeLink(NavTreeLink link) {
		open("https://market.yandex.ru/catalog--kompiuternaia-tekhnika/54425");
		link.click();
	}

	/**
	 *
	 * a function that test subcategory links in NavigationTree on the left side
	 * inside selected category (current links - in PC category)
	 *
	 * @param link - is link in @MarketNode/NavigationTree to subcategory items
	 *             inside category items.
	 */
	@ParameterizedTest
	@EnumSource(NavTreePcSubLink.class)
	void clickOnNavTreeSubLink(NavTreePcSubLink link) {
		open("https://market.yandex.ru/catalog--kompiuternaia-tekhnika/54425");
		link.click();
	}

	/**
	 * a function, with bruteforce realization of test from Sberbank HR
	 *
	 */
	@Test
	void explicitTest() {
		open("https://yandex.ru");
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

	/**
	 * a function that realize test from Sberbank HR, using predefined page elements
	 * (like list, links, checkbox).
	 * Looks like simple as explicitTest(), but contains in it a lot of logic
	 */
	@Test
	void implicitElementsTest() {
		open("https://yandex.ru");
		HeaderServiceLink.market.click();
		switchTo().window(1);
		TabLink.computers.click();
		NavTreePcSubLink.notebook.click();
		LimitPrice.min.setValue("10000");
		LimitPrice.max.setValue("30000");
		HP.set();
		Lenovo.set();
		ShowItemsOption.showTwelve.select();
		YandexMarketNotebooksPage notebooksPage = new YandexMarketNotebooksPage();
		notebooksPage.checkItemsCount(12);
		final String ITEM_NAME = notebooksPage.getItemNameByIndex(0);
		notebooksPage.search(ITEM_NAME);
		assertEquals(ITEM_NAME, notebooksPage.getItemNameByIndex(0),
				"Item name not equal item name before search");
	}

	/**
	 * a function that realize test from Sberbank HR, using functionality of page objects
	 * Page objects contains page elements and methods to work with it
	 * Final realization of test
	 */
	@Test
	void implicitTest() {
		YandexMarketNotebooksPage notebooksPage =
				openYandexRuPage()
						.clickOnMarketHeaderLink()
						.clickOnComputersCatalogLink()
						.clickOnNotebooksCatalogLink()
						.setLimitOfPrice(10000, 30000)
						.chooseManufacturer(HP, Lenovo)
						.showTwelveItems()
						.checkIndexItemDisplayedOnPositionAfterSearchIt(0, 0);
	}

	/**
	 * a function that running after every single test
	 * Current - close browser (also webDriver)
	 */
	@AfterEach
	void afterEach() {
		closeWebDriver();
	}
}
