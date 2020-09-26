package test.sberbank.qa;

public enum DisplayedItemsOption {
	showTwelve,
	showFortyEight;

	private static final int[] DISPLAYED_ITEMS_OPTION_VALUE = {12, 48};

	@Override
	public String toString() {
		return String.format("Показывать по %d", DISPLAYED_ITEMS_OPTION_VALUE[this.ordinal()]);
	}

	public int getIntValue() {
		return DISPLAYED_ITEMS_OPTION_VALUE[this.ordinal()];
	}
}
