package com.kap.algorithmspartone.collinearpoints;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Konstantinos Antoniou
 */
public class BruteCollinearPoints {

    private static final String CONSTRUCTOR_ARGUMENT_IS_NULL = "Constructor argument is null.";
    private static final String REPEATED_POINTS_FOUND = "Repeated points are found.";
    private static final String NULL_POINT_FOUND = "Null point found.";

    private final Point[] points;
    private final LineSegment[] segments;

    /**
     * Class constructor.
     * <p>
     * Checks argument validity and creates an array of line segments.
     *
     * @param points points array
     */
    public BruteCollinearPoints(final Point[] points) {
        checkPointsValidity(points);
        this.points = Arrays.copyOf(points, points.length);
        segments = getSegments();
    }

    /**
     * @return the line segments array created from the points given
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    /**
     * @return the number of line segments created from the points given
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * Searches for collinear points and returns straight line segments from these points.
     *
     * @return the line segments that can be created from the collinear points found
     */
    private LineSegment[] getSegments() {
        List<LineSegment> lineSegments = new ArrayList<>();

        Point[] sortedPoints = copyArrayAndSortAscending(points);

        for (int i = 0; i < sortedPoints.length; i++) {
            for (int j = i + 1; j < sortedPoints.length; j++) {
                for (int k = j + 1; k < sortedPoints.length; k++) {
                    for (int m = k + 1; m < sortedPoints.length; m++) {
                        if (pointsAreCollinear(sortedPoints[i], sortedPoints[j], sortedPoints[k], sortedPoints[m])) {
                            lineSegments.add(new LineSegment(sortedPoints[i], sortedPoints[m]));
                        }
                    }
                }
            }
        }

        LineSegment[] lines = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lines);
    }

    /**
     * Checks whether four points are collinear.
     *
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     * @param p4 point 4
     *
     * @return true if the points are collinear and false otherwise
     */
    private boolean pointsAreCollinear(Point p1, Point p2, Point p3, Point p4) {
        return (Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0) &&
               (Double.compare(p1.slopeTo(p3), p1.slopeTo(p4)) == 0);
    }

    /**
     * Checks if the constructor argument is valid.
     *
     * @param pointsToCheck constructor argument
     */
    private void checkPointsValidity(Point[] pointsToCheck) {
        nullConstructorArgumentCheck(pointsToCheck);
        nullPointCheck(pointsToCheck);
        duplicatePointsCheck(pointsToCheck);
    }

    /**
     * Checks whether the constructor argument is null.
     *
     * @param pointsToCheck constructor argument
     */
    private void nullConstructorArgumentCheck(Point[] pointsToCheck) {
        if (pointsToCheck == null) {
            throw new IllegalArgumentException(CONSTRUCTOR_ARGUMENT_IS_NULL);
        }
    }

    /**
     * Checks whether the constructor argument contains null points.
     *
     * @param pointsToCheck constructor argument
     */
    private void nullPointCheck(Point[] pointsToCheck) {
        for (Point point : pointsToCheck) {
            if (point == null) {
                throw new IllegalArgumentException(NULL_POINT_FOUND);
            }
        }
    }

    /**
     * Checks whether the constructor argument contains duplicate points.
     *
     * @param pointsToCheck constructor argument
     */
    private void duplicatePointsCheck(Point[] pointsToCheck) {
        Point[] sortedPoints = copyArrayAndSortAscending(pointsToCheck);

        for (int i = 1; i < sortedPoints.length; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0) {
                throw new IllegalArgumentException(REPEATED_POINTS_FOUND);
            }
        }
    }

    /**
     * Copies the array given and sorts it with ascending order.
     *
     * @param ar array given
     *
     * @return a copy from the array given which is sorted with ascending order
     */
    private Point[] copyArrayAndSortAscending(Point[] ar) {
        Point[] sortedArray = Arrays.copyOf(ar, ar.length);
        Arrays.sort(sortedArray);
        return sortedArray;
    }
}
