package generationDonnees;

public class FonctionQuadratique {
	
	private double theta0;
	private double theta1;
	private double theta2;
	
	public FonctionQuadratique(double theta0,double theta1, double theta2) {
		this.theta0=theta0;
		this.theta1=theta1;
		this.theta2=theta2;
	}
	public FonctionQuadratique(double theta0,double theta1) {
		this.theta0=theta0;
		this.theta1=theta1;
		this.theta2=generateDeuxiemeTheta(theta1);
	}
	
private double generateDeuxiemeTheta(double theta1) {
	//TODO parametrize les coef de la 2eme regression
		return (2.4458-theta1)/-24.991;
	}
	double getValue(double x) {
		return (this.theta0+this.theta1*x+this.theta2*x*x);
	}
	

}
