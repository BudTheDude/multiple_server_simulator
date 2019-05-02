package interfata;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class  Display extends JFrame  {
	
	private JLabel avgWait=new JLabel("Avg waiting time:");
	private JLabel peak=new JLabel("Peak value:");
	private JTextArea empty=new JTextArea();
	
	private ArrayList<JLabel> box= new ArrayList<JLabel>();
	private JTextArea logClient=new JTextArea();
	private JTextArea logServer=new JTextArea();
	private JPanel big=new JPanel();
	private JPanel p=new JPanel();
	private JPanel q=new JPanel();
	private JPanel r=new JPanel();
	private int nrOfServ;
	public Display(int nrOfServ) {
		this.nrOfServ=nrOfServ;
		this.setSize(900, 700);
		this.setVisible(true);
		this.setResizable(true);
		this.add(big);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		big.add(q);
		big.add(p);
		big.setLayout(new BoxLayout(big, BoxLayout.Y_AXIS));
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		logClient.setSize(400, 100);
		
		empty.setSize(400,100);
		JScrollPane scrollPane3 = new JScrollPane(empty); 
		scrollPane3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane3.setPreferredSize(new Dimension(400, 100));
		
		JScrollPane scrollPane = new JScrollPane(logClient); 
		scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        
        JScrollPane scrollPane2 = new JScrollPane(logServer); 
		scrollPane2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(400, 100));
		
		logClient.setEditable(false);
		logClient.setVisible(true);
		for(int i=0;i<this.nrOfServ;i++) {
			box.add(new JLabel(""));
		}
		box.forEach((n)->p.add(n));
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		logClient.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		box.forEach((n)->n.setAlignmentX(Component.RIGHT_ALIGNMENT));
		q.add(scrollPane);
		q.add(r);
		r.add(avgWait);
		r.add(peak);
		p.add(scrollPane2);
		p.add(scrollPane3);
		}
	
	public JLabel getTextele(int i) {
		return box.get(i);
	}
	public JTextArea getLog() {
		return logClient;
	}
	public JTextArea getLog2() {
		return logServer;
	}
	public JTextArea getLog3() {
		return empty;
	}
	public JLabel getWait() {
		return avgWait;
	}
	public JLabel getPeakt() {
		return peak;
	}
	

}
