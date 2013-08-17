package groundcontrol.dashboard;

import groundcontrol.dashboard.test.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


/******************************************************************
 * The Dashboard class creates the GUI for interacting with the 
 * groundcontrol objects.
 *****************************************************************/
public class Dashboard extends javax.swing.JFrame implements ActionListener {
	JButton b1;
	JButton b2;
	JButton b3;
	JProgressBar bar;
	int progress;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public Dashboard(){
		super("Ground Control");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLookAndFeel();
		b1 = new JButton("Up");
		b2 = new JButton("Down");
		b3 = new JButton("Set Value");
		bar = new JProgressBar(0,100);
		progress = 50;
		bar.setValue(progress);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		
		add(b2);
		add(bar);
		add(b1);
		add(b3);
		setVisible(true);
	}
	
	/******************************************************************
	 * Listen for actoinPerformed events.
	 *****************************************************************/
	public void actionPerformed( ActionEvent evt ){
		Object source = evt.getSource();
		if(source == b1){
			System.out.println("Up");
			progress++;
		}
		if(source == b2){
			System.out.println("Down");
			progress--;
		}
		if(source == b3){
			System.out.println("Open Test window.");
			test temp = new test();
			progress = temp.getSelection();
		}
		bar.setValue(progress);
	}
	
	/******************************************************************
	 * Setup java swing look and feel.
	 *****************************************************************/
	private void setLookAndFeel(){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch(Exception e){
			System.err.println("Error in look and feel setup.");
		}
	}
}
