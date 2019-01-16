package generationDonnees;

public class FonctionQuadratique {
	
	private int theta0;
	private int theta1;
	private int theta2;
	
	public FonctionQuadratique(int theta0,int theta1, int theta2) {
		this.theta0=theta0;
		this.theta1=theta1;
		this.theta2=theta2;
	}
	
	double getValue(double x) {
		return (this.theta0+this.theta1*x+this.theta2*x*x);
	}
	

}
