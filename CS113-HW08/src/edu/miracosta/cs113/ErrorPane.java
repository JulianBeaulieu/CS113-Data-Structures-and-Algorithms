/**
 * @author Julian Beaulieu
 * @version 4.0
 * @github julianbeaulieu
 */
package views;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ErrorPane extends JFrame
{
    /*##############################################################################*
     *######## Generic Error which can display any gif or picture and music ########*
     *##############################################################################*/

    /** Generic Error Panel which can display any Picture or Gif, the music and the header message
     * @param filenameGif filename with relative path
     * @param filenameMusic filename with relative path
     * @param message message which should be displayed in the top of the JFrame
     */
    public static void genericError(String filenameGif, String filenameMusic, String headerMessage)
    {
        picture(filenameGif, filenameMusic, headerMessage, false);
    }

    /** Generic Error Panel which can display any Picture or Gif, the music and the header message "Error!"
     * @param filenameGif filename with relative path
     * @param filenameMusic filename with relative path
     */
    public static void genericError(String filenameGif, String filenameMusic)
    {
        picture(filenameGif, filenameMusic, "Error!", false);
    }

    /*##############################################################################*
     *######## Transparent Confetti Error which displays any confetti gif   ########*
     *##############################################################################*/

    /** Transparent Confetti Error which displays any confetti gif
     */
    public static void confettiError()
    {
        picture("resources/confetti.gif", "", "", true);
    }

    /*##############################################################################*
     *######## Transparent Confetti Error which displays any confetti gif   ########*
     *##############################################################################*/

    /** Transparent Confetti Error which displays any confetti gif
     */
    public static void moneyRainError()
    {
        picture("resources/moneyRain.gif", "", "", true);
    }

    /*##############################################################################*
     *######## RickRoll Error with standard or customizable header Message #########*
     *##############################################################################*/

    /** This will display a Error with a RickRoll in it and a customizable header
     * @param headerMessage string which will be displayed in the header
     */
    public static void rickRollError(String headerMessage)
    {
        picture("resources/rickroll.GIF", "resources/rickroll.wav", headerMessage, false);
    }

    /** This will display a Error with a RickRoll in it and a "Error!" as the header
     *
     */
    public static void rickRollError()
    {
        picture("resources/rickroll.GIF", "resources/rickroll.wav", "Error", false);
    }

    /*##############################################################################*
     *######## RickRoll Error with standard or customizable header Message #########*
     *##############################################################################*/

    /** This will display a Error with a johnCena in it and a customizable header
     * @param headerMessage string which will be displayed in the header
     */
    public static void johnCenaError(String headerMessage)
    {
        picture("resources/johnCena.GIF", "resources/johnCena.wav", "headerMessage", false);
    }

    /** This will display a Error with a johnCena in it and a "Error!" as the header
     *
     */
    public static void johnCenaError()
    {
        picture("resources/johnCena.GIF", "resources/johnCena.wav", "Error", false);
    }

    /*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#*#*#*
     *#*#*#*#*#*#*#*#*#*#*#*#*#*# Back End #*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#*#*#*#*#*
     *#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#*#*#*/

    private static Clip clip;

    /** This is the method, which creates and opens up a JFrame, displays the gif and controlls the music
     * @param filenameGif filename with relative path
     * @param filenameMusic filename with relative path
     * @param message message which should be displayed in the top of the JFrame
     */
    private static void picture(String filenameGif, String filenameMusic, String message, boolean confetti)
    {
        //this will get the gif
        Icon icon = new ImageIcon(filenameGif);
        JLabel label = new JLabel(icon);

        //this will set the header of the frame
        JFrame f = new JFrame(message);
        f.getContentPane().add(label);

        //this turns of the music, when the JFrame is closed
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                clip.close();
            }
        });

        //this sets up the default closing operations

        if(confetti)
        {
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
            f.setUndecorated(true);
            f.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
            f.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        }
        else
        {
            audio(filenameMusic);
        }

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /** This method takes care of playing the musics and stoping it when JFrame is closed
     * @param filenameMusic filename with relative path
     */
    private static void audio(String filenameMusic)
    {
        //tries to open music file
        try
        {
            //tries to open music file,
            //then it will set it to loop and finally it will start the music
            File file = new File(filenameMusic);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e)
        {
            //Do nothing
        }
    }



}