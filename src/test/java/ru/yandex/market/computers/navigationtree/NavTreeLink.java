package ru.yandex.market.computers.navigationtree;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import qa.selenide.interfaces.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An Enum that realize @MarketNode/NavigationTree category link
 */
public enum NavTreeLink implements Clickable, SelenideElementGetter, XpathFormatGetter, XpathGetter, AssertURL {
	laptopsAndDesktops,
	components,
	peripherals,
	officeEquipment,
	consumables,
	dataStorage,
	accessories,
	networkHardware,
	software,
	equipmentForPresentations,
	organizationOfWorkspace,
	computerTables,
	computerChairs,
	ups;

	private final String[] TITLE = {
			"Ноутбуки и настольные ПК",
			"Комплектующие",
			"Переферийные устройства",
			"Оргтехника",
			"Расходные материалы",
			"Накопители данных",
			"Аксессуары",
			"Сетевое оборудование",
			"Программное обеспечение",
			"Оборудование для презентаций",
			"Организация рабочего места",
			"Компьютерные столы",
			"Компьютерные стулья и кресла",
			"Источники бесперебойного питания"
	};

	private static final String[] URL = {
			"https://market.yandex.ru/catalog--noutbuki-i-nastolnye-pk/",
			"https://market.yandex.ru/catalog--kompiuternye-komplektuiushchie/",
			"https://market.yandex.ru/catalog--periferiinye-ustroistva-kompiutera/",
			"https://market.yandex.ru/catalog--orgtekhnika/",
			"https://market.yandex.ru/catalog--raskhodniki-dlia-kompiuternoi-tekhniki/",
			"https://market.yandex.ru/catalog--nakopiteli-dannykh/",
			"https://market.yandex.ru/catalog--aksessuary-dlia-kompiuternoi-tekhniki/",
			"https://market.yandex.ru/catalog--setevoe-oborudovanie/",
			"https://market.yandex.ru/catalog--programmnoe-obespechenie/",
			"https://market.yandex.ru/catalog--oborudovanie-dlia-prezentatsii/",
			"https://market.yandex.ru/catalog--organizatsiia-rabochego-mesta/",
			"https://market.yandex.ru/catalog--kompiuternye-stoly/",
			"https://market.yandex.ru/catalog--kompiuternye-stulia-i-kresla/",
			"https://market.yandex.ru/catalog--istochniki-bespereboinogo-pitaniia/"
	};

	private final String XPATH_FORMAT = "//div[@data-apiary-widget-name = '@MarketNode/NavigationTree']//a[.='%s']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement LINK = $(By.xpath(XPATH));

	@Override
	public SelenideElement getSelenideElement() {
		return LINK;
	}

	@Override
	public String getXpathFormat() {
		return XPATH_FORMAT;
	}

	@Override
	public String getXpath() {
		return XPATH;
	}

	@Override
	public void click() {
		LINK.shouldBe(Condition.and(
				"SelenideElement check",
				Condition.exist, Condition.enabled, Condition.visible))
				.click();
		assertURL();
	}

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}

	@Override
	public void assertURL() {
		assertTrue(url().substring(0, URL[this.ordinal()].length()).contains(URL[this.ordinal()]));
	}
}
