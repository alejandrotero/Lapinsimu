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
		    //int frequenceEchantillonnage = Math.max(1, pattern.getFrequenceEchantillonnage());
		    int T1=420;
		    int T2=3; // nouvelle periode voulue
			
		
		    for (double i = 0; i < 1000; i+=0.01) {
		    	//pattern.modulationPattern(pattern.getPeriode(), 100/(i+1));
		    	double valeurAecrire = courbeAdrenaline.getValeur(i);
		    	valeurAecrire=valeurAecrire+pattern.getY((int)((T1/T2*i)%(pattern.nbPointsParPattern())));
		    	
		    	String iS=(String.valueOf ( i));
		    	iS=iS.replace('.', ',');
		    	System.out.println(iS);
		    	fw.write (iS+"\t");
		    	
		    	System.out.println(valeurAecrire);
		    	fw.write ((String.valueOf (valeurAecrire)).substring(0, 10));
		        fw.write ("\r\n");
		        System.out.println("i : "+i+"   val :  "+valeurAecrire);
		        if ((int)(i)%10==0) {
		        	System.out.println("ICI");
		        	pattern.modulationPattern(pattern.getPeriode(), 130-i*0.1);
		        }
		        
			}
		    /*pattern.modulationPattern(pattern.getPeriode(), 100);
		    for (double i = 500; i < 1000; i+=0.01) {
		    	
		    	double valeurAecrire = courbeAdrenaline.getValeur(i);
		    	valeurAecrire=valeurAecrire+pattern.getY((int)((T1/T2*i)%(pattern.nbPointsParPattern())));
		    	
		    	String iS=(String.valueOf ( i));
		    	iS=iS.replace('.', ',');
		    	System.out.println(iS);
		    	fw.write (iS+"\t");
		    	
		    	System.out.println(valeurAecrire);
		    	fw.write ((String.valueOf (valeurAecrire)).substring(0, 10));
		        fw.write ("\r\n");
		        System.out.println("i : "+i+"   val :  "+valeurAecrire);
		        
			}*/
		   
		        
		 
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
		f1.creationFichierTestPA();
		
		/*for (int i = 0; i < 100; i++) {
			//FonctionQuadratique f = FonctionQuadratique.createFonctionMonteAdrenaline(FonctionQuadratique.generateNormalRandomNumber(46.59, 16.48));
			FonctionQuadratique f = FonctionQuadratique.createFonctionDescendAdrenaline(FonctionQuadratique.generateNormalRandomNumber(100, 16.48));
			//System.out.println(f.toString());
			System.out.println(f.getTheta0()+" "+f.getTheta1()+" "+f.getTheta2()+";");
		}*/
	}

}



