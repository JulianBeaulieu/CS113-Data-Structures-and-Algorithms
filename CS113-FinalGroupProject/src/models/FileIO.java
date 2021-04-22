/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename FileIO.java
 * @description This class is used to transfer data from and to files
 */
package models;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileIO
{
  private static final int GRAPHSIZE = 112;
  private static final int TABLESIZE = 113;
  private static final String BINARYFILEMATRIXFILEPATH = "resources/savefiles/Matrix.dat";
  private static final String TEXTFILEMATRIXFILEPATH = "resources/savefiles/Matrix.txt";

  /*####################################################################*/
  /*########################## File Input ##############################*/
  /*####################################################################*/

  /** This method will try to load the graph from a File
   * it will try to first load a graph object from a binary file.
   * If it cannot open or find a binary file, then it will try to load the
   * Matrix out of the txt file.
   * If that does not work it will return a null
   *
   * pre: There is either the binary file or the text file in the recourse folder
   * post: The file has been successfully loaded
   *
   * @return An array of LinkedLists containing the Edges which are the vertices of the graph
   */
  public static LinkedList<Edge>[] loadGraph()
  {
    File matrixFile = new File(TEXTFILEMATRIXFILEPATH);
    File binaryMatrixFile = new File(BINARYFILEMATRIXFILEPATH);

    if(binaryMatrixFile.exists())
    {
      return loadGraphFromBinaryFile(BINARYFILEMATRIXFILEPATH, TEXTFILEMATRIXFILEPATH);
    }
    else if(matrixFile.exists())
    {
      return loadGraphFromTextFile(TEXTFILEMATRIXFILEPATH);
    }
    else
    {
      return null;
    }
  }

  /** This method loads the Graph from a binary file
   *
   * @return An array of LinkedLists containing the Edges which are the vertices of the graph
   */
  private static LinkedList<Edge>[] loadGraphFromBinaryFile(String filePath, String backupFilePath)
  {
    Matrix matrix = null;

    try
    {
      //tries to open file
      ObjectInputStream fileIo = new ObjectInputStream(new FileInputStream(filePath));

      //Loads a matrix object from the file
      matrix = (Matrix) fileIo.readObject();

      fileIo.close();
    }
    catch(IOException e)
    {
      //e.printStackTrace();

      //in the case that the file is somehow corrupted, it will try to load the graph from the binary file
      return loadGraphFromTextFile(backupFilePath);
    }
    catch(ClassNotFoundException e)
    {
      //e.printStackTrace();

      //in the case that the file is somehow corrupted, it will try to load the graph from the binary file
      return loadGraphFromTextFile(backupFilePath);
    }

    //returns the graph which the matrix object holds
    return matrix.getGraph();
  }

  /** This method loads the Graph from a text file
   *
   * @return An array of LinkedLists containing the Edges which are the vertices of the graph
   */
  private static LinkedList<Edge>[] loadGraphFromTextFile(String filePath)
  {
    LinkedList<Edge>[] graph = new LinkedList[GRAPHSIZE];

    double[][] table = new double[TABLESIZE][TABLESIZE];

    try
    {
      //tries to open file
      Scanner fileIo = new Scanner(new FileInputStream(filePath));

      //saves file into 2D array
      for(int row = 0; row < TABLESIZE; row++)
      {
        String newLine = fileIo.nextLine().replaceAll("x", "0");

        if(newLine.matches(".*[a-zA-Z]+.*"))
        {
          return null;
        }

        StringTokenizer str = new StringTokenizer(newLine, "|");

        for(int column = 0; column < TABLESIZE; column++)
        {
          table[row][column] = parseDouble(str.nextToken().trim());
        }
      }

      fileIo.close();
    }
    catch(IOException e)
    {
      //e.printStackTrace();

      return null;
    }

    //for debugging
    /*
    for(int row = 0; row < TABLESIZE; row++)
    {
      for(int column = 0; column < TABLESIZE; column++)
      {
        System.out.print(table[row][column] + " ");

        if(column > 109)
        {
          System.out.println(" " + column);
        }
      }
    }*/

    //copies 2D array to Graph
    for(int row = 1; row < TABLESIZE; row++)
    {
      graph[row - 1] = new LinkedList<Edge>();

      for(int column = 1; column < TABLESIZE; column++)
      {
        //System.out.println("Row: " + row + " Column: " + column +  " table: " + table[row][column]);
        graph[row - 1].add(new Edge(row, column, table[row][column]));
      }
    }


    ArrayList<Edge> tempArr = null;
    //weeds out the connections which are not connected
    for(LinkedList<Edge> list : graph)
    {
      //stores nodes which will be removed
      tempArr = new ArrayList<>();

      //finds nodes which will be removed
      for(Edge e : list)
      {
        if(e.getWeight() == -1.0)
        {
          tempArr.add(e);
        }
      }

      //removes the nodes
      for(Edge e : tempArr)
      {
        list.remove(e);
      }
    }

    //makes a backup binary file for next time to avoid this
    saveGraph(graph);

    //returns graph
    return graph;
  }

  /*####################################################################*/
  /*########################## File Output #############################*/
  /*####################################################################*/

  public static void saveGraph(LinkedList<Edge>[] graph)
  {
    try
    {
      //tries to make a file
      ObjectOutputStream fileOutput = new ObjectOutputStream(new FileOutputStream(BINARYFILEMATRIXFILEPATH));

      //saves a matrix object to the file
      fileOutput.writeObject(new Matrix(graph));

      fileOutput.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  /*####################################################################*/
  /*########################## InnerClass ##############################*/
  /*####################################################################*/

  /**
   * this class is needed to save the graph into a binary file
   */
  private static class Matrix implements Serializable
  {
    //the graph
    private LinkedList<Edge>[] graph = null;

    /**
     * Default constructor
     */
    public Matrix()
    {
      graph = (LinkedList<Edge>[]) new Object[GRAPHSIZE];
    }

    /** Saves a new Graph into the object
     * @param newGraph The new graph which is an array of linked lists
     */
    public Matrix(LinkedList<Edge>[] newGraph)
    {
      graph = newGraph;
    }

    /** Saves a new Graph into the object
     * @param newGraph The new graph which is an array of linked lists
     */
    public void setGraph(LinkedList<Edge>[] newGraph)
    {
      graph = newGraph;
    }

    /** returns the graph
     * @return The graph which is an array of linked lists
     */
    public LinkedList<Edge>[] getGraph()
    {
      return graph;
    }

    /** Compares two Matrix objects
     * @param o the other matrix object
     * @return true if both have the same graph, false if they don't
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

      //casts o to a matrix object and then extracts the graph
      Matrix otherMatrix = (Matrix) o;
      LinkedList<Edge>[] otherGraph = otherMatrix.getGraph();

      //checks if both graphs are the same length
      if(otherGraph.length != graph.length)
      {
        return false;
      }

      //checks if both graphs contain the same objects
      for(int i = 0; i < graph.length; i++)
      {
        if(!graph[i].equals(otherGraph[i]))
        {
          return false;
        }
      }

      return true;
    }

    /** Returns String representation of the map
     * @return A string containing the values contained in the map
     */
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < graph.length; i++)
      {
        sb.append("[" + i + "] ");

        if(graph[i] == null)
        {
          sb.append("-> null");
        }
        else
        {
          //search the list at table[index] to find the key.
          for(Edge nextItem : graph[i])
          {
            sb.append("-> " + nextItem.toString() + " ");
          }
        }

        sb.append("\n");
      }

      return sb.toString();
    }
  }

  /*####################################################################*/
  /*########################## Helper Method ###########################*/
  /*####################################################################*/

  private static double parseDouble(String newDouble)
  {
    try
    {
      return Double.parseDouble(newDouble);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return 0;
  }
}


