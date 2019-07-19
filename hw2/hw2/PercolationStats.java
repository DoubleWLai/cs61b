package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] numOfSite;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal Argument");
        }
        numOfSite = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int randomNum = StdRandom.uniform(1, N * N);
                int col;
                int row;
                if (randomNum % N == 0) {
                    col = N;
                } else {
                    col = randomNum % N;
                }
                row = ((randomNum - col) / N) + 1;
                perc.open(row, col);
            }
            double num = perc.numberOfOpenSites();
            double p = num / (N * N);
            numOfSite[i] = p;
        }
    }



    // sample mean of percolation threshold
    public double mean() {
        double i = 0;
        for (double a : numOfSite) {
            i = i + a;
        }
        return (i/numOfSite.length);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double u = mean();
        double i = 0;
        for ( double a : numOfSite) {
            i = i + ((a - u) * (a - u));
        }
        return i / (numOfSite.length -1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double oo = stddev();
        double u = mean();
        double o = Math.sqrt(oo);
        return u - (1.96 * o);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double oo = stddev();
        double u = mean();
        double o = Math.sqrt(oo);
        return u + (1.96 * o);
    }
}
