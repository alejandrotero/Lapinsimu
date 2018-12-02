package graphCharacteristics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GraphCharacteristics {
	public static List<Double> time;
	public List<Double> pressionArterielle;
	public List<Double> pressionRespiratiore;
	public static List<Double> pressionArterielleMoyenne;
	
	public double getMoyenne(List<Double> points) {
	 double result=0;
	 for (double element : points) {
		result+= element;
	}
	 return result/points.size();
	}
	
	public void readFile(String filePath) {
		try{
			InputStream flux=new FileInputStream(filePath); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){
				String[] columnDetail = new String[6];
				ligne = ligne.replace(',','.');
				columnDetail = ligne.split("\\t");
				time.add(Double.parseDouble(columnDetail[0]));
				pressionArterielle.add(Double.parseDouble(columnDetail[1]));
				pressionRespiratiore.add(Double.parseDouble(columnDetail[2]));
				pressionArterielleMoyenne.add(Double.parseDouble(columnDetail[3]));
			}
			buff.close(); 
			}		
			catch (Exception e){
			}
	}
	
	public GraphCharacteristics() {
		time= new ArrayList<>();
		pressionArterielle = new ArrayList<>();
		pressionRespiratiore = new ArrayList<>();
		pressionArterielleMoyenne = new ArrayList<>();
	}

	public static void main(String[] args) {
		GraphCharacteristics chara = new GraphCharacteristics();
		//chara.readFile("C:/Users/ASUS/Documents/Lapin/src/graphCharacteristics/g1Adre.txt");
		chara.readFile("C:/Users/ASUS/Documents/Lapin/src/graphCharacteristics/groupe 1.txt");
		List<Double> unrepeatedTimes = eliminateRepeated(pressionArterielleMoyenne);
		getVariations(unrepeatedTimes, pressionArterielleMoyenne, 10, 0.2);
	}
	private static String getStringTime(double timeInSeconds) {
		String result = timeInSeconds + " seconds ( ";
		int minutes = (int)(timeInSeconds/60);
		int secondes = (int) (timeInSeconds - (minutes*60));
		result += minutes +":"+secondes + " minutes)";
		return result;
	}
	private static void getVariations(List<Double> listTimes,List<Double> listValues, int parameterKMoyenne, double porcentageToChange) {
		List<Double> timesWithChanges = new ArrayList<>();
		//Special case for initialization
		timesWithChanges.add(-100.0);
		List<Double> valuesForAverage = new ArrayList<>();
		double average=0;
		//Initialization moyenne
		for (int i = 0; i < parameterKMoyenne; i++) {
			valuesForAverage.add(listValues.get(i));
			average += valuesForAverage.get(i);
		}
		average/=parameterKMoyenne;
		//Check all the points
		boolean peak = false;
		boolean valley = false;
		double extremeValue = 0;
		double timeOfExtreme = 0;
		double averageBeforeExtreme = 0;
		
		
		for (int i = 0; i < listTimes.size(); i++) {
			double actualValue =listValues.get(i);
			double variation = actualValue - average ;  
			if (Math.abs(variation)>average*porcentageToChange) {
				//At least there must be n seconds between two changements
				int n = 2;
				if (listTimes.get(i)-timesWithChanges.get(timesWithChanges.size()-1)>n) {
					timesWithChanges.add(listTimes.get(i));
					//Prints the value of the extreme value if the peak or valley ended
					//System.out.println("1");
					//ended(peak,valley,extremeValue,timeOfExtreme);
					if (variation<0) {
						valley= true;
						peak=false;
						extremeValue=Double.MAX_VALUE;
						averageBeforeExtreme=average;
						System.out.println("Negative changement in the time "+getStringTime(listTimes.get(i)));
					} else {
						peak = true;
						valley=false;
						extremeValue=Double.MIN_VALUE;
						averageBeforeExtreme=average;
						System.out.println("Positive changement in the time "+getStringTime(listTimes.get(i)));
					}
					//average = (actualValue+average)/2;
				}
				
			}
			//Looks for the top of the peak or the bottom of the valley
			if (valley && actualValue<extremeValue) {
				extremeValue=actualValue;
				timeOfExtreme=listTimes.get(i);
				//System.out.println("minupdated"+peak+valley);
			} else if (peak && actualValue>extremeValue) {
				extremeValue=actualValue;
				timeOfExtreme=listTimes.get(i);
				//System.out.println("maxupdated"+peak+valley);
			}
			
			//Update of the average
			average -= valuesForAverage.get(0)/parameterKMoyenne;
			valuesForAverage.remove(0);
			average += actualValue/parameterKMoyenne;
			valuesForAverage.add(actualValue);
			
			//Check if the average returned to normal value
			if (valley||peak) {
				if (Math.abs(averageBeforeExtreme-average)<(averageBeforeExtreme*porcentageToChange*0.1)) {
					//System.out.println("2");
					ended(peak,valley,extremeValue,timeOfExtreme);
					System.out.println("Returned to normal comportament in the time "+getStringTime(listTimes.get(i))+"average before: "+averageBeforeExtreme+", average after: "+average);
					averageBeforeExtreme=0;
				}
			}
		}
	}
	private static void ended(boolean peak, boolean valley, double extremeValue, double timeOfExtreme) {
		if (valley) {
			System.out.println("Minimun value: "+extremeValue+" at "+ getStringTime(timeOfExtreme));
			valley=false;
			peak=false;
		}else if (peak) {
			System.out.println("Maximun value: "+extremeValue+" at "+getStringTime(timeOfExtreme));
			peak=false;
			valley=false;
		}
		
	}

	private static List<Double> eliminateRepeated(List<Double> listAELiminerDOublons) {
		List<Double> uniqueTimes = new ArrayList<>();
		List<Double> uniqueValues = new ArrayList<>();
		uniqueTimes.add(time.get(0));
		uniqueValues.add(listAELiminerDOublons.get(0));
		for (int i = 1; i < listAELiminerDOublons.size(); i++) {
			if (listAELiminerDOublons.get(i-1).doubleValue()!=listAELiminerDOublons.get(i).doubleValue()) {
				uniqueTimes.add(time.get(i));
				uniqueValues.add(listAELiminerDOublons.get(i));
			}
		}
		listAELiminerDOublons.clear();
		listAELiminerDOublons.addAll(uniqueValues);
		return uniqueTimes;
	}

}
