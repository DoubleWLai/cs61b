package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;

public class Board implements WorldState{

    private int N;
    private int BLANK = 0;
    private ArrayList<Integer> board;

    public Board(int[][] titles) {
        board = new ArrayList<>();
        N = titles[0].length;

        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                board.add(titles[i][j]);
            }
        }
    }

    public int tileAt(int i, int j) {
        if (!validate(i) || !validate(j)) {
            throw new IndexOutOfBoundsException("j or i is out of bounds");
        }
        return board.get(i * N + j);
    }

    private boolean validate(int index) {
        return (index >=0 && index <= (N -1));
    }

    public int size() {
        return N;
    }

    /**
     *  Returns neighbors of this board.
     *  SPOILERZ: This is the answer.
     *  @source Josh Hug
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;

    }

    public int hamming() {
        int numOfWrong = 0;

        for (int i = 0; i < N * N -1; i++) {
            if (board.get(i) != i + 1) {
                numOfWrong += 1;
            }
        }
        return numOfWrong;
    }

    private int[] conv2D(int n) {
        int[] pos = new int[2];
        pos[0] = n / N;
        pos[1] = n % N;
        return pos;
    }

    private int compDist(int[] curr, int[] goal) {
        int dist = 0;
        dist += Math.abs(curr[0] - goal[0]);
        dist += Math.abs(curr[1] - goal[1]);
        return dist;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N * N - 1; i++) {
            int[] currPos = conv2D(board.indexOf(i + 1));
            int[] goalPos = conv2D(i);
            count += compDist(currPos, goalPos);
        }
        return count;
    }

    public int estimatedDistanceToGoal () {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board bd1 = (Board) y;

        if (this.N != bd1.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board.get(i * N + j) != bd1.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }














    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
