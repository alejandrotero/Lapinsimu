package generationDonnees;

public class Courbe {
	public FonctionQuadratique fMont;
    public FonctionQuadratique fDesc;
    public Long timeOfStart;
    public Double valeurInitial;
    
	public Courbe(FonctionQuadratique fMont, FonctionQuadratique fDesc, Long timeOfStart, Double valeurInitial) {
		this.fMont = fMont;
		this.fDesc = fDesc;
		this.timeOfStart = timeOfStart;
		this.valeurInitial = valeurInitial;
	}

	public double getValeur(long currentTime) {
		// TODO Auto-generated method stub
		return 0;
	}
}
