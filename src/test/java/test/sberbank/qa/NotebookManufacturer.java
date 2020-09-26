package test.sberbank.qa;

public enum NotebookManufacturer {
	Acer,
	Alienware,
	Apple,
	ASUS,
	BenQ,
	DELL,
	DIGMA,
	DURABOOK,
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
	Nautilus,
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

	private String _name() {
		/*return Character.isLetter(this.name().charAt(0)) ? this.name() : this.name().substring(1);*/
		return this == _3Q ? "3Q" : this.name();
	}

	@Override
	public String toString() {
		return String.format("Производитель %s", _name());
	}

	public String getManufacturerName() {
		return _name();
	}
}
