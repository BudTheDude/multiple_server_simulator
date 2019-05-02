package interfata;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import simulation.HW2.DistributeTasks;
import simulation.HW2.Serv;

public class Parameters extends JFrame{
	
	private int nrOfServers;
	
	JLabel serverLabel= new JLabel("Total number of servers:");
	JTextField totalServers=new JTextField(5);
	
	JLabel finishLabel= new JLabel("End Simulation at:");
	JTextField finishingTime=new JTextField(5);
	
	JLabel minLabel= new JLabel("Minimum time between clients:");
	JTextField minTime=new JTextField(5);
	
	JLabel maxLabel= new JLabel("Maximum time between clients:");
	JTextField maxTime=new JTextField(5);
	
	JLabel minClientLabel= new JLabel("Minimum processing time:");
	JTextField minClientTime=new JTextField(5);
	
	JLabel maxClientLabel= new JLabel("Maximum processing time:");
	JTextField maxClientTime=new JTextField(5);

	
	JPanel pan=new JPanel();
	JButton OK=new JButton("OK");
	
	public Parameters() {
		this.add(pan);
		this.setVisible(true);
		this.setSize(300, 300);
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		
		pan.add(serverLabel);
		pan.add(totalServers);
		pan.add(finishLabel);
		pan.add(finishingTime);
		
		pan.add(minLabel);
		pan.add(minTime);
		
		pan.add(maxLabel);
		pan.add(maxTime);
		
		pan.add(minClientLabel);
		pan.add(minClientTime);
		
		pan.add(maxClientLabel);
		pan.add(maxClientTime);
		
		
		pan.add(OK);
		this.nrOfServers=3;
		Handler h=new Handler();
		OK.addActionListener(h);
		OK.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==OK) {
				nrOfServers=Integer.parseInt(totalServers.getText());
				Display fata=new Display(nrOfServers);
				Serv s[]=new Serv[nrOfServers];
				for(int i=0;i<nrOfServers;i++) {
					s[i]=new Serv(fata,i,true);
				}
				DistributeTasks d=new DistributeTasks(Integer.parseInt(minTime.getText()),Integer.parseInt(maxTime.getText()),100,Integer.parseInt(maxClientTime.getText()),Integer.parseInt(minClientTime.getText()),Integer.parseInt(finishingTime.getText()),s,fata);
				Thread servThread[]=new Thread[nrOfServers];
				
				for(int i=0;i<nrOfServers;i++) {
					servThread[i]=new Thread(s[i]);
				}
				
				Thread t=new Thread(d);
				t.start();
				for(int i=0;i<nrOfServers;i++) {
					servThread[i].start();
				}
				
				
			
			}
			
		}
		

}

public static void main(String[] args) {
	
	Parameters sim=new Parameters();
	
}
}
