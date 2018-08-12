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
     * <p>
     * Checks whether the grid side length provided is valid (n > 0). Otherwise, throws IllegalArgumentException.
     * <p>
     * Initializes the WeightedQuickUnionFind class with a n^2 number of sites plus two additional virtual sites.
     * <p>
     * Maps the virtual sites positions on the grid to union find object terms.
     * <p>
     * Initializes the openSites matrix according to grid side length provided.
     *
     * @param n grid side length provided.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(SITES_INPUT_SUB_OR_EQUAL_ZERO);
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

    /**
     * Validates whether the indices provided are within the valid prescribed range. (1 to n)
     *
     * @param row row index provided
     * @param col column index provided
     */
    private void validateIndices(int row, int col) {
        if ((row < 1) || (col < 1) || (row > gridSideLength) || (col > gridSideLength)) {
            throw new IllegalArgumentException(INVALID_SITES_INPUT);
        }
    }

    /**
     * Maps a 2D coordinates input coming from the original grid, to a unique 1D index used by the union find class.
     * <p>
     * Here we use a row major ordering approach.
     * <p>
     * Thus, if the 2D matrix is an n by m matrix with i range : 0 -> n-1 and j range : 0 -> m-1, the 1D mapping is :
     * (i* m + j)
     * <p>
     * In our case the 2D matrix is an n by n matrix. Thus, the transformation is : (i * n + j).
     * <p>
     * In this specific case of percolation, the grid provided is of 1 to n range. And starts from (1,1).
     * <p>
     * However, we have to map this grid to a 0 to n-1 grid based on the UnionFind class standards. Thus, we will
     * transform the 1D mapping above to: (n * (i - 1) + j).
     * <p>
     * That means that in a 25 by 25 grid. Starting coordinates(1,1) map to 1D coordinates as: (25 * (1 - 1) + 1) = 1
     *
     * @param row row index provided
     * @param col column index provided
     */
    private int getUfIndex(int row, int col) {
        return (gridSideLength * (row - 1)) + col;
    }

}
