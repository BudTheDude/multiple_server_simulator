package simulation.HW2;

public class Task {
	private int ID;
	private int arrivalTime;
	private int processingTime;
	private int waitingTime;
	
	public Task(int ID, int processingTime) {
		this.ID=ID;
		this.processingTime=processingTime;
	}
	
	public void setArrival(int s) {
		this.arrivalTime=s;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public int getarrival() {
		return this.arrivalTime;
	}
	
	public int getproc() {
		return this.processingTime;
	}
	
	public int getWait() {
		return waitingTime;
	}
	
	public void setWait(int w) {
		this.waitingTime=w;
	}
	
}
