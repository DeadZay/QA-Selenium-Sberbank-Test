package ru.yandex.market.computers.navigationtree;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import qa.selenide.interfaces.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An Enum that realize @MarketNode/NavigationTree subcategory link
 */
public enum NavTreePcSubLink implements Clickable, XpathFormatGetter, XpathGetter, SelenideElementGetter, AssertURL {
	tablet,
	notebook,
	desktop,
	monoblock,
	industrial,
	servers;

	private final String[] TITLE = {
			"Планшеты",
			"Ноутбуки",
			"Настольные",
			"Моноблоки",
			"Промышленные",
			"Серверы"
	};

	private final String[] URL = {
			"https://market.yandex.ru/catalog--planshety/",
			"https://market.yandex.ru/catalog--noutbuki/",
			"https://market.yandex.ru/catalog--nastolnye/",
			"https://market.yandex.ru/catalog--monobloki/",
			"https://market.yandex.ru/catalog--promyshlennye/",
			"https://market.yandex.ru/catalog--servery/"
	};

	private final String XPATH_FORMAT = "//ul[@data-autotest-id = 'subItems']//a[.='%s']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement NAVTREE_SUBLINK = $(By.xpath(XPATH));

	@Override
	public void click() {
		NAVTREE_SUBLINK.shouldBe(Condition.and(
				"SelenideElement check",
				Condition.exist, Condition.enabled, Condition.visible))
				.click();
		assertURL();
	}

	@Override
	public SelenideElement getSelenideElement() {
		return NAVTREE_SUBLINK;
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
	public void assertURL() {
		assertTrue(url().substring(0, URL[this.ordinal()].length()).contains(URL[this.ordinal()]));
	}

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}
}
