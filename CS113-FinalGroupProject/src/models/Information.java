/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename Information.java
 * @description This class is used to transfer data from the black box to the view
 */
package models;

import java.math.BigDecimal;
import java.util.Arrays;

public class Information
{
  private int start;
  private int finish;
  private double time;
  private double distance;
  private int[] nodesTraveled;

  /*####################################################################*/
  /*##################### Constructors #################################*/
  /*####################################################################*/

  /**
   * Default constructor
   */
  public Information()
  {
    this.start = 0;
    this.finish = 0;
    this.time = 0.0;
    this.time = 0.0;
    this.nodesTraveled = new int[10];
  }

  /**
   * Constructor with all parameters
   * @param start start position/node
   * @param finish end position/node
   * @param time travel time between nodes
   * @param distance distance time between nodes
   * @param nodesTraveled nodes needed to go from A to B
   */
  public Information(int start, int finish, double time, double distance, int[] nodesTraveled)
  {
    this.start = start;
    this.finish = finish;
    this.time = time;
    this.distance = distance;
    this.nodesTraveled = nodesTraveled;
  }

  /**
   * Constructor with all parameters except distance
   *
   * Distance will be calculated by multiplying the time by 0.06miles
   *
   * @param start start position/node
   * @param finish end position/node
   * @param time travel time between nodes
   * @param nodesTraveled nodes needed to go from A to B
   */
  public Information(int start, int finish, double time, int[] nodesTraveled)
  {
    this.start = start;
    this.finish = finish;
    this.time = roundDouble(time);
    this.distance = roundDouble(time * 0.06); //conversion factor from time to distance
    this.nodesTraveled = nodesTraveled;
  }

  /**
   * Copy constructor
   *
   * @see String#Information(Information other)
   *
   * @param other which is of the Information class
   *
   */
  public Information(Information other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.start = other.start;
    this.finish = other.finish;
    this.time = other.time;
    this.distance = other.distance;
    this.nodesTraveled = other.nodesTraveled;
  }

  /*####################################################################*/
  /*##################### Getters & Setters ############################*/
  /*####################################################################*/

  /** Accessor for
   * @return start
   */
  public int getStart()
  {
    return start;
  }

  /** Accessor for
   * @return finish
   */
  public int getFinish()
  {
    return finish;
  }

  /** Accessor for
   * @return time
   */
  public double getTime()
  {
    return time;
  }

  /** Accessor for
   * @return distance
   */
  public double getDistance()
  {
    return distance;
  }

  /** Accessor for
   * @return nodesTraveled
   */
  public int[] getNodesTraveled()
  {
    return nodesTraveled;
  }

  /** Mutator for start
   * @param start
   */
  public void setStart(int start)
  {
    this.start = start;
  }

  /** Mutator for time
   * @param time
   */
  public void setTime(double time)
  {
    this.time = roundDouble(time);
  }

  /** Mutator for distance
   * @param distance
   */
  public void setDistance(double distance)
  {
    this.distance = roundDouble(distance);
  }

  /** Mutator for finish
   * @param finish
   */
  public void setFinish(int finish)
  {
    this.finish = finish;
  }

  /** Mutator for nodesTraveled
   * @param nodesTraveled
   */
  public void setNodesTraveled(int[] nodesTraveled)
  {
    this.nodesTraveled = nodesTraveled;
  }

  /*####################################################################*/
  /*##################### Default Methods ##############################*/
  /*####################################################################*/

  /** Method rounds a double to three sig figs
   * @param unroundedValue the unrounded value
   * @return the value rounded to three sig figs
   */
  private double roundDouble(double unroundedValue)
  {
    int decimalPlaces = 2;
    BigDecimal bd = new BigDecimal(unroundedValue);

    bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    return bd.doubleValue();
  }

  /** This method will compare to Information Object
   * @param o the other object which will be compared with
   * @return true if they are the same or equal, false if they aren't
   */
  @Override
  public boolean equals(Object o)
  {
    if(this == o)
    {
      return true;
    }
    if(o == null || getClass() != o.getClass())
    {
      return false;
    }
    Information that = (Information) o;
    return start == that.start && finish == that.finish && time == that.time && distance == that.distance && Arrays.equals(nodesTraveled, that.nodesTraveled);
  }

  /** Clones this object
   * @return A replica of this object, not a reference
   */
  public Information clone()
  {
    return new Information(this);
  }

  /** Returns a string representation of this obejct
   * @return a string containing this object
   */
  @Override
  public String toString()
  {
    String strng = "";

    for(double d : nodesTraveled)
    {
      strng += " " + d;
    }

    return "Information{" + "start=" + start + ", finish=" + finish + ", time=" + time + ", distance=" + distance + ", nodesTraveled=" + strng + '}';
  }
}