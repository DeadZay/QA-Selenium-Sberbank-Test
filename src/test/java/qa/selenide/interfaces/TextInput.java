package qa.selenide.interfaces;

import org.jetbrains.annotations.NotNull;

public interface TextInput {
	void setValue(@NotNull String value);
	void clear();
}
