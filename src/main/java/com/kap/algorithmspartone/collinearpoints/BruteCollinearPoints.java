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

        int equalSlopes;
        int j;
        double slope;
        for (int i = 0; i < sortedPoints.length; i++) {
            if ((i + 1) == sortedPoints.length) {
                break;
            }

            for (int k = i + 1; k < sortedPoints.length; k++) {
                slope = sortedPoints[i].slopeTo(sortedPoints[k]);

                j = k + 1;

                equalSlopes = 0;
                while ((equalSlopes < 2) && (j < sortedPoints.length)) {

                    if (Double.compare(sortedPoints[i].slopeTo(sortedPoints[j]), slope) == 0) {
                        equalSlopes++;
                    }

                    if (equalSlopes == 2) {
                        lineSegments.add(new LineSegment(sortedPoints[i], sortedPoints[j]));
                        j = sortedPoints.length;
                        k = sortedPoints.length;
                    }

                    j++;
                }
            }
        }

        LineSegment[] lines = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lines);
    }

    private void checkPointsValidity(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException(CONSTRUCTOR_ARGUMENT_IS_NULL);
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException(NULL_POINT_FOUND);
            }
        }

        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);

        for (int i = 1; i < sortedPoints.length; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0) {
                throw new IllegalArgumentException(REPEATED_POINTS_FOUND);
            }
        }
    }
}
