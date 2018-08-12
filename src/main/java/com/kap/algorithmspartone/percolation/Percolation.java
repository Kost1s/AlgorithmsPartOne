package com.kap.algorithmspartone.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Konstantinos Antoniou
 */
public class Percolation {

    private static final String INVALID_SITES_INPUT = "Sites numbers provided are outside of the prescribed range." +
                                                      "Valid range spans from 1 to gridSideLength provided";

    private static final String SITES_INPUT_SUB_OR_EQUAL_ZERO = "Number of sites provided as an input is invalid." +
                                                                "Valid input should be an integer biggen than zero";

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] openSites;
    private final int gridSideLength;
    private int virtualTopSite;
    private int virtualBottomSite;

    /**
     * Percolation class constructor.
     *
     * Checks whether the grid side length provided is valid (n > 0). Otherwise, throws IllegalArgumentException.
     *
     * Initializes the WeightedQuickUnionFind class with a n^2 number of sites plus two additional virtual sites.
     *
     * Maps the virtual sites positions on the grid to union find object terms.
     *
     * Initializes the openSites matrix according to grid side length provided.
     *
     * @param n grid side length provided.
     */
    public Percolation(int n) {
        if(n <= 0) {
            throw  new IllegalArgumentException(SITES_INPUT_SUB_OR_EQUAL_ZERO);
        }
        gridSideLength = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF((gridSideLength * gridSideLength) + 2);
        virtualTopSite = 0;
        virtualBottomSite = (gridSideLength * gridSideLength) + 1;
        openSites = new boolean[gridSideLength][gridSideLength];
    }

    public boolean isOpen(int row, int col) {

    }

    public boolean isFull(int row, int col) {

    }

    public int numberOfOpenSites() {

    }

    public boolean percolates() {

    }

    private void validateIndices(int row, int col) {
        if((row < 1) || (col < 1) || (row > gridSideLength) || (col > gridSideLength)) {
            throw new IllegalArgumentException(INVALID_SITES_INPUT);
        }
    }


}
