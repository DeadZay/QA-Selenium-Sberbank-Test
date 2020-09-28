package qa.selenide.interfaces;

/**
 * An interface guarantees that object implement it, can get you Xpath format String
 * to query WebElement or SelenideElement on page, after setting some variables (like %s or %d).
 * See printf format for more information about it
 */
public interface XpathFormatGetter {
	public String getXpathFormat();
}
