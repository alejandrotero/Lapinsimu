package graphCharacteristics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Extraction_SOLO {
	
	private List<Double> L_freqMoy;
	private String grandeur;
	private String produit;

	
	
	public Extraction_SOLO(String FilePath, String Produit,  String Grandeur) {
		this.grandeur=Grandeur;
		this.produit=Produit;
		
		this.L_freqMoy = new ArrayList<>();
		File rep=new File(FilePath);
		File[] fichiers = rep.listFiles();
		
		int St_l=Produit.length();
		
		
		for (File f : fichiers) {		
			
			
			double G1=0;
			
			
			for (File ft:f.listFiles()) {
				
				
			
				if (inverseString(ft.getName().replaceAll(".txt", "")).substring(0, St_l).toLowerCase().replaceAll("ž", "e").equals(inverseString(Produit).substring(0, St_l).toLowerCase())) {
					if(Grandeur=="frequenceCardiaque") {
						List<Double> L = new Data(ft.getAbsolutePath()).frequenceCardiaque;
						int l=L.size();
						if(Produit=="") {
							G1=Max(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Max(new Data(ft.getAbsolutePath()).frequenceCardiaque);
						}
					}
					if(Grandeur=="pressionRespiratoire") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionRespiratiore;
						int l=L.size();
						if(Produit=="") {
							G1=Max(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Max(new Data(ft.getAbsolutePath()).pressionRespiratiore);
						}
					}
					if(Grandeur=="pressionArterielleMoyenne") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielleMoyenne;
						int l=L.size();
						if(Produit=="") {
							G1=Max(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Max(new Data(ft.getAbsolutePath()).pressionArterielleMoyenne);
						}
					}
					if(Grandeur=="pressionArterielle") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielle;
						int l=L.size();
						if(Produit=="") {
							G1=Max(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Max(new Data(ft.getAbsolutePath()).pressionArterielle);
						}
					}
					
					
				}
				
			}
			
			if ((!Double.isNaN(G1))&&(G1!=0)) {
				this.L_freqMoy.add(G1);
				System.out.println(G1);
				System.out.println("--------------");

			}
		}
			
	}
	
	
	// fonctions utiles
	//----------------------------------------------------------------------------//
	
	public static double Moy(List<Double> LD) {
		int S=0;
		double T=0;
		for (double D:LD) {
			if (!Double.isNaN(D)) {
				T+=D;
				
					//System.out.println(D);
				
				S+=1;
			}
			//System.out.println(D);
		}
		//System.out.print(S);
		//System.out.println(T/S);
		return T/S;
	}
	
	public static double Max(List<Double> LD) {
		double Max=Double.MAX_VALUE;
		for (double D:LD) {
			if ((!Double.isNaN(D))&(D<Max)) {
				Max=D;
			}
			//System.out.println(D);
		}
		//System.out.print(S);
		//System.out.println(T/S);
		return Max;
	}
	
	
	public static String inverseString(String chaine) {
        StringBuilder result = new StringBuilder();
        for (int i = chaine.length() - 1; i >= 0; i--) {
            result.append(chaine.charAt(i));
        }
        return result.toString();
    }
	
	// A FAIRE
	
	public String Get_Grandeurs() {
		return this.grandeur;
	}

	
	//-------------------------------------------------------------------------//
	public void ecrire() throws IOException {
		System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		System.out.println(this.Get_Grandeurs());
		System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		File F=new File("C:/Users/gabri/Documents/A3/Projet_Lapin/Wk_python/SOLO/Solo_Moy_".concat(this.Get_Grandeurs()).concat("_").concat(this.produit).concat("_").concat(".txt"));
		FileWriter FW=new FileWriter (F);
		System.out.println("AAAAA");
		for (double f: this.L_freqMoy) {
			if (!Double.isNaN(f)) {
				FW.write(String.valueOf(f));
				FW.write("\n");
			}
			
		}

		FW.close();
	}
	
	public static void main(String[] args) throws IOException {
		Extraction_SOLO E_SOLO1=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","adrenaline","pressionRespiratoire");
		Extraction_SOLO E_SOLO2=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","","pressionRespiratoire");
		Extraction_SOLO E_SOLO3=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","acetylcholine","pressionRespiratoire");
		Extraction_SOLO E_SOLO4=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","adrenaline","pressionArterielleMoyenne");
		Extraction_SOLO E_SOLO5=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","","pressionArterielleMoyenne");
		Extraction_SOLO E_SOLO6=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","acetylcholine","pressionArterielleMoyenne");
		Extraction_SOLO E_SOLO7=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","adrenaline","frequenceCardiaque");
		Extraction_SOLO E_SOLO8=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","","frequenceCardiaque");
		Extraction_SOLO E_SOLO9=new Extraction_SOLO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","acetylcholine","frequenceCardiaque");
		E_SOLO1.ecrire();
		E_SOLO2.ecrire();
		E_SOLO3.ecrire();
		E_SOLO4.ecrire();
		E_SOLO5.ecrire();
		E_SOLO6.ecrire();
		E_SOLO7.ecrire();
		E_SOLO8.ecrire();
		E_SOLO9.ecrire();
	}

}
