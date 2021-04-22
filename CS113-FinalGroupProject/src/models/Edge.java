/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename Edge.java
 * @description This class is used to save data in the Graph
 */
package models;

import java.io.Serializable;

public class Edge implements Serializable, Comparable
{
  private double dest;
  private double source;
  private double weight;

  public Edge()
  {
    this.dest = 0.0;
    this.source = 0.0;
    this.weight = 0.0;
  }

  public Edge(double source, double dest, double weight)
  {
    this.dest = dest;
    this.source = source;
    this.weight = weight;
  }

  /**
  * Copy constructor
  *
  * @see String#Edge(Edge other)
  *
  * @param other which is of the Edge class
  *
  */
  public Edge(Edge other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.dest = other.dest;
    this.source = other.source;
    this.weight = other.weight;
  }

  /** Setter for travel dest
   * @return double which is the dest
   */
  public double getDest()
  {
    return dest;
  }

  /** Setter for travel source
   * @return double which is the source
   */
  public double getSource()
  {
    return source;
  }

  /** Setter for weight
   * @return time needed to travel to this podouble
   */
  public double getWeight()
  {
    return weight;
  }

  /** Setter for travel dest
   * @param dest new dest
   */
  public void setDest(double dest)
  {
    this.dest = dest;
  }

  /** Setter for travel source
   * @param source new source
   */
  public void setSource(double source)
  {
    this.source = source;
  }

  /** Setter for travel time
   * @param weight new travel time
   */
  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  /** Will check if this object is the same as other object
   * @param o other object
   * @return True if both objects are the same, false if they are not
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

    Edge edge = (Edge) o;

    return dest == edge.dest && source == edge.source && weight == edge.weight;
  }

  /** Returns a string representation of this object
   * @return A string representation of this object
   */
  @Override
  public String toString()
  {
    return "Edge{" + "source = " + source + ", dest = " + dest +  ", weight = " + weight + '}';
  }

  /** Clones this object
   * @return A replica of this object, not a refference
   */
  public Edge clone()
  {
    return new Edge(this);
  }

  /**
   *
   * @param other object that is compared agains this object
   * @return 1 if this is greater than parameter
   *         -1 if this is less than parameter
   *         0 if this is equal to parameter
   */
  public int compareTo(Object other)
  {
    Edge temp = (Edge) other;
    if(this.getWeight() > temp.getWeight())
    {
      return 1;
    }
    else if(this.getWeight() < temp.getWeight())
    {
      return -1;
    }
    else
    {
      return 0;
    }
  }
}
