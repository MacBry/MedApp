package pl.mac.bry.entities.enums;

public enum ABOBloodGroup {
	A("Group A"),
	AB("Group AB"),
	B("Group B"),
	O("Group 0");
	
	private final String descriptionString;

	private ABOBloodGroup(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public String getDescription() {
		return descriptionString;
	}
	

}
