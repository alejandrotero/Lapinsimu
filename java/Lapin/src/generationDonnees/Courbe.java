package generationDonnees;

public class Courbe {
	public FonctionQuadratique fMont;
    public FonctionQuadratique fDesc;
    public int timeOfStart;
    public Double valeurInitial;
    public Double valeurFinal;
    
	public Courbe(int timeOfStart, Double valeurInitial) {
		this.timeOfStart = timeOfStart;
		this.valeurInitial = valeurInitial;
		this.fMont = FonctionQuadratique.createFonctionMonteAdrenaline(valeurInitial);
		this.fDesc = createFonctionDescend(fMont);
		this.valeurFinal = generateValeurFinal(valeurInitial);
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
		return valeurInitial+delta;
	}

	private FonctionQuadratique createFonctionDescend(FonctionQuadratique fMont) {
		FonctionQuadratique fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		while (fdes.getMaxMinY()>valeurInitial) {
			fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		}
		return fdes;
	}
	/**
	 * 
	 * @param i
	 * @return le valeur de la courbe, null si la courbe est fini
	 */
	public Double getValeur(double i) {
		double valeurTimeFonction = i-timeOfStart;
		//System.out.println("valeurTimeFonction : "+valeurTimeFonction);
		Double result;
		if (valeurTimeFonction<=fMont.getMaxMinX()) {
			result = fMont.getValue(valeurTimeFonction);
		} else {
			result = fDesc.getValue(valeurTimeFonction-fMont.getMaxMinX());
			if (result<=valeurFinal) {
				result=valeurFinal;
			}
		}
		//System.out.println("result : "+result);
		return result;
		
	}
}
