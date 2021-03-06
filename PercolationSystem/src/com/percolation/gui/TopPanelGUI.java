package com.percolation.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.percolation.main.PercolationStats;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Top panel of our gui, it uses PercolationStats class
 * It has two fields for entering parameters
 * First is the size of the table
 * Second is the number of times the simulation plays
 * @author deki
 */
public class TopPanelGUI extends JPanel {
		
	private static final long serialVersionUID = 1L;
	JTextField field1; // text in the first text field
	JTextField field2; // text in the second text field
	
	public TopPanelGUI() {
		Dimension size = getPreferredSize();
		size.width = 450;
		size.height = 150; 
		setPreferredSize(size);
		
		//set Border and Title
		setBorder(BorderFactory.createTitledBorder("Monte Carlo Simulation"));
		
		// add Swing items to this panel
		JLabel label1 = new JLabel("N - Size of the Table (N * N): ");
		JLabel label2 = new JLabel("T - Number Of Runs: ");
		
		field1 = new JTextField(11);
		field2 = new JTextField(11);
		
		JButton button = new JButton("Start Simulation");
		// add action listener and event
		button.addActionListener(new ActionListener() {
			
			// On click this starts ResultScreenGui JFrame class and passes the PreculationStats data
			@Override
			public void actionPerformed(ActionEvent e) {
				int N = getN();
				int T = getT();
				
				Stopwatch stopwatch = new Stopwatch();
				PercolationStats ps = new PercolationStats(N, T);
				
				// results of running PercolationStats object after passing N and T  
				// N - size of the Table (N * N)
				// T - times object runs the simulation
				String runTime= "" + stopwatch.elapsedTime(); // gets run Time
				String mean = 	"" + ps.mean(); // gets Mean - look at Statistics
				String stddev = "" + ps.stddev(); // gets Standard Deviation
				String HiLo = 	"" + ps.confidenceLo() + ", " + ps.confidenceHi(); // gets 95% confidence interval:
				ResultScreenGUI result = new ResultScreenGUI(mean, stddev, HiLo, runTime);
				result.pack();
				result.setVisible(true);
			}
		});
		
		// set layout manager and add constraints
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		// adding first row of layout - - label, text field
		gc.anchor = GridBagConstraints.LINE_END;
		
		gc.gridx = 0;
		gc.gridy = 0;		
		add(label1, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;		
		add(field1, gc);
		
		// adding second row - label, text field
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(label2, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(field2, gc);
		
		// add third row - button
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add(button, gc);
	
	}
	
	/**
	 * Transform the input of text field to a Integer
	 * and returns that Integer
	 * @return returns Integer N that the user entered
	 */	
	private int getN()
	{
		int numberN = Integer.parseInt(field1.getText()) ;
		return numberN;
		
	}
	
	/**
	 * Transform the input of text field to a Integer
	 * and returns that Integer
	 * @return returns Integer T that the user entered
	 */
	private int getT()
	{
		int numberT = Integer.parseInt(field2.getText()) ;
		return numberT;
		
	}
}
