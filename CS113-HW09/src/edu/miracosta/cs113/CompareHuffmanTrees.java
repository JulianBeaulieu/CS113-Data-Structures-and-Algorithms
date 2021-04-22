/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description Comparator Object which compares Binary Trees with HuffmanNodes
 */
 
package edu.miracosta.cs113;

import edu.miracosta.cs113.BinaryTree;
import edu.miracosta.cs113.HuffmanNode;

import java.util.Comparator;

public class CompareHuffmanTrees implements Comparator<BinaryTree<HuffmanNode>>
{
  /**
   * Compare two objects.
   * @param treeLeft The left-hand object.
   * @param treeRight The right-hand object.
   * @return -1 if left less than right, 0 if left equals right, and +1 if left greater than right.
   */
  @Override
  public int compare(BinaryTree<HuffmanNode> treeLeft, BinaryTree<HuffmanNode> treeRight)
  {
    double wLeft = treeLeft.getData().getAmt();
    double wRight = treeRight.getData().getAmt();

    return Double.compare(wLeft, wRight);
  }
}
