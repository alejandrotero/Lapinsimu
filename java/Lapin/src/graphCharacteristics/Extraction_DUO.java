package graphCharacteristics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Extraction_DUO {
	
	private List<Double> L_freqMoy;
	private List<Double> L_freqMoy2;
	private String grandeur1;
	private String grandeur2;
	private String produit1;
	private String produit2;
	
	
	public Extraction_DUO(String FilePath, String Produit, String Produit2, String Grandeur1, String Grandeur2) {
		this.grandeur1=Grandeur1;
		this.grandeur2=Grandeur2;
		this.produit1=Produit;
		this.produit2=Produit2;
		
		this.L_freqMoy = new ArrayList<>();
		this.L_freqMoy2 = new ArrayList<>();
		File rep=new File(FilePath);
		File[] fichiers = rep.listFiles();
		
		int St_l=Produit.length();
		int St_l2=Produit2.length();
		
		
		for (File f : fichiers) {		
			
			
			double G1=0;
			double G2=0;
			
			
			for (File ft:f.listFiles()) {
				
				
			
				if (inverseString(ft.getName().replaceAll(".txt", "")).substring(0, St_l).toLowerCase().replaceAll("ž", "e").equals(inverseString(Produit).substring(0, St_l).toLowerCase())) {
					if(Grandeur1=="frequenceCardiaque") {
						List<Double> L = new Data(ft.getAbsolutePath()).frequenceCardiaque;
						int l=L.size();
						if(Produit=="") {
							G1=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Moy(new Data(ft.getAbsolutePath()).frequenceCardiaque);
						}
					}
					if(Grandeur1=="pressionRespiratoire") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionRespiratiore;
						int l=L.size();
						if(Produit=="") {
							G1=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Moy(new Data(ft.getAbsolutePath()).pressionRespiratiore);
						}
					}
					if(Grandeur1=="pressionArterielleMoyenne") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielleMoyenne;
						int l=L.size();
						if(Produit=="") {
							G1=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Moy(new Data(ft.getAbsolutePath()).pressionArterielleMoyenne);
						}
					}
					if(Grandeur1=="pressionArterielle") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielle;
						int l=L.size();
						if(Produit=="") {
							G1=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G1=Moy(new Data(ft.getAbsolutePath()).pressionArterielle);
						}
					}
					
					
				}
				
				
				if (inverseString(ft.getName().replaceAll(".txt", "")).substring(0, St_l2).toLowerCase().replaceAll("ž", "e").equals(inverseString(Produit2).substring(0, St_l2).toLowerCase())) {
					if(Grandeur2=="frequenceCardiaque") {
						List<Double> L = new Data(ft.getAbsolutePath()).frequenceCardiaque;
						int l=L.size();
						if(Produit2=="") {
							G2=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G2=Moy(new Data(ft.getAbsolutePath()).frequenceCardiaque);
						}
						
					}
					if(Grandeur2=="pressionRespiratoire") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionRespiratiore;
						int l=L.size();
						if(Produit2=="") {
							G2=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G2=Moy(new Data(ft.getAbsolutePath()).pressionRespiratiore);
						}
						
					}
					if(Grandeur2=="pressionArterielleMoyennee") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielleMoyenne;
						int l=L.size();
						if(Produit2=="") {
							G2=Max(L.subList((int) (0.9*l),l ));
						}
						else {
							G2=Moy(new Data(ft.getAbsolutePath()).pressionArterielleMoyenne);
						}
						
					}
					if(Grandeur2=="pressionArterielle") {
						List<Double> L = new Data(ft.getAbsolutePath()).pressionArterielle;
						int l=L.size();
						if(Produit2=="") {
							G2=Moy(L.subList((int) (0.9*l),l ));
						}
						else {
							G2=Moy(new Data(ft.getAbsolutePath()).pressionArterielle);
						}
						
					}
				}
				
			}
			
			if ((!Double.isNaN(G1))&&(!Double.isNaN(G2))&&(G1!=0)&&(G2!=0)) {
				this.L_freqMoy.add(G1);
				this.L_freqMoy2.add(G2);
				System.out.println(G1);
				System.out.println(G2);
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
		double Max=0;
		for (double D:LD) {
			if ((!Double.isNaN(D))&(D>Max)&&(D<500)) {
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
		return this.grandeur1.concat("_").concat( this.grandeur2);
	}

	
	//-------------------------------------------------------------------------//
	public void ecrire() throws IOException {
		System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		System.out.println(this.Get_Grandeurs());
		System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		File F=new File("C:/Users/gabri/Documents/A3/Projet_Lapin/Wk_python/DUO/Duo_Moy_moy_".concat(this.Get_Grandeurs()).concat("_").concat(this.produit1).concat("_").concat(this.produit2).concat("_").concat(".txt"));
		FileWriter FW=new FileWriter (F);
		File F2=new File("C:/Users/gabri/Documents/A3/Projet_Lapin/Wk_python/DUO/Duo2_Moy_moy_".concat(this.Get_Grandeurs()).concat("_").concat(this.produit1).concat("_").concat(this.produit2).concat("_").concat(".txt"));
		FileWriter FW2=new FileWriter (F2);
		System.out.println("AAAAA");
		for (double f: this.L_freqMoy) {
			if (!Double.isNaN(f)) {
				FW.write(String.valueOf(f));
				FW.write("\n");
			}
			
		}

		
		for (double f: this.L_freqMoy2) {
			System.out.println("bbb");
			if (!Double.isNaN(f)) {
				System.out.println("zrzrrrarza");
				FW2.write(String.valueOf(f));
				FW2.write("\n");
			}
			
		}
		FW.close();
		FW2.close();
	}
	
	public static void main(String[] args) throws IOException {
		Extraction_DUO Lclpb=new Extraction_DUO("C:/Users/gabri/Desktop/Cardio_rénaux_traité","acetylcholine","","frequenceCardiaque","frequenceCardiaque");
		Lclpb.ecrire();
	}

}
