/**
* @author Julian Beaulieu
* @version 1.0
*
* ALGORITHM:
*
* Objective:
* The user will be able to edit to different polynomials and then add them together
* to see what the result of the to polynomials is
*
* How it Works:
* So there is a main menu and a submenu.
* The user can choose in the main menu which polynomial they would like to edit
* Or they can choose to display the two polynomials added together or end the Program
* The submenu allows the user to display the polynomial they are working on,
* add to the polynomial, clear it, or make a new one.
* This they can do for both polynomials.
* when choosing add, the user is promted with entering the coefficient then the exponent
* it will then create a temporary Term objecz, add the two values into the object and
* then add the object to the polynomial. after adding to the polynomial the polynomial
* will be sorted so that the exponents are always in a decreasing order.
* When the user decides to see the polynomial the toString will be called and the
* user will then see the whole Polynomial
*
* Packages needed:
* + Scanner
* + StringTokenizer
* + LinkedList
* + NoSuchElementException
*/

package edu.miracosta.cs113;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver
{
  private static Polynomial[] polynomial = new Polynomial[2];
  private static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args)
  {
    //needed to first initialize the two polynomials
    polynomial[0] = new Polynomial();
    polynomial[1] = new Polynomial();

    //will be used for the selection of the user
    int selection = 0;

    //will loop until user decides to quit program
    //main menu
    while(true)
    {
      System.out.println("\nMain Menu:\n" +
                         "----------\n" +
                         "1) Edit 1. Polynomial\n" +
                         "2) Edit 2. Polynomial\n" +
                         "3) Display Sum of 1. & 2. Polynomial\n" +
                         "4) Exit Program");
      System.out.print("Please enter number of selection: ");
      selection = sTI(keyboard.nextLine());

      switch(selection)
      {
        case 1: secondaryMenu(1);
                break;

        case 2: secondaryMenu(2);
                break;

        case 3: dispSumOfBothPoly();
                break;

        case 4: exitProgram();
                break;

        default: break;
      }
    }
  }

  /*
  * Precondition: The user has made a choice to edit polynomial 1 or 2
  * Postcondition: The user has choosen to go back
  */
  private static void secondaryMenu(int choice)
  {
    boolean repeat = true;
    int selection = 0;
    while(repeat)
    {
      System.out.println("\nEdit Menu:\n" +
                         "----------\n" +
                         "1) Clear " + choice + ". Polynomial\n" +
                         "2) Create new " + choice + ". Polynomial\n" +
                         "3) Add to " + choice + ". Polynomial\n" +
                         "4) Display " + choice + ". Polynomial\n" +
                         "5) Back to Main Menu");
      System.out.print("Please enter number of selection: ");
      selection = sTI(keyboard.nextLine()); //this takes a string and turns it into a intiger

      switch(selection)
      {
        case 1: clearPolynomial(choice - 1);
                break;

        case 2: createNewPolynomial(choice - 1);
                break;

        case 3: addTermToPolynomial(choice - 1);
                break;

        case 4: displayPolynomial(choice - 1);
                break;

        case 5: repeat = false;
                break;

        default: break;
      }
    }
  }

  /*
  * Precondition: The user has made a choice to edit polynomial 1 or 2
  * Postcondition: The choosen polynomial has been deleted
  */
  private static void clearPolynomial(int choice)
  {
    polynomial[choice].clear();
  }

  /*
  * Precondition: The user has made a choice to edit polynomial 1 or 2
  * Postcondition: The choosen polynomial has been created again
  */
  private static void createNewPolynomial(int choice)
  {
    //tells user what to do
    System.out.println("\n\nBelow you will enter the new polynomial or just a new term:\n" +
            "-----------------------------------------\n");

    //takes care of the adding and error handling
    addAWholePolynomial(choice);
  }

  /*
  * Precondition: The user has made a choice to edit polynomial 1 or 2
  * Postcondition: A term has been added to the choosen Polynomial
  */
  private static void addTermToPolynomial(int choice)
  {
    //takes care of the adding and error handling
    addAWholePolynomial(choice);
  }

  /*
  * Precondition: The user has made a choice to display polynomial 1 or 2
  * Postcondition: The polynomial has been displayed
  */
  private static void displayPolynomial(int choice)
  {
    System.out.println("\n\n" + polynomial[choice].toString() + "\n\n");
  }

  /*
  * Precondition: The user has made a choice to display polynomial 1 & 2
  * Postcondition: The combination of both polynomials have been displayed
  */
  private static void dispSumOfBothPoly()
  {
    //makes a new temporary polynomial, adds both polynomials to it, and displays the tString
    Polynomial temp = new Polynomial();
    temp.addAllTerms(polynomial[0].getAllTerms());
    temp.addAllTerms(polynomial[1].getAllTerms());
    //System.out.println(temp.toString());

    System.out.println("\n\nSum of 1. & 2. Polynomial:\n" + temp.toString() + "\n\n");

    temp = null;
  }

  /*
  * Precondition: The program is running
  * Postcondition: The program is not running
  */
  private static void exitProgram()
  {
    System.exit(0);
  }

  /* Purpose: "converts" string to integer
   * Parameters: input
   * Returns: newInput
   * Output: int
   */
  public static int sTI(String input)
  {//sTI stands for String To Integer

    //Boolean makes do-while loop when input is wrong
    boolean good = false;

    //this is the int that will be returned
    int newInput = 0;

    //do-while loops when input is false in someway,
    //in this case if it can't be converted to int
    do
    {
      //incase input is not correct, ggod needs to be reseted,
      //so program doesn't get stuck in infinte loop
      good = false;

      //tries to convert the input into int
      try
      {
        //if it works, method will just run through but nothing would happen
        newInput = Integer.parseInt(input);
      }
      catch(NumberFormatException e)
      {
        //Prompts user for new input and makes loop, loop to check new input
        System.out.print("\n\nInput is not a number, please enter a number:");
        input = keyboard.next();
        good = true;
      }

      //Incase input is null, it will catch this exception
      catch(NullPointerException e)
      {
        System.out.print("\n\nPlease enter a number:");
        input = keyboard.next();
        good = true;
      }
    }
    while (good);

    //it returns the correct and "safe" to use input
    return newInput;
  }

  /*
   * Precondition: There is a polynomial[] and the user has made a choice, the terms need to seperated by spaces
   * Postcondition: The program has taken a String from the user, cut it up and added it to the polynomial
   */
  private static void addAWholePolynomial(int choice)
  {
    try
    {
      System.out.print("\nPlease enter a Polynomial, please seperate the Terms by a space: ");

      String userInput = keyboard.nextLine();

      StringTokenizer st = new StringTokenizer(userInput);

      while(st.hasMoreTokens())
      {
        polynomial[choice].addTerm(new Term(st.nextToken()));
      }
    }
    catch(NumberFormatException e)
    {
      //e.printStackTrace();
      System.out.println("There has been a problem adding your term or polynomial, please try again");
    }
  }
}
