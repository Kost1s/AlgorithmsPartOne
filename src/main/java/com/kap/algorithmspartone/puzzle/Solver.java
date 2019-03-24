package com.kap.algorithmspartone.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;

/**
 * @author Konstantinos Antoniou
 */
public class Solver {

    private Move lastMove;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<Move> moves = new MinPQ<>();
        moves.insert(new Move(initial));

        MinPQ<Move> twinMoves = new MinPQ<>();
        twinMoves.insert(new Move(initial.twin()));

        while (true) {
            lastMove = expand(moves);
            if ((lastMove != null) || (expand(twinMoves) != null)) {
                return;
            }
        }
    }

    public boolean isSolvable() {
        return (lastMove != null);
    }

    public int moves() {
        return isSolvable() ? lastMove.numMoves : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        ArrayDeque<Board> moves = new ArrayDeque<>();
        while (lastMove != null) {
            moves.addFirst(lastMove.board);
            lastMove = lastMove.previous;
        }

        return moves;
    }

    private Move expand(MinPQ<Move> moves) {
        if (moves.isEmpty()) {
            return null;
        }

        Move idealMove = moves.delMin();

        if (idealMove.board.isGoal()) {
            return idealMove;
        }
        for (Board neighbor : idealMove.board.neighbors()) {
            if ((idealMove.previous == null) || !neighbor.equals(idealMove.previous.board)) {
                moves.insert(new Move(neighbor, idealMove));
            }
        }
        return null;
    }

    private class Move implements Comparable<Move> {
        private Move previous;
        private final Board board;
        private int numMoves = 0;
        private int priority;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            numMoves = previous.numMoves + 1;
            priority = board.manhattan() + numMoves;
        }

        public int compareTo(Move move) {
            return (priority - move.priority);
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }

}
