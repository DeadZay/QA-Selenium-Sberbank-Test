package ru.yandex.market.computers.notebooks.searchpager;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import qa.selenide.interfaces.Selectable;

import static com.codeborne.selenide.Selenide.$;

/**
 * An Enum that realize control showing item count on page
 */
public enum ShowItemsOption implements Selectable{
	showTwelve,
	showFortyEight;

	private final String[] TITLE = {
			"Показывать по 12",
			"Показывать по 48"
	};

	private final int[] COUNT = {12, 48};

	private final String XPATH_FORMAT =
			"//div[./button = 'Показать ещё']//div[//button[@aria-expanded]]//div[@aria-hidden = 'false']//button[.='%s']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement OPTION = $(By.xpath(XPATH));
	private final SelenideElement OPTION_SELECTOR =
			$(By.xpath("//div[./button = 'Показать ещё']/div[./button[@aria-expanded = 'false']]/button"));

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}

	public int getCount() {
		return COUNT[this.ordinal()];
	}

	@Override
	public void select() {
		if (!OPTION.isDisplayed())
			OPTION_SELECTOR.click();
		OPTION.click();
	}
}
