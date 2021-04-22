/**
 * @Author Alec Holt / Julian Beaulieu
 * @git alecholt / julianbeaulieu
 * @filename MainFrame.java
 * @description This class is the Main JFrame for the Miracosta Student Tool, built with WindowsBuilder. See the
 * initialize method for info on the elements and their settings
 */

package view;

import models.BlackBox;
import models.Information;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.*;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.io.File;
import java.net.URI;

public class MainFrame {

    private static final String[] INFOBOX = {

           "\n   Today's opening hours:" +
           "\n\n   Oceanside:" +
           "\n   " + GetHours.getMiraCostaOpeningHours() +
           "\n\n   San Elijo:" +
           "\n   " + GetHours.getSanElijoOpeningHours() +
           "\n\n   CLC:" +
           "\n   " + GetHours.getCLCOpeningHours()

            ,

           "\n   This area is for student tips," +
           "\n   Important dates, and Cool and\n  useful information."

            ,

            "\n   Good luck with finals!!!"

            ,

            "\n   Made with love by:" +
            "\n\n   Frontend: Alec" +
            "\n\n   Backend: Julian"

            ,

            "\n   Disclaimer:" +
            "\n\n   This  program is not in any way" +
            "\n   made by " +
            "\n   or " +
            "\n   affiliated with MiraCosta"

            ,

            "\n   Hi Nery :D"
    };

    private int infoBoxPosition = 0;
    private static final String[] BEGINNING_CHOICES = {"", "8000", "7000", "6100", "5200", "5100", "5000-Gym", "4900", "4800", "4700", "4600", "4500", "4400", "4300", "4200", "4100", "4050", "4000", "3700", "3601", "3600", "3500", "3400", "3300", "3200", "3100", "3000", "2500", "2400-Concert Hall", "2300", "2200", "2100", "2000-Theatre", "1200-Library", "1000-Admin", "T600", "T530", "T520", "T510", "T500", "T430", "T420", "T410", "T400", "T310", "T300", "T130", "T120", "T110", "T100"};
    private static final String[] GOAL_CHOICES = {"", "8000", "7000", "6100", "5200", "5100", "5000-Gym", "4900", "4800", "4700", "4600", "4500", "4400", "4300", "4200", "4100", "4050", "4000", "3700", "3601", "3600", "3500", "3400", "3300", "3200", "3100", "3000", "2500", "2400-Concert Hall", "2300", "2200", "2100", "2000-Theatre", "1200-Library", "1000-Admin", "T600", "T530", "T520", "T510", "T500", "T430", "T420", "T410", "T400", "T310", "T300", "T130", "T120", "T110", "T100"};
    private static final String[] GOAL_CHOICES_WITH_EASTEREGGS = {"", "John Cena's House", "8000", "7000", "6100", "5200", "5100", "5000-Gym", "4900", "4800", "4700", "4600", "4500", "4400", "4300", "4200", "4100", "4050", "4000", "3700", "3601", "3600", "3500", "3400", "3300", "3200", "3100", "3000", "2500", "2400-Concert Hall", "2300", "2200", "2100", "2000-Theatre", "1200-Library", "1000-Admin", "T600", "T530", "T520", "T510", "T500", "T430", "T420", "T410", "T400", "T310", "T300", "T130", "T120", "T110", "T100"};

    private JFrame frmMiracostaStudentTool;
    private JTextField timeTextField;
    private JTextField distanceTextField;
    private boolean easterEggs = false;

    /**
     * Create the application.
     */
    public MainFrame() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame window = new MainFrame();

                    window.frmMiracostaStudentTool.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        //initialize the required classes
        BlackBox bB = new BlackBox();


        //Initialize all of the view
        //Main Frame
        frmMiracostaStudentTool = new JFrame();
        frmMiracostaStudentTool.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/spartan-helmet-og.png"));
        frmMiracostaStudentTool.setTitle("MiraCosta Student Tool");
        frmMiracostaStudentTool.setBounds(100, 100, 1020, 600);
        frmMiracostaStudentTool.setLocationRelativeTo(null); //this centers the program in the middle of the screen
        frmMiracostaStudentTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMiracostaStudentTool.getContentPane().setLayout(null);
        frmMiracostaStudentTool.setResizable(false);


        PathPanel DrawingPanel = new PathPanel();
        DrawingPanel.setBounds(179, 11, 581, 512);
        frmMiracostaStudentTool.getContentPane().add(DrawingPanel);

        DrawingPanel.addMouseListener(new MouseListener() { //mouseListener to fill in position index
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("X: " + e.getX());
                System.out.println("Y: " + e.getY());
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });


        //Schedule Button
        JButton btnSchedule = new JButton("Schedule");
        btnSchedule.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnSchedule.setBounds(10, 495, 151, 38);
        frmMiracostaStudentTool.getContentPane().add(btnSchedule);
        //listener for the Schedule button
        btnSchedule.addActionListener(e -> System.out.println("schedule"));

        //HyperLink Button
        JButton hyprLink = new JButton("");
        hyprLink.setBounds(20, 0, 150, 150);
        hyprLink.setOpaque(false);
        hyprLink.setContentAreaFilled(false);
        hyprLink.setBorderPainted(false);
        frmMiracostaStudentTool.getContentPane().add(hyprLink);
        //listener for the Schedule button
        hyprLink.addActionListener(e -> {
            System.out.println("Open MiraCosta Website");

            if(easterEggs)
            {
                ErrorPane.rickRollError("Never gonna let you go!");
            }
            else
            {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://www.miracosta.edu"));
                }
                catch(Exception ex)
                {

                }
            }
        });

        //Button Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 255, 255));
        inputPanel.setBounds(791, 21, 206, 502);
        frmMiracostaStudentTool.getContentPane().add(inputPanel);
        inputPanel.setLayout(null);

        //Start combobox
        JComboBox beginningComboBox = new JComboBox();
        beginningComboBox.setModel(new DefaultComboBoxModel(BEGINNING_CHOICES));
        beginningComboBox.setBounds(10, 37, 186, 29);
        inputPanel.add(beginningComboBox);

        JLabel lblBeginning = new JLabel("Start");
        lblBeginning.setLabelFor(beginningComboBox);
        lblBeginning.setHorizontalAlignment(SwingConstants.CENTER);
        lblBeginning.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblBeginning.setBounds(54, 12, 98, 14);
        inputPanel.add(lblBeginning);

        //End combobox
        JComboBox goalComboBox = new JComboBox();
        goalComboBox.setModel(new DefaultComboBoxModel(GOAL_CHOICES));
        goalComboBox.setBounds(10, 102, 186, 29);
        inputPanel.add(goalComboBox);

        JLabel lblGoal = new JLabel("End");
        lblGoal.setHorizontalAlignment(SwingConstants.CENTER);
        lblGoal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblGoal.setBounds(54, 77, 98, 14);
        inputPanel.add(lblGoal);

        //Time label
        JLabel lblTime = new JLabel("Time:");
        lblTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTime.setBounds(10, 154, 46, 14);
        inputPanel.add(lblTime);

        //Distance label
        JLabel lblDistance = new JLabel("Distance:");
        lblDistance.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblDistance.setBounds(10, 178, 61, 14);
        inputPanel.add(lblDistance);

        //Time text field
        timeTextField = new JTextField();
        timeTextField.setBackground(SystemColor.text);
        timeTextField.setEditable(false);
        timeTextField.setBounds(110, 151, 86, 20);
        inputPanel.add(timeTextField);
        timeTextField.setColumns(10);

        //Distance text field
        distanceTextField = new JTextField();
        distanceTextField.setEditable(false);
        distanceTextField.setColumns(10);
        distanceTextField.setBackground(Color.WHITE);
        distanceTextField.setBounds(110, 175, 86, 20);
        inputPanel.add(distanceTextField);

        //Reset button
        JButton btnReset = new JButton(new ImageIcon("resources/images/Reset.png"));
        btnReset.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnReset.setBounds(38, 254, 135, 25);
        btnReset.setBorderPainted(false);
        inputPanel.add(btnReset);
        //listener for the Reset button
        btnReset.addActionListener(e -> {

          beginningComboBox.setModel(new DefaultComboBoxModel(BEGINNING_CHOICES));

          if(easterEggs)
          {
            goalComboBox.setModel(new DefaultComboBoxModel(GOAL_CHOICES_WITH_EASTEREGGS));
          }
          else
          {
            goalComboBox.setModel(new DefaultComboBoxModel(GOAL_CHOICES));
          }


          //set the fields with the time and distance
          timeTextField.setText("");
          distanceTextField.setText("");

          DrawingPanel.setNodesTravelled(null);
          DrawingPanel.repaint();

          System.out.println("reset");
        });

        //Calculate button
        JButton btnCalculate = new JButton(new ImageIcon("resources/images/Calculate.png"));
        btnCalculate.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnCalculate.setBounds(38, 223, 135, 25);
        btnCalculate.setBorderPainted(false);
        inputPanel.add(btnCalculate);
        //listener for the Calculate button
        btnCalculate.addActionListener(e -> {
            Information routeInfo;
            Double time, distance;
            int startIndex, goalIndex;
            String start, goal;

            startIndex = beginningComboBox.getSelectedIndex(); //index of start and finish from the choices
            goalIndex = goalComboBox.getSelectedIndex();

            if(easterEggs)
            {
              start = BEGINNING_CHOICES[startIndex];
              goal = GOAL_CHOICES_WITH_EASTEREGGS[goalIndex]; //string of start and finish from the choices

              if(goal.equals("John Cena's House"))
              {
                ErrorPane.johnCenaError("You have arrived");

                //set the fields with the time and distance
                timeTextField.setText("0 min");
                distanceTextField.setText("Arrived");
              }

                //use the BlackBox to get the Information, time and distance
                routeInfo = bB.calculateRoute(getNodeNumber(start), getNodeNumber(goal));
                time = routeInfo.getTime();
                distance = routeInfo.getDistance();

                //set the fields with the time and distance
                timeTextField.setText(time.toString() + " min");
                distanceTextField.setText(distance.toString() + " miles");

                //paint the path
                DrawingPanel.setNodesTravelled(routeInfo.getNodesTraveled());
                DrawingPanel.repaint();
            }
            else
            {
              start = BEGINNING_CHOICES[startIndex]; //string of start and finish from the choices
              goal = GOAL_CHOICES[goalIndex];

              //use the BlackBox to get the Information, time and distance
              routeInfo = bB.calculateRoute(getNodeNumber(start), getNodeNumber(goal));
              time = routeInfo.getTime();
              distance = routeInfo.getDistance();

              //set the fields with the time and distance
              timeTextField.setText(time.toString() + " min");
              distanceTextField.setText(distance.toString() + " miles");

              //repaint blank so it resets
              DrawingPanel.setNodesTravelled(routeInfo.getNodesTraveled());
              DrawingPanel.repaint();

              //paint the path
              DrawingPanel.setNodesTravelled(routeInfo.getNodesTraveled());
              DrawingPanel.repaint();
            }


        });

        ImageJTextArea txtTips = new ImageJTextArea(new File("resources/images/Help.png"));
        txtTips.setFont(new Font("Serif", Font.PLAIN, 13));
        txtTips.setBackground(SystemColor.control);
        txtTips.setLineWrap(true);
        txtTips.setEditable(false);
        txtTips.setText(INFOBOX[infoBoxPosition]);
        txtTips.setWrapStyleWord(true);
        txtTips.setBounds(8, 280, 190, 190);
        inputPanel.add(txtTips);

        JButton btnLeft = new JButton(new ImageIcon("resources/images/LeftButton.png"));
        btnLeft.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnLeft.setBounds(10, 465, 80, 40);
        btnLeft.setBorderPainted(false);
        //listener for the Left scroll button
        btnLeft.addActionListener(e -> {
          if(infoBoxPosition - 1 < 0)
          {
            infoBoxPosition = INFOBOX.length - 1;
          }
          else
          {
            infoBoxPosition--;
          }
          txtTips.setText(INFOBOX[infoBoxPosition]);

          System.out.println("left");
        });
        inputPanel.add(btnLeft);

        JButton btnRight = new JButton(new ImageIcon("resources/images/RightButton.png"));
        btnRight.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRight.setBounds(120, 465, 80, 40);
        btnRight.setBorderPainted(false);
        inputPanel.add(btnRight);
        //listener for the Right scroll button
        btnRight.addActionListener(e -> {
          if(infoBoxPosition + 1 > INFOBOX.length - 1)
          {
            infoBoxPosition = 0;
          }
          else
          {
            infoBoxPosition++;
          }
          txtTips.setText(INFOBOX[infoBoxPosition]);

          System.out.println("right");
        });

        JLabel overlayLabel = new JLabel("");
        overlayLabel.setIcon(new ImageIcon("resources/images/background.png"));
        overlayLabel.setBounds(0, 0, 1024, 560);
        frmMiracostaStudentTool.getContentPane().add(overlayLabel);

        JMenuBar menuBar = new JMenuBar();
        frmMiracostaStudentTool.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Options");
        mnNewMenu.setBackground(SystemColor.menu);
        mnNewMenu.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mnNewMenu);

        JCheckBoxMenuItem highlightParkingBox = new JCheckBoxMenuItem(" - Highlight Parking");
        //listener for the highlightParking button
        highlightParkingBox.addActionListener(e -> {
            if (!DrawingPanel.getHighlightParking())
            {
                DrawingPanel.setHighlightParking(true);
                System.out.println(DrawingPanel.getHighlightParking());
                DrawingPanel.repaint();
            }
            else
            {
                DrawingPanel.setHighlightParking(false);
                System.out.println(DrawingPanel.getHighlightParking());
                DrawingPanel.repaint();
            }
        });
        mnNewMenu.add(highlightParkingBox);

        JCheckBoxMenuItem highlightRestroomsBox = new JCheckBoxMenuItem(" - Highlight Restrooms");
        //listener for the highlightRestrooms button
        highlightRestroomsBox.addActionListener(e -> {
            if (!DrawingPanel.getHighlightRestrooms())
            {
                DrawingPanel.setHighlightRestrooms(true);
                System.out.println(DrawingPanel.getHighlightRestrooms());
                DrawingPanel.repaint();
            }
            else
            {
                DrawingPanel.setHighlightRestrooms(false);
                System.out.println(DrawingPanel.getHighlightRestrooms());
                DrawingPanel.repaint();
            }
        });
        mnNewMenu.add(highlightRestroomsBox);

        JCheckBoxMenuItem enableEasterEggsBox = new JCheckBoxMenuItem(" - Enable Easter Eggs");
        //listener for the Calculate button
        enableEasterEggsBox.addActionListener(e -> {

            if(!easterEggs)
            {
              goalComboBox.setModel(new DefaultComboBoxModel(GOAL_CHOICES_WITH_EASTEREGGS));
            }
            else
            {
              goalComboBox.setModel(new DefaultComboBoxModel(GOAL_CHOICES));
            }

            if(easterEggs)
            {
              easterEggs = false;
            }
            else
            {
              easterEggs = true;
            }
        });
        mnNewMenu.add(enableEasterEggsBox);
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    /**
     * Innerclass needed to make a JTextArea to have a picture as a background
     */
    class ImageJTextArea extends JTextArea
    {
        File image;
        public ImageJTextArea(File image)
        {
            setOpaque(false);
            this.image=image;
        }

        public void paintComponent(final Graphics g)
        {
            try
            {
                // Scale the image to fit by specifying width,height
                g.drawImage(new ImageIcon(image.getAbsolutePath()).getImage(),0,0,190, 190,this);
                super.paintComponent(g);
            }catch(Exception e){}
        }
    }


    private int getNodeNumber(String buildingNumber)
    {
        switch(buildingNumber)
        {
            case "8000":              return    83;
            case "7000":              return    110;
            case "6100":              return    105;
            case "5200":              return    37;
            case "5000-Gym":          return    35;
            case "5100":              return    40;
            case "4900":              return    93;
            case "4800":              return    111;
            case "4700":              return    77;
            case "4600":              return    77;
            case "4500":              return    88;
            case "4400":              return    91;
            case "4300":              return    92;
            case "4200":              return    90;
            case "4100":              return    104;
            case "4050":              return    108;
            case "4000":              return    102;
            case "3700":              return    59;
            case "3601":              return    85;
            case "3600":              return    95;
            case "3500":              return    95;
            case "3400":              return    66;
            case "3300":              return    62;
            case "3200":              return    61;
            case "3100":              return    52;
            case "3000":              return    49;
            case "2500":              return    7;
            case "2400-Concert Hall": return    11;
            case "2300":              return    13;
            case "2200":              return    13;
            case "2100":              return    12;
            case "2000-Theatre":      return    4;
            case "1200-Library":      return    54;
            case "1000-Admin":        return    21;
            case "T600":              return    1;
            case "T530":              return    37;
            case "T520":              return    37;
            case "T510":              return    37;
            case "T500":              return    37;
            case "T430":              return    70;
            case "T420":              return    71;
            case "T410":              return    76;
            case "T400":              return    74;
            case "T310":              return    97;
            case "T300":              return    107;
            case "T130":              return    24;
            case "T120":              return    24;
            case "T110":              return    23;
            case "T100":              return    23;
            default: return 1;
        }
    }
} // end of MainFrame class