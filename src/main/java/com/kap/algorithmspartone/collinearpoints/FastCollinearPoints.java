package com.kap.algorithmspartone.collinearpoints;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Konstantinos Antoniou
 */
public class FastCollinearPoints {

    private static final String CONSTRUCTOR_ARGUMENT_IS_NULL = "Constructor argument is null.";
    private static final String REPEATED_POINTS_FOUND = "Repeated points are found.";
    private static final String NULL_POINT_FOUND = "Null point found.";

    private final Point[] points;
    private final LineSegment[] segments;
    private final List<Point> uniqueLists;

    /**
     * Class constructor.
     * <p>
     * Checks argument validity and creates an array of line segments.
     *
     * @param points points array
     */
    public FastCollinearPoints(final Point[] points) {
        checkPointsValidity(points);
        uniqueLists = new ArrayList<>();
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
        List<Point> collinearPoints = new ArrayList<>();

        Point[] pointsToProcess = Arrays.copyOf(points, points.length);
        Point[] pointsToSort = Arrays.copyOf(points, points.length);

        for (Point point : pointsToProcess) {
            Arrays.sort(pointsToSort, point.slopeOrder());

            collinearPoints.clear();
            for (int i = 2; i < pointsToSort.length; i++) {
                if (Double.compare(point.slopeTo(pointsToSort[i - 1]), point.slopeTo(pointsToSort[i])) == 0) {
                    if (collinearPoints.isEmpty()) {
                        collinearPoints.add(point);
                        collinearPoints.add(pointsToSort[i - 1]);
                    }
                    collinearPoints.add(pointsToSort[i]);
                } else if (collinearPoints.size() > 3) {
                    Collections.sort(collinearPoints);
                    if (segmentIsUnique(collinearPoints)) {
                        lineSegments.add(new LineSegment(collinearPoints.get(0),
                                                         collinearPoints.get(collinearPoints.size() - 1)));
                    }
                    collinearPoints.clear();
                }
            }
        }

        LineSegment[] lines = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lines);
    }

    private boolean segmentIsUnique(List<Point> points) {
        if (!uniqueLists.contains(points.get(0))) {
            uniqueLists.add(points.get(0));
            return true;
        }
        return false;
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
