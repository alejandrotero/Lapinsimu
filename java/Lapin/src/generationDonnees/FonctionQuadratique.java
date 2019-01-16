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
	public FonctionQuadratique(double theta0,double muPourThetha1,double varPourTheta1,double coeffA2Regression,double coeffB2Regression) {
		this.theta0=theta0;
		this.theta1=generatePremierTheta(muPourThetha1,varPourTheta1);
		this.theta2=generateDeuxiemeTheta(theta1, coeffA2Regression, coeffB2Regression);
	}
	
private double generatePremierTheta(double muPourThetha1, double varPourTheta1) {
		Random gen = new Random();
		double rnd = gen.nextGaussian();
		return rnd*varPourTheta1+muPourThetha1;
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
		return (coeffA2Regression*theta1)+coeffB2Regression;
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
	

}
