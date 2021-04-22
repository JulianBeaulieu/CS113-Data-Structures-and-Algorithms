/**
* @author Julian Beaulieu
* @version 2.0
* @see this Object with static method makes print jobs and returns them in a array
*/

package edu.miracosta.cs113;
import java.util.Random;

public class Worker
{
  /** standard method
   * @return array of PrintJobs
   */
  public static PrintJob[] goWork()
  {
    return doYourJob(100);
  }

  /** specialized method
   * @param printJobs
   * @return array of PrintJobs
   */
  public static PrintJob[] goWork(int printJobs)
  {
    if(0 < printJobs)
    {
      return doYourJob(printJobs);
    }
    else
    {
      return null;
    }
  }


  /** backend of the methods
   * @param numOfPrintJobs
   * @return array of PrintJobs
   */
  private static PrintJob[] doYourJob(int numOfPrintJobs)
  {
    Random randomGenerator = new Random();
    PrintJob[] printJobs = new PrintJob[numOfPrintJobs];

    try
    {
      for (int i = 0; i < numOfPrintJobs; i++)
      {
        printJobs[i] = new PrintJob(randomGenerator.nextInt(50) + 1, 0, 0, i + 1);
      }
    }
    catch(InvalidValueException e)
    {
      System.out.println("Error");
    }

    return printJobs;
  }
}
