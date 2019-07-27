import java.awt.Color;
import java.util.Arrays;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture pic;
    private double[][] energy;
    private SeamRemover sr;

    public SeamCarver(Picture picture) {
        pic = picture;
        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }
    }

    // width of current picture
    public int width() {
        return pic.width();
    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    public Picture picture() {
        return pic;
    }

    private double energyX(int x, int y) {
        int x1, x2, y1, y2;
        y1 = y;
        y2 = y;

        if (x > 0 && x < width() - 1) {
            x1 = x - 1;
            x2 = x + 1;
        } else if (x == 0) {
            x1 = width() - 1;
            x2 = x + 1;
        } else if (x == width() - 1) {
            x1 = x - 1;
            x2 = 0;
        } else {
            throw new IllegalArgumentException("x is out of bounds!");
        }

        Color l = pic.get(x1, y1);
        Color r = pic.get(x2, y2);

        double Rx = Math.abs(l.getRed() - r.getRed());
        double Gx = Math.abs(l.getGreen() - r.getGreen());
        double Bx = Math.abs(l.getBlue() - r.getBlue());

        return Math.pow(Rx, 2) + Math.pow(Gx, 2) + Math.pow(Bx, 2);

    }

    private double energyY(int x, int y) {
        int x1, x2, y1, y2;
        x1 = x;
        x2 = x;

        if (y > 0 && y < height() - 1) {
            y1 = y - 1;
            y2 = y + 1;
        } else if (y == 0) {
            y1 = height() - 1;
            y2 = y + 1;
        } else if (y == height() - 1) {
            y1 = y - 1;
            y2 = 0;
        } else {
            throw new IllegalArgumentException("y is out of bounds!");
        }

        Color l = pic.get(x1, y1);
        Color r = pic.get(x2, y2);

        double Rx = Math.abs(l.getRed() - r.getRed());
        double Gx = Math.abs(l.getGreen() - r.getGreen());
        double Bx = Math.abs(l.getBlue() - r.getBlue());

        return Math.pow(Rx, 2) + Math.pow(Gx, 2) + Math.pow(Bx, 2);

    }

    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return energyX(x, y) + energyY(x, y);
    }

    private int findMin(double[] e) {
        int minIndex = 0;
        for (int i = 0; i < e.length; i++) {
            if (e[i] < e[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int[] findVerticalSeam() {
        int row = energy.length;;
        int col = energy[0].length;
        int[] vSeam = new int[row];
        double[] candidate;

        vSeam[0] = findMin(energy[0]);

        for (int index = 1; index < row; index ++) {
            candidate = new double[col];
            for (int e = 0; e < candidate.length; e += 1) {
                candidate[e] = Integer.MAX_VALUE;
            }
            System.arraycopy(energy[index], vSeam[index - 1] - 1, candidate, vSeam[index - 1] - 1, 3);
            vSeam[index] = findMin(candidate);
        }
        return vSeam;
    }

    private double[][] transpose(double[][] original) {

        double[][] transpose = new double[original[0].length][original.length];
        for (int i = 0; i < original.length; i += 1) {
            for (int j = 0; j < original[0].length; j += 1) {
                transpose[j][i] = original[i][j];
            }
        }
        return transpose;
    }

    public int[] findHorizontalSeam() {
        energy = transpose(energy);
        int[] vSeam = findVerticalSeam();
        energy = transpose(energy);
        return vSeam;
    }


    private boolean checkSeam(int[] seam) {
        int prev = seam[0];
        for (int i = 1; i < seam.length; i += 1) {
            if (seam[i] - prev > 1) {
                return false;
            }
            prev = seam[i];
        }
        return true;

    }



    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || !checkSeam(seam)) {
            throw new IllegalArgumentException("wrong length or not a valid seam.");
        }

        pic = sr.removeHorizontalSeam(pic, seam);

        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }
    }



    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || !checkSeam(seam)){
            throw new IllegalArgumentException("wrong length or not a valid seam.");
        }

        pic = sr.removeVerticalSeam(pic, seam);

        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }
    }
}
