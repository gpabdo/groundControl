package groundcontrol.dashboard;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

public class test extends javax.swing.JFrame implements ActionListener  {
	JButton ZERO;
	JButton TWENTYFIVE;
	JButton FIFTY;
	JButton SEVENTYFIVE;
	JButton ONEHUNDRED;
	int value;
	
	public test(){
		super("Ground Control");
		ZERO = new JButton("0%");
		TWENTYFIVE = new JButton("25%");
		FIFTY = new JButton("50%");
		SEVENTYFIVE = new JButton("75%");
		ONEHUNDRED = new JButton("100%");
		
		ZERO.addActionListener(this);
		TWENTYFIVE.addActionListener(this);
		FIFTY.addActionListener(this);
		SEVENTYFIVE.addActionListener(this);
		ONEHUNDRED.addActionListener(this);
		
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		
		add(ZERO);
		add(TWENTYFIVE);
		add(FIFTY);
		add(SEVENTYFIVE);
		add(ONEHUNDRED);
		pack();
		setVisible(true);
	}
	
	public int getSelection(){
		return value;
	}
	
	public void actionPerformed( ActionEvent evt ){
		Object source = evt.getSource();
		if(source == ZERO)
			value = 0;
		if(source == TWENTYFIVE)
			value = 25;
		if(source == FIFTY)
			value = 50;
		if(source == SEVENTYFIVE)
			value = 75;
		if(source == ONEHUNDRED)
			value = 100;
	}
}
