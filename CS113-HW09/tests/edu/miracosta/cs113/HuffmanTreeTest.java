/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description JUnit test for HuffmanTree class
 */
package edu.miracosta.cs113;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HuffmanTreeTest
{
  private static HuffmanTree huffTree = new HuffmanTree();
  private static final String huffTreeToStringHelloWorldEdition = "HuffmanNode{letter='null', amt=12.0}\n" + " HuffmanNode{letter='null', amt=5.0}\n" + "  HuffmanNode{letter='o', amt=2.0}\n" + "   null\n" + "   null\n" + "  HuffmanNode{letter='null', amt=3.0}\n" + "   HuffmanNode{letter='e', amt=1.0}\n" + "    null\n" + "    null\n" + "   HuffmanNode{letter='null', amt=2.0}\n" + "    HuffmanNode{letter='r', amt=1.0}\n" + "     null\n" + "     null\n" + "    HuffmanNode{letter='!', amt=1.0}\n" + "     null\n" + "     null\n" + " HuffmanNode{letter='null', amt=7.0}\n" + "  HuffmanNode{letter='l', amt=3.0}\n" + "   null\n" + "   null\n" + "  HuffmanNode{letter='null', amt=4.0}\n" + "   HuffmanNode{letter='null', amt=2.0}\n" + "    HuffmanNode{letter=' ', amt=1.0}\n" + "     null\n" + "     null\n" + "    HuffmanNode{letter='W', amt=1.0}\n" + "     null\n" + "     null\n" + "   HuffmanNode{letter='null', amt=2.0}\n" + "    HuffmanNode{letter='d', amt=1.0}\n" + "     null\n" + "     null\n" + "    HuffmanNode{letter='null', amt=1.0}\n" + "     HuffmanNode{letter='null', amt=0.0}\n" + "      HuffmanNode{letter='null', amt=0.0}\n" + "       HuffmanNode{letter='b', amt=0.0}\n" + "        null\n" + "        null\n" + "       HuffmanNode{letter='c', amt=0.0}\n" + "        null\n" + "        null\n" + "      HuffmanNode{letter='null', amt=0.0}\n" + "       HuffmanNode{letter='null', amt=0.0}\n" + "        HuffmanNode{letter='s', amt=0.0}\n" + "         null\n" + "         null\n" + "        HuffmanNode{letter='null', amt=0.0}\n" + "         HuffmanNode{letter='t', amt=0.0}\n" + "          null\n" + "          null\n" + "         HuffmanNode{letter='null', amt=0.0}\n" + "          HuffmanNode{letter='6', amt=0.0}\n" + "           null\n" + "           null\n" + "          HuffmanNode{letter='null', amt=0.0}\n" + "           HuffmanNode{letter='3', amt=0.0}\n" + "            null\n" + "            null\n" + "           HuffmanNode{letter='null', amt=0.0}\n" + "            HuffmanNode{letter='null', amt=0.0}\n" + "             HuffmanNode{letter='k', amt=0.0}\n" + "              null\n" + "              null\n" + "             HuffmanNode{letter='null', amt=0.0}\n" + "              HuffmanNode{letter='I', amt=0.0}\n" + "               null\n" + "               null\n" + "              HuffmanNode{letter='null', amt=0.0}\n" + "               HuffmanNode{letter='m', amt=0.0}\n" + "                null\n" + "                null\n" + "               HuffmanNode{letter='null', amt=0.0}\n" + "                HuffmanNode{letter='n', amt=0.0}\n" + "                 null\n" + "                 null\n" + "                HuffmanNode{letter='null', amt=0.0}\n" + "                 HuffmanNode{letter='N', amt=0.0}\n" + "                  null\n" + "                  null\n" + "                 HuffmanNode{letter='null', amt=0.0}\n" + "                  HuffmanNode{letter='p', amt=0.0}\n" + "                   null\n" + "                   null\n" + "                  HuffmanNode{letter='null', amt=0.0}\n" + "                   HuffmanNode{letter='q', amt=0.0}\n" + "                    null\n" + "                    null\n" + "                   HuffmanNode{letter='null', amt=0.0}\n" + "                    HuffmanNode{letter='T', amt=0.0}\n" + "                     null\n" + "                     null\n" + "                    HuffmanNode{letter='null', amt=0.0}\n" + "                     HuffmanNode{letter='V', amt=0.0}\n" + "                      null\n" + "                      null\n" + "                     HuffmanNode{letter='null', amt=0.0}\n" + "                      HuffmanNode{letter='X', amt=0.0}\n" + "                       null\n" + "                       null\n" + "                      HuffmanNode{letter='null', amt=0.0}\n" + "                       HuffmanNode{letter='u', amt=0.0}\n" + "                        null\n" + "                        null\n" + "                       HuffmanNode{letter='null', amt=0.0}\n" + "                        HuffmanNode{letter='null', amt=0.0}\n" + "                         HuffmanNode{letter='null', amt=0.0}\n" + "                          HuffmanNode{letter='9', amt=0.0}\n" + "                           null\n" + "                           null\n" + "                          HuffmanNode{letter='h', amt=0.0}\n" + "                           null\n" + "                           null\n" + "                         HuffmanNode{letter='null', amt=0.0}\n" + "                          HuffmanNode{letter='z', amt=0.0}\n" + "                           null\n" + "                           null\n" + "                          HuffmanNode{letter='A', amt=0.0}\n" + "                           null\n" + "                           null\n" + "                        HuffmanNode{letter='0', amt=0.0}\n" + "                         null\n" + "                         null\n" + "            HuffmanNode{letter='null', amt=0.0}\n" + "             HuffmanNode{letter='a', amt=0.0}\n" + "              null\n" + "              null\n" + "             HuffmanNode{letter='null', amt=0.0}\n" + "              HuffmanNode{letter='null', amt=0.0}\n" + "               HuffmanNode{letter='null', amt=0.0}\n" + "                HuffmanNode{letter='null', amt=0.0}\n" + "                 HuffmanNode{letter='B', amt=0.0}\n" + "                  null\n" + "                  null\n" + "                 HuffmanNode{letter='null', amt=0.0}\n" + "                  HuffmanNode{letter='C', amt=0.0}\n" + "                   null\n" + "                   null\n" + "                  HuffmanNode{letter='null', amt=0.0}\n" + "                   HuffmanNode{letter='D', amt=0.0}\n" + "                    null\n" + "                    null\n" + "                   HuffmanNode{letter='null', amt=0.0}\n" + "                    HuffmanNode{letter='E', amt=0.0}\n" + "                     null\n" + "                     null\n" + "                    HuffmanNode{letter='null', amt=0.0}\n" + "                     HuffmanNode{letter='F', amt=0.0}\n" + "                      null\n" + "                      null\n" + "                     HuffmanNode{letter='null', amt=0.0}\n" + "                      HuffmanNode{letter='G', amt=0.0}\n" + "                       null\n" + "                       null\n" + "                      HuffmanNode{letter='null', amt=0.0}\n" + "                       HuffmanNode{letter='y', amt=0.0}\n" + "                        null\n" + "                        null\n" + "                       HuffmanNode{letter='null', amt=0.0}\n" + "                        HuffmanNode{letter='x', amt=0.0}\n" + "                         null\n" + "                         null\n" + "                        HuffmanNode{letter='null', amt=0.0}\n" + "                         HuffmanNode{letter='J', amt=0.0}\n" + "                          null\n" + "                          null\n" + "                         HuffmanNode{letter='null', amt=0.0}\n" + "                          HuffmanNode{letter='K', amt=0.0}\n" + "                           null\n" + "                           null\n" + "                          HuffmanNode{letter='null', amt=0.0}\n" + "                           HuffmanNode{letter='L', amt=0.0}\n" + "                            null\n" + "                            null\n" + "                           HuffmanNode{letter='null', amt=0.0}\n" + "                            HuffmanNode{letter='M', amt=0.0}\n" + "                             null\n" + "                             null\n" + "                            HuffmanNode{letter='null', amt=0.0}\n" + "                             HuffmanNode{letter='g', amt=0.0}\n" + "                              null\n" + "                              null\n" + "                             HuffmanNode{letter='null', amt=0.0}\n" + "                              HuffmanNode{letter='O', amt=0.0}\n" + "                               null\n" + "                               null\n" + "                              HuffmanNode{letter='null', amt=0.0}\n" + "                               HuffmanNode{letter='P', amt=0.0}\n" + "                                null\n" + "                                null\n" + "                               HuffmanNode{letter='null', amt=0.0}\n" + "                                HuffmanNode{letter='Q', amt=0.0}\n" + "                                 null\n" + "                                 null\n" + "                                HuffmanNode{letter='null', amt=0.0}\n" + "                                 HuffmanNode{letter='R', amt=0.0}\n" + "                                  null\n" + "                                  null\n" + "                                 HuffmanNode{letter='null', amt=0.0}\n" + "                                  HuffmanNode{letter='S', amt=0.0}\n" + "                                   null\n" + "                                   null\n" + "                                  HuffmanNode{letter='null', amt=0.0}\n" + "                                   HuffmanNode{letter='w', amt=0.0}\n" + "                                    null\n" + "                                    null\n" + "                                   HuffmanNode{letter='null', amt=0.0}\n" + "                                    HuffmanNode{letter='U', amt=0.0}\n" + "                                     null\n" + "                                     null\n" + "                                    HuffmanNode{letter='null', amt=0.0}\n" + "                                     HuffmanNode{letter='null', amt=0.0}\n" + "                                      HuffmanNode{letter='\n" + "', amt=0.0}\n" + "                                       null\n" + "                                       null\n" + "                                      HuffmanNode{letter='null', amt=0.0}\n" + "                                       HuffmanNode{letter='2', amt=0.0}\n" + "                                        null\n" + "                                        null\n" + "                                       HuffmanNode{letter='null', amt=0.0}\n" + "                                        HuffmanNode{letter='.', amt=0.0}\n" + "                                         null\n" + "                                         null\n" + "                                        HuffmanNode{letter='null', amt=0.0}\n" + "                                         HuffmanNode{letter='1', amt=0.0}\n" + "                                          null\n" + "                                          null\n" + "                                         HuffmanNode{letter='?', amt=0.0}\n" + "                                          null\n" + "                                          null\n" + "                                     HuffmanNode{letter='null', amt=0.0}\n" + "                                      HuffmanNode{letter='\t', amt=0.0}\n" + "                                       null\n" + "                                       null\n" + "                                      HuffmanNode{letter='null', amt=0.0}\n" + "                                       HuffmanNode{letter='v', amt=0.0}\n" + "                                        null\n" + "                                        null\n" + "                                       HuffmanNode{letter='null', amt=0.0}\n" + "                                        HuffmanNode{letter='Y', amt=0.0}\n" + "                                         null\n" + "                                         null\n" + "                                        HuffmanNode{letter='null', amt=0.0}\n" + "                                         HuffmanNode{letter='Z', amt=0.0}\n" + "                                          null\n" + "                                          null\n" + "                                         HuffmanNode{letter='null', amt=0.0}\n" + "                                          HuffmanNode{letter='f', amt=0.0}\n" + "                                           null\n" + "                                           null\n" + "                                          HuffmanNode{letter='null', amt=0.0}\n" + "                                           HuffmanNode{letter='4', amt=0.0}\n" + "                                            null\n" + "                                            null\n" + "                                           HuffmanNode{letter='8', amt=0.0}\n" + "                                            null\n" + "                                            null\n" + "                HuffmanNode{letter='i', amt=0.0}\n" + "                 null\n" + "                 null\n" + "               HuffmanNode{letter='5', amt=0.0}\n" + "                null\n" + "                null\n" + "              HuffmanNode{letter='j', amt=0.0}\n" + "               null\n" + "               null\n" + "       HuffmanNode{letter='7', amt=0.0}\n" + "        null\n" + "        null\n" + "     HuffmanNode{letter='H', amt=1.0}\n" + "      null\n" + "      null\n";


  //helper methods

  /**
   * This method will make a corrupt file
   */
  private void buildFile(String filename, String fileContent)
  {
    //makes a test file with invalid characters
    try
    {
      PrintWriter fileOut = new PrintWriter(filename);

      fileOut.println(fileContent);

      fileOut.close();
    }
    catch(IOException e)
    {
      //do nothing this should always work
    }
  }

  /** This method will delete the two files created by the test
   * @param filenameOne filepath to file #1
   * @param filenameTwo filepath to file #2
   * @param resultOfTest boolean got from a test
   * @return the result of the test which was put in method
   */
  private boolean deleteFilesAfterTest(String filenameOne, String filenameTwo, String filenameThree, boolean resultOfTest)
  {
    //deletes two or three files made during a Test is finish to keep folder unlcuttered
    File fileOne = new File(filenameOne);
    fileOne.delete();

    File fileTwo = new File(filenameTwo);
    fileTwo.delete();

    File fileThree = new File(filenameThree);
    fileThree.delete();

    return resultOfTest;
  }

  /** This method will delete the file created by the test
   * @param filenameOne filepath to file
   * @return the result of the test which was put in method
   */
  private String deleteFileAfterTest(String filenameOne, String testString)
  {
    //deletes two or three files made during a Test is finish to keep folder unlcuttered
    File fileOne = new File(filenameOne);
    fileOne.delete();

    return testString;
  }

  /** Compares two files with each other
   * @param filenameOne filepath to file #1
   * @param filenameTwo filepath to file #2
   * @return boolean true if content of both files are the same, false if they aren't
   */
  private boolean compareTwoFiles(String filenameOne, String filenameTwo)
  {
    //tries to open both files
    try
    {
      Scanner fileInOne = new Scanner(new FileInputStream(filenameOne));
      Scanner fileInTwo = new Scanner(new FileInputStream(filenameTwo));

      //loops through files and compares lines. will return error if two lines don't match
      while(fileInOne.hasNextLine() && fileInTwo.hasNextLine())
      {
        if(!fileInOne.nextLine().equals(fileInTwo.nextLine()))
        {
          return false;
        }
      }
    }
    catch(IOException e)
    {
      //e.printStackTrace();
      //if something went wrong it should return false
      return false;
    }

    //if the program gets here, both files are the same
    return true;
  }

  //Not Fail tests

  /**
   * Test will let HuffmanTree object try to compress file with invalid characters
   */
  @Test
  public void testOpenCorruptFile()
  {
    //makes the corrupt file first
    buildFile("doc/HelloWorldInvalidCharacter.txt", "Hello, World!\n:_-<<>>-_:");

    //tries to compress it, it will first run through the file and remove all unsupported characters
    huffTree.compress("HelloWorldInvalidCharacter.txt", "HelloWorldInvalidCharacter.cph", false);

    //should return true
    assertTrue(deleteFilesAfterTest("doc/HelloWorldInvalidCharacter.txt", "doc/HelloWorldInvalidCharacter.cph", "",
                            compareTwoFiles("doc/HelloWorld.txt", "doc/HelloWorldInvalidCharacter.txt")));
  }

  /**
   * Test will let HuffmanTree object try to decompress file which was corrupted
   */
  @Test
  public void testOpenCorruptCompressedFile()
  {
    //tries to compress it, it will first run through the file and remove all unsupported characters
    String errorResult = huffTree.decompress("HelloWorldBrokenCompressed.cph",
                                                    "doc/HelloWorldBrokenCompressed.txt", false);

    //should return true
    assertTrue(errorResult.equals("Error, compressed file corrupt"));
  }

  /**
   * Test will let HuffmanTree object try to decompress file which is not the correct file type
   */
  @Test
  public void testOpenCompressedFileWithWrongFileExtension()
  {
    //tries to compress it, it will first run through the file and remove all unsupported characters
    String errorResult = huffTree.decompress("HelloWorldInvalidFileextension.cuh", "doc/HelloWorldBrokenCompressed.txt", false);

    //should return true
    assertTrue(errorResult.equals("Error"));
  }

  /**
   * Test will let HuffmanTree object try to compress website which is invalid
   */
  @Test
  public void testOpenCorruptWebsite()
  {
    //tries to compress it, it will first run through the file and remove all unsupported characters
    String errorResult = huffTree.compress("HelloWorldWebsite.com", "HelloWorldWebsite.cph", false);

    System.out.println(errorResult);

    //should return true
    assertTrue(errorResult.equals("Error, URL incorrect. \n" + "Please try again!"));
  }

  /**
   * Test will let HuffmanTree object try to compress &
   * decompress hello world file and compare both of them
   */
  @Test
  public void testFileBeforeAndAfterCompression()
  {
    //tries to compress it HelloWorld file
    huffTree.compress("HelloWorld.txt", "HelloWorldTest.cph", false);

    //tries to decompress HelloWorldTest.cph
    huffTree.decompress("HelloWorldTest.cph", "HelloWorldTest.txt", false);

    //should return true
    assertTrue(deleteFilesAfterTest("doc/HelloWorldTest.txt", "doc/HelloWorldTest.cph", "",
                            compareTwoFiles("doc/HelloWorldTest.txt", "doc/HelloWorld.txt")));
  }

  /**
   * Test will let HuffmanTree object try to scrape and compress test website
   * which has a lower case Hello World text
   */
  @Test
  public void testScrapeWebsiteThenTestFileBeforeAndAfterCompression()
  {
    buildFile("doc/HelloWorldLowerCase.txt", "hello world");
    //tries to compress it HelloWorld file
    huffTree.compress("http://michaelwflaherty.com/files/index.html", "HelloWorldTest.cph", false);

    //tries to decompress HelloWorldTest.cph
    huffTree.decompress("HelloWorldTest.cph", "HelloWorldTest.txt", false);

    //should return true
    assertTrue(deleteFilesAfterTest("doc/HelloWorldTest.txt", "doc/HelloWorldTest.cph", "doc/HelloWorldLowerCase.txt",
                            compareTwoFiles("doc/HelloWorldTest.txt", "doc/HelloWorldLowerCase.txt")));
  }

  /**
   * This Test calls the to string of a a huffman Tree object that just compressed a hallo world file
   */
  @Test
  public void testToString()
  {

    //tries to compress it HelloWorld file
    huffTree.compress("HelloWorld.txt", "HelloWorldTest.cph", false);

    assertEquals(deleteFileAfterTest("doc/HelloWorldTest.cph", huffTreeToStringHelloWorldEdition), huffTree.toString());
  }
}
