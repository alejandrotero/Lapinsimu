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
			System.out.println("data readed correctly, "+time.size() + " data points");
			}		
			catch (Exception e){
				System.out.println("error reading the file!");
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
		//chara.readFile("data/g1Adre.txt");
		//chara.readFile("data/sequence4.txt");
		chara.readFile("data/groupe 1.txt");
		List<Double> unrepeatedTimes = eliminateRepeated(pressionArterielleMoyenne);
		List<EventInTheTrace> eventsInTheTrace = getVariations(unrepeatedTimes, pressionArterielleMoyenne, 10, 0.2);
		for (EventInTheTrace eventInTheTrace : eventsInTheTrace) {
			System.out.println(eventInTheTrace.toString());
			System.out.println();
		}
		
	}
	
	private static List<EventInTheTrace> getVariations(List<Double> listTimes,List<Double> listValues, int parameterKMoyenne, double porcentageToChange) {
		List<EventInTheTrace> eventsInTheTrace = new ArrayList<>();
		//Special case for initialization
		List<Double> valuesForAverage = new ArrayList<>();
		double average=0;
		//Initialization moyenne
		for (int i = 0; i < parameterKMoyenne; i++) {
			valuesForAverage.add(listValues.get(i));
			average += valuesForAverage.get(i);
		}
		average/=parameterKMoyenne;
		//Check all the points
		EventInTheTrace newEvent = null;
		double extremeValue = 0;
		double timeOfExtreme = 0;
		double averageBeforeExtreme = 0;
		
		
		for (int i = 0; i < listTimes.size(); i++) {
			double actualValue =listValues.get(i);
			double variation = actualValue - average;  
			if (Math.abs(variation)>average*porcentageToChange) {
				
				if (checkProximity(eventsInTheTrace,listTimes.get(i))) {
					if (newEvent!=null) {
						System.out.println("WARNING! at the time "+listTimes.get(i)+" one event started before another event ended.");
					}
					if (variation<0) {
						extremeValue=Double.MAX_VALUE;
						averageBeforeExtreme=average;
						newEvent = new EventInTheTrace(eventsInTheTrace.size());
						newEvent.setPeak(false);
						newEvent.setStartingTime(listTimes.get(i), actualValue);
						eventsInTheTrace.add(newEvent);
						//System.out.println("Negative changement in the time "+getStringTime(listTimes.get(i)));
					} else {
						extremeValue=Double.MIN_VALUE;
						averageBeforeExtreme=average;
						newEvent = new EventInTheTrace(eventsInTheTrace.size());
						newEvent.setPeak(true);
						newEvent.setStartingTime(listTimes.get(i), actualValue);
						eventsInTheTrace.add(newEvent);
						//System.out.println("Positive changement in the time "+getStringTime(listTimes.get(i)));
					}
					//average = (actualValue+average)/2;
				}
				
			}
			
			//Update of the average
			average -= valuesForAverage.get(0)/parameterKMoyenne;
			valuesForAverage.remove(0);
			average += actualValue/parameterKMoyenne;
			valuesForAverage.add(actualValue);
			
			//Looks for the top of the peak or the bottom of the valley
			if (newEvent!=null) {
				if (!newEvent.isPeak() && actualValue<extremeValue) {
					extremeValue=actualValue;
					timeOfExtreme=listTimes.get(i);
					//System.out.println("minupdated"+peak+valley);
				} else if (newEvent.isPeak() && actualValue>extremeValue) {
					extremeValue=actualValue;
					timeOfExtreme=listTimes.get(i);
					//System.out.println("maxupdated"+peak+valley);
				}
				
				//Check if the average returned to normal value
				if (Math.abs(averageBeforeExtreme-average)<(averageBeforeExtreme*porcentageToChange*0.1)) {
					//System.out.println("Maximun value: "+extremeValue+" at "+getStringTime(timeOfExtreme));
					newEvent.setTimeOfExtremePoint(timeOfExtreme, extremeValue);
					//System.out.println("Returned to normal comportament in the time "+getStringTime(listTimes.get(i))+"average before: "+averageBeforeExtreme+", average after: "+average);
					newEvent.setEndingTime(listTimes.get(i), actualValue);
					averageBeforeExtreme=0;
					newEvent=null;
				}
			}
			
			
			
		}
		return eventsInTheTrace;
	}
	/**
	 * 
	 * @param eventsInTheTrace
	 * @param checkingTime
	 * @return true if the last event in the trace is at least n seconds away from the last event
	 */
	private static boolean checkProximity(List<EventInTheTrace> eventsInTheTrace, Double checkingTime) {
		boolean result = true;
		if (!eventsInTheTrace.isEmpty()) {
			//At least there must be n seconds between two changements
			int n = 2;
			if (checkingTime-eventsInTheTrace.get(eventsInTheTrace.size()-1).getStartingTime()<n) {
				result = false;
			} 
		} 
		return result;
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
