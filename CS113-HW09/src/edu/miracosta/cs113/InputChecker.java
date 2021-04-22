/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 3.0
 * @ProblemStatement - Checks various inputs for correctness so that program doesn't crash,
 *                   - because of a input mismatch exception
 *
 * @OverallPlan
 *              1. Get input
 *              2. Check input for rightnes
 *              3. Return (formatted) input
 *              4. If input is wrong it will prompt user to re enter a number for instance
 *
 */
package edu.miracosta.cs113;

import java.util.Scanner;

public class InputChecker
{
  private static Scanner keyboard = new Scanner(System.in);

  /* Purpose: -
   * Parameters: -
   * Returns: -
   * Output: -
   */
  public InputChecker()
  {
    // Good Practice
  }

/*----------------------------------------------------------------------------*/
/*- Input to Integer ---------------------------------------------------------*/
/*----------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------*/
/*- Input to Double ----------------------------------------------------------*/
/*----------------------------------------------------------------------------*/

  /* Purpose: "converts" string to double
   * Parameters: input
   * Returns: newInput
   * Output: double
   */
  public static double sTD(String input)
  {//sTD means String to double

    //Boolean makes do-while loop when input is wrong
    boolean good = false;

    //this is the int that will be returned
    double newInput = 0;

    //do-while loops when input is false in someway,
    //in this case if it can't be converted to double
    do
    {
      //incase input is not correct, ggod needs to be reseted,
      //so program doesn't get stuck in infinte loop
      good = false;

      //tries to convert the input into double
      try
      {
        //if it works, method will just run through but nothing would happen
        newInput = Double.parseDouble(input);
      }

      //incase input is not a number which can be converted into double
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
}
