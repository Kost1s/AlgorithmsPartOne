package com.kap.algorithmspartone.collinearpoints;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

        Point[] pointsToProcess = Arrays.copyOf(points, points.length);
        Point[] pointsToSort = Arrays.copyOf(points, points.length);

        Point minPoint;
        Point maxPoint;
        int collinearPoints;
        for (Point point : pointsToProcess) {
            Arrays.sort(pointsToSort, point.slopeOrder());

            collinearPoints = 0;
            minPoint = point;
            maxPoint = point;
            for (int i = 2; i < pointsToSort.length; i++) {
                if (Double.compare(point.slopeTo(pointsToSort[i - 1]), point.slopeTo(pointsToSort[i])) == 0) {
                    collinearPoints = collinearPointsNo(collinearPoints);
                    minPoint = findMinPoint(minPoint, pointsToSort[i - 1], pointsToSort[i]);
                    maxPoint = findMaxPoint(maxPoint, pointsToSort[i - 1], pointsToSort[i]);
                } else if ((collinearPoints > 3) && (point.compareTo(minPoint) == 0)) {
                    lineSegments.add(new LineSegment(minPoint, maxPoint));
                    collinearPoints = 0;
                    maxPoint = point;
                    minPoint = point;
                } else {
                    collinearPoints = 0;
                    maxPoint = point;
                    minPoint = point;
                }
            }

            if ((collinearPoints > 3) && (point.compareTo(minPoint) == 0)) {
                lineSegments.add(new LineSegment(minPoint, maxPoint));
            }
        }

        LineSegment[] lines = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lines);
    }

    /**
     * Collinear points counter.
     * <p>
     * Checks whether the counting sequence is in its start or not and acts accordingly.
     *
     * @param pointsNo number of collinear points found
     *
     * @return cumulative number of collinear points found after each iteration
     */
    private int collinearPointsNo(int pointsNo) {
        if (pointsNo == 0) {
            pointsNo = 2;
        }
        pointsNo++;
        return pointsNo;
    }

    /**
     * Finds the minimum point from a group of points provided given a minimum reference point.
     *
     * @param min    minimum reference point
     * @param pointA first point to be checked
     * @param pointB second point to be checked
     *
     * @return the minimum point from the group of points provided
     */
    private Point findMinPoint(Point min, Point pointA, Point pointB) {
        if ((min.compareTo(pointA) <= 0) && (min.compareTo(pointB) <= 0)) {
            return min;
        }
        if ((pointA.compareTo(min) <= 0) && (pointA.compareTo(pointB) <= 0)) {
            return pointA;
        }
        return pointB;
    }

    /**
     * Finds the maximum point from a group of points provided given a maximum reference point.
     *
     * @param max    maximum reference point
     * @param pointA first point to be checked
     * @param pointB second point to be checked
     *
     * @return the maximum point from the group of points provided
     */
    private Point findMaxPoint(Point max, Point pointA, Point pointB) {
        if ((max.compareTo(pointA) >= 0) && (max.compareTo(pointB) >= 0)) {
            return max;
        }
        if ((pointA.compareTo(max) >= 0) && (pointA.compareTo(pointB) >= 0)) {
            return pointA;
        }
        return pointB;
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
