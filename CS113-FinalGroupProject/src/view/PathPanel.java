/**
 * @Author Alec Holt
 * @git alecholt
 * @filename PathPanel.java
 * @description This class is the Panel for displaying the path on the Main Frame of the Miracosta Student tool, and
 * using paintComponent method to draw the path.
 */

package view;

import models.PathList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class PathPanel extends JLabel
{
    //CONSTANTS
    final static BasicStroke STROKE = new BasicStroke(3.0f);


    //INSTANCE VARIABLES
    private PathList pathList;
    private int[] nodesTraveled = null;
    private boolean highlightParking, highlightRestrooms = false;


    //CONSTRUCTORS
    /**
     * Default constructor for PathPanel, initializes the PathList and calls the super constructer
     */
    public PathPanel()
    {
        super();
        pathList = new PathList();
    }


    //OVERRIDEN JLABEL METHODS
    @Override
    public void paintComponent(Graphics g)
    {
        //create the 2D graphics object to increase the lineweight
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(STROKE);

        if (highlightParking) //highlight parking button logic
        {
            int ovalWidth = 26;
            int ovalHeight = 26;

            g.setColor(new Color(10, 0, 200));

            g.drawOval(420 - (ovalWidth/2), 79 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(443 - (ovalWidth/2), 233 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(308 - (ovalWidth/2), 445 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(225 - (ovalWidth/2), 453 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(124 - (ovalWidth/2), 318 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(92 - (ovalWidth/2), 237 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(90 - (ovalWidth/2), 181 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(411 - (ovalWidth/2), 437 - (ovalHeight/2), ovalWidth, ovalHeight);
        }

        if (highlightRestrooms) //highlight restrooms button logic
        {
            int ovalWidth = 12;
            int ovalHeight = 12;

            g.setColor(new Color(255, 255, 0));

            g.drawOval(507 - (ovalWidth/2), 272 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(424 - (ovalWidth/2), 273 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(378 - (ovalWidth/2), 250 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(362 - (ovalWidth/2), 380 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(255 - (ovalWidth/2), 444 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(173 - (ovalWidth/2), 374 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(192 - (ovalWidth/2), 347 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(174 - (ovalWidth/2), 255 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(211 - (ovalWidth/2), 256 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(175 - (ovalWidth/2), 214 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(217 - (ovalWidth/2), 220 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(203 - (ovalWidth/2), 191 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(163 - (ovalWidth/2), 119 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(235 - (ovalWidth/2), 160 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(277 - (ovalWidth/2), 63 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(357 - (ovalWidth/2), 54 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(294 - (ovalWidth/2), 120 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(326 - (ovalWidth/2), 124 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(349 - (ovalWidth/2), 123 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(283 - (ovalWidth/2), 172 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(368 - (ovalWidth/2), 155 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(316 - (ovalWidth/2), 201 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(369 - (ovalWidth/2), 180 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(315 - (ovalWidth/2), 238 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(283 - (ovalWidth/2), 253 - (ovalHeight/2), ovalWidth, ovalHeight);
            g.drawOval(284 - (ovalWidth/2), 296 - (ovalHeight/2), ovalWidth, ovalHeight);
        }

        if (nodesTraveled != null) //path and calculate button logic
        {
            super.paintComponent(g);
            g.setColor(new Color(200,30,30));

            for (int i = 0; i < nodesTraveled.length - 1; i++)
            {
                if (nodesTraveled[i] != 0) {
                    int xPos1 = pathList.getNodeXPos(nodesTraveled[i] - 1);
                    int yPos1 = pathList.getNodeYPos(nodesTraveled[i] - 1);
                    int xPos2 = pathList.getNodeXPos(nodesTraveled[i + 1] - 1);
                    int ypos2 = pathList.getNodeYPos(nodesTraveled[i + 1] - 1);

                    //g.drawLine(xPos1, yPos1, xPos2, ypos2);
                    g2.draw(new Line2D.Double(xPos1, yPos1, xPos2, ypos2));
                }
            }
        }
    } //end of paintComponent method


    //PATHPANEL METHODS
    /**
     * setNodesTravelled sets the instance variable nodesTravelled to the int[] given
     *
     * @param nodesTraveled an int[] that is used to set the instance variable nodesTravelled
     */
    public void setNodesTravelled(int[] nodesTraveled)
    {
        if (nodesTraveled != null) {
            this.nodesTraveled = new int[nodesTraveled.length];

            for (int i = 0; i < nodesTraveled.length; i++) {
                this.nodesTraveled[i] = nodesTraveled[i];
            }
        } else
        {
            this.nodesTraveled = null;
        }
    }


    //SETTERS AND GETTERS

    /**
     * @param state sets the boolean state of the highlightParking instance variable
     */
    public void setHighlightParking(boolean state)
    {
        this.highlightParking = state;
    }

    /**
     * @param state sets the boolean state of the highlightRestrooms
     */
    public void setHighlightRestrooms(boolean state)
    {
        this.highlightRestrooms = state;
    }

    /**
     * @return returns the boolean state of the highlightParking instance variable
     */
    public boolean getHighlightParking()
    {
        return this.highlightParking;
    }

    /**
     * @return returns the boolean state of the highlightRestrooms instance variable
     */
    public boolean getHighlightRestrooms()
    {
        return this.highlightRestrooms;
    }


    //DEFAULT CLASS METHODS
    @Override
    public String toString()
    {
        return super.toString() + "\n" + pathList.toString() + "\n" + nodesTraveled.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public PathPanel clone()
    {
        PathPanel other = new PathPanel();
        other.setNodesTravelled(this.nodesTraveled);

        return other;
    }
} //end of PathPanel class
