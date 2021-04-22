/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @filename BinarSearchTreeWithRotateTest
 * @HW 11
 * @Professor Nery Chapeton-Lamas
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeWithRotateTest
{
  //System.out.println();
  private static BinarySearchTreeWithRotate<String> tree = null;

  private static final String[] ABC = {"E", "G", "F", "I", "H", "J", "B", "A", "D", "C"};

  private static final String rotateLeftComplete = "B\n" + " A\n" + "  null\n" + "  null\n" + " E\n" + "  D\n" + "   C\n" +
                                                    "    null\n" + "    null\n" + "   null\n" + "  G\n" + "   F\n" + "    null\n" +
                                                    "    null\n" + "   I\n" + "    H\n" + "     null\n" + "     null\n" + "    J\n" +
                                                    "     null\n" + "     null\n";

  private static final String rotateRightComplete = "B\n" + " A\n" + "  null\n" + "  null\n" + " E\n" + "  D\n" + "   C\n" +
                                                    "    null\n" + "    null\n" + "   null\n" + "  G\n" + "   F\n" + "    null\n" +
                                                    "    null\n" + "   I\n" + "    H\n" + "     null\n" + "     null\n" + "    J\n" +
                                                    "     null\n" + "     null\n";

  @Before
  public void setUp()
  {
    tree = new BinarySearchTreeWithRotate<>();

    for(String str : ABC)
    {
      tree.add(str);
    }
  }

  @Test
  public void rotateRight()
  {
    BinaryTree newTree = new BinaryTree<>(tree.rotateRight(tree.root));
    assertEquals(rotateLeftComplete, newTree.toString());
  }

  @Test
  public void rotateLeft()
  {
    BinaryTree newTree = new BinaryTree<>(tree.rotateRight(tree.root));
    assertEquals(rotateRightComplete, newTree.toString());
  }
}