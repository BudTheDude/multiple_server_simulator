package simulation.HW2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import interfata.Display;

public class DistributeTasks implements Runnable {
	private BlockingQueue<Task> q = new ArrayBlockingQueue<Task>(100);
	private int minTime;
	private int maxTime;
	private int maxClientCount;
	private int maxClientTime;
	private int minClientTime;
	private int finishingTime;
	private int peakValue;
	Random random = new Random();
	Serv[] s;
	Display i;
	private float currClients;
	private float totalWait;
	public DistributeTasks( int minTime, int maxTime, int maxClientCount, int maxClientTime,  int minClientTime, int finishingTime,Serv[] s,Display i) {
			
			this.minTime=minTime;
			this.maxTime=maxTime;
			this.maxClientCount=maxClientCount;
			this.maxClientTime=maxClientTime;
			this.minClientTime=minClientTime;
			this.s=s;
			this.i=i;
			this.finishingTime=finishingTime;
			this.peakValue=0;
			
	}
	public void place(Serv serv) {
		try{serv.retQ().put(q.take());}catch(InterruptedException e) {}
	}
	public Serv findShortest() {
		int min=999;
		int ind=0;
		for(int i=0;i<s.length;i++) {
			if(s[i].FINALwait()<min) {
				min=s[i].FINALwait();
				ind=i;
				}
		}
		return s[ind];
	}
	public void run() {
		currClients=0;
		for(int i=0;i<maxClientCount;i++) {
			Task t=new Task(i, random.nextInt(maxClientTime + 1 - minClientTime) + minClientTime);
			try{q.put(t);} catch(InterruptedException e) {}
		}
		
		int currTime=0;
		
		for(int i=0;i<finishingTime;i=i) {
			
			int randomNumber = random.nextInt(maxTime + 1 - minTime) + minTime;
			
			q.peek().setArrival(currTime);
			q.peek().setWait(q.peek().getproc()+findShortest().FINALwait());
			this.i.getLog().setText(("Client"+q.peek().getID()+": arrived at "+q.peek().getarrival()+", processing time is "+q.peek().getproc()+" , wait time:" +q.peek().getWait())+"\n"+this.i.getLog().getText());
			currTime+=randomNumber;
			currClients++;
			totalWait+=q.peek().getWait();
			
			place(findShortest());
			
			try {
				for(int j=0;j<randomNumber;j++) {
					Thread.sleep(1000);
					i++;
					if(peakValue<sumPeak()) {
						peakValue=sumPeak();
						this.i.getPeakt().setText("Peak value:"+peakValue+" at moment "+(currTime-randomNumber+j));
					}
					
					
					if(i==finishingTime) {
						for(int k=0;k<s.length;k++)
						{
							s[k].setFalse();
						}
					}
					this.i.getWait().setText("Avg waiting time:"+(totalWait/currClients));
				}
				
			}catch(InterruptedException e) {}
		}
		for(int i=0;i<s.length;i++)
		{
			this.i.getLog3().setText(this.i.getLog3().getText()+"Server"+i+" empty time:"+(finishingTime-s[i].retEmpty())+"\n");
		}
	}
	
	public int sumPeak() {
		int sum=0;
		for(int i=0;i<s.length;i++)
		{
			sum+=s[i].retQ().size();
		}
		return sum;
		
	}
}
