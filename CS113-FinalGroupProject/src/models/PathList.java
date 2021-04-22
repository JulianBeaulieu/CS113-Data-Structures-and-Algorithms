/**
 * @Author Alec Holt
 * @git alecholt
 * @filename PathList.java
 * @description this class is used to create the PathList to initialize the array of PositionNodes, where each node
 * from BlackBox shares a positional value on the PathPanel in order to correctly map and draw the shortest path on the
 * panel.
 */

package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PathList
{
    //CONSTANTS
    private static final String OCEANSIDE_FILE_NAME = "resources/savefiles/OceansideList.txt";


    //INSTANCE VARIABLES
    private PositionNode[] oceansideList = new PositionNode[112];


    //CONSTRUCTORS
    /**
     * Default Constructor for PathList, runs the initializeOceansideList
     */
    public PathList()
    {
        initializeOceansideList();
    }


    //METHODS

    /**
     * initializeOceansideList takes the constant OCEANSIDE_FILE_NAME and loads the .txt file to enter in the variables
     * for the positionNodes
     */
    private void initializeOceansideList()
    {
        try
        {
            Scanner fileInput = new Scanner(new FileInputStream(OCEANSIDE_FILE_NAME));

            for (int i = 0; i < 112; i++)
            {
                if (fileInput.hasNextInt()) {
                    PositionNode node = new PositionNode();
                    node.setId(fileInput.nextInt());
                    node.setxPos(fileInput.nextInt());
                    node.setyPos(fileInput.nextInt());
                    oceansideList[i] = node;
                }
            }

            fileInput.close(); //close that file
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not Found...");
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Error: Values missing while reading File...");
        }
    }


    /**
     * getNodeXPos returns the xPos variable of the PositionNode at the current index pos
     *
     * @param pos the index that the PositionNode is located in the array oceansideList
     * @return xPos variable of the PositionNode
     */
    public int getNodeXPos(int pos)
    {
        if (pos < oceansideList.length && pos >= 0) {
            return oceansideList[pos].getxPos();
        }
        else {
            return -1;
        }
    }

    /**
     * getNodeYPos returns the yPos variable of the PositionNode at the current index pos
     *
     * @param pos the index that the PositionNode is located in the array oceansideList
     * @return yPos variable of the PositionNode
     */
    public int getNodeYPos(int pos)
    {
        if (pos < oceansideList.length && pos >= 0) {
            return oceansideList[pos].getyPos();
        }
        else {
            return -1;
        }
    }

    /**
     * getNodeId returns the id variable of the PositionNode at the current index pos
     *
     * @param pos the index that the PositionNode is located in the array oceansideList
     * @return id variable of the PositionNode
     */
    public int getNodeId(int pos)
    {
        if (pos < oceansideList.length && pos >= 0) {
            return oceansideList[pos].getId();
        }
        else {
            return -1;
        }
    }


    //INNER CLASS POSITION NODE

    /**
     * Inner class PositionNode holds the instance variables for determining the X and Y positions of each node
     */
    private class PositionNode
    {
        //INSTANCE VARIABLES
        private int id;
        private int xPos;
        private int yPos;


        //CONSTRUCTORS
        /**
         * Default constructor of PositionNode, sets all instance variables to -1
         */
        public PositionNode()
        {
            this.id = -1;
            this.xPos = -1;
            this.yPos = -1;
        }

        /**
         * Full constructor of PositionNode
         *
         * @param id id is the ID of the PositionNode
         * @param xPos xPos is the X position of the node on the panel
         * @param yPos yPos is the Y position of the node on the panel
         */
        public PositionNode(int id, int xPos, int yPos)
        {
            this.id = id;
            this.xPos = xPos;
            this.yPos = yPos;
        }


        //GETTERS AND SETTERS
        /**
         * @param id id to set, the node's id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @param xPos xPos to set, the node's X position on the panel
         */
        public void setxPos(int xPos) {
            this.xPos = xPos;
        }

        /**
         * @param yPos yPos to set, the node's Y position on the panel
         */
        public void setyPos(int yPos) {
            this.yPos = yPos;
        }

        /**
         * @return id of the PositionNode
         */
        public int getId() {
            return id;
        }

        /**
         * @return xPos of the PositionNode, the node's X position on the panel
         */
        public int getxPos() {
            return xPos;
        }

        /**
         * @return yPos of the PositionNode, the node's Y position on the panel
         */
        public int getyPos() {
            return yPos;
        }

        @Override
        public String toString()
        {
            return "ID: " + id + ", X: " + xPos + ", Y: " + yPos;
        }
    } //end of PositionNode inner class
} //end of PathList class
