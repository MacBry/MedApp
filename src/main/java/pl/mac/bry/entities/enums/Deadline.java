package pl.mac.bry.entities.enums;

public enum Deadline {
	FIRST("First deadline"),
	SECOND("Second deadline"),
	THIRD("Third deadline"),
	FOURTH("Fourth deadline"),
	FIFTH("Fifth deadline"),
	SIXTH("Sixth deadline");
	
	private final String description;

	private Deadline(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	

}
