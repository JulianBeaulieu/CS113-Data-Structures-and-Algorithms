/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename InformationTest.java
 * @description JUnit test class for Information Object
 */
package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InformationTest
{
  private static final int STARTNODE = 1;
  private static final int ENDNODE = 5;
  private static final double TRAVELTIME = 1.2;
  private static final double TRAVELDISTANCE = 0.07;
  private static final int[] NODESTRAVELED = {1, 2, 3, 4, 5};

  private static final String CORRECTEMPTYOBJECT = "Information{start=0, finish=0, time=0.0, distance=0.0, nodesTraveled= 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0}";
  private static final String CORRECTFULLOBJECT = "Information{start=1, finish=5, time=1.2, distance=0.07, nodesTraveled= 1.0 2.0 3.0 4.0 5.0}";

  private static Information info = null;

  @Before
  public void setUp()
  {
    info = new Information();
  }

  @Test
  public void defaultConstructerTest()
  {
    assertEquals(CORRECTEMPTYOBJECT, info.toString());
  }

  @Test
  public void fullConstructerTest()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    assertEquals(CORRECTFULLOBJECT, info.toString());
  }

  @Test
  public void semiFullConstructerTest()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, NODESTRAVELED);

    assertEquals(CORRECTFULLOBJECT, info.toString());
  }

  @Test
  public void getSetStartTest()
  {
    info.setStart(STARTNODE);
    assertEquals(STARTNODE, info.getStart());
  }

  @Test
  public void getSetFinishTest()
  {
    info.setFinish(ENDNODE);
    assertEquals(ENDNODE, info.getFinish());
  }

  @Test
  public void getSetTimeTest()
  {
    info.setTime(TRAVELTIME);
    assertTrue(TRAVELTIME == info.getTime());
  }

  @Test
  public void getSetDistanceTest()
  {
    info.setDistance(TRAVELDISTANCE);
    assertTrue(TRAVELDISTANCE == info.getDistance());
  }

  @Test
  public void getSetNodesTraveledTest()
  {
    info.setNodesTraveled(NODESTRAVELED);
    assertEquals(NODESTRAVELED, info.getNodesTraveled());
  }

  @Test
  public void toStringTest()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    assertEquals(CORRECTFULLOBJECT, info.toString());
  }

  @Test
  public void equalsTestTrue()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    Information temp = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    assertTrue(temp.equals(info));
  }

  @Test
  public void equalsTestFalse()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    Information temp = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELTIME, NODESTRAVELED);

    assertFalse(temp.equals(info));
  }

  @Test
  public void cloneAndCopyConstructorTest()
  {
    info = new Information(STARTNODE, ENDNODE, TRAVELTIME, TRAVELDISTANCE, NODESTRAVELED);

    Information temp = info.clone();

    assertTrue(temp.equals(info));
  }
}