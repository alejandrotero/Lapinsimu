package generationDonnees;

public class Courbe {
	public FonctionQuadratique fMont;
    public FonctionQuadratique fDesc;
    public Long timeOfStart;
    public Double valeurInitial;
    
	public Courbe(Long timeOfStart, Double valeurInitial) {
		this.timeOfStart = timeOfStart;
		this.valeurInitial = valeurInitial;
		this.fMont = FonctionQuadratique.createFonctionMonteAdrenaline(valeurInitial);
		this.fDesc = createFonctionDescend(fMont);
	}

	private FonctionQuadratique createFonctionDescend(FonctionQuadratique fMont) {
		FonctionQuadratique fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		while (fdes.getMaxMinY()>valeurInitial) {
			fdes = FonctionQuadratique.createFonctionDescendAdrenaline(fMont.getMaxMinY());
		}
		return fdes;
	}

	public double getValeur(long currentTime) {
		double valeurTimeFonction = currentTime-timeOfStart;
		double result;
		if (valeurTimeFonction>=fMont.getMaxMinX()) {
			result = fMont.getValue(valeurTimeFonction);
		} else {
			result = fDesc.getValue(valeurTimeFonction-fMont.getMaxMinX());
			if (result<=valeurInitial) {
				result=valeurInitial;
			}
		}
		return result;
	}
}
