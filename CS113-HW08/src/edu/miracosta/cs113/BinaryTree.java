/**
 * @author Julian Beaulieu
 * @github julianbeaulieu
 * @version 4.0
 * @Professor Nery Chapeton-Lamas
 * @projectStatement Make a program which uses a BinaryTree to translate Morse Code either from a file or through user input
 * @Algorithm 1. Ask user to choose what they would like to do
 *            2. Get user input
 *            3. Call method
 *
 *            -> testOutput()
 *            - calls toString of the MorseCodeTree which is a binaryTree
 *
 *            -> inputTextFile()
 *            - Asks user to enter the filename
 *            - Tries to open file
 *            - takes every line and splits it up into tokens
 *            - translates tokens
 *            - returns the translated tokens
 *
 *            ->inputMorseCode
 *            - Asks user for the morse code which should be translated
 *            - takes input and splits it up into tokens
 *            - translates tokens
 *            - returns the translated tokens
 *
 *            4. If user chooses to exit program they can do so by entering 4
 */
package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable
{
  /*##################################################*/
  /*################## Inner Classes #################*/
  /*##################################################*/

  protected static class Node<E> implements Serializable
  {
    protected E data;
    protected Node<E> left;
    protected Node<E> right;

    public Node(E data)
    {
      this.data = data;
      this.left = null;
      this.right = null;
    }

    public String toString()
    {
        return data.toString();
    }
  }

  /*##################################################*/
  /*################## Fields  #######################*/
  /*##################################################*/

  protected Node<E> root;

  /*##################################################*/
  /*################## Consructors  ##################*/
  /*##################################################*/

  public BinaryTree()
  {
    root = null;
  }

  protected BinaryTree(Node<E> root)
  {
    this.root = root;
  }

  public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
  {
    root = new Node<E>(data);

    if (leftTree != null)
    {
      root.left = leftTree.root;
    }
    else
    {
      root.left = null;
    }

    if (rightTree != null)
    {
      root.right = rightTree.root;
    }
    else
    {
      root.right = null;
    }
  }

  /*##################################################*/
  /*################## Methods  ######################*/
  /*##################################################*/

  public BinaryTree<E> getLeftSubtree()
  {
    if (root != null && root.left != null)
    {
      return new BinaryTree<E>(root.left);
    }
    else
    {
      return null;
    }
  }

  public BinaryTree<E> getRightSubtree()
  {
    if (root != null && root.right != null)
    {
      return new BinaryTree<E>(root.right);
    }
    else
    {
      return null;
    }
  }

  public E getData()
  {
    return root.data;
  }

  public boolean isLeaf()
  {
    return (root.left == null && root.right == null);
  }

  public static BinaryTree<String> readBinaryTree(Scanner scan)
  {
    String data = scan.next();

    if (data.equals("null"))
    {
      return null;
    }
    else
    {
      BinaryTree<String> leftTree = readBinaryTree(scan);
      BinaryTree<String> rightTree = readBinaryTree(scan);
      return new BinaryTree<String>(data, leftTree, rightTree);
    }
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    preOrderTraverse(root, 1, sb);
    return sb.toString();
  }

  /*################## Travers Methods  ##############*/

  protected void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
  {
    for (int i = 1; i < depth; i++)
    {
      sb.append(" ");
    }
    if (node == null)
    {
      sb.append("null\n");
    }
    else
    {
      sb.append(node.toString() + "\n");
      preOrderTraverse(node.left, depth + 1, sb);
      preOrderTraverse(node.right, depth + 1, sb);
    }
  }

  protected void inOrderTraverse(Node<E> node, int depth, StringBuilder sb)
  {
    for (int i = 1; i < depth; i++)
    {
      sb.append(" ");
    }
    if (node == null)
    {
      sb.append("null\n");
    }
    else
    {
      preOrderTraverse(node.left, depth + 1, sb);
      sb.append(node.toString() + "\n");
      preOrderTraverse(node.right, depth + 1, sb);
    }
  }

  protected void postOrderTraverse(Node<E> node, int depth, StringBuilder sb)
  {
    for (int i = 1; i < depth; i++)
    {
      sb.append(" ");
    }
    if (node == null)
    {
      sb.append("null\n");
    }
    else
    {
      preOrderTraverse(node.left, depth + 1, sb);
      preOrderTraverse(node.right, depth + 1, sb);
      sb.append(node.toString() + "\n");
    }
  }

  /*################## Save Methods  #################*/

  public boolean saveTreeToBinaryFile(String filename)
  {
    try
    {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(this);
      out.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean loadTreeFromBinaryFile(String filename)
  {
    try
    {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      BinaryTree<E> loadedTree = (BinaryTree) in.readObject();
      root = loadedTree.root;
      in.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
