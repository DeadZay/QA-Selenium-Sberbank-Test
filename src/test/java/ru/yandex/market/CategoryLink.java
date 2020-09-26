package ru.yandex.market;

public enum CategoryLink {
	electronics,
	appliances,
	computers,
	repairs,
	house,
	countryHouse,
	children,
	beauty,
	health,
	food;

	private static final String[] CATEGORY_LINK_TITLE = {
			"Электроника",
			"Бытовая техника",
			"Компьютеры",
			"Ремонт",
			"Дом",
			"Дача",
			"Дети",
			"Красота",
			"Здоровье",
			"Продукты"
	};

	@Override
	public String toString() {
		return CATEGORY_LINK_TITLE[this.ordinal()];
	}
}
