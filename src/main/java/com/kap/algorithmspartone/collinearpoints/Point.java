package com.kap.algorithmspartone.collinearpoints;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * @author Konstantinos Antoniou
 */
public class Point implements Comparable<Point> {

    private static final String COORDINATE_LESS_THAN_ZERO = "Coordinate cannot be less than zero";
    private static final String COORDINATE_MORE_THAN_APPROVED = "Coordinate cannot be more than 32767";

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        if ((x < 0) || (y < 0)) {
            throw new IllegalArgumentException(COORDINATE_LESS_THAN_ZERO);
        }

        if ((x > 32767) || (y > 32767)) {
            throw new IllegalArgumentException(COORDINATE_MORE_THAN_APPROVED);
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally, if the two points are (x0, y0) and (x1,
     * y1), then the slope is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be +0.0 if the line
     * segment connecting the two points is horizontal; Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     *
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {

        // vertical line segment
        if (x == that.x) {
            return Double.POSITIVE_INFINITY;
        }

        // horizontal line segment
        if (y == that.y) {
            return +0.0;
        }

        // negative infinity
        if(compareTo(that) == 0) {
           return Double.NEGATIVE_INFINITY;
        }

        return ((double) (that.y - y)) / (that.x - x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking point (x0, y0) is less
     * than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     *
     * @return the value <tt>0</tt> if this point is equal to the argument point (x0 = x1 and y0 = y1); a negative
     * integer if this point is less than the argument point; and a positive integer if this point is greater than the
     * argument point
     */
    @Override
    public int compareTo(Point that) {
        if ((x == that.x) && (y == that.y)) {
            return 0;
        }

        if ((y < that.y) || ((y == that.y) && (x < that.x))) {
            return -1;
        }

        return 1;
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new PointComparator();
    }

    private class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {

            // vertical line segments
            if ((slopeTo(a) == Double.POSITIVE_INFINITY) && (slopeTo(b) == Double.POSITIVE_INFINITY)) {
                return (int) Double.POSITIVE_INFINITY;
            }

            // negative infinity
            if ((slopeTo(a) == Double.NEGATIVE_INFINITY) && (slopeTo(b) == Double.NEGATIVE_INFINITY)) {
                return (int) Double.NEGATIVE_INFINITY;
            }

            // horizontal line segments
            if ((slopeTo(a) == 0.0) && (slopeTo(b) == 0.0)) {
                return (int) 0.0;
            }

            return (int) ((int) slopeTo(a) - slopeTo(b));
        }
    }

    /**
     * Returns a string representation of this point. This method is provide for debugging; your program should not rely
     * on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
