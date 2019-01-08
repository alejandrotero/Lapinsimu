package graphCharacteristics;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.applet.Applet;


public class BattementCardiaque {
	
	public Data data;
	List<Double> valeursX ;
	List<Double> valeursY ;
	
	public BattementCardiaque(Data data) {
		this.data = data;
	}
	
	void extractionPattern() {
		this.valeursX =  data.getTime();
		this.valeursY =  data.getPressionArterielle();
		for (int i=0; i<this.valeursX.size(); i++) {
			valeursY.set(i, valeursY.get(i)-48) ;

		}
		
	}
	
	double getMax() {
		double aux=this.valeursY.get(0);
		for (int i=0; i<this.valeursX.size(); i++) {
			if(this.valeursY.get(i)>aux) aux=this.valeursY.get(i);
		}
		return aux;
		
	}
	
	double getMin() {
		double aux=this.valeursY.get(0);
		for (int i=0; i<this.valeursX.size(); i++) {
			if(this.valeursY.get(i)<aux) aux=this.valeursY.get(i);
		}
		return aux;
		
	}
	
	void modulationPattern(double periode, double amplitude) {
		double t0=this.valeursX.get(0);
		for (int i=0; i<this.valeursX.size(); i++) {
			double valTn=i*periode/this.valeursX.size()+t0;
			this.valeursX.set(i, valTn);
		}
		//this.valeursY.
	}
	
	void creationFichier1Battement(String nomFichier) {
			
			File f = new File (nomFichier);
			
			 
			try
			{
			    FileWriter fw = new FileWriter (f);
			    
			 
			    for (int i=0; i<this.valeursX.size(); i++)
			    {
			    	String sX=(String.valueOf ( this.valeursX.get(i)));
			    	String sY=(String.valueOf ( this.valeursY.get(i)));
			    	sX=sX.replace('.', ',');
			    	sY=sY.replace('.', ',');
			    	fw.write (sX+"\t");
			    	fw.write (sY);
			        fw.write ("\r\n");
			        
			    }
			 
			    fw.close();
			}
			catch (IOException exception)
			{
			    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			} 
		
	}
		public static void main(String[] argv) {
			Data data= new Data("Data Ana/Pattern.txt");
			BattementCardiaque pattern = new BattementCardiaque(data);
			pattern.extractionPattern();
			pattern.creationFichier1Battement("Original.txt");
			pattern.modulationPattern(10, 1);
			pattern.creationFichier1Battement("Module.txt");
			
		}

}
