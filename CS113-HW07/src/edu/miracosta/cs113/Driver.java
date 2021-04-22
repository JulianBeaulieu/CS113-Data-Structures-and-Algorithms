/**
 * @author Julian Beaulieu
 * @version 3.0
 * @see  "An operating system assigns jobs to print queues based on the number of pages to be printed
 *        (less than 10 pages, less than 20 pages, or more than 20 pages).
 *        You may assume that the system printers are able to print 10 pages per minute.
 *        Smaller print jobs are printed before larger print jobs, and print jobs of the same priority are queued up in the order in which they are received.
 *        The system administrator would like to compare the time required to process a set of print jobs using one, two, or three system printers.
 *
 *        Write a program to simulate processing 100 print jobs of varying lengths using one, two, or three printers.
 *        Assume that a print request is made every minute and that the number of pages to print varies from 1 to 50 pages.
 *
 *        The output from your program should indicate the order in which the jobs were received,
 *        the order in which they were printed, and the time required to process the set of print jobs.
 *        If more than one printer is being used, indicate which printer each job was printed on."
 */

package edu.miracosta.cs113;

import java.util.Scanner;

public class Driver
{
  private static Printer[] printer;
  private static PrintJob[] printJobs;

  public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    boolean thereAreTwoPrinters = false;
    boolean thereAreThreePrinters = false;

    System.out.print("How many printers do you want to use? ");
    int numOfPrinters = keyboard.nextInt();

    //makes sure the user plays be the rules
    if(numOfPrinters < 1 || 3 < numOfPrinters)
    {
      System.out.println("Invalid number of printers: " + numOfPrinters);
      System.exit(0);
    }

    if(numOfPrinters == 2)
    {
      thereAreTwoPrinters = true;
    }
    else if(numOfPrinters == 3)
    {
      thereAreTwoPrinters = true;
      thereAreThreePrinters = true;
    }

    //for spacing purposes
    System.out.println("\n\n");


    //creates the print Jobs and the three printers
    printJobs = Worker.goWork();
    printer = new Printer[numOfPrinters];

    //initializes the three printers
    for(int i = 0; i < numOfPrinters; i++)
    {
      printer[i] = new Printer(i + 1);
    }

    assignPrintJobsToPrinters(numOfPrinters);

    displayTheTableHeader();

    //loop which loops until all printjobs have completed
    for(int printJobNumber = 1, time = 1; areThereMorePrintJobs(printer, numOfPrinters); time++)
    {
      //printer 1 1 -> 9 Pages
      print(printer[0].print(time), 1, printJobNumber, time);

      if(thereAreTwoPrinters)
      {
        //printer 2 10 -> 19 Pages
        print(printer[1].print(time),2, printJobNumber, time);
      }

      if(thereAreThreePrinters)
      {
        //printer 3 20 -> 50 Pages
        print(printer[2].print(time), 3, printJobNumber, time);
      }
    }
  }

  /**
   * Assigns the PrintJobs to the printers
   * @param numOfPrinters so the program knows to how many printers it can assign print jobs
   */
  private static void assignPrintJobsToPrinters(int numOfPrinters)
  {
    //lets user know, that print jobs have started
    System.out.println("Starting Print Jobs:");

    if(numOfPrinters == 1)
    {
      //assigns the printJobs to the printers and prints out the array of printJobs at the same time
      for(int i = 0; i < printJobs.length; i++)
      {
        printer[0].addPrintJob(printJobs[i]);

        System.out.println("Print Job " + printJobs[i].getPrintJobNumber() + ".: "
                + printJobs[i].getTotalPages() + " Pages");
      }
    }
    else if(numOfPrinters == 2)
    {
      //assigns the printJobs to the printers and prints out the array of printJobs at the same time
      for(int i = 0; i < printJobs.length; i++)
      {
        if(printJobs[i].getTotalPages() < 10)
        {
          printer[0].addPrintJob(printJobs[i]);
        }
        else
        {
          printer[1].addPrintJob(printJobs[i]);
        }

        System.out.println("Print Job " + printJobs[i].getPrintJobNumber() + ".: "
                + printJobs[i].getTotalPages() + " Pages");
      }
    }
    else
    {
      //assigns the printJobs to the printers and prints out the array of printJobs at the same time
      for(int i = 0; i < printJobs.length; i++)
      {
        if(printJobs[i].getTotalPages() < 10)
        {
          printer[0].addPrintJob(printJobs[i]);
        }
        else if(9 < printJobs[i].getTotalPages() && printJobs[i].getTotalPages() < 20)
        {
          printer[1].addPrintJob(printJobs[i]);
        }
        else
        {
          printer[2].addPrintJob(printJobs[i]);
        }

        System.out.println("Print Job " + printJobs[i].getPrintJobNumber() + ".: "
                + printJobs[i].getTotalPages() + " Pages");
      }
    }

  }

  /**
   * //lets user know, that program will now show completed printjobs
   */
  private static void displayTheTableHeader()
  {
    System.out.println("\n\nCompleted Print Jobs:");
    System.out.println(" Nr. | Print Job Nr. |      Time elapsed        |  Number of Pages   | Printer used");
    System.out.println("--------------------------------------------------------------------------------------");
  }

  /**
   * This prints out the printJob information
   * @param temp is the PrintJob
   * @param printerNumber Is the number of the printer, from which the printJob was printed from
   */
  private static void print(PrintJob temp, int printerNumber, int printJobNumber, int time)
  {
    if(temp != null)
    {
      System.out.printf("%3d. | Print Job %3d |  %14d min      |  %10d        |  %4d\n", printJobNumber, temp.getPrintJobNumber(), time, temp.getTotalPages(), printerNumber);
      System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
      printJobNumber++;
    }
  }

  /**
   * This checks if there are still more print jobs
   * @param printer the array of printers
   * @param numOfPrinters number of printers in array
   * @return true if any printer still has more print jobs, false if all are finish with printing
   */
  private static boolean areThereMorePrintJobs(Printer[] printer, int numOfPrinters)
  {
    if(numOfPrinters == 1)
    {
      return printer[0].hasAnotherPrintJob();
    }
    else if(numOfPrinters == 2)
    {
      return printer[0].hasAnotherPrintJob() || printer[1].hasAnotherPrintJob();
    }
    else if(numOfPrinters == 3)
    {
      return printer[0].hasAnotherPrintJob() || printer[1].hasAnotherPrintJob() || printer[2].hasAnotherPrintJob();
    }
    else
    {
      return false;
    }
  }
}
