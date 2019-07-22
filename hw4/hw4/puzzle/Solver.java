package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private ArrayList<WorldState> solution;

    public Solver(WorldState initial) {
        solution = new ArrayList<>();
        MinPQ<SNode> pq = new MinPQ<>();
        pq.insert(new SNode(initial, 0, null));

        while (!pq.min().getWorldState().isGoal()) {
            SNode X = pq.delMin();

            for (WorldState neighbor : X.getWorldState().neighbors()) {
                if (X.getPrev() == null || !(neighbor.equals(X.getPrev().getWorldState()))) {
                    pq.insert(new SNode(neighbor, X.getMoves() + 1, X));
                }
            }
        }

        SNode s = pq.min();
        while (s != null) {
            solution.add(0, s.getWorldState());
            s = s.getPrev();
        }
    }


    private class SNode implements Comparable<SNode>{
        private WorldState ws;
        private int numOfMoves;
        private SNode prev;

        private SNode(WorldState ws, int m, SNode p) {
            this.ws = ws;
            this.numOfMoves = m;
            this.prev = p;
        }

        public WorldState getWorldState() {
            return ws;
        }

        public int getMoves() {
            return numOfMoves;
        }

        public SNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SNode sn) {
            return ( ( this.numOfMoves + ws.estimatedDistanceToGoal() ) - ( sn.numOfMoves + sn.ws.estimatedDistanceToGoal() ) );
        }
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
