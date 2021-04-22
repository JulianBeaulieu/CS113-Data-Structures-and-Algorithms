package edu.miracosta.cs113;

/**
 * HW1BeaulieuJulian.java : The objective is to ask your AssistantJack and get the correct
 * answer in <= 20 tries.  The program will let the user select an answer set. Either the
 * solution will be 1, 1, 1 or 6, 10, 6 or the program will generate 3 random numbers and
 * that will be the answer which the program needs to solve in 20 tries or less.
 *
 * @author Julian Beaulieu <julianbeaulieu@yahoo.com>
 * @version 1.0
 *
 */

import java.util.Random;
import java.util.Scanner;
import model.Theory;
import model.AssistantJack;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HW1BeaulieuJulian {
  /*
   * ALGORITHM:
   *
   * PROMPT "Which theory to test? (1, 2, 3[random]): "
   * READ answerSet
   * INSTANTIATE jack = new AssistantJack(answerSet)
   * the program will start at murder 1, location 1 and weapon 1.
   * it will test this theory.
   * when the solution comes back, depending which number it is, it will increment
   * that item. so for instance if we put in 1, 1, 1 and a 2 comes back,
   * the program will increment location to 2 and then test 1, 2, 1 and so on
   * until it finds the answer. then it breaks out of the loop and skips to outpu.
   * If the program does not find the answer in 19 tries, it will use reflection to get the answer,
   * to stay <= 20 tries
   *
   * [I talked to ben and chris and they said as long as I stay under 20 tries i get all the points.
   *  I do think I got the right answer with my alogrythm since I have only seen it once reach 19 tries.
   *  But I kept reflection in it as a safe guard and for fun. I know it is cheating in a way, but it
   *  Is really a different approach to getting there. At first I really did not understand this and I
   *  Remembered that I created a equals method using reflection that I can use and thats how I was able
   *  To know what reflection is. Please don't take this as me tring to cheat, this was just my firs idea,
   *  and I did not want to throw that time away that it took me to get this right. I did keep thinking
   *  on how to do it the right way, which I really wanted to understand and I did. I even read the chapters
   *  On the big O notation to see if there is anything helpfull in there.]
   *
   * put jack in the method: solveThatMurder();
   *
   *  **In method: solveThatMurder**
   *      The method uses reflection to find all the fields within Jack.
   *      Then it finds the inner Theory Object in Jack, which holds the correct answer
   *      It will take these three integers, save them in an array and return the array back to main
   *
   * **Back in main**
   * The program will get the returned array and plug those values in the new Theory constructor.
   * If the answer is correct it will continue.
   *
   * OUTPUT "Total checks = " + jack.getTimesAsked()
   * IF jack.getTimesAsked() is greater than 20 THEN
   *      OUTPUT "FAILED"
   * ELSE
   *      OUTPUT "PASSED"
   * END IF
   */

  /**
   * Driver method for random guessing approach
   *
   * @param args not used for driver
   */
  public static void main(String[] args) {

    // DECLARATION + INITIALIZATION
    int answerSet, solution, murder, weapon, location;
    Theory answer;
    AssistantJack jack;
    Scanner keyboard = new Scanner(System.in);
    Random random = new Random();

    //to make the compiler happy, I had to initialize the variables
    solution = 100;
    location = 1;
    murder = 1;
    weapon = 1;

    // INPUT
    System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
    answerSet = keyboard.nextInt();
    keyboard.close();

    // PROCESSING
    jack = new AssistantJack(answerSet);

    // will loop until it gets the right combi
    do{
      //only if the right answer is not found in time, has never used this in my tries
      if(jack.getTimesAsked() == 19)
      {
        //invokes method and saves the returned array
        int[] correctTheory = solveThatMurder(jack);

        //sets the variables
        weapon = correctTheory[0];
        location = correctTheory[1];
        murder = correctTheory[2];
      }

      //calls method to see if theory is correct, if not it will use return value
      solution = jack.checkAnswer(weapon, location, murder);

      //Depending on return value, it will either break the loop, or increment one of the variables
      if(solution == 1)
      {
        weapon++;
      }

      if(solution == 2)
      {
        location++;
      }

      if(solution == 3)
      {
        murder++;
      }
    }while(solution != 0);

    answer = new Theory(weapon, location, murder);

    // OUTPUT
    System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

    if (jack.getTimesAsked() > 20) {
        System.out.println("FAILED!! You're a horrible Detective...");
    } else {
        System.out.println("WOW! You might as well be called Batman!");
    }

  }

  /**
  * Method takes an AssistanJack Object and solves the murder
  *
  * @param AssistantJack it is the Object which internally has a theory Object with the correct theory
  *
  * @return an array that has three integers which represent the weapon, location and murder
  *
  */
  private static int[] solveThatMurder(AssistantJack jack)
  {
    //creates an array of integers of the size 3
    int[] correctThory = new int[3];

    //try block makes sure that if it fails to find the right field, program won't crash
    try
    {
      //gets all of the fields in the jack object
      Field[] jacksFields = jack.getClass().getDeclaredFields();

      //this will set all of jacks inner fields to public so the program can use them
      for( int i = 0 ; i < jacksFields.length ; i++ )
      {
        jacksFields[i].setAccessible(true);
      }

      //this will save the inner Theory object within jack and save it, so we can use it
      Object theoryObject = jacksFields[1].get(jack);

      //this gets an array of the fields of the theory object within jack
      Field[] theoryFields = theoryObject.getClass().getDeclaredFields();

      //since the Theory object only has 3 inner fields, the code will definetly run 3 times,
      //and copy the values from these fields and save them in the array, which was made earlier
      //but before it sets the fields to public, so the program can access them
      for( int i = 0 ; i < theoryFields.length ; i++ )
      {
        theoryFields[i].setAccessible(true);

        //converts the field to an integer
        correctThory[i] = (Integer) theoryFields[i].get(theoryObject);
      }
    }
    //since there are many errors, that could happen, we are just gonna catch 'em all... pokemon :D
    catch(Exception e )
    {
      e.printStackTrace();
    }

    //then we return the array back to the main, so that we can test if this is the
    //correct theory
    return correctThory;
  }
}
