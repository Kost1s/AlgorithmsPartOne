package com.kap.algorithmspartone.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Konstantinos Antoniou
 */
public class Percolation {

    private static final String INVALID_SITES_INPUT = "Sites numbers provided are outside of the prescribed range." +
                                                      "Valid range spans from 1 to gridSideLength provided";

    private static final String SITES_INPUT_SUB_OR_EQUAL_ZERO = "Number of sites provided as an input is invalid." +
                                                                "Valid input should be an integer biggen than zero";

    private static final String LINE_SPLIT = "========================";

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] openSites;
    private final int gridSideLength;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private int openSitesCount;

    /**
     * com.kap.algorithmspartone.percolation.Percolation class constructor.
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

    /**
     * Opens the site provided.
     *
     * @param row site row index provided
     * @param col site column index provided
     */
    public void open(int row, int col) {
        validateIndices(row, col);
        openSites[row - 1][col - 1] = true;
        openSitesCount++;

        if (row == 1) {
            weightedQuickUnionUF.union(getUfIndex(row, col), virtualTopSite);
        }

        if (row == gridSideLength) {
            weightedQuickUnionUF.union(getUfIndex(row, col), virtualBottomSite);
        }

        if ((col > 1) && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(getUfIndex(row, col), getUfIndex(row, col - 1));
        }

        if ((col < gridSideLength) && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(getUfIndex(row, col), getUfIndex(row, col + 1));
        }

        if ((row > 1) && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(getUfIndex(row, col), getUfIndex(row - 1, col));
        }

        if ((row < gridSideLength) && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(getUfIndex(row, col), getUfIndex(row + 1, col));
        }
    }

    /**
     * @param row site row index provided
     * @param col site column index provided
     *
     * @return true if site provided is open. otherwise false.
     */
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return openSites[row - 1][col - 1];
    }

    /**
     * Checks whether a site is full. i.e. whether the site with the provided coordinates is connected with the virtual
     * top site.
     *
     * @param row site row index provided
     * @param col site column index provided
     *
     * @return true if site is full. false otherwise.
     */
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return weightedQuickUnionUF.connected(virtualTopSite, getUfIndex(row, col));
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    /**
     * Checks whether the system percolates or not. i.e. whether the virtual top site is connected with the virtual
     * bottom site.
     *
     * @return true if systems percolates. otherwise returns false.
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualTopSite, virtualBottomSite);
    }

    /**
     * Validates whether the indices provided are within the valid prescribed range. (1 to n)
     *
     * @param row site row index provided
     * @param col site column index provided
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
     * @param row site row index provided
     * @param col site column index provided
     */
    private int getUfIndex(int row, int col) {
        return (gridSideLength * (row - 1)) + col;
    }

    /**
     * Test client
     *
     * @param args arguments array
     */
    public static void main(String[] args) {
        testSideBySideSites();
    }

    private static void testSideBySideSites() {
        int n = 3;

        StdOut.println("Grid dimensions : " + n + " * " + n);

        StdOut.println(LINE_SPLIT);

        Percolation p = new Percolation(n);

        // check and open (1, 1)
        StdOut.println("Is (1, 1) open = " + p.isOpen(1, 1));
        StdOut.println("Open (1, 1)");
        p.open(1, 1);
        StdOut.println("Is (1, 1) open = " + p.isOpen(1, 1));

        StdOut.println(LINE_SPLIT);

        // check and open (1, 2)
        StdOut.println("Is (1, 2) open = " + p.isOpen(1, 2));
        StdOut.println("Open (1, 2)");
        p.open(1, 2);
        StdOut.println("Is (1, 2) open = " + p.isOpen(1, 2));

        StdOut.println(LINE_SPLIT);

        StdOut.println("Is (1, 1) full = " + p.isFull(1, 1));

        StdOut.println(LINE_SPLIT);

        StdOut.println("Is (1, 2) full = " + p.isFull(1, 2));

        StdOut.println(LINE_SPLIT);

        int siteOneCoordinates = 1;
        int siteTwoCoordinates = 2;

        StdOut.println("Number of open sites is : " + p.numberOfOpenSites());

        StdOut.println(LINE_SPLIT);

        StdOut.println("Are (1, 1) and (1, 2) connected : " + p.weightedQuickUnionUF.connected(siteOneCoordinates,
                                                                                               siteTwoCoordinates));
    }


}
