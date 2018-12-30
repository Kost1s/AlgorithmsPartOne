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

    /**
     * Class constructor.
     * <p>
     * Checks argument validity and creates an array of line segments.
     *
     * @param points points array
     */
    public FastCollinearPoints(final Point[] points) {
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
        List<Point> collinearPoints;
        List<List<Point>> collinearPointsLists = new ArrayList<>();

        Point[] pointsToProcess = Arrays.copyOf(points, points.length);
        Point[] pointsToSort = Arrays.copyOf(points, points.length);

        for (Point point : pointsToProcess) {
            Arrays.sort(pointsToSort, point.slopeOrder());

            collinearPoints = new ArrayList<>();
            collinearPoints.add(point);
            for (int i = 2; i < pointsToSort.length; i++) {
                if (Double.compare(point.slopeTo(pointsToSort[i - 1]), point.slopeTo(pointsToSort[i])) == 0) {
                    addUniquePoints(collinearPoints, pointsToSort[i - 1], pointsToSort[i]);
                } else if ((collinearPoints.size() > 3)
                           && (!collinearPointsListsContains(collinearPointsLists, collinearPoints))) {
                    lineSegments.add(new LineSegment(collinearPoints.get(0),
                                                     collinearPoints.get(collinearPoints.size() - 1)));
                }
            }
        }

        LineSegment[] lines = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lines);
    }

    /**
     * Checks whether the collinear points list that is about to be added already exists in the list of collinear points
     * lists.
     *
     * @param collinearPointsLists list of collinear points lists
     * @param collinearPoints      collinear points list about to be added
     *
     * @return true if the list to be added already exists in the list of coll. points lists and false otherwise
     */
    private boolean collinearPointsListsContains(List<List<Point>> collinearPointsLists, List<Point> collinearPoints) {
        boolean listExists = false;

        Collections.sort(collinearPoints);
        for (List<Point> pointsList : collinearPointsLists) {
            if ((pointsList.get(0).compareTo(collinearPoints.get(0)) == 0) &&
                (pointsList.get(pointsList.size() - 1).compareTo(collinearPoints.get(collinearPoints.size() - 1)) ==
                 0)) {
                listExists = true;
            }
        }
        if (!listExists) {
            collinearPointsLists.add(collinearPoints);
        }
        return listExists;
    }

    /**
     * Adds points in the given collinear points list only in the case that these points are not already included in the
     * list.
     *
     * @param collinearPoints given collinear points list
     * @param pointA          first point to be added in the list
     * @param pointB          second point to be added in the list
     */
    private void addUniquePoints(List<Point> collinearPoints, Point pointA, Point pointB) {
        boolean listContainsPointA = false;
        boolean listContainsPointB = false;

        for (Point point : collinearPoints) {
            if (point.compareTo(pointA) == 0) {
                listContainsPointA = true;
            }
            if (point.compareTo(pointB) == 0) {
                listContainsPointB = true;
            }
        }

        if (!listContainsPointA) {
            collinearPoints.add(pointA);
        }
        if (!listContainsPointB) {
            collinearPoints.add(pointB);
        }
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
