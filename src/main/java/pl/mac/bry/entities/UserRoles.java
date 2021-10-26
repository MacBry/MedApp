package pl.mac.bry.entities;

public enum UserRoles {
	DEFAULT_ROLE("ROLE_USER"),
	USER_ROLE("ROLE_USER"),
	ADMIN_ROLE("ROLE_ADMIN");
	
	private final String description;
	
	private UserRoles(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	
}
