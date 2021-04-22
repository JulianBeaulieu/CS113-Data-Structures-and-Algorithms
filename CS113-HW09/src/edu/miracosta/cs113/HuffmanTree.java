/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 4.0
 * @description Class which can compress and decompress text files
 *              and even convert human readable files into binary txt files
 */
package edu.miracosta.cs113;

import java.io.*;
import java.util.*;

public class HuffmanTree implements Serializable
{
  /*##########################################################################*/
  /*######################### Instance Variables #############################*/
  /*##########################################################################*/

  //array with the alphabet in binary
  private static final String[] binaryStrng = { "0000000000110001","0000000000110010","0000000000110011","0000000000110100","0000000000110101",
                                                "0000000000110110","0000000000110111","0000000000111000","0000000000111001","0000000000110000",//1-9,0
                                                "0000000001100001","0000000001100010","0000000001100011","0000000001100100","0000000001100101",
                                                "0000000001100110","0000000001100111","0000000001101000","0000000001101001","0000000001101010",
                                                "0000000001101011","0000000001101100","0000000001101101","0000000001101110","0000000001101111",//a-z
                                                "0000000001110000","0000000001110001","0000000001110010","0000000001110011","0000000001110100",
                                                "0000000001110101","0000000001110110","0000000001110111","0000000001111000","0000000001111001",
                                                "0000000001111010",
                                                "0000000001000001","0000000001000010","0000000001000011","0000000001000100","0000000001000101",
                                                "0000000001000110","0000000001000111","0000000001001000","0000000001001001","0000000001001010",
                                                "0000000001001011","0000000001001100","0000000001001101","0000000001001110","0000000001001111",//A-Z
                                                "0000000001010000","0000000001010001","0000000001010010","0000000001010011","0000000001010100",
                                                "0000000001010101","0000000001010110","0000000001010111","0000000001011000","0000000001011001",
                                                "0000000001011010",
                                                "0000000001000000","0000000000001001","0000000000001101","0000000000100001","0000000000101110",//\t\n!?.
                                                "0000000000111111"};; //holds the alphabet in binary
  //array containing the alphabet
  private static final String[] stringArr = { "1","2","3","4","5","6","7","8","9","0",
                                              "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                                              "p","q","r","s","t","u","v","w","x","y","z",
                                              "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
                                              "P","Q","R","S","T","U","V","W","X","Y","Z",
                                              " ","\t","\n","!",".","?"};; //holds the alphabet in human
  //saves the amount of numbers in the txt
  private long[] charAmt = null;
  //symbols that are valid
  private static final String VALID_SYMBOLS = " !.?\t\n";

  //binary tree which is the key to the compression
  private BinaryTree<HuffmanNode> huffTree = null;

  /*##########################################################################*/
  /*######################### Constructors ###################################*/
  /*##########################################################################*/

  /**
   * Default Constructor
   */
  public HuffmanTree()
  {
    //really does nothing  ¯\_(ツ)_/¯
  }

  /** Copy constructor
  * @see String#HuffmanTree(HuffmanTree other)
  * @param other which is of the HuffmanTree class
  */
  public HuffmanTree(HuffmanTree other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.huffTree = other.huffTree;
    this.charAmt = other.charAmt;
  }

  /*##########################################################################*/
  /*######################### Methods ########################################*/
  /*##########################################################################*/

  /** Method compresses a txt file or a web page
   * @param input Can either be the link to a website or the filename with extension of a file in the doc folder
   * @param outputFileName Is the filename of the compressed file which will be stored in the doc folder
   * @param binary is a boolean which controls if programs makes an additional normal binary text file
   * @return a string which can be returned
   */
  public String compress(String input, String outputFileName, boolean binary)
  {
    //checks if the input is a url or a filename
    if(input.contains("www.") || input.contains("http://") || input.contains("http://www.") ||
        input.contains(".com") || input.contains(".edu") || input.contains(".org"))
    {
      //this will first scrape website then compress file and delete the scraped file
      return compressWebsite(input, "doc/" + outputFileName, binary);
    }
    else
    {
      //this will just compress a file
      return compressFile("doc/" + input, "doc/" + outputFileName, binary);
    }
  }

  /** Takes in a text file with compressed text and the decompresses it
   * @param inputFilename is the filename and extension of a file in the doc folder
   * @param outputFileName is the filename and extension of the decompressed file in the doc folder
   * @param binary is if it should make a binary version of the compressed
   */
  public String decompress(String inputFilename, String outputFileName, boolean binary)
  {
    //checks if the file exists or not
    File testTheFile = new File("doc/" + inputFilename);

    if(!testTheFile.exists())
    {
      return "Error File does not Exist";
    }
    else if(!inputFilename.substring(inputFilename.length() - 4, inputFilename.length()).equals(".cph"))
    {
      return "Error";
    }

    //compresses the file and saves the states incase a binary file should be created
    String stats = decompressFile("doc/" + inputFilename);

    //this will make the binary file if desired
    if(binary)
    {
      humanToBinary(outputFileName.substring(0, outputFileName.length() - 3));
    }

    //returned the saved stats
    return stats;
  }

  /*+++++++++++ Binary File Methods +++++++++++*/

  /** this method takes the filename of the txt file which will be translated into a binary file
   * @param filename filename with file extension
   * @return int array used for compression
   */
  public void humanToBinary(String filename)
  {
    //saves the output binary string
    StringBuilder sB = null;

    //tries to open and create a file
    try
    {
      Scanner fileIn = new Scanner(new FileInputStream(filename + ".txt"));
      PrintWriter fileOut = new PrintWriter(new FileOutputStream(filename + ".bnry"));

      //loops through the whole array
      while(fileIn.hasNextLine())
      {
        //creates a fresh stringBuilder and a char array of the next line of the file
        sB = new StringBuilder();
        char[] line = fileIn.nextLine().toCharArray();

        //translates each char into a binary string
        for(char c : line)
        {
          humanToBinarySwitch(c, sB);
        }

        //writes the string builder to the file
        fileOut.println(sB.toString() + "\n");
      }

      //closes the files
      fileIn.close();
      fileOut.close();
    }
    catch(FileNotFoundException e)
    {
      //do nothing
    }
  }

  /** This method takes the filename of the txt file which will be translated into a human readable file
   * @param filename filename with file extension
   */
  public void binaryToHuman(String filename)
  {
    //tries to open and create a file
    try
    {
      Scanner fileIn = new Scanner(new FileInputStream("doc/" + filename));
      PrintWriter fileOut = new PrintWriter(new FileOutputStream("doc/NonBinary" + filename));
      StringBuilder sB = null;

      //loops through the whole file
      while(fileIn.hasNextLine())
      {
        //makes a fresh string builder
        sB = new StringBuilder();
        String line = fileIn.nextLine();
        String[] lineArr = new String[line.length() / 8];

        //devides the line into 16 parts because its 16bit binary
        for(int i = 0; i < line.length() / 16; i++)
        {
          lineArr[i] = line.substring((i * 16), (i * 16) + 16);
        }

        //converts every binary string into human
        for(String c : lineArr)
        {
          binaryToHumanSwitch(c, sB);
        }

        //prints the human alphabet into file
        fileOut.println(sB.toString());
      }

      //cloeses the files
      fileIn.close();
      fileOut.close();
    }
    catch(FileNotFoundException e)
    {
      //do nothing
    }
  }

  /*+++++++++++ Other File Methods +++++++++++*/

  /** Returns a string representation of the tree
   * @return String containing tree
   */
  public String toString()
  {
    return huffTree.toString();
  }

  /** Equals method which compares two HuffmanTree Objects
   * @param o a different huffmanTree object
   * @return true if they are equal, false if they are not
   */
  @Override
  public boolean equals(Object o)
  {
    if(this == o)
    {
      return true;
    }
    if(o == null || getClass() != o.getClass())
    {
      return false;
    }
    HuffmanTree that = (HuffmanTree) o;
    return Arrays.equals(charAmt, that.charAmt) && this.huffTree.equals(that.huffTree);
  }

  /**
   * Clone method which will return a copy of this object
   * @return a new HuffmanTree that has the same values as this one
   */
  public HuffmanTree clone()
  {
    return new HuffmanTree(this);
  }

  /*##########################################################################*/
  /*######################### Helper Methods #################################*/
  /*##########################################################################*/

  /*+++++++++++ Controller Methods +++++++++++*/

  /** This method will controll the actions needed to compress a file
   * @param inputFilename filename and extension of the file that should be compressed
   * @param outputFileName txt file and extension which the website will be stored in
   * @param binary if it should create a binary version of that file
   * @return a string with the savings
   */
  private String compressFile(String inputFilename, String outputFileName, boolean binary)
  {
    //checks if the file exists or not
    File testTheFile = new File(inputFilename);

    if(!testTheFile.exists())
    {
      return "Error File does not Exist";
    }

    //resets the char array
    initializeCharAmt();

    cleanFile(inputFilename);

    //if the user wishes to create a binary file version of the file,
    //it will be controlled by this if statement
    if(binary)
    {
      humanToBinary(inputFilename.substring(0, inputFilename.length() - 4)); //takes away file extension
    }
    else
    {
      countChars(inputFilename);
    }

    //this will then build the custom tree
    buildTree(buildHuffmanNodeArray());

    //finally it will create the compressed file and return the savings
    return compressFile(inputFilename, outputFileName);
  }

  /** This method will controll the actions needed to compress a file which is scraped from the web
   * @param inputURL the URL of the website
   * @param outputFileName txt file and extension which the website will be stored in
   * @param binary if it should create a binary version of that file
   * @return a string with the savings
   */
  private String compressWebsite(String inputURL, String outputFileName, boolean binary)
  {
    //this might throw and exception.
    try
    {
      //opens up a website and stores the scraped text in a txt file
      TextFileGenerator.makeCleanFile(inputURL, "doc/website.txt");
    }
    catch (IOException e)
    {
      return "Error, URL incorrect. \nPlease try again!";
    }
    //if it can scrape the website it will then compress it
    String stats = compressFile("doc/website.txt", outputFileName, binary);

    //this deletes the scraped txt file
    File file = new File("doc/website.txt");
    file.delete();

    //returns the stats
    return stats;
  }

  /** This method will take a filename and decompress the file
   * @param filename string containing the file name and path
   * @return the string of the decompression stats
   */
  private String decompressFile(String filename)
  {
    //checks if the file is of the right filetype
    if(filename.contains(".cph"))
    {
      //opens file, gets HuffmanNode array, then builds the tree
      //needs to go through if incase the compressed file was corrupted
      HuffmanNode[] temp = loadFile(filename);

      if(temp == null)
      {
        return "Error, compressed file corrupt";
      }

      buildTree(temp);

      //decompresses file and returns the compression stats
      return decompress(filename.substring(0, filename.length() - 4));
    }
    else
    {
      //else it will return error message
      return "Error. File cannot be decompressed!\nWrong filetype";
    }
  }

  /*+++++++++++ Compression Methods +++++++++++*/

  /** Incase a binary file is not wanted this will go through the file and count the chars
   * @param filename string containing the file name and path
   */
  private void countChars(String filename)
  {
    //tries to open file
    try
    {
      //open file
      Scanner fileIn = new Scanner(new FileInputStream(filename));

      //loops through file
      while(fileIn.hasNextLine())
      {
        //takes the line and saves it as a char array
        char[] line = fileIn.nextLine().toCharArray();

        //uses switch to increment the array charAmt
        for(char c : line)
        {
          humanToBinarySwitch(c, new StringBuilder());
        }
      }

      //closes file
      fileIn.close();
    }
    catch(FileNotFoundException e)
    {
      // should not return anything
    }
  }

  /** Will build the Huffman tree using an array of HuffmanNodes
   * @param symbols is a array of Huffmannodes
   */
  private void buildTree(HuffmanNode[] symbols)
  {
    Queue<BinaryTree<HuffmanNode>> theQueue = (Queue<BinaryTree<HuffmanNode>>)
            new PriorityQueue<BinaryTree<HuffmanNode>>(symbols.length, new CompareHuffmanTrees());

    // Load the queue with the leaves.
    for (HuffmanNode nextSymbol : symbols)
    {
      BinaryTree<HuffmanNode> aBinaryTree = new BinaryTree<HuffmanNode>(nextSymbol, null, null);
      theQueue.offer(aBinaryTree);
    }

    // Build the tree.
    while (theQueue.size() > 1)
    {
      BinaryTree<HuffmanNode> left = theQueue.poll();
      BinaryTree<HuffmanNode> right = theQueue.poll();
      double wl = left.getData().getAmt();
      double wr = right.getData().getAmt();
      HuffmanNode sum = new HuffmanNode(null, wl + wr);
      BinaryTree<HuffmanNode> newTree = new BinaryTree<HuffmanNode>(sum, left, right);
      theQueue.offer(newTree);
    }
    huffTree = theQueue.poll();
  }

  /** This actually compresses the file
   * @param inputFilename filename and path of the file which will be compressed
   * @param outputFileName filename and path of the file which is compressed file
   * @return a string containing the stats of the compression
   */
  private String compressFile(String inputFilename, String outputFileName)
  {
    //to string builders are needed to write to file and get total number of letters
    StringBuilder sB = null;
    StringBuilder totalFileLength = new StringBuilder();

    //try catch for file in and output
    try
    {
      //scanner to read in file, PrintWriter to make compressed file
      Scanner fileIn = new Scanner(new FileInputStream(inputFilename));
      PrintWriter fileOut = new PrintWriter(new FileOutputStream(outputFileName));

      //saves the HuffmanNodes in the file, so when decompressing the program can decompress correctly
      for(HuffmanNode hN : buildHuffmanNodeArray())
      {
        if(hN.getLetter().equals(" "))
        {
          fileOut.println("space " + hN.getAmt());
        }
        else if(hN.getLetter().equals("\t"))
        {
          fileOut.println("tab " + hN.getAmt());
        }
        else if(hN.getLetter().equals("\n"))
        {
          fileOut.println("nL " + hN.getAmt());
        }
        else
        {
          fileOut.println(hN.getLetter() + " " + hN.getAmt());
        }
      }

      fileOut.println("\n\n");

      //this takes each line, compresses it and writes it to the file
      while(fileIn.hasNextLine())
      {
        sB = new StringBuilder();
        char[] line = fileIn.nextLine().toCharArray();

        for(char c : line)
        {
          sB.append(encode("" + c) + " ");
        }

        totalFileLength.append(sB.toString());
        fileOut.println(sB.toString());
      }

      //closes both files
      fileIn.close();
      fileOut.close();
    }
    catch(FileNotFoundException e)
    {
      return "Error";
    }

    //saves the old and new file
    double compressedFileBits = totalFileLength.toString().length();
    double uncompressedFileBits = 0;

    //gets the size of the old file
    for(long i : charAmt)
    {
      uncompressedFileBits += i;
    }

    //this formats the returning string with the compression stats
    return String.format("Compression Stats:\n" +
                          "Uncompressed File Size: %.0f Bytes" +
         "\nCompressed File Size: %.0f Bytes" +
         "\nRatio: %.2f",
            (uncompressedFileBits * 16),
                compressedFileBits,
                ((compressedFileBits / (uncompressedFileBits * 16)) * 100)) + "%";
  }

  /** Wrapper method for the recursive find method,
   *  the find method will get the path to a Node in a Binary tree
   * @param letter is the letter which the path is wanted to
   * @return is a string containing the path to letter
   */
  private String encode(String letter)
  {
    //this makes a new refference to the binary tree saved in this object
    BinaryTree<HuffmanNode> currentTree = huffTree;

    //calls the recursive method which searches for a letter and then returns the path
    return encodeRec(currentTree, "", letter);
  }

  /** Recursive method, which searches a binary tree and
   *  returns the path to the node which holds the information wanted
   * @param currentTree Is a reference to the binary tree
   * @param path which is the string which will save the path
   * @param letter is the letter which the method is searching for
   * @return is the path that the program needs to take to get to the node
   */
  private String encodeRec(BinaryTree<HuffmanNode> currentTree, String path, String letter)
  {
    String returnStr= "";

    /*needs to first check if Letter is null. Since Java does not check the second boolean
    expression if the first one is false, it will not throw a null pointer exception*/
    if(currentTree.getData().getLetter() != null && currentTree.getData().getLetter().equals(letter))
    {
      returnStr = "" + path;
    }

    //if statement branches to the left if the left is not null
    if(currentTree.getLeftSubtree() != null)
    {
      returnStr += encodeRec(currentTree.getLeftSubtree(), path.concat("0"), letter);
    }

    //if statement branches to the right if the right is not null
    if(currentTree.getRightSubtree() != null)
    {
      returnStr += encodeRec(currentTree.getRightSubtree(), path.concat("1"), letter);
    }

    /**
     * Incase the left and right branch are null and the node does not contain the Letter
     * which is searched for that path is not saved
     */

    return returnStr;
  }

  /*+++++++++++ Decompression Methods +++++++++++*/

  /** Method which actually decompresses the file
   * @param filename filename and path of the file which will be decompressed
   * @return a string containing the stats of the compression
   */
  private String decompress(String filename)
  {
    //to string builders are needed to write to file and get total number of letters
    StringBuilder totalresultingFileLength = new StringBuilder();
    StringBuilder totalFileLength = new StringBuilder();

    //tries to open and create a file
    try
    {
      Scanner fileIn = new Scanner(new FileInputStream(filename + ".cph"));
      PrintWriter fileOut = new PrintWriter(new FileOutputStream(filename + ".txt"));
      StringBuilder sB = null;

      //jumps ahead over the part where it saves the encoding
      for(int i = 0; i < 71; i++)
      {
        fileIn.nextLine();
      }

      //actual decompression part
      while(fileIn.hasNextLine())
      {
        //makes a fresh StringBuilder
        sB = new StringBuilder();

        //saves the next line in the temp variable
        String temp = fileIn.nextLine();

        //adds the line to a different string builder for stats
        totalFileLength.append(temp);

        //this cuts the temp string into pieces
        StringTokenizer sT = new StringTokenizer(temp, " ");

        //this loops through the line and decompresses it
        while(sT.hasMoreTokens())
        {
          sB.append(decode(sT.nextToken()));
        }

        //adds the compressed line into a string builder
        totalresultingFileLength.append(sB.toString());
        fileOut.print(sB.toString() + "\n");
      }

      //closes the file
      fileIn.close();
      fileOut.close();
    }
    catch(FileNotFoundException e)
    {
      return "Error, file " + filename + " could not be found.\n" +
              "Please try again!";
    }
    catch(NullPointerException e)
    {
      return "Error!";
    }


    //saves the old and new file
    double uncompressedFileBits = totalresultingFileLength.toString().length();
    double compressedFileBits = totalFileLength.toString().length();

    //this formats the returning string with the compression stats
    return String.format("Compression Stats:\n" +
                          "Uncompressed File Size: %.0f Bytes" +
         "\nCompressed File Size: %.0f Bytes" +
         "\nRatio: %.2f",
            (uncompressedFileBits * 16),
                compressedFileBits,
                ((compressedFileBits / (uncompressedFileBits * 16)) * 100)) + "%";
  }

  /** Recursive method which decompresses the string of compressed binary string
   * @param codedMessage is a compressed binary string which will represents a word
   * @return the decompressed letter
   */
  private String decode(String codedMessage)
  {
    //makes a string builder
    StringBuilder result = new StringBuilder();

    //makes a new connection to the huffman tree
    BinaryTree<HuffmanNode> currentTree = huffTree;

    //loops through the whole string and finds the letters and
    //adds the letters to the string builder
    for (int i = 0; i < codedMessage.length(); i++)
    {
      if (codedMessage.charAt(i) == '1')
      {
        currentTree = currentTree.getRightSubtree();
      }
      else
      {
        currentTree = currentTree.getLeftSubtree();
      }
      if (currentTree.isLeaf())
      {
        HuffmanNode theData = currentTree.getData();
        result.append(theData.getLetter());
        currentTree = huffTree;
      }
    }

    //returns the resulting decompressed string
    return result.toString();
  }

  /** The loadFile method will go through a compressed file and
   * return a array of HuffmanNodes so the program can build a huffmantree,
   * which is unique to every file and therefore needs to be made everytime we decompress a file
   * @param filename filename and path of the file which will be decompressed
   * @return a array of HuffmanNodes
   */
  private HuffmanNode[] loadFile(String filename)
  {
    //makes a temporary array of HuffmanNodes
    HuffmanNode[] temp = new HuffmanNode[68];

    try
    {
      //tries to open the file
      Scanner fileIn = new Scanner(new FileInputStream(filename));

      //there should be as many huffman nodes as the size of the array
      for(int i = 0; i < 68; i++)
      {
        //cuts the line into two tokens
        StringTokenizer sT = new StringTokenizer(fileIn.nextLine(), " ");
        temp[i] = new HuffmanNode();

        //saves the two tokens in variables
        String letter = sT.nextToken();
        String amt = sT.nextToken();

        //if the letter is a word then it is a special case that needs to be saved
        //in a hard coded manner to prevent problems
        if(letter.equals("space"))
        {
          temp[i].setLetter(" ");
        }
        else if(letter.equals("tab"))
        {
          temp[i].setLetter("\t");
        }
        else if(letter.equals("nL"))
        {
          temp[i].setLetter("\n");
        }
        else
        {
          //this is if it is just a noral letter
          temp[i].setLetter(letter);
        }

        //this takes the double which is the second token and parse it into a double
        temp[i].setAmt(Double.parseDouble(amt));
      }
    }
    catch(Exception e)
    {
      return null;
    }

    //returns the HuffmanNode array
    return temp;
  }

  /*+++++++++++ Switch Methods +++++++++++*/

  /** Helper method which adds the corresponding binary string to the stringBuilder
   * @param c the character which is going to be added as a binary string
   * @param sB the StringBuilder object
   */
  private void humanToBinarySwitch(char c, StringBuilder sB)
  {
    //this should be pretty straight forward
    switch(c)
    {
      case '1': sB.append(binaryStrng[0]);
                charAmt[0]++;
                break;
      case '2': sB.append(binaryStrng[1]);
                charAmt[1]++;
                break;
      case '3': sB.append(binaryStrng[2]);
                charAmt[2]++;
                break;
      case '4': sB.append(binaryStrng[3]);
                charAmt[3]++;
                break;
      case '5': sB.append(binaryStrng[4]);
                charAmt[4]++;
                break;
      case '6': sB.append(binaryStrng[5]);
                charAmt[5]++;
                break;
      case '7': sB.append(binaryStrng[6]);
                charAmt[6]++;
                break;
      case '8': sB.append(binaryStrng[7]);
                charAmt[7]++;
                break;
      case '9': sB.append(binaryStrng[8]);
                charAmt[8]++;
                break;
      case '0': sB.append(binaryStrng[9]);
                charAmt[9]++;
                break;
      case 'a': sB.append(binaryStrng[10]);
                charAmt[10]++;
                break;
      case 'b': sB.append(binaryStrng[11]);
                charAmt[11]++;
                break;
      case 'c': sB.append(binaryStrng[12]);
                charAmt[12]++;
                break;
      case 'd': sB.append(binaryStrng[13]);
                charAmt[13]++;
                break;
      case 'e': sB.append(binaryStrng[14]);
                charAmt[14]++;
                break;
      case 'f': sB.append(binaryStrng[15]);
                charAmt[15]++;
                break;
      case 'g': sB.append(binaryStrng[16]);
                charAmt[16]++;
                break;
      case 'h': sB.append(binaryStrng[17]);
                charAmt[17]++;
                break;
      case 'i': sB.append(binaryStrng[18]);
                charAmt[18]++;
                break;
      case 'j': sB.append(binaryStrng[19]);
                charAmt[19]++;
                break;
      case 'k': sB.append(binaryStrng[20]);
                charAmt[20]++;
                break;
      case 'l': sB.append(binaryStrng[21]);
                charAmt[21]++;
                break;
      case 'm': sB.append(binaryStrng[22]);
                charAmt[22]++;
                break;
      case 'n': sB.append(binaryStrng[23]);
                charAmt[23]++;
                break;
      case 'o': sB.append(binaryStrng[24]);
                charAmt[24]++;
                break;
      case 'p': sB.append(binaryStrng[25]);
                charAmt[25]++;
                break;
      case 'q': sB.append(binaryStrng[26]);
                charAmt[26]++;
                break;
      case 'r': sB.append(binaryStrng[27]);
                charAmt[27]++;
                break;
      case 's': sB.append(binaryStrng[28]);
                charAmt[28]++;
                break;
      case 't': sB.append(binaryStrng[29]);
                charAmt[29]++;
                break;
      case 'u': sB.append(binaryStrng[30]);
                charAmt[30]++;
                break;
      case 'v': sB.append(binaryStrng[31]);
                charAmt[31]++;
                break;
      case 'w': sB.append(binaryStrng[32]);
                charAmt[32]++;
                break;
      case 'x': sB.append(binaryStrng[33]);
                charAmt[33]++;
                break;
      case 'y': sB.append(binaryStrng[34]);
                charAmt[34]++;
                break;
      case 'z': sB.append(binaryStrng[35]);
                charAmt[35]++;
                break;
      case 'A': sB.append(binaryStrng[36]);
                charAmt[36]++;
                break;
      case 'B': sB.append(binaryStrng[37]);
                charAmt[37]++;
                break;
      case 'C': sB.append(binaryStrng[38]);
                charAmt[38]++;
                break;
      case 'D': sB.append(binaryStrng[39]);
                charAmt[39]++;
                break;
      case 'E': sB.append(binaryStrng[40]);
                charAmt[40]++;
                break;
      case 'F': sB.append(binaryStrng[41]);
                charAmt[41]++;
                break;
      case 'G': sB.append(binaryStrng[42]);
                charAmt[42]++;
                break;
      case 'H': sB.append(binaryStrng[43]);
                charAmt[43]++;
                break;
      case 'I': sB.append(binaryStrng[44]);
                charAmt[44]++;
                break;
      case 'J': sB.append(binaryStrng[45]);
                charAmt[45]++;
                break;
      case 'K': sB.append(binaryStrng[46]);
                charAmt[46]++;
                break;
      case 'L': sB.append(binaryStrng[47]);
                charAmt[47]++;
                break;
      case 'M': sB.append(binaryStrng[48]);
                charAmt[48]++;
                break;
      case 'N': sB.append(binaryStrng[49]);
                charAmt[49]++;
                break;
      case 'O': sB.append(binaryStrng[50]);
                charAmt[50]++;
                break;
      case 'P': sB.append(binaryStrng[51]);
                charAmt[51]++;
                break;
      case 'Q': sB.append(binaryStrng[52]);
                charAmt[52]++;
                break;
      case 'R': sB.append(binaryStrng[53]);
                charAmt[53]++;
                break;
      case 'S': sB.append(binaryStrng[54]);
                charAmt[54]++;
                break;
      case 'T': sB.append(binaryStrng[55]);
                charAmt[55]++;
                break;
      case 'U': sB.append(binaryStrng[56]);
                charAmt[56]++;
                break;
      case 'V': sB.append(binaryStrng[57]);
                charAmt[57]++;
                break;
      case 'W': sB.append(binaryStrng[58]);
                charAmt[58]++;
                break;
      case 'X': sB.append(binaryStrng[59]);
                charAmt[59]++;
                break;
      case 'Y': sB.append(binaryStrng[60]);
                charAmt[60]++;
                break;
      case 'Z': sB.append(binaryStrng[61]);
                charAmt[61]++;
                break;
      case ' ': sB.append(binaryStrng[62]);
                charAmt[62]++;
                break;
      case '\t': sB.append(binaryStrng[63]);
                charAmt[63]++;
                 break;
      case '\n': sB.append(binaryStrng[64]);
                charAmt[64]++;
                 break;
      case '!': sB.append(binaryStrng[65]);
                charAmt[65]++;
                break;
      case '.': sB.append(binaryStrng[66]);
                charAmt[66]++;
                break;
      case '?': sB.append(binaryStrng[67]);
                charAmt[67]++;
                break;
      default: //this can't break
          break;
    }
  }

  /** Helper method which translates binary to human
   * @param c the binary string
   * @param sB the StringBuilder which will build the translated string
   */
  private void binaryToHumanSwitch(String c, StringBuilder sB)
  {
    //this should be pretty straight forward
    switch(c)
    {
      case "0000000000110001": sB.append('1');
                       break;
      case "0000000000110010": sB.append('2');
                       break;
      case "0000000000110011": sB.append('3');
                       break;
      case "0000000000110100": sB.append('4');
                       break;
      case "0000000000110101": sB.append('5');
                       break;
      case "0000000000110110": sB.append('6');
                       break;
      case "0000000000110111": sB.append('7');
                       break;
      case "0000000000111000": sB.append('8');
                       break;
      case "0000000000111001": sB.append('9');
                       break;
      case "0000000000110000": sB.append('0');
                       break;
      case "0000000001100001": sB.append('a');
                       break;
      case "0000000001100010": sB.append('b');
                       break;
      case "0000000001100011": sB.append('c');
                       break;
      case "0000000001100100": sB.append('d');
                       break;
      case "0000000001100101": sB.append('e');
                       break;
      case "0000000001100110": sB.append('f');
                       break;
      case "0000000001100111": sB.append('g');
                       break;
      case "0000000001101000": sB.append('h');
                       break;
      case "0000000001101001": sB.append('i');
                       break;
      case "0000000001101010": sB.append('j');
                       break;
      case "0000000001101011": sB.append('k');
                       break;
      case "0000000001101100": sB.append('l');
                       break;
      case "0000000001101101": sB.append('m');
                       break;
      case "0000000001101110": sB.append('n');
                       break;
      case "0000000001101111": sB.append('o');
                       break;
      case "0000000001110000": sB.append('p');
                       break;
      case "0000000001110001": sB.append('q');
                       break;
      case "0000000001110010": sB.append('r');
                       break;
      case "0000000001110011": sB.append('s');
                       break;
      case "0000000001110100": sB.append('t');
                       break;
      case "0000000001110101": sB.append('u');
                       break;
      case "0000000001110110": sB.append('v');
                       break;
      case "0000000001110111": sB.append('w');
                       break;
      case "0000000001111000": sB.append('x');
                       break;
      case "0000000001111001": sB.append('y');
                       break;
      case "0000000001111010": sB.append('z');
                       break;
      case "0000000001000001": sB.append('A');
                       break;
      case "0000000001000010": sB.append('B');
                       break;
      case "0000000001000011": sB.append('C');
                       break;
      case "0000000001000100": sB.append('D');
                       break;
      case "0000000001000101": sB.append('E');
                       break;
      case "0000000001000110": sB.append('F');
                       break;
      case "0000000001000111": sB.append('G');
                       break;
      case "0000000001001000": sB.append('H');
                       break;
      case "0000000001001001": sB.append('I');
                       break;
      case "0000000001001010": sB.append('J');
                       break;
      case "0000000001001011": sB.append('K');
                       break;
      case "0000000001001100": sB.append('L');
                       break;
      case "0000000001001101": sB.append('M');
                       break;
      case "0000000001001110": sB.append('N');
                       break;
      case "0000000001001111": sB.append('O');
                       break;
      case "0000000001010000": sB.append('P');
                       break;
      case "0000000001010001": sB.append('Q');
                       break;
      case "0000000001010010": sB.append('R');
                       break;
      case "0000000001010011": sB.append('S');
                       break;
      case "0000000001010100": sB.append('T');
                       break;
      case "0000000001010101": sB.append('U');
                       break;
      case "0000000001010110": sB.append('V');
                       break;
      case "0000000001010111": sB.append('W');
                       break;
      case "0000000001011000": sB.append('X');
                       break;
      case "0000000001011001": sB.append('Y');
                       break;
      case "0000000001011010": sB.append('Z');
                       break;
      case "0000000001000000": sB.append(' ');
                       break;
      case "0000000000001001": sB.append('\t');
                       break;
      case "0000000000001101": sB.append('\n');
                       break;
      case "0000000000100001": sB.append('!');
                       break;
      case "0000000000101110": sB.append('.');
                       break;
      case "0000000000111111": sB.append('?');
                       break;
      default: //this can't break
                       break;
    }
  }

  /*+++++++++++ File Cleaner Methods +++++++++++*/

  /**
   * This method cleans a file before compressing it to prevent problems
   * @param filename filename and path of the file which will be decompressed
   */
  private void cleanFile(String filename)
  {
    try
    {
      Scanner dirtyFile = new Scanner(new FileInputStream(filename));
      PrintWriter cleanFile = new PrintWriter(new FileOutputStream("doc/temp.txt"));

      while(dirtyFile.hasNextLine())
      {
        cleanFile.println(cleanString(dirtyFile.nextLine()));
      }

      dirtyFile.close();
      cleanFile.close();

      //deletes the dirty file
      File oldFile = new File(filename);
      oldFile.delete();

      //renames the clean file like the diry file
      File newFile = new File("doc/temp.txt");
      newFile.renameTo(new File(filename));
    }
    catch (IOException e)
    {
      //
    }
  }

  /**
   * Take in an unParsed String and clean it, only leaving characters
   * that are in the whiteList
   * @param unParsed The dirty/unparsed line
   * @return The cleaned line
   */
  private static String cleanString(String unParsed)
  {
    char current;
    StringBuilder returnString = new StringBuilder();

    // Loop through each char and if it is within the whitelist,
    // then add it to the StringBuilder
    for(int i = 0; i < unParsed.length(); i++)
    {
      current = unParsed.charAt(i);

      if(withinWhiteList(current))
      {
        returnString.append(current);
      }
    }
    return returnString.toString();
  }

  /**
   * Check to see if the ascii value is within our whiteList
   * @param ascii The ascii of the character we are checking
   * @return True if it is within the whitelist, false if not
   */
  private static boolean withinWhiteList(char ascii)
  {
    //If it is a valid symbols, digit, capital letter or lower case letter
    return VALID_SYMBOLS.indexOf(ascii) != -1 || ((ascii >= '0' && ascii <= '9') || (ascii >= 'A' && ascii <= 'Z')
            || (ascii >= 'a' && ascii <= 'z'));
  }

  /*+++++++++++ Other Helper Methods +++++++++++*/

  /** builds an array of HuffmanNodes out of an alphabet array and the charAmt array
   * @return
   */
  private HuffmanNode[] buildHuffmanNodeArray()
  {
    //makes a array of HuffmanNodes
    HuffmanNode[] temp = new HuffmanNode[charAmt.length];

    //loops and creates an array of HuffmanNodes
    for(int i = 0; i < charAmt.length; i++)
    {
      temp[i] = new HuffmanNode(stringArr[i], charAmt[i]);
    }

    //returns the array
    return temp;
  }

  /**
   * Simple method which just resets the charAmt array
   */
  private void initializeCharAmt()
  {
    //resets the charAmt array to an empty array of site 68
    charAmt = new long[68];
  }
}
