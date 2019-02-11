package generationDonnees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import database.Scribe;

/**
 * Class principal du projet
 **/
public class Main {
	
	/**
	 * Methode principal du projet qui permet de simuler le lapin
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		boolean ended=true;
		long startingTime = System.currentTimeMillis();
		int currentTime = (int) (System.currentTimeMillis()-startingTime);
		//Temps max pour la simulation, apr�s ce temps la le program arretera sa execution
		long timeMax= 1000000;
		//On va a generer des donnes pour les courbes chaque stepInMiliseconds
		long stepInMiliseconds=5;
		String nomDB1="PressionA";
		String nomDB2="event";
		
		Scribe writter = new Scribe();
		writter.creerDB(nomDB1);
		writter.creerDB(nomDB2);
		
		List<Events> listEvents = new ArrayList<>();
		
		while(ended && currentTime<timeMax) {
			//Mise a jour du temps IN MILLISECONDS
			currentTime=(int) (System.currentTimeMillis()-startingTime);
			
			//Check new events
			Events event=demandeEvent(currentTime, nomDB2,listEvents);
			//Ecrire dans la DB
			writter.generePoint(currentTime, nomDB1, event);
			
			
			TimeUnit.MILLISECONDS.sleep(stepInMiliseconds);
		}
	}
	
	/**
	 * Methode qui recherche dans la BD des events si on a eu un nouvel event
	 * @param currentTime
	 * @param nomDB2 BD avec les events
	 * @param listEvents lis avec tous les anciennes events
	 * @return le nouvel event ou null si on a aucun nouvel event
	 */
	public static Events demandeEvent(long currentTime, String nomDB2, List<Events> listEvents) {
		Events reponse = null;
		if (currentTime>10000) {
			if (listEvents.size()==0) {
				reponse=Events.ADRENALINE;
				listEvents.add(reponse);
			} 
		} 
		return reponse;
	}

}
