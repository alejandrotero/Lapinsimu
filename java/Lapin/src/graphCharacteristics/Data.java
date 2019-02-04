package graphCharacteristics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Data {
	public String nomFicher;
	public String getNomFicher() {
		return nomFicher;
	}
	public List<Double> time;
	public List<Double> pressionArterielle;
	public List<Double> pressionRespiratiore;
	public  List<Double> pressionArterielleMoyenne;
	public  List<Double> frequenceCardiaque;
	public  List<Double> frequenceRespiratoire;
	//Times in which the pressionArterielleMoyenne is not repeated (we have the same pressionArterielleMoyenne pour 100 values aprox)
	public List<Double> uniqueTimes;
	
	public Data(String filePath) {
		time= new ArrayList<>();
		pressionArterielle = new ArrayList<>();
		pressionRespiratiore = new ArrayList<>();
		pressionArterielleMoyenne = new ArrayList<>();
		frequenceCardiaque = new ArrayList<>();
		frequenceRespiratoire = new ArrayList<>();
		readFile(filePath);
	}
	public  List<Double> getTime() {
		return time;
	}
	public List<Double> getUniqueTimes() {
		return uniqueTimes;
	}
	public List<Double> getPressionArterielle() {
		return pressionArterielle;
	}
	public List<Double> getPressionRespiratiore() {
		return pressionRespiratiore;
	}
	public  List<Double> getPressionArterielleMoyenne() {
		return pressionArterielleMoyenne;
	}
	public List<Double> getFrequenceCardiaque() {
		return frequenceCardiaque;
	}
	public List<Double> getFrequenceRespiratoire() {
		return frequenceRespiratoire;
	}
	public void readFile(String filePath) {
		try{
			InputStream flux=new FileInputStream(filePath); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			nomFicher = filePath;
			String ligne;
			while ((ligne=buff.readLine())!=null){
				String[] columnDetail = new String[6];
				ligne = ligne.replace(',','.');
				columnDetail = ligne.split("\\t");
				try {
					time.add(Double.parseDouble(columnDetail[0]));
					pressionArterielle.add(Double.parseDouble(columnDetail[1]));
					pressionRespiratiore.add(Double.parseDouble(columnDetail[2]));
					pressionArterielleMoyenne.add(Double.parseDouble(columnDetail[3]));
					frequenceCardiaque.add(Double.parseDouble(columnDetail[4]));
					frequenceRespiratoire.add(Double.parseDouble(columnDetail[5]));
				} catch (Exception e) {
					//System.out.println("line of the fichier txt ignored, not numeric");
				}
				
			}
			buff.close(); 
			System.out.println("data: "+nomFicher+" readed correctly, "+time.size() + " data points");
			}		
			catch (Exception e){
				System.out.println("error reading the file!");
			}
	}
	/**
	 * Creates the list with the unrepeated pressionArterielleMoyenne and the unique times
	 */
	public void eliminateRepeatedPAMoyenne() {
		uniqueTimes = new ArrayList<>();
		List<Double> uniqueValues = new ArrayList<>();
		uniqueTimes.add(time.get(0));
		uniqueValues.add(pressionArterielleMoyenne.get(0));
		for (int i = 1; i < pressionArterielleMoyenne.size(); i++) {
			if (pressionArterielleMoyenne.get(i-1).doubleValue()!=pressionArterielleMoyenne.get(i).doubleValue()) {
				uniqueTimes.add(time.get(i));
				uniqueValues.add(pressionArterielleMoyenne.get(i));
			}
		}
		pressionArterielleMoyenne.clear();
		pressionArterielleMoyenne.addAll(uniqueValues);
	}
}
