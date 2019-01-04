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
    private Point[] inputNine;
    private Point[] inputFiveByFiveGrid;

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

        inputNine = new Point[9];
        inputNine[0] = new Point(1000, 1000);
        inputNine[1] = new Point(4000, 4000);
        inputNine[2] = new Point(2000, 2000);
        inputNine[3] = new Point(3000, 3000);
        inputNine[4] = new Point(5000, 5000);
        inputNine[5] = new Point(6000, 6000);
        inputNine[6] = new Point(8000, 8000);
        inputNine[7] = new Point(7000, 7000);
        inputNine[8] = new Point(9000, 9000);

        inputFiveByFiveGrid = new Point[25];
        inputFiveByFiveGrid[0] = new Point(0, 0);
        inputFiveByFiveGrid[1] = new Point(0, 1);
        inputFiveByFiveGrid[2] = new Point(0, 2);
        inputFiveByFiveGrid[3] = new Point(0, 3);
        inputFiveByFiveGrid[4] = new Point(0, 4);
        inputFiveByFiveGrid[5] = new Point(1, 0);
        inputFiveByFiveGrid[6] = new Point(1, 1);
        inputFiveByFiveGrid[7] = new Point(1, 2);
        inputFiveByFiveGrid[8] = new Point(1, 3);
        inputFiveByFiveGrid[9] = new Point(1, 4);
        inputFiveByFiveGrid[10] = new Point(2, 0);
        inputFiveByFiveGrid[11] = new Point(2, 1);
        inputFiveByFiveGrid[12] = new Point(2, 2);
        inputFiveByFiveGrid[13] = new Point(2, 3);
        inputFiveByFiveGrid[14] = new Point(2, 4);
        inputFiveByFiveGrid[15] = new Point(3, 0);
        inputFiveByFiveGrid[16] = new Point(3, 1);
        inputFiveByFiveGrid[17] = new Point(3, 2);
        inputFiveByFiveGrid[18] = new Point(3, 3);
        inputFiveByFiveGrid[19] = new Point(3, 4);
        inputFiveByFiveGrid[20] = new Point(4, 0);
        inputFiveByFiveGrid[21] = new Point(4, 1);
        inputFiveByFiveGrid[22] = new Point(4, 2);
        inputFiveByFiveGrid[23] = new Point(4, 3);
        inputFiveByFiveGrid[24] = new Point(4, 4);
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

    @Test
    public void testInputNine() {
        FastCollinearPoints collinearPoints = new FastCollinearPoints(inputNine);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(1, collinearPoints.numberOfSegments());
        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

    @Test
    public void testInputFiveByFiveGrid() {
        FastCollinearPoints collinearPoints = new FastCollinearPoints(inputFiveByFiveGrid);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(16, collinearPoints.numberOfSegments());
        for (LineSegment segment : segments) {
            StdOut.println(segment);
        }
    }

}
