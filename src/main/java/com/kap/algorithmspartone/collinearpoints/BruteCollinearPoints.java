package com.kap.algorithmspartone.collinearpoints;

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

    public BruteCollinearPoints(final Point[] points) {
        checkPointsValidity(points);
        this.points = Arrays.copyOf(points, points.length);
        segments = getSegments();
    }

    /**
     * @return the line segments array created from the point given
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

    private LineSegment[] getSegments() {
        ArrayList<LineSegment> lineSegments = new ArrayList<>();

        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);

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

    private boolean pointsAreCollinear(Point p1, Point p2, Point p3, Point p4) {
        return (Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0) &&
               (Double.compare(p1.slopeTo(p3), p1.slopeTo(p4)) == 0);
    }

    private void checkPointsValidity(Point[] pointsToCheck) {
        if (pointsToCheck == null) {
            throw new IllegalArgumentException(CONSTRUCTOR_ARGUMENT_IS_NULL);
        }

        for (Point point : pointsToCheck) {
            if (point == null) {
                throw new IllegalArgumentException(NULL_POINT_FOUND);
            }
        }

        Point[] sortedPoints = Arrays.copyOf(pointsToCheck, pointsToCheck.length);
        Arrays.sort(sortedPoints);

        for (int i = 1; i < sortedPoints.length; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0) {
                throw new IllegalArgumentException(REPEATED_POINTS_FOUND);
            }
        }
    }
}
