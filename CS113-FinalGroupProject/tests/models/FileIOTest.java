/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename FileIOTest.java
 * @description JUnit test class for FileIOTest Object
 */
package models;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.*;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FileIOTest
{
  private static final String LOADGRAPHFROMBINARYFILE = "loadGraphFromBinaryFile";
  private static final String LOADGRAPHFROMTEXTFILE = "loadGraphFromTextFile";

  private static ArrayList<String> methodsIntFileIo= null;
  private static LinkedList<Edge>[] graph = null;

  @Before
  public void setUp()
  {
    graph = FileIO.loadGraph();
    methodsIntFileIo = new ArrayList<String>();

    String[] tempArray = {"PARSEDOUBLE", "LOADGRAPH", "LOADGRAPHFROMBINARYFILE", "LOADGRAPHFROMTEXTFILE", "SAVEGRAPH"};

    for(int i = 0; i < 5; i++)
    {
      methodsIntFileIo.add(i, tempArray[i]);
    }
  }

  private Object runMethod(String methodName, String filePath, String backupFilePath)
  {
    FileIO fileIO = new FileIO();
    try
    {
      Method[] methods = fileIO.getClass().getDeclaredMethods();

      for(Method method : methods)
      {
        method.setAccessible(true);

        if(methodName.equals(method.getName()))
        {
          //System.out.println("No Backup file: " + methodName + " | " + method.getName() + " | " + filePath + " | " + backupFilePath); // for testing the tester (Inception?)

          if(methodName.equals(LOADGRAPHFROMTEXTFILE))
          {
            return method.invoke(fileIO, filePath);
          }
          else
          {
            return method.invoke(fileIO, filePath, backupFilePath);
          }
        }
      }
    }
    catch(InvocationTargetException e)
    {
      //if this exception is thrown, everything worked as it should, since this catches an exception thrown in the method
    }
    catch(Exception e)
    {
      System.out.println("Something went wrong while Testing");
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Test Corrupt .dat file with correct .txt file -> should work and load up right graph
   */
  @Test
  public void corruptBinaryFileWithCorrectBackUpFileTest()
  {


    LinkedList<Edge>[] tempGraph = (LinkedList<Edge>[]) runMethod(LOADGRAPHFROMBINARYFILE,"testResources/MatrixCorrupted.dat", "resources/savefiles/Matrix.txt");

    assertEquals("This should be true, since both of the lenghts of the linked list should be the same", tempGraph.length, graph.length);

    for(int i = 0; i < 112; i++)
    {
      assertEquals("This should be true, since both of the lenghts of the linked list should be the same", tempGraph[i].size(), graph[i].size());

      for(int j = 0; j < tempGraph[i].size(); j++)
      {
        assertEquals("This should return true, because the linked lists should be the same",tempGraph[i].get(j), graph[i].get(j));
      }
    }
  }

  /**
   * Test Corrupt .dat file with corrupt .txt file -> should return null
   */
  @Test
  @SuppressWarnings("all")
  public void corruptBinaryFileWithOutCorrectBackUpFileTest()
  {


    LinkedList<Edge>[] tempGraph = (LinkedList<Edge>[]) runMethod(LOADGRAPHFROMBINARYFILE, "testResources/MatrixCorrupted.dat", "testResources/MatrixIllegalChars.txt");

    assertEquals("This should return null, since it throws a FileNotFoundException", null, tempGraph);
  }

  /**
   * Test Corrupt .txt file -> should return null
   */
  @Test
  @SuppressWarnings("unchecked")
  public void corruptTextFileTest()
  {
    assertEquals("This should return null, since it throws a FileNotFoundException", null,
                          runMethod(LOADGRAPHFROMTEXTFILE, null, "testResources/MatrixCorrupted.txt"));
  }

  /**
   * test .txt file with wrong chars -> should return null
   */
  @Test
  public void corruptTextFileWithWrongCharsTest()
  {


    assertEquals("This should return null, since it throws a FileNotFoundException", null,
                          runMethod(LOADGRAPHFROMTEXTFILE, null, "testResources/MatrixIllegalChars.txt"));
  }

  /**
   * Test non existent .dat file -> should return null
   */
  @Test
  public void nonExistentTextFileTest()
  {


    assertEquals("This should return null, since it throws a FileNotFoundException", null,
                          runMethod(LOADGRAPHFROMTEXTFILE, null, "testResources/nonExistentMatrix.txt"));
  }

  /**
   * test non existent .txt file -> should return null
   */
  @Test
  public void nonExistentBinaryFileTest()
  {


    assertEquals("This should return null, since it throws a FileNotFoundException", null,
                          runMethod(LOADGRAPHFROMBINARYFILE, "testResources/nonExistentMatrix.dat", null));
  }
}
