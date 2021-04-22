/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename EdgeTest.java
 * @description JUnit test class for Edge Object
 */
package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest
{

  private static final double STARTNODE = 1.0;
  private static final double ENDNODE = 5.0;
  private static final double WEIGHT = 1.2;

  private static final String CORRECTEMPTYOBJECT = "Edge{source = 0.0, dest = 0.0, weight = 0.0}";
  private static final String CORRECTFULLOBJECT = "Edge{source = 1.0, dest = 5.0, weight = 1.2}";

  private static Edge edge = null;

  @Before
  public void setUp()
  {
    edge = new Edge();
  }

  @Test
  public void defaultConstructerTest()
  {
    assertEquals(CORRECTEMPTYOBJECT, edge.toString());
  }

  @Test
  public void fullConstructerTest()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    assertEquals(CORRECTFULLOBJECT, edge.toString());
  }

  @Test
  public void semiFullConstructerTest()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    assertEquals(CORRECTFULLOBJECT, edge.toString());
  }

  @Test
  public void getSetSourceTest()
  {
    edge.setSource(STARTNODE);
    assertTrue(STARTNODE == edge.getSource());
  }

  @Test
  public void getSetDesthTest()
  {
    edge.setDest(ENDNODE);
    assertTrue(ENDNODE == edge.getDest());
  }

  @Test
  public void getSetWeightTest()
  {
    edge.setWeight(WEIGHT);
    assertTrue(WEIGHT == edge.getWeight());
  }

  @Test
  public void toStringTest()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    assertEquals(CORRECTFULLOBJECT, edge.toString());
  }

  @Test
  public void equalsTestTrue()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    Edge temp = new Edge(STARTNODE, ENDNODE, WEIGHT);

    assertTrue(temp.equals(edge));
  }

  @Test
  public void equalsTestFalse()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    Edge temp = new Edge(STARTNODE, ENDNODE, ENDNODE);

    assertFalse(temp.equals(edge));
  }

  @Test
  public void cloneAndCopyConstructorTest()
  {
    edge = new Edge(STARTNODE, ENDNODE, WEIGHT);

    Edge temp = edge.clone();

    assertTrue(temp.equals(edge));
  }
}