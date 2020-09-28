package ru.yandex.market.computers.notebooks.searchfilter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import qa.selenide.interfaces.CheckboxInput;
import qa.selenide.interfaces.SelenideElementGetter;
import qa.selenide.interfaces.XpathFormatGetter;
import qa.selenide.interfaces.XpathGetter;

import static com.codeborne.selenide.Selenide.$;

/**
 * An Enum that realize control on list with manufacturer checkboxex
 */
public enum Manufacturer implements XpathFormatGetter, XpathGetter, SelenideElementGetter, CheckboxInput {
	Acer,
	Alienware,
	Apple,
	ASUS,
	DELL,
	DIGMA,
	DURABOOK,
	Echips,
	eMachines,
	Fujitsu,
	Fujitsu_Siemens,
	Getac,
	GIGABYTE,
	Google,
	HP,
	Haier,
	HONOR,
	HUAWEI,
	Irbis,
	Lenovo,
	LG,
	Microsoft,
	MSI,
	Packard_Bell,
	Prestigio,
	Panasonic,
	PNY,
	Razer,
	RoverBook,
	Samsung,
	Sony,
	Toshiba,
	Xiaomi,
	_3Q;
	
	private final String[] TITLE = {
			"Acer",
			"Alienware",
			"Apple",
			"ASUS",
			"DELL",
			"DIGMA",
			"DURABOOK",
			"Echips",
			"eMachines",
			"Fujitsu",
			"Fujitsu-Siemens",
			"Getac",
			"GIGABYTE",
			"Google",
			"HP",
			"Haier",
			"HONOR",
			"HUAWEI",
			"Irbis",
			"Lenovo",
			"LG",
			"Microsoft",
			"MSI",
			"Packard Bell",
			"Prestigio",
			"Panasonic",
			"PNY",
			"Razer",
			"RoverBook",
			"Samsung",
			"Sony",
			"Toshiba",
			"Xiaomi",
			"3Q"
	};
	private final String XPATH_FIELDSET = "//fieldset[./legend = 'Производитель']";
	private final String XPATH_FORMAT =
			"//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель %s']]//div[./span = '%1$s']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement MANUFACTURER_CHECKBOX_CLICKABLE = $(By.xpath(XPATH));
	private final SelenideElement MANUFACTURER_CHECKBOX = $(By.xpath(String.format(
					"//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель %s']]",
					TITLE[this.ordinal()])));
	private final SelenideElement SHOW_ALL_BUTTON =
			$(By.xpath(XPATH_FIELDSET + "//button[.='Показать всё']"));
	private final SelenideElement SEARCH_INPUT =
			$(By.xpath(XPATH_FIELDSET + "//input[@type = 'text' and @name = 'Поле поиска']"));

	private void searchCurrentManufacturer() {
		if (!SEARCH_INPUT.isDisplayed())
			SHOW_ALL_BUTTON.click();
		SEARCH_INPUT.clear();
		SEARCH_INPUT.setValue(TITLE[this.ordinal()]);
	}

	@Override
	public SelenideElement getSelenideElement() {
		return MANUFACTURER_CHECKBOX_CLICKABLE;
	}

	@Override
	public String getXpathFormat() {
		return XPATH_FORMAT;
	}

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}

	@Override
	public String getXpath() {
		return XPATH;
	}

	@Override
	public void set() {
		searchCurrentManufacturer();
		MANUFACTURER_CHECKBOX_CLICKABLE.shouldBe(Condition.visible).click();
		//MANUFACTURER_CHECKBOX.selectOption(TITLE[this.ordinal()]);
	}

	@Override
	public void unSet() {
		if (MANUFACTURER_CHECKBOX.shouldBe(Condition.visible).isSelected())
			set();
	}
}
