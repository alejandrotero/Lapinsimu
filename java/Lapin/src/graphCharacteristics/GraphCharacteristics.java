package graphCharacteristics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GraphCharacteristics {
	public Data data;
	
	public void setData(Data data) {
		this.data = data;
	}



	public double getMoyenne(List<Double> points) {
	 double result=0;
	 for (double element : points) {
		result+= element;
	}
	 return result/points.size();
	}



	public static void main(String[] args) {
		//List<EventInTheTrace> eventsInTheTrace = individualAnalyse("data/sequence5-2.txt");
		List<EventInTheTrace> eventsInTheTrace = multipleAnalyse("data");
		
		for (EventInTheTrace eventInTheTrace : eventsInTheTrace) {
			System.out.println(eventInTheTrace.toString());
			System.out.println();
		}
	}
	
	private static List<EventInTheTrace> multipleAnalyse(String folderPath) {
		File rep = new File(folderPath);
		File[] fichiersTxt = rep.listFiles();
		List<EventInTheTrace> eventsInTheTrace = new ArrayList<>();
		
		for (File F: fichiersTxt) {
			eventsInTheTrace.addAll(individualAnalyse(F.getAbsolutePath()));
		}
		return eventsInTheTrace;
	}



	private static List<EventInTheTrace> individualAnalyse(String path) {
		GraphCharacteristics chara = new GraphCharacteristics();
		Data data = new Data(path);
		chara.setData(data);
		data.eliminateRepeatedPAMoyenne();
		double pourcentageOfChangement = 0.2;
		List<EventInTheTrace> eventsInTheTrace = chara.getVariations(data.getUniqueTimes(), data.getPressionArterielleMoyenne(), 10, pourcentageOfChangement);
		boolean ended = false;
		while (eventsInTheTrace.size()==0 && !ended) {
			pourcentageOfChangement-=0.05;
			if (pourcentageOfChangement<=0) {
				ended = true;
				System.out.println("WARNING! no events found in the dataset");
			} else {
				eventsInTheTrace = chara.getVariations(data.getUniqueTimes(), data.getPressionArterielleMoyenne(), 10, pourcentageOfChangement);
			}
		}
		return eventsInTheTrace;
	}



	private List<EventInTheTrace> getVariations(List<Double> listTimes,List<Double> listValues, int parameterKMoyenne, double porcentageToChange) {
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
		double firstTimeinDB=listTimes.get(0);
		double firstValueinDB=listValues.get(0);
		
		for (int i = 0; i < listTimes.size(); i++) {
			double actualValue =listValues.get(i);
			double variation = actualValue - average;  
			if (Math.abs(variation)>average*porcentageToChange) {
				
				if (checkProximity(eventsInTheTrace,listTimes.get(i))) {
					if (newEvent!=null) {
						//System.out.println("WARNING! at the time "+listTimes.get(i)+" one event started before another event ended.");
					}else {
						//Special case when the changement is in the first values
						int indexForKMoyenne = i-((int)parameterKMoyenne/2);
						if (indexForKMoyenne<0) {
							indexForKMoyenne=i;
						}
						double timeOfVariation = listTimes.get(indexForKMoyenne);
						double valueInTheTimeOfVariation =valuesForAverage.get((int)parameterKMoyenne/2);
						averageBeforeExtreme=average;
						newEvent = new EventInTheTrace(eventsInTheTrace.size(),data.getNomFicher(),firstTimeinDB,firstValueinDB);
						newEvent.setStartingTime(timeOfVariation, valueInTheTimeOfVariation);
						eventsInTheTrace.add(newEvent);
						if (variation<0) {
							extremeValue=Double.MAX_VALUE;
							newEvent.setPeak(false);
							//System.out.println("Negative changement in the time "+getStringTime(listTimes.get(i)));
						} else {
							extremeValue=Double.MIN_VALUE;
							newEvent.setPeak(true);
							//System.out.println("Positive changement in the time "+getStringTime(listTimes.get(i)));
						}
					}
					
					
				}
				
			}
			
			//Update of the average
			average -= valuesForAverage.get(0)/parameterKMoyenne;
			valuesForAverage.remove(0);
			average += actualValue/parameterKMoyenne;
			valuesForAverage.add(actualValue);
			
			//Looks for the top of the peak or the bottom of the valley
			if (newEvent!=null) {
				boolean extremeValueChanged = false;
				if (!newEvent.isPeak() && actualValue<extremeValue) {
					extremeValue=actualValue;
					timeOfExtreme=listTimes.get(i);
					extremeValueChanged=true;
					//System.out.println("minupdated"+peak+valley);
				} else if (newEvent.isPeak() && actualValue>extremeValue) {
					extremeValue=actualValue;
					timeOfExtreme=listTimes.get(i);
					extremeValueChanged=true;
					//System.out.println("maxupdated"+peak+valley);
				}
				
				//Check if the average returned to normal value
				if (!extremeValueChanged||i==listTimes.size()-1) {
					if (Math.abs(averageBeforeExtreme-average)<(averageBeforeExtreme*porcentageToChange*0.1)||i==listTimes.size()-1) {
						newEvent.setTimeOfExtremePoint(timeOfExtreme, extremeValue);
						//System.out.println("Returned to normal comportament in the time "+getStringTime(listTimes.get(i))+"average before: "+averageBeforeExtreme+", average after: "+average);
						newEvent.setEndingTime(listTimes.get(i), actualValue);
						averageBeforeExtreme=0;
						if (i==listTimes.size()-1) {
							newEvent.setEndedBeforeDataFinished(false);
							//System.out.println("The data finished before arriving to the normal value");
						}
						
						if (!newEvent.isValide()) {
							eventsInTheTrace.remove(eventsInTheTrace.size()-1);
						}
						newEvent=null;
					}
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
	private boolean checkProximity(List<EventInTheTrace> eventsInTheTrace, Double checkingTime) {
		boolean result = true;
		if (!eventsInTheTrace.isEmpty()) {
			//At least there must be n seconds between two changements
			int n = 10;
			if (checkingTime-eventsInTheTrace.get(eventsInTheTrace.size()-1).getStartingTime()<n) {
				result = false;
			} 
		} 
		return result;
	}
}
