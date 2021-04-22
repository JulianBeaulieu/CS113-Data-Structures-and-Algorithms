/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description Class which holds a number and a letter.
 *              Used for a Huffman Tree, hence the name
 */
package edu.miracosta.cs113;

import java.util.Objects;
import java.io.Serializable;

public class HuffmanNode implements Comparable, Serializable
{
  //Instance Variables
  private String letter;
  private double amt;

  //constructors

  /**
   * Default Constructor
   */
  public HuffmanNode()
  {
    this.letter = "";
    this.amt = 0.0;
  }


  /** Constructor with parameters
   * @param letter is the letter the node holds
   * @param amt is the amount of numbers
   */
  public HuffmanNode(String letter, double amt)
  {
    this.letter = letter;
    this.amt = amt;
  }

  /**
  * Copy constructor
  *
  * @see String#HuffmanNode(HuffmanNode other)
  *
  * @param other which is of the HuffmanNode class
  *
  */
  public HuffmanNode(HuffmanNode other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.letter = other.letter;
    this.amt = other.amt;
  }

  //Accessors / Mutators


  /** Accessor for the letter variable
   * @return the letter as a string
   */
  public String getLetter()
  {
    return letter;
  }

  /** Accessor for the amount variable
   * @return the amount as a double
   */
  public double getAmt()
  {
    return amt;
  }

  /** Mutator for the amount variable
   * @param amt which is the amount of times a letter is in a text
   */
  public void setAmt(double amt)
  {
    this.amt = amt;
  }

  /** Mutator for the letter variable
   * @param letter which is the letter saved
   */
  public void setLetter(String letter)
  {
    this.letter = letter;
  }

  //other methods


  /** Clone Method for this object
   * @return a cloned version of this variable
   */
  public HuffmanNode clone()
  {
    return new HuffmanNode(this);
  }

  /** Compares this object with another one and tells if they are the same
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HuffmanNode that = (HuffmanNode) o;
    return amt == that.amt &&
            Objects.equals(letter, that.letter);
  }

  /**
   * Returns a string representation of the object. In general, the
   * {@code toString} method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a
   * person to read.
   * @return  a string representation of the object.
   */
  @Override
  public String toString()
  {
    return "HuffmanNode{" +
            "letter='" + letter + '\'' +
            ", amt=" + amt +
            '}';
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   * <p>
   * <p>The implementor must ensure
   * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
   * for all {@code x} and {@code y}.  (This
   * implies that {@code x.compareTo(y)} must throw an exception iff
   * {@code y.compareTo(x)} throws an exception.)
   * <p>
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
   * {@code x.compareTo(z) > 0}.
   * <p>
   * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
   * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
   * all {@code z}.
   * <p>
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   * class that implements the {@code Comparable} interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   * <p>
   * <p>In the foregoing description, the notation
   * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
   * <i>signum</i> function, which is defined to return one of {@code -1},
   * {@code 0}, or {@code 1} according to whether the value of
   * <i>expression</i> is negative, zero, or positive, respectively.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   */
  @Override
  public int compareTo(Object o) {
    HuffmanNode other = (HuffmanNode) o;

    if(this.amt < other.getAmt())
    {
      return -1;
    }
    else if(this.amt > other.getAmt())
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }
}
