package creatures;

import huglife.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestClorus {
  private Clorus c1;
  private Clorus c2;

  @Before
  public void setUp() {
    c1 = new Clorus();
    c2 = new Clorus(2.5);
  }

  @Test
  public void testClorusBasics() {
    Clorus c3 = new Clorus(0.4);
    assertEquals(2.5, c2.energy(), 0.01);
    assertEquals(new Color(34, 0, 231), c2.color());
    c2.move();
    assertEquals(2.47, c2.energy(), 0.001);
    for (int i = 0; i < 50; i++) {
      c1.move();
      c3.stay();
    }
    assertEquals(0, c1.energy(), 0.01);
    assertEquals(0, c3.energy(), 0.01);
  }

  @Test
  public void testStay() {
    double expected = c1.energy() -0.01;
    c1.stay();
    double actual = c1.energy();
    assertEquals(expected, actual, 0.01);
  }

  @Test
  public void testMove() {
    double expected = c1.energy() - 0.03;
    c1.move();
    double actual = c1.energy();
    assertEquals(expected, actual, 0.01);
  }

  @Test
  public void testReplicate() {
    double oldE = c2.energy();
    Clorus c2Son = c2.replicate();
    assertFalse(c2.equals(c2Son));
    assertEquals(oldE * 0.5, c2.energy());
    assertEquals(oldE * 0.5, c2Son.energy());
  }

  @Test
  public void testAttack() {
    Plip p1 = new Plip();
    Plip p2 = new Plip(2);
    double expected = c2.energy() + p1.energy();
    // first attack
    c2.attack(p1);
    assertEquals(expected, c2.energy());
    // second attack
    double expected1 = c2.energy() + p2.energy();
    c2.attack(p2);
    assertEquals(expected1, c2.energy());
  }

  @Test
  public void testChoose() {

    // no empty squares, stay
    HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
    surrounded.put(Direction.TOP, new Impassible());
    surrounded.put(Direction.BOTTOM, new Impassible());
    surrounded.put(Direction.LEFT, new Impassible());
    surrounded.put(Direction.RIGHT, new Impassible());

    Action actual = c2.chooseAction(surrounded);
    Action expected = new Action((Action.ActionType.STAY));

    assertEquals(expected, actual);

    // if see some Plips, the Clorus attack them randomly.
    HashMap<Direction, Occupant> topEmpty = new HashMap<>();
    topEmpty.put(Direction.TOP, new Empty());
    topEmpty.put(Direction.BOTTOM, new Plip());
    topEmpty.put(Direction.LEFT, new Impassible());
    topEmpty.put(Direction.RIGHT, new Plip(2));

    Action actual2 = c2.chooseAction(topEmpty);
    Action expected2 = new Action(Action.ActionType.ATTACK, Direction.BOTTOM );
    Action expected2b = new Action(Action.ActionType.ATTACK, Direction.RIGHT );

    assertTrue(actual2.equals(expected2) || actual2.equals(expected2b));


    // Otherwise, energy >= 1, REPLICATE to a random empty square.
    HashMap<Direction, Occupant> allEmpty = new HashMap<>();
    allEmpty.put(Direction.TOP, new Empty());
    allEmpty.put(Direction.BOTTOM, new Impassible());
    allEmpty.put(Direction.LEFT, new Impassible());
    allEmpty.put(Direction.RIGHT, new Empty());

    Action actual3 = c2.chooseAction(allEmpty);
    Action expected3 = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
    Action expected3b = new Action(Action.ActionType.REPLICATE, Direction.TOP);

    //assertEquals(expected3, actual3);
    assertTrue(actual3.equals(expected3) || actual3.equals(expected3b));


    // if energy < 1, MOVE to a random empty square.
    Clorus c4 = new Clorus(0.4);

    Action actual4 = c4.chooseAction(allEmpty);
    Action expected4 = new Action(Action.ActionType.MOVE, Direction.RIGHT);
    Action expected4b = new Action(Action.ActionType.MOVE, Direction.TOP);

    //assertEquals(expected4, actual4);
    assertTrue(actual4.equals(expected4) || actual4.equals(expected4b));

  }
}
