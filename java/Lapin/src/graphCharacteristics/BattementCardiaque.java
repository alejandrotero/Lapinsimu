package graphCharacteristics;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.applet.Applet;


public class BattementCardiaque {
	
	public Data data;
	Object[] valeursX ;
	Object[] valeursY ;
	
	public BattementCardiaque(Data data) {
		this.data = data;
	}
	
	void extractionPattern() {
		this.valeursX =  data.getTime().toArray();
		this.valeursY =  data.getPressionArterielle().toArray();
		for (int i=0; i<this.valeursX.length; i++) {
			valeursY[i]=(double) valeursY[i]-48;
		}
		
	}
	
	void creationFichier() {
			
			System.out.println("ici");
			File f = new File ("test.txt");
			
			 
			try
			{
			    FileWriter fw = new FileWriter (f);
			    System.out.println("ici");
			    System.out.println(this.valeursX.length);
			 
			    for (int i=0; i<this.valeursX.length; i++)
			    {
			    	String sX=(String.valueOf ((double) this.valeursX[i]));
			    	String sY=(String.valueOf ((double) this.valeursY[i]));
			    	fw.write (sX.substring(0,sX.indexOf(",")+3)+"\t");
			    	fw.write (sY.substring(0,sY.indexOf(",")+3));
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
			pattern.creationFichier();
			
		}

}
