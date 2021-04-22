/**
 * @author Julian Beaulieu
 * @github julianbeaulieu
 * @version 5.0
 * @Professor Nery Chapeton-Lamas
 * @projectStatement Make a program which uses a BinaryTree will compress files and scrape websites
 * @Algorithm 1. Ask user to choose what they would like to do
 *            2. Get user input
 *            3. Call method
 *
 *            -> scrapeAndCompressWebsite()
 *            - Asks user to enter the URL of a website
 *            - Tries to open website
 *            - saves the website to a txt file
 *            - compresses the txt file
 *            - returns the compression stats
 *
 *            -> compressFile()
 *            - Asks user to enter filename
 *            - Tries to open file
 *            - compresses the txt file
 *            - returns the compression stats
 *
 *            ->decompressFile()
 *            - Asks user to enter filename
 *            - Tries to open file
 *            - decompresses the txt file
 *            - returns the compression stats
 *
 *            ->displayFile()
 *            - Asks user to enter filename
 *            - Tries to open file
 *            - Displays the file content
 *
 *            4. If user chooses to exit program they can do so by entering 4
 */
package edu.miracosta.cs113;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver
{
  private static HuffmanTree theTree = new HuffmanTree();
  private static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args)
  {
    boolean ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour = true; // :D

    //Main Menu
    while(ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour)
    {
      System.out.println("Please choose from the following menu by entering the number below:" +
                       "\n1. Scrape and Compress Website" +
                       "\n2. Compress File" +
                       "\n3. Decompress File" +
                       "\n4. Display File" +
                       "\n5. End Program");
      switch(InputChecker.sTI(keyboard.nextLine()))
      {
        case 1: scrapeAndCompressWebsite();
                break;
        case 2: compressFile();
                break;
        case 3: decompressFile();
                break;
        case 4: displayFile();
                break;
        case 5: ThisLoopWillLoopUntilTheUserDecidesToEndProgramInMenuByEnteringNumberFour = false;
                break;
        default: break;
      }
    }
  }

  /**
   * This method is called if the user wants to scape and compress a website
   */
  private static void scrapeAndCompressWebsite()
  {
    //Asks user for URL of website
    boolean binary = false;

    //Asks user what website to scrape and compress
    System.out.println("\nPlease enter the URL of the website which you would like to scrape and compress\n" +
                      "Please make sure that the URL is valid");
    String uRL = keyboard.nextLine().trim();

    //asks user for filename of compressed file
    System.out.println("Please enter the filename of the compressed file");
    String outputFileName =  keyboard.nextLine() + ".cph";

    //asks if user would like a binary file
    System.out.println("Would you like to create a binary file? Y/N");
    if(keyboard.nextLine().toLowerCase().charAt(0) == 'y')
    {
      binary = true;
    }

    //returns the compression stats or error if something went wrong
    System.out.println("\n" + theTree.compress(uRL, outputFileName, binary) + "\n\n");
  }

  /**
   * This method is called if the user would like to compress a txt file
   */
  private static void compressFile()
  {
    //asks user if they would like to see all the valid files in the doc folder
    displayFiles(".txt");
    boolean binary = false;

    //Asks user what file to compress
    System.out.println("Please enter the name of the file which you would like to compress\n" +
                      "Please make sure that the file is in the /doc folder & contains file extension");
    String filename = keyboard.nextLine();

    //asks if user would like a binary file
    System.out.println("Would you like to create a binary file? Y/N");
    if(keyboard.nextLine().toLowerCase().charAt(0) == 'y')
    {
      binary = true;
    }

    //edits the filename
    String outputFileName = filename.substring(0, filename.length() - 4) + ".cph";

    //returns the compression stats or error if something went wrong
    System.out.println("\n" + theTree.compress(filename, outputFileName, binary) + "\n\n");

    //Easter Egg
    if(filename.equals("extremeCompression.txt"))
    {
      views.ErrorPane.johnCenaError("Extreemmmeee!!");
    }
  }

  /**
   * This method is used if the user would like to decompress a .cph file
   */
  private static void decompressFile()
  {
    //asks user if they would like to see all the valid files in the doc folder
    displayFiles(".cph");
    boolean binary = false;

    //Asks user what file to decompress
    System.out.println("Please enter the name of the file which you would like to decompress\n" +
                      "Please make sure that the file is in the /doc folder & contains file extension");
    String filename = keyboard.nextLine();

    //asks if user would like a binary file
    System.out.println("Would you like to create a binary file? Y/N");
    if(keyboard.nextLine().toLowerCase().charAt(0) == 'y')
    {
      binary = true;
    }

    //edits the filename
    String outputFileName = filename.substring(0, filename.length() - 4) + ".txt";

    //returns the compression stats or error if something went wrong
    System.out.println("\n" + theTree.decompress(filename, outputFileName, binary) + "\n\n");
  }

  /**
   * This method is used if the user would like to see what is in a file
   */
  private static void displayFile()
  {
    //asks user if they would like to see all the valid files in the doc folder
    displayFiles(".txt");

    //Asks user what file to display
    boolean binary = false;
    System.out.println("Please enter the name of the file which you would like to compress\n" +
                      "Please make sure that the file is in the /doc folder & contains file extension");
    String filename = keyboard.nextLine();

    //incase the filename the user enters is incorrect this will return an error
    if(!filename.contains(".txt"))
    {
      System.out.println("Unsupported File");
    }
    else
    {
      //tries to open file
      try
      {
        Scanner fileIn = new Scanner(new FileInputStream("doc/" + filename));

        //loops through file and prints it out line per line
        System.out.println("\n"); //for my OCD
        while(fileIn.hasNextLine())
        {
          System.out.println(fileIn.nextLine());
        }

        System.out.println("\n"); //for my OCD

        //closes file at the end
        fileIn.close();
      }
      catch(IOException e)
      {
        System.out.println("There has been an error while opening the file.\n" +
                           "Please try again");
      }
    }
  }

  /**
   * This method is used if the user would like to see
   * what files are in the doc/ folder
   */
  private static void displayFiles(String filetype)
  {
    //asks user if they would like to know what files are available
    System.out.println("\nWould you like to see what files are available? Y/N");

    //if user chooses yes it will display the files in the doc folder
    if(keyboard.nextLine().toLowerCase().charAt(0) == 'y')
    {
      System.out.println("\nIn doc/ Folder:");

      //opens the direcotry and then creates a array of files
      File docFolder = new File("doc/");
      File[] listOfFiles = docFolder.listFiles();

      //loops through the array and prints the files with vaild extensions
      for(File f : listOfFiles)
      {
        if(f.getName().contains(filetype))
        {
          System.out.println("File: " + f.getName());
        }
      }

      //for my OCD
      System.out.println();
    }
  }
}
