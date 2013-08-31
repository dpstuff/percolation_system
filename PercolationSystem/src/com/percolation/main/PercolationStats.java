package com.percolation.main;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


/**
 * Testing Class for Percolation object
 * Runs a Monte-Carlo Simulation on a N * N table T times 
 * and outputs how many times the system percolates
 * http://en.wikipedia.org/wiki/Percolation
 * @author Dejan Pekovic
 *
 */





public class PercolationStats 
{
	
	private double percolationtThreshold[]; // table of results gained from running Simulation T times
	private int T; // times we run the simulation
	
	/** 
	 * perform T independent computational experiments on an N-by-N grid
	 * @param N size of the table N * N
	 * @param T times simulation plays
	 */
	public PercolationStats (int N, int T) 
	{
		this.T = T; 
		percolationtThreshold = new double[T];
		
		int runTimes = 0;
		while ( runTimes < T)
		{
			Percolation per = new Percolation(N);
			int perTrashhold = 0;
			while (per.percolates() == false)
			{
				int i = 1 + StdRandom.uniform(N);
				int j = 1 + StdRandom.uniform(N);
				// System.out.println(i + ", " + j); // Random number tester
				if (!per.isOpen(i, j))
				{
					per.open(i, j);
					perTrashhold++;
				}
			}
			double threshold = perTrashhold / Math.pow(N, 2);
			// System.out.println(threshold);
			
			percolationtThreshold[runTimes] = threshold;
			runTimes++;
		}
	}
	
	/**
	 *  sample mean of percolation threshold
	 * @return returns statistical main of this simulation
	 */
	public double mean()
	{		
		return StdStats.mean(percolationtThreshold);
	}
	
	/**
	 *  sample standard deviation of percolation threshold
	 * @return returns standard deviation of the sample size
	 */
	public double stddev()
	{
		return StdStats.stddev(percolationtThreshold);
	}
	
	/**
	 * Lower bound of the 95% confidence interval if T > 15
	 * @return returns lower bound of the 95% confidence interval
	 */
	public double confidenceLo()
	{
		return mean() - (1.96 * stddev()) / Math.sqrt(this.T);
	}
	
	/**
	 *  Upper bound of the 95% confidence interval if T > 15
	 * @return Upper bound of the 95% confidence interval
	 */
	public double confidenceHi()
	{
		return mean() + (1.96 * stddev()) / Math.sqrt(this.T);		
	}
		
	/**
	 * Test client 
	 * @param args input 1 size of the Table, input 2 number of repeat operations	 
	 */	
	public static void main (String[] args)
	{
		
		int sizeOfTable = Integer.parseInt(args[0]);
		int numberOfRepeats = Integer.parseInt(args[1]);;
		if (sizeOfTable <=0 || sizeOfTable <=0) throw  new IllegalArgumentException("Cant be lower than 1"); 
			
		PercolationStats ps = new PercolationStats(sizeOfTable, numberOfRepeats);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
		
	}

}