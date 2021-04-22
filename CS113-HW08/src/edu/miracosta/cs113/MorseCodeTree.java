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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MorseCodeTree<E> extends BinaryTree
{
  /** Default Constructor
   * which calls the default constructor of the BinaryTree class
   * and then calls the readMorseCodeTree() method which will populate the tree
   */
  public MorseCodeTree()
  {
    super();

    readMorseCodeTree();
  }

  /** Translates a letter in morseCode
   * @param morseCode String containing the letter in morse code
   * @return a String containing the translated letter
   */
  public String translate(String morseCode)
  {
    //makes a new node object
    Node<E> tree = root;

    //creates a char array out of the string
    char[] morseCodeCharArr = morseCode.toCharArray();

    //for each loop which loops through the array
    for(char c : morseCodeCharArr)
    {
      if(c == '*') //if the current char is a * it will go left
      {
        tree = tree.left;
      }
      else if(c == '-') //if the current char is a * it will go left
      {
        tree = tree.right;
      }
      else //if the current char doesn't contain a - or a * it will go return "Error"
      {
        return "Error";
      }
    }

    //after the loop has finished looping the program has arrived at the letter
    return tree.toString();
  }

  /** Translates a file from morse code
   * @param filename which is a string containing the file name AND extension
   * @return the string with the translated morse code
   */
  public String translateMorseCodeFromFile(String filename)
  {
    //creates a scanner for the file input
    Scanner fileIn = null;

    //creates a StringBuilder to build the words
    StringBuilder resultingString = new StringBuilder();

    try
    {
      //this will try to open up the file
      fileIn = new Scanner(new FileInputStream("doc/" + filename.trim()));

      //loops while file has lines
      while(fileIn.hasNextLine())
      {
        //takes the next lines and breaks it up into seperate tokens which will be translated
        StringTokenizer tokenizer = new StringTokenizer(fileIn.nextLine(), " ");

        while(tokenizer.hasMoreTokens())
        {
          resultingString.append(translate(tokenizer.nextToken()));
        }

        //after every line it adds a linebreak for beautiness
        resultingString.append("\n");

        if(resultingString.toString().contains("Error"))
        {
          throw new Exception();
        }
      }
    }
    catch(FileNotFoundException e)
    {
      return "An Error has been encountered while trying to open the file." +
              "Please make sure you entered the correct file name";
    }
    catch(Exception e)
    {
      return "There has been a problem translating the morse code.\n" +
              "Please make sure code is legal and try again.";
    }

    //returns the resulting string when the complete file was translated
    return resultingString.toString();
  }

  /** reads a file of morse code and populates the tree
   * this will open a pre defined text file and load the morse code from it
   * by using the add method
   */
  private void readMorseCodeTree()
  {
    try
    {
      //makes a scanner and tries to open the file
      Scanner fileIn = new Scanner(new FileInputStream("resources/morseCode.txt"));

      //makes the root node empty, alternaitvely it could also just contain "root"
      root = new Node("");

      //loops while there are next lines
      while(fileIn.hasNextLine())
      {
        //takes the next line and trims the white spaces
        String newLine = fileIn.nextLine().trim();

        //then cuts the string into two tokens: the letter than the code
        StringTokenizer tokenizer = new StringTokenizer(newLine, " ");

        //calls add method to add the code to the tree
        addUsingRecursion(tokenizer.nextToken(), tokenizer.nextToken());
      }
    }
    catch(FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  /*################## Add Methods  ##################*/


  /** Add method which uses looping and the Nodes to traverse the tree
   * @param letter the letter which will be added to the array
   * @param code contains the code which is needed to find where the method needs to go
   */
  private void addUsingNode(String letter, String code)
  {
    //makes a new node witch point to the root
    Node<E> tree = root;

    //makes the char array which is needed to traverese the Tree
    char[] morseCodeCharArr = code.toCharArray();

    for(char c : morseCodeCharArr)
    {
      if(c == '*' && tree.left != null) //If the left branch is not empty, it will go down it
      {
        tree = tree.left;
      }
      else if(c == '-' && tree.right != null) //If the right branch is not empty, it will go down it
      {
        tree =  tree.right;
      }
      else if(c == '*' && tree.left == null) //If the left branch is  empty, it add to it
      {
        tree.left = new Node<E>((E) letter);
      }
      else if(c == '-' && tree.right == null) //If the right branch is  empty, it add to it
      {
        tree.right = new Node<E>((E) letter);
      }
    }
  }

  /** Add method which uses looping and the Branches/Nodes to traverse the tree
   * @param letter the letter which will be added to the array
   * @param code contains the code which is needed to find where the method needs to go
   */
  private void addUsingTree(String letter, String code)
  {
    //Makes a new BinaryTree witch ponts to this one
    BinaryTree tree = this;

    //makes the char array which is needed to traverese the Tree
    char[] morseCodeCharArr = code.toCharArray();

    for(char c : morseCodeCharArr)
    {
      if(c == '*' && tree.getLeftSubtree() != null) //If the left branch is not empty, it will go down it
      {
        tree = tree.getLeftSubtree(); //If the right branch is not empty, it will go down it
      }
      else if(c == '-' && tree.getRightSubtree() != null) //If the right branch is not empty, it will go down it
      {
        tree = tree.getRightSubtree(); //If the right branch is not empty, it will go down it
      }
      else if(c == '*' && tree.getLeftSubtree() == null) //If the left branch is  empty, it add to it
      {
        tree.root.left = new Node<E>((E) letter); //adds the letter and then returns true
      }
      else if(c == '-' && tree.getRightSubtree() == null) //If the right branch is  empty, it add to it
      {
        tree.root.right = new Node<E>((E) letter); //adds the letter and then returns true
      }
    }
  }

  /** Wrapper method for the Recursive add method
   * @param letter the letter which will be added to the array
   * @param code contains the code which is needed to find where the method needs to go
   * @return true if adding was successfull, false if it was not succesfull
   */
  private boolean addUsingRecursion(String letter, String code)
  {
    return addCodeRecursively(root, letter, code, 0);
  }

  /** Recursive part of the add method
   * @param tree wich is the root node of the "tree"; pointer to the branchings
   * @param letter contains the letter which will be added to the array
   * @param code contains the code which is needed to find where the method needs to go
   * @param position is needed for the Recursive part to end
   * @return true if adding was successfull, false if it was not succesfull
   */
  private boolean addCodeRecursively(Node<E> tree, String letter, String code, int position)
  {
    //saves the char which will be used for evaluation
    char c = code.charAt(position);

    if(code.length() == position) //base case in case something went wrong
    {
      return false;
    }
    else if(c == '*' && tree.left != null) //If the left branch is not empty, it will go down it
    {
      addCodeRecursively(tree.left, letter, code, ++position); //calls itself but adds 1 to position
    }
    else if(c == '-' && tree.right != null) //If the right branch is not empty, it will go down it
    {
      addCodeRecursively(tree.right, letter, code, ++position); //calls itself but adds 1 to position
    }
    else if(c == '*' && tree.left == null) //If the left branch is  empty, it add to it
    {
      tree.left = new Node<E>((E) letter); //adds the letter and then returns true
      return true;
    }
    else if(c == '-' && tree.right == null) //If the right branch is  empty, it add to it
    {
      tree.right = new Node<E>((E) letter); //adds the letter and then returns true
      return true;
    }

    //returns false if something went wrong
    return false;
  }

  /*################## Encode Methods  ##################*/

  public String encode(String letter)
  {
    return getAP(root, "", letter);
  }

  private String getAP(Node<E> root, String path, String letter)
  {
    String returnStr= "";

    if(root.data.toString().equals(letter))
    {
      returnStr = "" + path;
    }

    if(root.left != null){
      returnStr += getAP(root.left, path.concat("*"), letter);
    }

    if(root.right != null){
      returnStr += getAP(root.right, path.concat("-"), letter);
    }

    return returnStr;
  }
}
