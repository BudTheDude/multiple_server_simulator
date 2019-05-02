package simulation.HW2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import interfata.Display;

public class Serv implements Runnable{
	
	private BlockingQueue<Task> q = new ArrayBlockingQueue<Task>(11);
	private Display i;
	String s="";
	private int wait;
	private int waitFinal;
	private int whatText;
	private int emptyTime;
	private boolean ok;
	public Serv(Display i, int whatText, boolean ok) {
		this.i=i;
		this.whatText=whatText;
		this.ok=ok;
		this.emptyTime=0;
		
	}
	public String print() {
		s="SERVER"+whatText;
		q.forEach((n)->s="Client "+n.getID()+"    "+s);
		
		return s;
	}
	public int getWaitingTime() {
		wait=0;
		q.forEach((n)->wait+=n.getproc());
		return wait;
	}
	public void run() {
		while(true)
		{
			if(q.isEmpty()==false) {
				try {
					
					for(int j=0;j<q.peek().getproc();j++) {
					waitFinal=(getWaitingTime()-j);
					i.getTextele(whatText).setText(print()+"    "+"WaitTime:"+(getWaitingTime()-j));
					emptyTime++;
					Thread.sleep(1000);
					}
					
				}catch(InterruptedException e) {}
				
				try{
					this.i.getLog2().setText(this.i.getLog2().getText()+"Server"+whatText+" processed Client"+q.peek().getID()+" at "+(q.peek().getarrival()+q.peek().getWait())+"\n");
					q.take();
					if(q.isEmpty()) {
						s="No clients"+"    SERVER"+whatText+"                       ";
						i.getTextele(whatText).setText(s);
					}
				
				}catch(InterruptedException e) {}
				
			}
			
		}
	}
	
	public BlockingQueue<Task> retQ() {
		return q;
	}
	public int  FINALwait() {
		return waitFinal;
	}

	public void setFalse() {
		ok=false;
	}
	public int retEmpty()
	{
		return emptyTime;
	}
	
	
}

