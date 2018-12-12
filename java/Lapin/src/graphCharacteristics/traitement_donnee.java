package graphCharacteristics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class traitement_donnee {
	
	public static void Echantillione(String filePath, String filePathW, double FE, double FEB) throws IOException {
		
		// ceci est une modif
		/* yo les nazes, alors cette classe est censé réunir tous les fonctions permettant le traitement des données
		 * la fonction traitement_dossier va modifier la fréquence d'échantillonage de tous les fichiers dans un dossier donné
		 * elle va aussi découper ces fichiers en plusieurs partie correspondant au injections 
		 * 
		 */
		
		
			Path PE=Paths.get(filePath);
			File OriginalF=new  File(filePath);
			System.out.println(OriginalF.exists());
			System.out.println(OriginalF.getAbsolutePath().toString());
			
			BufferedReader br = new BufferedReader(new FileReader(OriginalF));
			String line;
			
			String NomfichierEntre=PE.getFileName().toString();
			String sortie=(filePathW.concat(File.separator).concat("echantillone_").concat(NomfichierEntre));
			System.out.println(sortie);
			PrintWriter writer = new PrintWriter(sortie);
			int compteur=0;
			int Ecart=(int) Math.round(FEB/FE);
			while ((line = br.readLine()) != null) {
				
				//System.out.println(line);
				if (compteur<8){
					writer.println(line);
				}
				else if (compteur%Ecart==0) {
					writer.println(line);
				}
				compteur+=1;
			
			}
			writer.close();
			br.close();
			
	
	}
	
	static void separationAuxHashtag(String FilePath, String EndPath) {
		BufferedReader br = null;
		FileReader fr = null;
		FileWriter fw = null;
		int i=2;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FilePath);
			File f = new File (EndPath+File.separator+"sequence.txt");
			System.out.println(f.getAbsolutePath());
			
			fw = new FileWriter (f);
			
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				
				if (sCurrentLine.contains("#")) {
					String Name=sCurrentLine.split("#")[1];
					fw.close();
					
					f = new File (EndPath+File.separator+"sequence_"+Name.replaceAll("\\*","").trim()+".txt");
					//System.out.println(f.getAbsolutePath());
					fw = new FileWriter (f);
					fw.write (sCurrentLine+"\r\n");
					i++;
				}
				else {
					//System.out.println(sCurrentLine);
					fw.write (sCurrentLine+"\r\n");
				}
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();
				

				if (fr != null) {
					fr.close();
					fw.close();
				}
					

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
		/**
		 * 
		 * @param FilePath dossier
		 * @param EndPath 
		 * @throws IOException
		 */
		static void traitement_dossier(String FilePath, String EndPath) throws IOException {
			File rep = new File(FilePath);
			File[] fichiersTxt = rep.listFiles();
			
			String EndPathBis =EndPath+File.separatorChar+"Donnee_echantillonnee";
			File EndDir = new File(EndPathBis);
			EndDir.mkdir();
			
			for (File F: fichiersTxt) {
				File F_End = new File(EndPathBis+File.separator+"traitee_"+F.getName().replaceAll(".txt", "").trim());
				F_End.mkdirs();
				separationAuxHashtag(F.getAbsolutePath(), F_End.getAbsolutePath());
				
				for(File FF:F_End.listFiles()) {
					
					BufferedReader bufr = new BufferedReader(new FileReader(FF));
					String line;
					for (int j=0;j<9;j++) {
						bufr.readLine();
					}
					line=bufr.readLine();
					double temps1=Double.parseDouble(line.split("\\t")[0].replaceAll(",", "."));
					line=bufr.readLine();
					double temps2=Double.parseDouble(line.split("\\t")[0].replaceAll(",", "."));
					double FEB=1/(Math.abs(temps1-temps2));
					System.out.println("ifseopfeopfsepofepkf");
					bufr.close();
					Echantillione(FF.getAbsolutePath(), F_End.getAbsolutePath(), 200,  FEB);
					FF.delete();				}
				
			}
		}
		
		public static void main(String[] args) throws IOException {
			String ENDPath="C:/Users/gabri/Desktop";
			String FilePath="C:/Users/gabri/Desktop/test_traitement_dossier";
			traitement_dossier(FilePath, ENDPath);
		}
}

	


