package generationDonnees;
/**
 * Class qui contient les differents Events que le lapin peut avoir
 *
 */
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
