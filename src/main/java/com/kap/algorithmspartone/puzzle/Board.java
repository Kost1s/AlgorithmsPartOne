package com.kap.algorithmspartone.puzzle;

/**
 * @author Konstantinos Antoniou
 */
public class Board {

    private static final int BLANK_BLOCK = 0;

    private final int[][] blocks;

    private int hammingValue;
    private int manhattanValue;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = copyConstructorArgumentAndInitializeFields(blocks);
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {

    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

    }

    // is this board the goal board?
    public boolean isGoal() {

    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {

    }

    public boolean equals(Object y) {

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // string representation of this board
    public String toString() {

    }

    private int[][] copyConstructorArgumentAndInitializeFields(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException("Constructor argument is null");
        }

        hammingValue = 0;
        manhattanValue = 0;
        int[][] derivedArray = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                derivedArray[row][col] = blocks[row][col];

            }
        }
        return derivedArray;
    }

    /**
     * This method checks whether a block sits at its correct place in the board. This is done by converting a 2D array
     * coordinate to an 1D coordinate and adjusting for the correct value according to the requested topology
     * requirements for the board.
     * <p>
     * Board topology requirements (first block value should start from 1 not 0):
     * <p>
     * [0][0] [0][1] [0][2]
     *   1      2      3
     * <p>
     * [1][0] [1][1] [1][2]
     *   4      5      6
     * <p>
     * [2][0] [2][1] [2][2]
     *   7      8      0
     * <p>
     * Board topology requirements translated to 1D array coordinates starting from 1 instead of 0:
     * <p>
     * [1] [2] [3] [4] [5] [6] [7] [8] 1   2   3   4   5   6   7   8
     * <p>
     * Essentially the check is whether value 1 sits at the first position of the 1D array, if 2 sits at the second
     * position and so on and so forth.
     * <p>
     * If the above is not the case for any block then this block is considered to not be in its correct position.
     * <p>
     * Correct position example:
     * <p>
     * row = 0, col = 1 => blocks[0][1] = 2
     * <p>
     * 2D to 1D [0, 1] => row*2D_dimension + col + 1 = [1] + 1 = [2]
     * <p>
     * value at 1D [2] = 2
     * <p>
     * derivedArray[2] value = blocks[0][1] value  => correct
     * <p>
     * Wrong position example:
     * <p>
     * blocks[0][1] = 4
     * <p>
     * value at [0][1] to 1D = 2
     * <p>
     * derivedArray[2] value != blocks[0][1] value => not correct
     *
     * @param row
     * @param col
     *
     * @return
     */
    private boolean positionOfBlockIsNotCorrect(int row, int col) {
        int block = blocks[row][col];

        return !isBlankBlock(block) && (block != correctPositionForBlock(row, col));
    }

    private boolean isBlankBlock(int block) {
        return block == BLANK_BLOCK;
    }

    /**
     * This method converts a 2D array coordinate to an 1D coordinate and adjusting for for the requested topology
     * requirements for the board.
     * <p>
     * Board 2D coordinates :
     * <p>
     * [0][0] [0][1] [0][2]
     * <p>
     * [1][0] [1][1] [1][2]
     * <p>
     * [2][0] [2][1] [2][2]
     * <p>
     * Board topology requirements translated to 1D array coordinates starting from 1 instead of 0:
     * <p>
     * [1] [2] [3] [4] [5] [6] [7] [8]
     * <p>
     * conversion algorithm example:
     * <p>
     * 2D to 1D [0, 1] => row*2D_dimension + col + 1 = [1] + 1 = [2]
     *
     * @param row blocks array row value
     * @param col blocks array column value
     *
     * @return 1D coordinate for 2D coordinates provided, adjusted to start from 1
     */
    private int correctPositionForBlock(int row, int col) {
        return (row * dimension()) + col + 1;
    }

}
