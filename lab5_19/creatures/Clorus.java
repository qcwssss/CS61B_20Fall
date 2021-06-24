package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.Map;

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

	/**
	 * Creates a Clorus with energy equal to 1.
	 */
	public Clorus() {
		this(1);
	}

	/**
	 * Should always return the color
	 * red = 34, green = 0, blue = 231.
	 */
	public Color color() {
		r = 34;
		g = 0;
		b = 231;
		return color(r, g, b);
	}

	/**
	 * A helper method to check whether the energy of
	 * a Clorus is below 0.
	 * If so, return 0.
	 * @param e energy
	 * @return checked energy.
	 */
	private double checkClorusEnergy(double e){
		e = Math.max(0, e);
		return e;
	}

	/** Cloruses lose 0.03 units of energy on a MOVE. */
	public void move() {
		energy = checkClorusEnergy(energy - 0.03);
	}

	/**
	 * Cloruses lose 0.01 units of energy on a STAY.
	 */
	public void stay() {
		energy = checkClorusEnergy(energy - 0.01);
	}

	/**
	 * If a Clorus attacks another creature,
	 * it should gain that creature’s energy.
	 * @param c other creature
	 */
	public void attack(Creature c) {
		energy += c.energy();
	}

	/**
	 * When a Clorus replicates, it keeps 50% of its energy.
	 * The other 50% goes to its offspring.
	 * @return a newborn Clorus
	 */
	public Creature replicate() {
		energy = energy * 0.5;
		return new Clorus(energy);
	}

  /**
   * This Clorus will choose an action
   * depends on the the grid around it.
   *
   * <p>Cloruses should obey exactly the following behavioral rules:
   *
   * 1. If there are no empty squares, the Clorus will STAY
   * (even if there are Plips nearby they could attack
   * not count as empty squares).
   * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of
   * them randomly.
   * 3. Otherwise, if the Clorus has energy greater than or equal to one, it will
   * REPLICATE to a random empty square. Otherwise, the Clorus will MOVE to a random empty square.
   *
   * @param neighbors
   */
  public Action chooseAction(Map<Direction, Occupant> neighbors) {
		return null;
	}


}
