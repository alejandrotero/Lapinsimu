package generationDonnees;

import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.Math.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import graphCharacteristics.BattementCardiaque;
import graphCharacteristics.Data;

public class GenerationFichierTexte {
	
	private int evenement;
	
	public GenerationFichierTexte() {
		this.evenement=0;
	}
	
	
	void creationFichier() {
		
		File f = new File ("test.txt");
		
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		    double valeurInitial = FonctionQuadratique.generateNormalRandomNumber(43.59, 19.48);
		    //System.out.println(valeurInitial);
		    Courbe courbeAdrenaline = new Courbe((long) 0.0, valeurInitial);
			
		
		    for (int i = 0; i < 2000; i++) {
		    	Double valeurAecrire = courbeAdrenaline.getValeur(i);
		    	if (valeurAecrire!=null) {
		    		fw.write (i+"\t");
			    	fw.write ((String.valueOf (valeurAecrire)).substring(0, 10));
			        fw.write ("\r\n");
			        //System.out.println("i : "+i+"   val :  "+valeurAecrire);
				}else {
					break;
				}
			}
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
		
	}
	
void creationFichierTestPA() {
		
		File f = new File ("test.txt");
		Data data= new Data("Data Ana/Pattern.txt");
		BattementCardiaque pattern = new BattementCardiaque(data);
		pattern.extractionPattern();
		
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		    double valeurInitial = FonctionQuadratique.generateNormalRandomNumber(43.59, 19.48);
		    System.out.println(valeurInitial);
		    Courbe courbeAdrenaline = new Courbe((long) 0.0, valeurInitial);
			
		
		    for (int i = 0; i < 2000; i++) {
		    	double valeurAecrire = courbeAdrenaline.getValeur(i)+pattern.getY(i%(pattern.nbPointsParPattern()));
		    	fw.write (i+"\t");
		    	fw.write ((String.valueOf (valeurAecrire)).substring(0, 10));
		        fw.write ("\r\n");
		        System.out.println("i : "+i+"   val :  "+valeurAecrire);
		        
			}
		   
		        
		 
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
		
	}
	
	
	
	void keyPressed(KeyEvent e) { // ??
		this.evenement=1;
	}
	
	
	
	public static void main(String[] argv) {
		GenerationFichierTexte f1 = new GenerationFichierTexte();
		f1.creationFichier();
		
		/*for (int i = 0; i < 100; i++) {
			//FonctionQuadratique f = FonctionQuadratique.createFonctionMonteAdrenaline(FonctionQuadratique.generateNormalRandomNumber(46.59, 16.48));
			FonctionQuadratique f = FonctionQuadratique.createFonctionDescendAdrenaline(FonctionQuadratique.generateNormalRandomNumber(100, 16.48));
			//System.out.println(f.toString());
			System.out.println(f.getTheta0()+" "+f.getTheta1()+" "+f.getTheta2()+";");
		}*/
	}

}



