package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final static int virtualTop = 0;
    private int n;
    private int gridLength;
    private int virtualBottom;
    private boolean[] grid;
    private boolean isPercolated;
    private WeightedQuickUnionUF unionInstance;
    private WeightedQuickUnionUF backWash;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int n) {
        if(n < 1) {
            throw new java.lang.IllegalArgumentException("Illegal argument!");
        }
        this.n = n;
        gridLength = n * n + 2;
        isPercolated = false;
        virtualBottom = n * n + 1;
        unionInstance = new WeightedQuickUnionUF(gridLength);
        backWash = new WeightedQuickUnionUF(gridLength - 1);
        grid = new boolean[gridLength];
    }

    private void isValidBounds(int row, int col) {
        if (row < 1 || row > n) {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        if (col < 1 || col > n) {
            throw new IndexOutOfBoundsException("col index out of bounds");
        }
    }

    private int xyTo1D(int row, int col) {
        int i = (row - 1) * n + col;
        return i;
    }

    private boolean isPosValid(int row, int col) {
        if (row >= 1 && row <= n && col >=1 && col <= n) {
            return true;
        }
        return false;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValidBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int gridId = xyTo1D(row, col);
        grid[gridId] = true;
        if (row == 1) {
            unionInstance.union(virtualTop, gridId);
            backWash.union(virtualTop, gridId);
        }
        if (n == row) {
            unionInstance.union(virtualBottom, gridId);
        }
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            int posX = row + dx[i];
            int posY = col + dy[i];
            if (isPosValid(posX, posY) && isOpen(posX, posY)) {
                int posId = xyTo1D(posX, posY);
                unionInstance.union(gridId, posId);
                backWash.union(gridId, posId);
            }
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValidBounds(row, col);
        return (grid[xyTo1D(row, col)]);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isValidBounds(row, col);
        return backWash.connected(virtualTop, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        int openNum = 0;
        for (int i = 1; i < virtualBottom; i++) {
            if (grid[i]) {
                openNum++;
            }
        } return openNum;
    }

    // does the system percolate?
    public boolean percolates() {
        if (isPercolated) {
            return true;
        }
        if (unionInstance.connected(virtualTop, virtualBottom)) {
            isPercolated = true;
            return true;
        }
        return false;
    }
}
