package generationDonnees;

public enum Events {
	REPOS("Repos"),ADRENALINE("Adrenaline"), MORT("Mort");
	
	private String event;
	 
    Events(String env) {
        this.event = env;
    }
 
    public String getEvent() {
        return event;
    }
	
}
