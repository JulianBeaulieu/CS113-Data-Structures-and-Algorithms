/**
 * @author Julian Beaulieu
 * @github julianbeaulieu
 * @version 5.0
 * @Professor Nery Chapeton-Lamas
 * @projectStatement Make a program which uses a BinaryTree to translate Morse Code either from a file or through user input
 * @Algorithm 1. Ask user to choose what they would like to do
 *            2. Get user input
 *            3. Call method
 *
 *            -> testOutput()
 *            - uses the encode method to translate the abc in morse code and displays it beautifully
 *
 *            -> inputTextFile()
 *            - Asks user to enter the filename
 *            - Tries to open file
 *            - takes every line and splits it up into tokens
 *            - translates tokens
 *            - returns the translated tokens
 *
 *            ->inputMorseCode()
 *            - Asks user for the morse code which should be translated
 *            - takes input and splits it up into tokens
 *            - translates tokens
 *            - returns the translated tokens
 *
 *            ->translateToMorseCode()
 *            - Asks user for the letter which should be translated
 *            - takes input
 *            - translates letter
 *            - returns the translated letter in morse code
 *
 *            4. If user chooses to exit program they can do so by entering 4
 */
package edu.miracosta.cs113;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver
{
  private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                                                                            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
  private static MorseCodeTree<String> theTree = new MorseCodeTree<String>();
  private static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args)
  {
    boolean ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour = true; // :D

    //Main Menu
    while(ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour)
    {
      System.out.println("Please choose from the following menu by entering the number below:" +
                       "\n1. Test Output" +
                       "\n2. Translate Text File With Morse Code" +
                       "\n3. Enter Morse Code Manually" +
                       "\n4. Translate to Morse Code Manually" +
                       "\n5. End Program");
      switch(InputChecker.sTI(keyboard.nextLine()))
      {
        case 1: testOutput();
                break;
        case 2: inputTextFile();
                break;
        case 3: inputMorseCode();
                break;
        case 4: translateToMorseCode();
                break;
        case 5: ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour = false;
                break;
        default: break;
      }
    }
  }

  /**
   *  This method is called if the user wants to check the output of the tree
   */
  private static void testOutput()
  {
    //Prints out upper line
    System.out.print("\n\n|");
    for(int i = 0; i < ALPHABET.length * 5 - 1; i++)
    {
      System.out.print("-");
    }
    System.out.println("|");

    //letters
    System.out.print("|");
    for(int i = 0; i < ALPHABET.length; i++)
    {
      System.out.print(" " + ALPHABET[i] + "  ");
      System.out.print("|");
    }
    System.out.println("");


    //middle line
    System.out.print("|");
    for(int i = 0; i < ALPHABET.length * 5 - 1; i++)
    {
      System.out.print("-");
    }
    System.out.println("|");

    //morse code line
    System.out.print("|");
    for(int i = 0; i < ALPHABET.length; i++)
    {
      System.out.printf("%4s", theTree.encode(ALPHABET[i]));
      System.out.print("|");
    }
    System.out.println("");


    //bottom line
    System.out.print("|");
    for(int i = 0; i < ALPHABET.length * 5 - 1; i++)
    {
      System.out.print("-");
    }
    System.out.println("|\n\n");
  }

  /**
   * This method is called if the user would like to translate a txt file
   */
  private static void inputTextFile()
  {
    System.out.println("Please enter the name of the file containing the morse code\n" +
                      "Please make sure that the file is in the /doc folder");
    String filename = keyboard.nextLine();

    System.out.println("The morse code in the file translates to:\n" +
                        theTree.translateMorseCodeFromFile(filename));

    //Easter Egg
    if(filename.equals("specialMorse.txt"))
    {
      views.ErrorPane.rickRollError();
    }
  }

  /**
   * This method is used if the user would like to translate morse code manually
   */
  private static void inputMorseCode()
  {
    System.out.println("Please enter morse code below, separate each character by a space");
    String morseCode = keyboard.nextLine();

    StringTokenizer tokenizer = new StringTokenizer(morseCode, " ");
    StringBuilder resultingString = new StringBuilder();

    try
    {
      while(tokenizer.hasMoreTokens())
      {
        resultingString.append(theTree.translate(tokenizer.nextToken()));
      }

      System.out.println("\nYour morse code: \n" +
                          morseCode +
                          "\nTranslates to: \n" +
                          resultingString.toString() + "\n\n");
    }
    catch(Exception e)
    {
      System.out.println("There has been a problem translating the morse code.\n" +
                         "Please retry again.");
    }
  }

  /**
   * This method is used if the user would like to translate morse code manually
   */
  private static void translateToMorseCode()
  {
    System.out.println("Please enter what you wish to translate into morse code below, \nseparate each character by a space");
    String morseCode = keyboard.nextLine().toUpperCase();

    if(!morseCode.matches("[a-zA-Z]"))
    {
      System.out.println("There has been a problem translating into morse code.\n" +
              "Please retry again.");
    }
    else
    {
      StringTokenizer tokenizer = new StringTokenizer(morseCode, " ");
      StringBuilder resultingString = new StringBuilder();

      try
      {
        while(tokenizer.hasMoreTokens())
        {
          resultingString.append(theTree.encode(tokenizer.nextToken()) + " ");
        }

        System.out.println("\nYour input: \n" +
                morseCode +
                "\nTranslates to the morse code: \n" +
                resultingString.toString() + "\n\n");
      }
      catch(Exception e)
      {
        e.printStackTrace();
        System.out.println("There has been a problem translating into morse code.\n" +
                "Please retry again.");
      }
    }
  }
}
