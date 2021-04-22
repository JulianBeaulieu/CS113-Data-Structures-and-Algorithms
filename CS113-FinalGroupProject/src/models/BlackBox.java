/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename BlackBox.java
 * @description This class is used to calculate the shortest distance between two points
 */
package models;

import java.io.Serializable;
import java.util.*;

public class BlackBox implements Serializable
{
  //the actual graph object
  private static LinkedList<Edge>[] graph = null;

  //the distance between the starting node and all other nodes
  double[] distances = null;

  //the size of the graph
  private static int sizeOfGraph = 0;

  /**
   * Default Constructor
   */
  public BlackBox()
  {
    graph = FileIO.loadGraph();

    sizeOfGraph = graph.length;

    distances = new double[sizeOfGraph];

    /*for(int i = 0; i < 112; i++)
    {
      System.out.print("\n[" + (i + 1) + "] : ");
      for(Edge e : graph[i])
      {
        System.out.println(e.toString() + " -> ");
      }
    }*/
  }

  /** This method takes in the number of the start and end node and will calculate the
   * shortest distance between the two. It will also return the nodes which are used to travel this way,
   * the time it takes and the distance the path is
   * @param start the number of the starting node of the route
   * @param finish the number of the ending node of the route
   * @return a object of the information class, which will hold all of the information necessary to display the user
   *          the path and tell them how long it takes to get from a to b and how far the distance is
   */
  public Information calculateRoute(int start, int finish)
  {
    if(0 > start || start > 113 || 0 > finish || finish > 113 || start == finish)
    {
      throw new IllegalArgumentException("Start=" + start + " or Finish=" + finish + " are not legal");
    }

    return dijkstra(start - 1, finish);
  }


  /** This method is the method which does the actual calculating of the shortest route form A to B
   * @param start the number of the starting node of the route
   * @param finish the number of the ending node of the route
   * @return a object of the information class, which will hold all of the information necessary to display the user
   *          the path and tell them how long it takes to get from a to b and how far the distance is
   */
  private Information dijkstra(int start, int finish)
  {
    //saves the path from A to B
    double[] precedingNodes = new double[sizeOfGraph];

    //Set is needed to keep track of which nodes have been processed
    Set<Integer> visitedVetricySet = new HashSet<>(sizeOfGraph);

    // Initialize V-S
    for(int i = 0; i < sizeOfGraph; i++)
    {
      if(i != start)
      {
        visitedVetricySet.add(i);
      }

      distances[i] = Double.POSITIVE_INFINITY;
    }

    //this initializes the distances of the adjacent Vertices to the correct Weight
    for(Edge e : graph[start])
    {
      precedingNodes[(int) e.getDest() - 1] = start;

      distances[(int) e.getDest() - 1] = e.getWeight();
    }

    //Main part of the Dijkstra's algorithm
    while(visitedVetricySet.size() > 0)
    {
      // Find the value u in V-S with the smallest dist[u]
      double minDist = Double.POSITIVE_INFINITY;

      int u = -1;

      //looks for the shortest distance
      for(int v : visitedVetricySet)
      {
        if(distances[v] < minDist)
        {
          minDist = distances[v];
          u = v;
        }
      }

      // Remove u from vMinusS
      visitedVetricySet.remove(u);

      if(u == -1 || u == finish - 1)
      {
        break;
      }

      // Update the distances from the start to the other nodes
      Iterator<Edge> iterator = graph[u].iterator();

      while(iterator.hasNext())
      {
        Edge edge = iterator.next();

        int v = (int) edge.getDest() - 1;

        //it will only update the distances if the new distance to that node is shorter that the previously known route
        if(visitedVetricySet.contains(new Integer(v)))
        {
          double weight = edge.getWeight();

          if(distances[u] + weight < distances[v])
          {
            distances[v] = distances[u] + weight;

            precedingNodes[v] = u;
          }
        }
      }
    }

    //returns the Information object with all of the necessary details back
    return new Information(start + 1, finish, distances[finish - 1], getRoute(precedingNodes, start, finish));
  }

  /** This takes the array which holds all of the preceding nodes and
   * returns an array with just the nodes used for the route
   * @param precedingNodes is the array with the nodes used for the route
   * @param finish is the number of the node at which the route ends
   * @return is an int array with the nodes which comprise the route
   */
  private int[] getRoute(double[] precedingNodes, int start, int finish)
  {
    Stack<Double> s = new Stack<Double>();

    int u = finish - 1;

    while(precedingNodes[u] != 0.0)
    {
      s.push((double) u);
      u = (int) precedingNodes[u];
    }

    s.push((double) u);

    if(s.peek() != start)
    {
      s.push((double) start);
    }


    int[] finalRouteArray = new int[s.size()];

    for(int i = 0; i < finalRouteArray.length; i++)
    {
      finalRouteArray[i] = s.pop().intValue() + 1;
    }

    return finalRouteArray;
  }
}
