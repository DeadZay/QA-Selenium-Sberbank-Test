package qa.selenide.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * An interface guarantees that object implement it, may setting value in <input type="text"></input>
 * or clear value in it
 */
public interface TextInput {
	void setValue(@NotNull String value);
	void clear();
}
