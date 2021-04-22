/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename BlackBoxTest.java
 * @description JUnit test class for BlackBox Object
 */
package models;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static java.lang.Double.NaN;
import static org.junit.Assert.*;

public class BlackBoxTest
{
  private static final int ILLEGALSTART = -4;
  private static final int ILLEGALEND = 200;


  private static final int STARTEASY = 1;
  private static final int STARTSEMIHARD = 47;
  private static final int STARTHARD = 47;

  private static final int ENDEASY = 5;
  private static final int ENDSEMIEASY = 26;
  private static final int ENDSEMIHARD = 83;
  private static final int ENDHARD = 105;

  //easy
  private static final double TRAVELTIMEEASY = 1.2;
  private static final double TRAVELDISTANCEEASY = 0.07;
  private static final int[] NODESTRAVELEASY = {1, 2, 3, 4, 5};

  //semiEasy
  private static final double TRAVELTIMESEMIEASY = 4.3;
  private static final double TRAVELDISTANCESEMIEASY = 0.26;
  private static final int[] NODESTRAVELSEMIEASY = {1, 2, 6, 23, 26};

  //semiHard
  private static final double TRAVELTIMESEMIHARD = 2.5;
  private static final double TRAVELDISTANCESEMIHARD = 0.15;
  private static final int[] NODESTRAVELSEMIHARD = {47, 48, 49, 50, 61, 60, 66, 81, 82, 83};

  //Hard
  private static final double TRAVELTIMEHARD = 5.7;
  private static final double TRAVELDISTANCEHARD = 0.34;
  private static final int[] NODESTRAVELHARD = {47, 46, 45, 44, 51, 52, 53, 59, 54, 68, 78, 85, 86, 87, 88, 103, 104, 105};


  // done
  private static final double TRAVELTIMELONG = 9.0;
  private static final double TRAVELDISTANCELONG = 0.54;
  private static final int[] NODESTRAVELLONG = {1, 2, 6, 23, 22, 21, 20, 29, 32, 111, 56, 58, 70, 76, 75, 88, 103, 104, 105};

  private static BlackBox bB = null;

  @Before
  public void setUp()
  {
    bB = new BlackBox();
  }

  @Test
  public void easyRouteTest()
  {
    Information tempInfo = bB.calculateRoute(STARTEASY, ENDEASY);

    assertTrue("This should return the time needed to travel from starting Node: " + STARTEASY
            + " to ending Node: " + ENDEASY, tempInfo.getTime() == TRAVELTIMEEASY);

    assertTrue("This should return the distance from starting Node: " + STARTEASY
            + " to ending Node: " + STARTEASY, tempInfo.getDistance() == TRAVELDISTANCEEASY);

    assertArrayEquals("The two arrays should contain the same amount of nodes", NODESTRAVELEASY, tempInfo.getNodesTraveled());
  }

  @Test
  public void semiEasyRouteTest()
  {
    Information tempInfo = bB.calculateRoute(STARTEASY, ENDSEMIEASY);

    assertTrue("This should return the time needed to travel from starting Node: " + STARTEASY
            + " to ending Node: " + ENDSEMIEASY, tempInfo.getTime() == TRAVELTIMESEMIEASY);

    assertTrue("This should return the distance from starting Node: " + STARTEASY
            + " to ending Node: " + ENDSEMIEASY, tempInfo.getDistance() == TRAVELDISTANCESEMIEASY);

    assertArrayEquals("The two arrays should contain the same amount of nodes", NODESTRAVELSEMIEASY, tempInfo.getNodesTraveled());
  }

  @Test
  public void semiHardRouteTest()
  {
    Information tempInfo = bB.calculateRoute(STARTSEMIHARD, ENDSEMIHARD);

    assertTrue("This should return the time needed to travel from starting Node: " + STARTSEMIHARD
            + " to ending Node: " + ENDSEMIHARD, tempInfo.getTime() == TRAVELTIMESEMIHARD);

    assertTrue("This should return the distance from starting Node: " + STARTSEMIHARD
            + " to ending Node: " + ENDSEMIHARD, tempInfo.getDistance() == TRAVELDISTANCESEMIHARD);

    assertArrayEquals("The two arrays should contain the same amount of nodes", NODESTRAVELSEMIHARD, tempInfo.getNodesTraveled());
  }

  @Test
  public void hardRouteTest()
  {
    Information tempInfo = bB.calculateRoute(STARTHARD, ENDHARD);

    assertTrue("This should return the time needed to travel from starting Node: " + STARTHARD
            + " to ending Node: " + ENDHARD, tempInfo.getTime() == TRAVELTIMEHARD);

    assertTrue("This should return the distance from starting Node: " + STARTHARD
            + " to ending Node: " + ENDHARD, tempInfo.getDistance() == TRAVELDISTANCEHARD);

    assertArrayEquals("The two arrays should contain the same amount of nodes", NODESTRAVELHARD, tempInfo.getNodesTraveled());
  }

  @Test
  public void longRouteTest()
  {
    Information tempInfo = bB.calculateRoute(STARTEASY, ENDHARD);

    assertTrue("This should return the time needed to travel from starting Node: " + STARTEASY
                          + " to ending Node: " + ENDHARD, tempInfo.getTime() == TRAVELTIMELONG);

    assertTrue("This should return the distance from starting Node: " + STARTEASY
            + " to ending Node: " + ENDHARD, tempInfo.getDistance() == TRAVELDISTANCELONG);

    assertArrayEquals("The two arrays should contain the same amount of nodes", NODESTRAVELLONG, tempInfo.getNodesTraveled());
  }


  /**
   * Method, for developer to test a route
   *//*
  @Test
  public void test()
  {
    Information tempInfo = bB.calculateRoute(49, 110);

    System.out.println("Trave Time: " + tempInfo.getTime());
    System.out.println("Trave Distance: " + tempInfo.getDistance());

    for(int i : tempInfo.getNodesTraveled())
    {
      System.out.print(i + ", ");
    }
  }*/

  @Test
  public void hardCoreTest()
  {
    for(int from = 1; from < 113; from++)
    {
      for(int to = 1; to < 113; to++)
      {
        if(from != to)
        {
          Information tempInfo = bB.calculateRoute(from, to);

          assertTrue(tempInfo.getTime() != NaN);
          assertTrue(tempInfo.getDistance() != NaN);
          assertTrue(tempInfo.getTime() != Double.POSITIVE_INFINITY);
          assertTrue(tempInfo.getDistance() != Double.POSITIVE_INFINITY);
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalStartTest()
  {
    bB.calculateRoute(ILLEGALSTART, ENDEASY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalFinishTest()
  {
    bB.calculateRoute(STARTEASY, ILLEGALEND);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalStartIsFinishTest()
  {
    bB.calculateRoute(STARTEASY, STARTEASY);
  }

}
