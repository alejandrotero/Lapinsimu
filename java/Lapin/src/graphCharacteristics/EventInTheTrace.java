package graphCharacteristics;
/**
 * Class pour identifier les characteristiques principales de une courbe donnée
 *
 */
public class EventInTheTrace {
	//true if peak, false if valley
	private boolean peak;
	public boolean isPeak() {
		return peak;
	}
	public void setPeak(boolean peak) {
		this.peak = peak;
	}
	private double startingTime;
	private double startingTimeValue;
	private double timeOfExtremePoint;
	private double extremePointValue;
	private double endingTime;
	private double endingTimeValue;
	//Une courbe peut avoir plusieurs events, alors index dit le nombre de l'event courant dans la courbe
	private int index;
	public String nomFicher;
	//True if this event finished before the data finished, false if not
	private boolean endedBeforeDataFinished;
	private double firstTimeinDB;
	public int getIndex() {
		return index;
	}
	public String getNomFicher() {
		return nomFicher;
	}
	public double getFirstTimeinDB() {
		return firstTimeinDB;
	}
	public double getFirstValueinDB() {
		return firstValueinDB;
	}
	private double firstValueinDB;
	
	public double getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(double startingTime,double startingTimeValue) {
		this.startingTimeValue = startingTimeValue;
		this.startingTime = startingTime;
	}
	public double getStartingTimeValue() {
		return startingTimeValue;
	}
	public double getTimeOfExtremePoint() {
		return timeOfExtremePoint;
	}
	public void setTimeOfExtremePoint(double timeOfExtremePoint,double extremePointValue) {
		this.extremePointValue = extremePointValue;
		this.timeOfExtremePoint = timeOfExtremePoint;
	}
	public double getExtremePointValue() {
		return extremePointValue;
	}
	public double getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(double endingTime,double endingTimeValue) {
		this.endingTimeValue = endingTimeValue;
		this.endingTime = endingTime;
	}
	
	public double getEndingTimeValue() {
		return endingTimeValue;
	}
	/**
	 * Constructeur
	 * @param startingTime
	 * @param startingTimeValue
	 * @param timeOfExtremePoint
	 * @param extremePointValue
	 * @param endingTime
	 * @param endingTimeValue
	 * @param peak
	 */
	public EventInTheTrace(double startingTime, double startingTimeValue, double timeOfExtremePoint,
			double extremePointValue, double endingTime, double endingTimeValue, boolean peak) {
		this.startingTime = startingTime;
		this.startingTimeValue = startingTimeValue;
		this.timeOfExtremePoint = timeOfExtremePoint;
		this.extremePointValue = extremePointValue;
		this.endingTime = endingTime;
		this.endingTimeValue = endingTimeValue;
		this.peak = peak;
	}
	/**
	 * Constructeur
	 * @param index
	 * @param nomFicher
	 * @param firstTimeinDB
	 * @param firstValueinDB
	 */
	public EventInTheTrace(int index,String nomFicher,double firstTimeinDB,double firstValueinDB) {
		this.index=index;
		endedBeforeDataFinished=true;
		this.nomFicher=nomFicher;
		this.firstTimeinDB = firstTimeinDB;
		this.firstValueinDB = firstValueinDB;
	}
	@Override
	public String toString() {
		return "eventInTheTrace: "+index+
				"\n [ firstTimeinDB=" + getStringTime(firstTimeinDB) + ", firstValueinDB=" + firstValueinDB
				+", \n startingTime=" + getStringTime(startingTime) + ", startingTimeValue=" + startingTimeValue
				+ ", \n timeOfExtremePoint=" + getStringTime(timeOfExtremePoint) + ", extremePointValue=" + extremePointValue
				+ ", \n endingTime=" + getStringTime(endingTime) + ", endingTimeValue=" + endingTimeValue +
				 ", \n peak=" + peak+" ,completeData=" + endedBeforeDataFinished +
				 ", \n nomFicher=" + nomFicher+" ]";
	}
	/**
	 * 
	 * @param timeInSeconds
	 * @return the time in a format mm:ss
	 */
	private static String getStringTime(double timeInSeconds) {
		String result = timeInSeconds + " seconds ( ";
		int minutes = (int)(timeInSeconds/60);
		int secondes = (int) (timeInSeconds - (minutes*60));
		result += minutes +":"+secondes + " minutes)";
		return result;
	}
	public boolean isEndedBeforeDataFinished() {
		return endedBeforeDataFinished;
	}
	public void setEndedBeforeDataFinished(boolean endedBeforeDataFinished) {
		this.endedBeforeDataFinished = endedBeforeDataFinished;
	}
	/**
	 *If the event started and ended in a small period of time (3 seconds) we assume that it's an error of the data collection
	 * @return
	 */
	public boolean isValide() {
		if (endingTime-startingTime<3) {
			return false;
		} else {
			return true;
		}
	}

}
