package generationDonnees;

import java.util.Random;

public class FonctionQuadratique {
	
	private double theta0;
	private double theta1;
	private double theta2;
	
	public FonctionQuadratique(double theta0,double theta1, double theta2) {
		this.theta0=theta0;
		this.theta1=theta1;
		this.theta2=theta2;
	}
	public FonctionQuadratique(double theta0,double muPourThetha1,double ecartPourTheta1,double minTheta1,double maxTheta1,double coeffA2Regression,double coeffB2Regression) {
		this.theta0=theta0;
		this.theta1=generatePremierTheta(muPourThetha1,ecartPourTheta1,minTheta1,maxTheta1);
		this.theta2=generateDeuxiemeTheta(theta1, coeffA2Regression, coeffB2Regression);
	}

	public static double generateNormalRandomNumber(double mu,double ecart) {
		Random gen = new Random();
		return gen.nextGaussian()*ecart+mu;
	}
	private double generatePremierTheta(double muPourThetha1, double ecartPourTheta1,double minTheta1,double maxTheta1) {
		
		double theta1 = generateNormalRandomNumber(muPourThetha1, ecartPourTheta1);
		while(theta1<minTheta1||theta1>maxTheta1) {
			theta1 =generateNormalRandomNumber(muPourThetha1, ecartPourTheta1);
		}
		return theta1;
	}
	@Override
	public String toString() {
		return "FonctionQuadratique [" +theta0 + " + " + theta1 + " x + " + theta2 + " x^2]";
	}
	/**
	 * Si thetha2 = coeffA2Regression * thetha1 + coeffB2Regression
	 * @param theta1
	 * @param coeffA2Regression
	 * @param coeffB2Regression
	 * @return thetha2
	 */
	
	private double generateDeuxiemeTheta(double theta1, double coeffA2Regression, double coeffB2Regression) {
			double tetha2=(coeffA2Regression*theta1)+coeffB2Regression;
			return tetha2;
	}
	
	public double getMaxMinX() {
		return (-this.theta1/(2*this.theta2));
	}
	
	public double getMaxMinY() {
		return (this.theta0-this.theta1*this.theta1/(2*this.theta2)+this.theta1*this.theta1/(4*this.theta2));
	}
	
	public double getValue(double x) {
		return (this.theta0+this.theta1*x+this.theta2*x*x);
	}
	public double getTheta0() {
		return theta0;
	}
	public double getTheta1() {
		return theta1;
	}
	public double getTheta2() {
		return theta2;
	}
	public static FonctionQuadratique createFonctionMonteAdrenaline(double thetha0) {
		FonctionQuadratique function = new FonctionQuadratique(thetha0,
				5.4453, 2.3055, //mu et ecart de la distribution normal de thetha1
				1, 11, //min et max de thetha1
				-0.0366, 0.0795);//coef de la regression entre theha1 et thetha2
		return function;
	}
	
	public static FonctionQuadratique createFonctionDescendAdrenaline(double thetha0) {
		FonctionQuadratique function = new FonctionQuadratique(thetha0,
				-0.250888375, 0.102409935, //mu et ecart de la distribution normal de thetha1
				-0.42, -0.07, //min et max de thetha1
				-0.0028, -0.0004);//coef de la regression entre theha1 et thetha2
		while (function.getMaxMinY()>thetha0 /*theta0debut*/) {
			function =createFonctionDescendAdrenaline(thetha0);
		}
				
		return function;
	}

}
