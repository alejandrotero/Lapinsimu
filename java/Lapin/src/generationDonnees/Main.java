package generationDonnees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import database.ecriteur;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		boolean ended=true;
		long startingTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();
		long timeMax= 1000000;
		long miliseconds=5;
		String nomDB1="pression";
		String nomDB2="event";
		
		ecriteur ec = new ecriteur();
		ec.creerDB(nomDB1);
		ec.creerDB(nomDB2);
		
		List<Events> listEvents = new ArrayList<>();
		
		while(ended && currentTime<timeMax) {
			//Mise a jour du temps IN MILLISECONDS
			currentTime=System.currentTimeMillis()-startingTime;
			
			//Check new events
			Events event=demandeEvent(currentTime, nomDB2,listEvents);
			
			//Ecrire dans la DB
			ec.generePoint(currentTime, nomDB1, event);
			
			
			TimeUnit.MILLISECONDS.sleep(miliseconds);
		}
	}
	
	
	private static Events demandeEvent(long currentTime, String nomDB2, List<Events> listEvents) {
		Events reponse = null;
		if (currentTime>10000) {
			if (listEvents.size()==0) {
				reponse=Events.ADRENALINE;
			} 
		} 
		return reponse;
	}

}
