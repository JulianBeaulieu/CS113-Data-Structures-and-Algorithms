/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @filename ALVTreeTest
 * @HW 11
 * @Professor Nery Chapeton-Lamas
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeTest
{
  private static AVLTree<String> tree = null;

  private static final int RANDOMTESTSIZE = 30;

  private static final String[] SENTENCE = {"The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};

  private static final String CORRECTAVLTREEAFTERADD =  "0: jumps\n" +
                                                        " 1: brown\n" +
                                                        "  0: The\n" +
                                                        "   null\n" +
                                                        "   null\n" +
                                                        "  -1: fox\n" +
                                                        "   0: dog\n" +
                                                        "    null\n" +
                                                        "    null\n" +
                                                        "   null\n" +
                                                        " -1: quick\n" +
                                                        "  -1: over\n" +
                                                        "   0: lazy\n" +
                                                        "    null\n" +
                                                        "    null\n" +
                                                        "   null\n" +
                                                        "  0: the\n" +
                                                        "   null\n" +
                                                        "   null\n";

  @Before
  public void setUp()
  {
    tree = new AVLTree<String>();
  }

  /** This just prints out the trees so the tester can make sure that the tree is adding correctly
   * @param avlTree A reference to the AVLTree
   * @param bnrySearchTree A reference to the BinarySearchTree
   * @param i the number which has been added
   */
  private void checkForCorrectAddWithInorderTraversal(AVLTree<Integer> avlTree, BinarySearchTree<Integer> bnrySearchTree, int i)
  {
    System.out.println("Added: " + i + " to the AVL Tree:\n" + "------------------------------------------------");

    System.out.println(avlTree.toString());

    System.out.println("\n\n\nAdded: " + i + " to the Binary Search Tree:\n" + "------------------------------------------------");

    System.out.println(bnrySearchTree.toString());
  }

  /** This just prints out the trees so the tester can see the final trees
   * @param avlTree A reference to the AVLTree
   * @param bnrySearchTree A reference to the BinarySearchTree
   */
  private void displayTrees(AVLTree<Integer> avlTree, BinarySearchTree<Integer> bnrySearchTree)
  {
    int[] depthArray = calculateDepth(avlTree, bnrySearchTree);

    System.out.println("The AVL Tree with a depth of " + depthArray[0] + ": \n" + "------------------------------------------------");

    System.out.println(avlTree.toString());

    System.out.println("The Binary Search Tree with a depth of " + depthArray[1] + ":\n" + "------------------------------------------------");

    System.out.println(bnrySearchTree.toString());
  }

  /** This method will calculate the depth of an BinarySearchTree and an AVL Tree.
   * Then it will return an array of integers of size two with the first spot being the depth of an AVLTree
   * and the second spot being the depth of an BinarySearchTree
   * @param avlTree a reference to an AVL Tree
   * @param bnrySearchTree a reference to a Binary Search Tree
   * @return An array containing the depths of the two trees
   */
  private int[] calculateDepth(AVLTree<Integer> avlTree, BinarySearchTree<Integer> bnrySearchTree)
  {
    int[] tempArr = {calculateDepthOfTree(avlTree.toString().toCharArray()), calculateDepthOfTree(bnrySearchTree.toString().toCharArray())};
    return tempArr;
  }

  /** This will look for the deepest depth of a tree
   * @param avlTreeCharArray an array of characters
   * @return The depth of the tree in as an int
   */
  private int calculateDepthOfTree(char[] avlTreeCharArray)
  {
    int depth = 0;
    int tempDepth = 0;

    for(char c : avlTreeCharArray)
    {
      if(c == ' ')
      {
        tempDepth++;
      }
      else if(c != ' ')
      {
        tempDepth = 0;
      }

      if(tempDepth > depth)
      {
        depth = tempDepth;
      }
    }

    //need to subtract one because the leaves do not count as depth
    return depth - 1;
  }

  @Test
  public void addTest()
  {
    for(String str : SENTENCE)
    {
      tree.add(str);
    }

    System.out.println("Correct Tree:\n---------------------------------------\n" + CORRECTAVLTREEAFTERADD);
    System.out.println("Actual Tree:\n---------------------------------------\n" + tree.toString());

    assertEquals(CORRECTAVLTREEAFTERADD, tree.toString());
  }

  @Test
  public void correctPlacementTest()
  {
    //creates and initializes the two trees and a array
    AVLTree<Integer> avlTree = new AVLTree<Integer>();
    BinarySearchTree<Integer> bnrySearchTree = new BinarySearchTree<Integer>();
    int[] randomNumberArray = new int[RANDOMTESTSIZE];

    //fills the array and the binary trees with random numbers
    for(int i = 0; i < RANDOMTESTSIZE; i++)
    {
      int temp = (int) (Math.random() * 10);
      avlTree.add(temp);
      bnrySearchTree.add(temp);
      randomNumberArray[i] = temp;

      //this checks the add with the inorder traversal
      checkForCorrectAddWithInorderTraversal(avlTree, bnrySearchTree, temp);
    }

    //display the two trees
    displayTrees(avlTree, bnrySearchTree);

    //tests if the binary trees added all the numbers correctly
    for(int i : randomNumberArray)
    {
      assertTrue(avlTree.contains(i));
      assertTrue(bnrySearchTree.contains(i));
    }
  }
}