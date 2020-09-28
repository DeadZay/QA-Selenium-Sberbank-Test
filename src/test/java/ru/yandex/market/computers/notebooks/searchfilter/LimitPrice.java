package ru.yandex.market.computers.notebooks.searchfilter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import qa.selenide.interfaces.SelenideElementGetter;
import qa.selenide.interfaces.TextInput;
import qa.selenide.interfaces.XpathFormatGetter;
import qa.selenide.interfaces.XpathGetter;

import static com.codeborne.selenide.Selenide.$;

public enum LimitPrice implements SelenideElementGetter, XpathGetter, XpathFormatGetter, TextInput {
	min,
	max;

	private final String[] TITLE = {
			"Цена от",
			"Цена до"
	};

	private final String XPATH_FORMAT =
			"//fieldset[./legend = 'Цена, ₽']//*[./label[@aria-label = '%s']]//input[@type = 'text']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement INPUT = $(By.xpath(XPATH));

	@Override
	public SelenideElement getSelenideElement() {
		return INPUT;
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
	public void setValue(@NotNull String value) {
		INPUT.shouldBe(Condition.visible).setValue(value);
	}

	@Override
	public void clear() {
		INPUT.clear();
	}
}
