package coupage;
import java.io.*;

public class CoupageSequences {
	
	// Cette fonction permet de couper un jeu de donnees en differents sets de donnees a chaque '#', soit chaque injection
	
	void separationAuxHashtag() {
		BufferedReader br = null;
		FileReader fr = null;
		FileWriter fw = null;
		int i=2;
		
		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			String nomFichier = "Data Ana/Robert groupe 7";
			fr = new FileReader(nomFichier+ ".txt");
			File file = new File(nomFichier);
			
			file.mkdirs();
			
			File f = new File (nomFichier+"/sequence.txt");
			
			fw = new FileWriter (f);
			
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				
				if (sCurrentLine.contains("#")) {
					
					fw.close();
					
					f = new File (nomFichier+"/sequence"+i+".txt");
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
	
	public static void main(String[] argv) {
		CoupageSequences f2= new CoupageSequences();
		f2.separationAuxHashtag();
		
	}

}
