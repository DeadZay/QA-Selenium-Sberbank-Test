package qa.selenide.interfaces;

import com.codeborne.selenide.SelenideElement;

/**
 * An interface guarantees that object implements it, can get you SelenideElement
 */
public interface SelenideElementGetter {
	SelenideElement getSelenideElement();
}
