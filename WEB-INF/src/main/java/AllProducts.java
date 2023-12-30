

public class AllProducts {
	
	private SmartDoorbell smartDoorbell;
	
	private SmartDoorLock smartDoorLock;
	
	private SmartLighting smartLighting;
	
	private SmartThermostat smartThermostat;

    private Speaker speaker;
	
	private String category;
	
	public SmartDoorLock getSmartDoorLock() {
		return smartDoorLock;
	}

	public void setSmartDoorLock(SmartDoorLock smartDoorLock) {
		this.smartDoorLock = smartDoorLock;
	}

	public SmartDoorbell getSmartDoorbell() {
		return smartDoorbell;
	}

	public void setSmartDoorbell(SmartDoorbell smartDoorbell) {
		this.smartDoorbell = smartDoorbell;
	}

	public SmartLighting getSmartLighting() {
		return smartLighting;
	}

	public void setSmartLighting(SmartLighting smartLighting) {
		this.smartLighting = smartLighting;
	}

    public SmartThermostat getSmartThermostat() {
		return smartThermostat;
	}

	public void setSmartThermostat(SmartThermostat smartThermostat) {
		this.smartThermostat = smartThermostat;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
