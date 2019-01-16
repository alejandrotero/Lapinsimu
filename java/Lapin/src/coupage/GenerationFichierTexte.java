package coupage;

import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.Math.*;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class GenerationFichierTexte {
	
	private int evenement;
	
	public GenerationFichierTexte() {
		this.evenement=0;
	}
	

	void creationFichier() {
		
		double[] data = new double[50];
		double s=0;
		for (int i=0; i<50; i++) {
			data[i]=s+(double)0.1;
			s=data[i];
		}
		System.out.println(data);
		File f = new File ("test.txt");
		
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		 
		    for (double d : data)
		    {
		    	java.util.Date date= new java.util.Date();
		        String heure = date.toString();
		        
		        double valFonction=20;
		        
		        if (d>3 /*modélisation évènement pour changer de fonction*/) {
		        	valFonction=Math.sin((d));
		        }
		        else {
		        	valFonction=20;
		        }
	
		        // pour l'instant génère un fichier texte mais à la place on écrira les points dans la base de données
		    	fw.write ((String.valueOf (d)).substring(0, 3)+"\t");
		    	fw.write (heure+"\t");
		    	fw.write ((String.valueOf (valFonction)).substring(0, 4));
		        fw.write ("\r\n");
		        System.out.println(d+"        "+heure+"        "+ valFonction);
		        TimeUnit.SECONDS.sleep(1);
		       
		        
		        
		        
		    }
		 
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void keyPressed(KeyEvent e) { // ??
		this.evenement=1;
	}
	
	
	
	public static void main(String[] argv) {
		GenerationFichierTexte f1 = new GenerationFichierTexte();
		f1.creationFichier();
	}

}



