package views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class DisplayThe extends JFrame
{
    private static Clip clip;

    public static void rickRollError()
    {
        picture();
    }

    private static void audio()
    {
        //tries to open music file
        try
        {
            //tries to open music file,
            //then it will set it to loop and finally it will start the music
            File file = new File("resources/rickroll.wav");
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

    private static void picture()
    {
        //this will get the gif
        Icon icon = new ImageIcon("resources/rickroll.GIF");
        JLabel label = new JLabel(icon);

        //this will set the header of the frame
        JFrame f = new JFrame("Error!");
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
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        audio();
    }
}