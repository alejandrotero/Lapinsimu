package graphCharacteristics;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.applet.Applet;


public class BattementCardiaque {
	
	public Data data;
	List<Double> valeursX ;
	List<Double> valeursY ;
	HashMap<Integer,Double> valeurs;
	int frequenceEchantillonnage;
	int periode;
	
	public BattementCardiaque(Data data) {
		this.data = data;
		this.valeurs=new HashMap<Integer,Double>();
		this.valeursX =  data.getTime();
		this.valeursY =  data.getPressionArterielle();
		this.frequenceEchantillonnage=(int)Math.round((this.valeursX.get(1)-this.valeursX.get(0))*1000);
		this.periode=(int)(Math.round((this.valeursX.get(this.valeursX.size()-1)-this.valeursX.get(0))*1000));
		System.out.println(this.frequenceEchantillonnage);
		
		
	}
	
	public int nbPointsParPattern() {
		return this.valeursY.size();
	}
	
	public void extractionPattern() {
		
		double timeDebut= this.valeursX.get(0);
		for (int i=0; i<this.valeursY.size(); i++) {
			this.valeursY.set(i, valeursY.get(i)-48) ;
			int valI=(int)(Math.round((this.valeursX.get(i)-timeDebut)*1000));
			this.valeurs.put(valI, this.valeursY.get(i));

		}
		System.out.println(this.valeurs);
		//System.out.println(this.frequenceEchantillonnage);
		
	}
	
	public double getX(int i) {
		return this.valeursX.get(i);
	}
	
	
	public double getY(int i) {
		return this.valeursY.get(i);
	}
	
	public double getY(double i) {
		return this.valeurs.get(i);
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
	
	double getAmplitude() {
		return this.getMax()-this.getMin();
	}
	
	void modulationPattern(double nouvellePeriode, double nouvelleAmplitude) {
		double t0=0;
		double coefficientMultiplicateur=nouvelleAmplitude/this.getAmplitude();
		this.valeurs=new HashMap<Integer,Double>();
		
		for (int i=0; i<this.valeursX.size(); i++) {
			double valTnX=i*nouvellePeriode/this.periode+t0;
			this.valeursX.set(i, valTnX);
			System.out.println(valTnX);
			double valTnY=this.valeursY.get(i)*coefficientMultiplicateur;
			this.valeursY.set(i, valTnY);
			this.valeurs.put((int)(1000*this.frequenceEchantillonnage*i*nouvellePeriode/this.valeursX.size()+t0), valTnY);
		}
		
		
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
			    	String sXb=(String.valueOf (i*this.frequenceEchantillonnage));
			    	String sYb=(String.valueOf ( this.valeurs.get((int)i*this.frequenceEchantillonnage)));
			    	sX=sX.replace('.', ',');
			    	sY=sY.replace('.', ',');
			    	sXb=sXb.replace('.', ',');
			    	//System.out.println(sXb);
			    	sYb=sYb.replace('.', ',');
			    	fw.write (sX+"\t");
			    	fw.write (sY+"\t");
			    	fw.write (sXb+"\t");
			    	fw.write (sYb);
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
			pattern.modulationPattern(10, 10);
			pattern.creationFichier1Battement("Module.txt");
			
		}

}
