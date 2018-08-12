package com.kap.algorithmspartone.percolation;

import edu.princeton.cs.algs4.*;

import java.util.Scanner;

/**
 * @author Konstantinos Antoniou
 */
public class PercolationStats {

    private int experimentsNo;
    private int openSites;
    private double percolationThreshold;
    private double[] thresholds;
    private Percolation percolation;

    /**
     * Constructor responsible for performing trials independent experiments on an n-by-n grid
     *
     * @param n      size of the grid
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials) {
        if ((n <= 0) || (trials <= 0)) {
            throw new IllegalArgumentException("Invalid constructor inputs.");
        }
        experimentsNo = trials;
        thresholds = new double[experimentsNo];
        for (int expNo = 0; expNo < experimentsNo; expNo++) {
            percolation = new Percolation(n);
            openSites = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if(!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    openSites++;
                }
            }
            percolationThreshold = (double) openSites / (n * n);
            thresholds[expNo] = percolationThreshold;
        }
    }

    /**
     * @return sample mean of the percolation percolationThreshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * @return sample standard deviation of percolation percolationThreshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsNo));
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsNo));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int trials = sc.nextInt();
        PercolationStats ps = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + "[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
    }

}
