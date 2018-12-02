package coupage;
import java.io.*;

public class CoupageSequences {
	
	void separationAuxHashtag() {
		BufferedReader br = null;
		FileReader fr = null;
		FileWriter fw = null;
		int i=2;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("groupe 1.txt");
			File f = new File ("sequence.txt");
			
			fw = new FileWriter (f);
			
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				
				if (sCurrentLine.contains("#")) {
					
					fw.close();
					
					f = new File ("sequence"+i+".txt");
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
