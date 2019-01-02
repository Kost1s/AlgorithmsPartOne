package com.kap.algorithmspartone.collinearpoints;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Konstantinos Antoniou
 */
public class FastCollinearPointsTest {

    private Point[] inputEight;
    private Point[] inputSix;
    private Point[] inputFour;

    @Before
    public void setUp() {
        inputEight = new Point[8];
        inputEight[0] = new Point(10000, 0);
        inputEight[1] = new Point(0, 10000);
        inputEight[2] = new Point(3000, 7000);
        inputEight[3] = new Point(7000, 3000);
        inputEight[4] = new Point(20000, 21000);
        inputEight[5] = new Point(3000, 4000);
        inputEight[6] = new Point(14000, 15000);
        inputEight[7] = new Point(6000, 7000);

        inputSix = new Point[6];
        inputSix[0] = new Point(19000, 10000);
        inputSix[1] = new Point(18000, 10000);
        inputSix[2] = new Point(32000, 10000);
        inputSix[3] = new Point(21000, 10000);
        inputSix[4] = new Point(1234, 5678);
        inputSix[5] = new Point(14000, 10000);

        inputFour = new Point[4];
        inputFour[0] = new Point(12399,  9707);
        inputFour[1] = new Point(8978,  9707);
        inputFour[2] = new Point(16280,  9707);
        inputFour[3] = new Point(14071,  9707);

    }

    @Test
    public void testInputEight() {
        FastCollinearPoints collinearPoints = new FastCollinearPoints(inputEight);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(2, collinearPoints.numberOfSegments());
        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

    @Test
    public void testInputSix() {
        FastCollinearPoints collinearPoints = new FastCollinearPoints(inputSix);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(1, collinearPoints.numberOfSegments());
        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

    @Test
    public void testInputFour() {
        FastCollinearPoints collinearPoints = new FastCollinearPoints(inputFour);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(1, collinearPoints.numberOfSegments());
        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }
}
