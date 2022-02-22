package pl.mac.bry.entities.enums;

public enum SampleType {
	SERUM_SAMPLE("Serum sample"),
	PLASMA_SAMPLE("Plasma sample"),
	BLOOD_SAMPLE("Blood sample"),
	URINE_SAMPLE("Urine sample");
	
	private final String description;

	private SampleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
