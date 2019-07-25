package creatures;
import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;


public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;


    public Clorus(double e) {
        super("clrus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }


    public Clorus() {
        this(1);
    }


    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }


    public void attack(Creature c) {
        energy += c.energy();
    }


    public void move() {
        energy -= 0.03;
    }



    public void stay() {
        energy -= 0.01;

    }


    public Clorus replicate() {
        this.energy = this.energy * 0.5;
        Clorus repl = new Clorus(this.energy);
        return repl;
    }



    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> food = getNeighborsOfType(neighbors, "plip");

        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else {
            if (food.size() != 0) {
                Direction moveDir = HugLifeUtils.randomEntry(food);
                return new Action(Action.ActionType.ATTACK, moveDir);
            }
            if (energy >= 1) {
                Direction moveDir = HugLifeUtils.randomEntry(empties);
                return new Action(Action.ActionType.REPLICATE, moveDir);
            }
        }
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }
}

