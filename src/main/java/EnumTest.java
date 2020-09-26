class EnumTest {
	public static void main(String[] args) {
		Enumerator enumerator = Enumerator.val1;
		System.out.println(enumerator.name() + " " + enumerator.getTitle());
		enumerator = Enumerator.val2;
		System.out.println(enumerator.name() + " " + enumerator.getTitle());
	}
}
enum Enumerator {
	val1("Value 1"),
	val2("Value 2"),
	val3("Value 3");
	private final String title;

	Enumerator(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}
