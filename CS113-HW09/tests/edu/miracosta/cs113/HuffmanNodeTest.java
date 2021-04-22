/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description JUnit test for HuffmanNode class
 */
package edu.miracosta.cs113;

import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanNodeTest
{
  private static HuffmanNode huffNode = null;

  private static void setNode(String letter, double amt)
  {
    huffNode = new HuffmanNode(letter, amt);
  }

  /**
   *  Tests getLetter method
   */
  @Test
  public void getLetter()
  {
    setNode("A", 1.0);

    assertTrue(huffNode.getLetter().equals("A"));
  }

  /**
   * Tests getAmt method
   */
  @Test
  public void getAmt()
  {
    setNode("A", 1.0);

    assertTrue(huffNode.getAmt() == 1.0);
  }

  /**
   *  Tests setAmt Method
   */
  @Test
  public void setAmt()
  {
    huffNode.setAmt(5.0);

    assertTrue(huffNode.getAmt() == 5.0);
  }

  /**
   *  Tests setLetter Method
   */
  @Test
  public void setLetter()
  {
    huffNode.setLetter("B");

    assertTrue(huffNode.getLetter().equals("B"));
  }

  /**
   *  Tests clone Method
   */
  @Test
  public void cloneTest()
  {
    HuffmanNode huffNodeClone = huffNode.clone();

    assertTrue(huffNode.equals(huffNodeClone));
  }

  /**
   *  Tests equal Method for true
   */
  @Test
  public void equalsTrue()
  {
    setNode("A", 1.0);

    assertTrue(huffNode.equals(new HuffmanNode("A", 1.0)));
  }

  /**
   *  Tests equal Method for false
   */
  @Test
  public void equalsFalse()
  {
    setNode("A", 1.0);

    assertFalse(huffNode.equals(new HuffmanNode("B", 6.0)));
  }

  /**
   *  Tests toString Method
   */
  @Test
  public void toStringTest()
  {
    setNode("A", 1.0);

    assertEquals(huffNode.toString(), "HuffmanNode{letter='A', amt=1.0}");
  }

  /**
   *  Tests compareTo Method for bigger
   */
  @Test
  public void compareToBigger()
  {
    setNode("A", 1.0);
    HuffmanNode temp = new HuffmanNode("A", 6.0);

    assertEquals(huffNode.compareTo(temp), -1);
  }

  /**
   *  Tests compareTo Method for smaller
   */
  @Test
  public void compareToSmaller()
  {
    setNode("A", 7.0);
    HuffmanNode temp = new HuffmanNode("A", 6.0);

    assertEquals(huffNode.compareTo(temp), 1);
  }

  /**
   *  Tests compareTo Method for equal
   */
  @Test
  public void compareToEqual()
  {
    setNode("A", 1.0);
    HuffmanNode temp = new HuffmanNode("A", 1.0);

    assertEquals(huffNode.compareTo(temp), 0);
  }
}
