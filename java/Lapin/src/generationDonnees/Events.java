package generationDonnees;

public enum Events {
	ADRENALINE("Adrenaline"), MORT("Mort");
	
	private String event;
	 
    Events(String env) {
        this.event = env;
    }
 
    public String getEvent() {
        return event;
    }
	
}
