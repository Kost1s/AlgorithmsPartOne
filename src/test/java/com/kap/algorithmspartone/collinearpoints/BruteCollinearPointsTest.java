package com.kap.algorithmspartone.collinearpoints;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Konstantinos Antoniou
 */
public class BruteCollinearPointsTest {

    private Point[] inputSix;
    private Point[] inputEight;

    @Before
    public void setUp() {
        inputSix = new Point[6];
        inputSix[0] = new Point(19000 ,10000);
        inputSix[1] = new Point(18000 ,10000);
        inputSix[2] = new Point(32000 ,10000);
        inputSix[3] = new Point(21000 ,10000);
        inputSix[4] = new Point(1234 ,5678);
        inputSix[5] = new Point(14000 ,10000);

        inputEight = new Point[8];
        inputEight[0] = new Point(10000 ,0);
        inputEight[1] = new Point(0 ,10000);
        inputEight[2] = new Point(3000 ,7000);
        inputEight[3] = new Point(7000 ,3000);
        inputEight[4] = new Point(20000 ,21000);
        inputEight[5] = new Point(3000 ,4000);
        inputEight[6] = new Point(14000 ,15000);
        inputEight[7] = new Point(6000 ,7000);
    }

    @Test
    public void testInputSix() {
        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(inputSix);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(collinearPoints.numberOfSegments(), 1);
        for(LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

}
