package generationDonnees;

public class Courbe {
	//Fonction quadratique qui represent la monté de la courbe
	public FonctionQuadratique fMont;
	//Fonction quadratique qui represent la descend de la courbe
    public FonctionQuadratique fDesc;
    //Temps initial de la courbe, par exemple si la courbe represent l'adrenaline ce valeur serait le temps ou le lapin a eu l'injection
    public int timeOfStart;
    //Valeur initial de la courbe
    public Double valeurInitial;
    //Valeur final de la courbe, quand elle return a un valeur normal
    public Double valeurFinal;
    
    /**
     * Constructeur de un nouvel courbe d'adrenaline
     * @param timeOfStart
     * @param valeurInitial
     */
	public Courbe(int timeOfStart, Double valeurInitial) {
		this.timeOfStart = timeOfStart;
		this.valeurInitial = valeurInitial;
		this.fMont = createFonctionMonte(valeurInitial);
		this.fDesc = createFonctionDescend(fMont);
		this.valeurFinal = generateValeurFinal(valeurInitial);
	}
	/**
	 * Fonction qui return une nouvelle fonction de monte qui commence en valeur initial
	 * @param valeurInitial
	 * @return
	 */
	private FonctionQuadratique createFonctionMonte(Double valeurInitial) {
		FonctionQuadratique fmon = FonctionQuadratique.createFonctionMonteAdrenaline(valeurInitial);
		while (fmon.getMaxMinY()>130) {
			fmon = FonctionQuadratique.createFonctionMonteAdrenaline(valeurInitial);
		}
		return fmon;
	}
	/**
	 * Returns the new finishing value of the function including a variation given by the data
	 * @param valeurInitial
	 * @return
	 */
	private Double generateValeurFinal(Double valeurInitial) {
		double delta = FonctionQuadratique.generateNormalRandomNumber(-12.03, 12.75);
		while (valeurInitial+delta<0) {
			delta = FonctionQuadratique.generateNormalRandomNumber(-12.03, 12.75);
		}
		//System.out.println("delta pour finir"+(valeurInitial+delta));
		return valeurInitial+delta;
	}
	/**
	 * Fonction qui return une nouvelle fonction de descend qui commence dans le max valeur de la fonction de monte
	 * @param fMont
	 * @return
	 */
	private FonctionQuadratique createFonctionDescend(FonctionQuadratique fMont) {
		FonctionQuadratique fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		while (fdes.getMaxMinY()>valeurInitial) {
			fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		}
		return fdes;
	}
	/**
	 * 
	 * @param i en miliseconds
	 * @return le valeur de la courbe, null si la courbe est fini
	 */
	public Double getValeur(double i) {
		double valeurTimeFonction = (i-timeOfStart)/1000;
		//System.out.println("valeurTimeFonction : "+valeurTimeFonction);
		Double result;
		if (valeurTimeFonction<=fMont.getMaxMinX()) {
			//System.out.println("mont");
			result = fMont.getValue(valeurTimeFonction);
		} else {
			//System.out.println("desc");
			result = fDesc.getValue(valeurTimeFonction-fMont.getMaxMinX());
			if (result<=valeurFinal) {
				result=null;
				//System.out.println("fini");
			}
		}
		//System.out.println("result : "+result);
		return result;
	}
}
