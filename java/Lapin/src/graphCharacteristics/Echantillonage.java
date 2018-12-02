package graphCharacteristics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Echantillonage {
	
	public static void readFile(String filePath, String filePathW, float FE, float FEB) {
		
		
			Path PE=Paths.get(filePath);
			File OriginalF=new  File(filePath);
			System.out.println(OriginalF.exists());
			System.out.println(OriginalF.getAbsolutePath().toString());
			
			InputStream flux=new FileInputStream(OriginalF.getAbsolutePath().toString()); 
			/*
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			String NomfichierEntre=PE.getFileName().toString();
			String sortie= filePathW.concat(NomfichierEntre).concat("_echantillone");
			System.out.println(sortie);
			PrintWriter writer = new PrintWriter(sortie);
			int compteur=0;
			int Ecart=Math.round(FEB/FE);
			while ((ligne=buff.readLine())!=null){
				System.out.println(ligne);
				if (compteur<8){
					writer.println(ligne);
				}
				else if (compteur%Ecart==0) {
					writer.println(ligne);
				}
				
				String[] columnDetail = new String[6];
				ligne = ligne.replace(',','.');
				columnDetail = ligne.split("\\t");
				
			}
			writer.close();
			buff.close(); 
			*/
	
	}
	
	public static void main(String[] args) {
		//File F= new File("teste.txt");
		//System.out.println(F.getAbsolutePath().toString());
		readFile(
				"C:/Users/gabri/Desktop/TEST.txt",
				"",
				200,1000);
				
	}
}
