package qa.selenide.interfaces;

import io.qameta.allure.Step;

import java.net.URISyntaxException;

/**
 * a interface that guarantees contain function, that check:
 *  is current browser url contains in page element url list
 */
public interface AssertURL {

	@Step
	public void assertURL();
}
