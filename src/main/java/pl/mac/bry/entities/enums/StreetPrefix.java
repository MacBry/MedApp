package pl.mac.bry.entities.enums;

public enum StreetPrefix {
	STREET("St"),
	ROAD("Rd"),
	AVENUE("Ave"),
	BOULEVARD("Bvd"),
	ALLEY("Ally"),
	PLACE("Pl");
	
	private final String description;

	private StreetPrefix(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
