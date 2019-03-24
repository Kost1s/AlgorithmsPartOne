package com.kap.algorithmspartone.puzzle;

import java.util.LinkedList;

/**
 * @author Konstantinos Antoniou
 */
public class Board {

    private static final int BLANK_BLOCK = 0;

    private final int[][] blocksBoard;

    private int hammingValue;
    private int manhattanValue;

    // construct a board from an n-by-n array of blocksBoard
    // (where blocksBoard[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException("Constructor argument is null");
        }
        blocksBoard = copyArray(blocks);
        initializeFields();
    }

    // board dimension n
    public int dimension() {
        return blocksBoard.length;
    }

    // number of blocks out of place
    public int hamming() {
        return hammingValue;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattanValue;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hammingValue == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int row;
        int col = 0;
        boolean rowLoopFinished = false;
        for (row = 0; (row < blocksBoard.length) && !rowLoopFinished; row++) {
            for (col = 0; col < (blocksBoard.length - 1); col++) {
                if (!isBlankBlock(blocksBoard[row][col]) && !isBlankBlock(blocksBoard[row][col + 1])) {
                    rowLoopFinished = true;
                    break;
                }
            }
        }
        return new Board(exchange(row, col, row, col + 1));
    }

    public boolean equals(final Object y) {
        if (this == y) {
            return true;
        }

        if ((y == null) || (getClass() != y.getClass())) {
            return false;
        }

        final Board board = (Board) y;

        for (int row = 0; row < blocksBoard.length; row++) {
            for (int col = 0; col < blocksBoard.length; col++) {
                if (board.blocksBoard[row][col] != blocksBoard[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<>();

        int[] blankBlockCoordinates = blankBlockCoordinates();
        int blankBlockRow = blankBlockCoordinates[0];
        int blankBlockCol = blankBlockCoordinates[1];

        if (blankBlockRow > 0) {
            neighbors.add(new Board(exchange(blankBlockRow, blankBlockCol, blankBlockRow - 1, blankBlockCol)));
        }
        if (blankBlockRow < (dimension() - 1)) {
            neighbors.add(new Board(exchange(blankBlockRow, blankBlockCol, blankBlockRow + 1, blankBlockCol)));
        }
        if (blankBlockCol > 0) {
            neighbors.add(new Board(exchange(blankBlockRow, blankBlockCol, blankBlockRow, blankBlockCol - 1)));
        }
        if (blankBlockCol < (dimension() - 1)) {
            neighbors.add(new Board(exchange(blankBlockRow, blankBlockCol, blankBlockRow, blankBlockCol + 1)));
        }

        return neighbors;
    }

    // string representation of this board
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension()).append("\n");
        for (int row = 0; row < blocksBoard.length; row++) {
            for (int col = 0; col < blocksBoard.length; col++) {
                stringBuilder.append(String.format("%2d ", blocksBoard[row][col]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void initializeFields() {
        hammingValue = 0;
        manhattanValue = 0;
        for (int row = 0; row < blocksBoard.length; row++) {
            for (int col = 0; col < blocksBoard.length; col++) {
                if (positionOfBlockIsNotCorrect(row, col)) {
                    hammingValue++;
                }
                manhattanValue += calculateManhattanDistance(row, col);
            }
        }
    }

    private int[][] copyArray(int[][] blocks) {
        int[][] arrayCopy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                arrayCopy[row][col] = blocks[row][col];
            }
        }
        return arrayCopy;
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
     * [1] [2] [3] [4] [5] [6] [7] [8]
     *  1   2   3   4   5   6   7   8
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
     * @param row block row coordinate
     * @param col block col coordinate
     *
     * @return true if position of block is correct and false otherwise
     */
    private boolean positionOfBlockIsNotCorrect(int row, int col) {
        int blockValue = blocksBoard[row][col];

        return !isBlankBlock(blockValue) && (blockValue != correctPositionForBlock(row, col));
    }

    private int calculateManhattanDistance(int row, int col) {
        int blockValue = blocksBoard[row][col];

        return isBlankBlock(blockValue) ? 0 : (Math.abs(row - rowFrom1dCoordinate(blockValue)) +
                                               Math.abs(col - colFrom1dCoordinate(blockValue)));
    }

    private boolean isBlankBlock(int blockValue) {
        return blockValue == BLANK_BLOCK;
    }

    /**
     * This method converts a 2D array coordinate to an 1D coordinate and adjusting for the requested topology
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

    /**
     * @param coordinate 1d coordinate for which a 2d row coordinate will be derived
     *
     * @return 2D array (with an index starting from [0][0]) row coordinate derived from an 1D array (with an index
     * starting from [1]) coordinate
     */
    private int rowFrom1dCoordinate(int coordinate) {
        return (coordinate - 1) / dimension();
    }

    private int colFrom1dCoordinate(int coordinate) {
        return (coordinate - 1) % dimension();
    }

    private int[][] exchange(int rowA, int colA, int rowB, int colB) {
        int[][] arrayCopy = copyArray(blocksBoard);
        int temp = arrayCopy[rowA][colA];
        arrayCopy[rowA][colA] = arrayCopy[rowB][colB];
        arrayCopy[rowB][colB] = temp;
        return arrayCopy;
    }

    private int[] blankBlockCoordinates() {
        int[] coordinates = new int[2];
        for (int row = 0; row < blocksBoard.length; row++) {
            for (int col = 0; col < blocksBoard.length; col++) {
                if (isBlankBlock(blocksBoard[row][col])) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                }
            }
        }
        return coordinates;
    }

}