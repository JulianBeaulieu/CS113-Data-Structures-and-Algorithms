/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description JUnit test for CompareHuffmanTrees class
 */
package edu.miracosta.cs113;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CompareHuffmanTreesTest
{
  //creates and initializes comparator object
  private static CompareHuffmanTrees comparatorImparator = new CompareHuffmanTrees();

  /**
   * Tests for right tree to be smaller than left tree
   */
  @Test
  public void compareBigger()
  {
    BinaryTree<HuffmanNode> leftTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 6), null, null);
    BinaryTree<HuffmanNode> rightTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 4), null, null);

    assertTrue(comparatorImparator.compare(leftTree, rightTree) == 1);
  }

  /**
   * Tests for right tree to be bigger than left tree
   */
  @Test
  public void compareSmaller()
  {
    BinaryTree<HuffmanNode> leftTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 4), null, null);
    BinaryTree<HuffmanNode> rightTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 6), null, null);

    assertTrue(comparatorImparator.compare(leftTree, rightTree) == -1);
  }

  /**
   * Tests for right tree to be equal to left tree
   */
  @Test
  public void compareEqual()
  {
    BinaryTree<HuffmanNode> leftTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 6), null, null);
    BinaryTree<HuffmanNode> rightTree = new BinaryTree<HuffmanNode>(new HuffmanNode("L", 6), null, null);

    assertTrue(comparatorImparator.compare(leftTree, rightTree) == 0);
  }
}
