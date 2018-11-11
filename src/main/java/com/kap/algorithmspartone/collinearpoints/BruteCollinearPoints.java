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
    private int numberOfSegments = 0;

    public BruteCollinearPoints(final Point[] points) {
        checkPointsValidity(points);
        this.points = points;
    }

    /**
     * @return the line segments array created from the point given
     */
    public LineSegment[] segments() {
        ArrayList<LineSegment> lineSegments = new ArrayList<>();

        Arrays.sort(points);

        int equalSlopes;
        int j;
        double slope;
        for (int i = 0; i < points.length; i++) {
            if ((i + 1) == points.length) {
                break;
            }

            for (int k = i + 1; k < points.length; k++) {
                checkPointsValidity(points[i], points[k]);
                slope = points[i].slopeTo(points[k]);

                j = k + 1;

                equalSlopes = 0;
                while ((equalSlopes < 2) && (j < points.length)) {

                    if (points[i].slopeTo(points[j]) == slope) {
                        equalSlopes++;
                    }

                    if (equalSlopes == 2) {
                        lineSegments.add(new LineSegment(points[i], points[j]));
                        numberOfSegments++;
                        j = points.length;
                        k = points.length;
                    }

                    j++;
                }
            }
        }

        LineSegment[] segments = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(segments);
    }

    /**
     * @return the number of line segments created from the points given
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }

    private void checkPointsValidity(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException(CONSTRUCTOR_ARGUMENT_IS_NULL);
        }

        if(points[0] == null) {
            throw new IllegalArgumentException(NULL_POINT_FOUND);
        }

        for(int i = 1; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException(NULL_POINT_FOUND);
            }

            if (points[i].compareTo(points[i-1]) == 0) {
               throw new IllegalArgumentException(REPEATED_POINTS_FOUND);
           }
        }
    }
}
