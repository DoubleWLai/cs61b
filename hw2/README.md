# [HW 1: Packages, Interfaces, Generics, Exceptions, Iteration](https://sp18.datastructur.es/materials/hw/hw2/hw2)

## Introduction

Percolation. Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

The model. We model a percolation system using an N-by-N grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

The problem. In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (top) and 100-by-100 random grid (bottom).


## Percolation.java - API
public class Percolation {
   -public Percolation(int N)                // create N-by-N grid, with all sites initially blocked
   -public void open(int row, int col)       // open the site (row, col) if it is not open already
   -public boolean isOpen(int row, int col)  // is the site (row, col) open?
   -public boolean isFull(int row, int col)  // is the site (row, col) full?
   -public int numberOfOpenSites()           // number of open sites
   -public boolean percolates()              // does the system percolate?
   -public static void main(String[] args)   // use for unit testing (not required)
}

## PercolationStats.java
Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:

Initialize all sites to be blocked.
Repeat the following until the system percolates:
Choose a site uniformly at random among all blocked sites.
Open the site.
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
For example, if sites are opened in a 20-by-20 grid according to the snapshots below, then our estimate of the percolation threshold is 204/400 = 0.51 because the system percolates when the 204th site is opened. The images correspond to the 50, 100, 150, and 204 sites being open, respectively.

public class PercolationStats {
   -public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
   -public double mean()                                           // sample mean of percolation threshold
   -public double stddev()                                         // sample standard deviation of percolation threshold
   -public double confidenceLow()                                  // low endpoint of 95% confidence interval
   -public double confidenceHigh()                                 // high endpoint of 95% confidence interval
}
