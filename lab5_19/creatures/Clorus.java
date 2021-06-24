package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

  /** red color. */
  private int r;
  /** green color. */
  private int g;
  /** blue color. */
  private int b;

  /** Creates Clorus with energy equal to E. */
  public Clorus(double e) {
    super("clorus");
    r = 0;
    g = 0;
    b = 0;
    energy = checkClorusEnergy(e);
  }

  /** Creates a Clorus with energy equal to 1. */
  public Clorus() {
    this(1);
  }

  /** Should always return the color red = 34, green = 0, blue = 231. */
  public Color color() {

    return color(34, 0, 231);
  }

  /**
   * A helper method to check whether the energy of a Clorus is below 0. If so, return 0.
   *
   * @param e energy
   * @return checked energy.
   */
  private double checkClorusEnergy(double e) {
    e = Math.max(0, e);
    return e;
  }

  /** Cloruses lose 0.03 units of energy on a MOVE. */
  @Override
  public void move() {
    energy = checkClorusEnergy(energy - 0.03);
  }

  /** Cloruses lose 0.01 units of energy on a STAY. */
  @Override
  public void stay() {
    energy = checkClorusEnergy(energy - 0.01);
  }

  /**
   * If a Clorus attacks another creature, it should gain that creature’s energy.
   *
   * @param c other creature
   */
  @Override
  public void attack(Creature c) {
    energy += c.energy();
  }

  /**
   * When a Clorus replicates, it keeps 50% of its energy. The other 50% goes to its offspring.
   *
   * @return a newborn Clorus
   */
  @Override
  public Clorus replicate() {
    energy = energy * 0.5;
    return new Clorus(energy);
  }

  /**
   * This Clorus will choose an action depends on the the grid around it.
   *
   * <p>Cloruses should obey exactly the following behavioral rules:
   *
   * <p>
   * 1. If there are no empty squares, the Clorus will STAY
   * (even if there are Plips nearby they
   * could attack not count as empty squares).
   * 2. Otherwise, if any Plips are seen, the Clorus will
   * ATTACK one of them randomly.
   * 3. Otherwise, if the Clorus has energy greater than or equal to
   * one, it will REPLICATE to a random empty square.
   * 4. Otherwise, the Clorus will MOVE to a random
   * empty square.
   *
   * @param neighbors
   */
  public Action chooseAction(Map<Direction, Occupant> neighbors) {
    ArrayDeque<Direction> emptyNeighbors = new ArrayDeque<>();
    ArrayDeque<Direction> plipsNeighbors = new ArrayDeque<>();
    boolean anyPlips = false;

    for (Direction d : neighbors.keySet()) {
      if (neighbors.get(d).name().equals("empty")) {
        emptyNeighbors.add(d);
      }
      else if (neighbors.get(d).name().equals("plip")) {
        anyPlips = true;
        plipsNeighbors.add(d);
      }
    }
    // Rule 1: No empty squares, STAY.
    if (emptyNeighbors.isEmpty()) {
      return new Action(Action.ActionType.STAY);
    }
    // Rule 2
    else if (anyPlips) {
      return new Action(Action.ActionType.ATTACK, randomEntry(plipsNeighbors));
    }
    // Rule 3
    else if (energy >= 1) {
      return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
    // Rule 4
    } else {
      return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
  }
}
