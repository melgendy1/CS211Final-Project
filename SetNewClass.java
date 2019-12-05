/**
 * This class extends the class JFrame to create the NewClass
 * GUI. This class reads the contents of a folder for txt files
 * to check if the user entered a name for a file that already exists
 */

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.*;

public class SetNewClass extends JFrame {
    private JMenuBar menuBar;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textfield1;
    private JTextField textfield2;
    private JTextField textfield3;
    private JTextField textfield4;

    //Constructor
    public SetNewClass(){

        this.setTitle("SetNewClass");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //menu generate method

        this.setJMenuBar(menuBar);

        //pane with null layout
        JFrame frame = new JFrame();
        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
        frame.setResizable(true);

        JPanel contentPane = new JPanel(null);
        // GUI component for the create button
        button1 = new JButton();
        button1.setBounds(299,316,90,35);
        button1.setLocation((((this.getWidth()-button1.getWidth())/2)+200),((this.getHeight()-button1.getHeight())/2)-30);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Create");
        button1.setVisible(true);
        button1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
                    String name = textfield2.getText() + "_" + textfield1.getText();
                    int numMeetings=-1;
                    int numStudents = -1;
                    File dir = new File("./txtFiles");
                    dir.mkdir();
                    boolean checkpointReached=false;
                    try {
                        if (name.contains("/")||name.contains("\\")||name.contains(":")||name.contains("?")||name.contains("\"")||name.contains("<")||name.contains(">")||name.contains("|")||name.contains("*"))
                            throw new Exceptions("Name cannot contain / \\ : ? \" < > | *\nPlease change format of date to MM-DD-YY or\nchange class name.");


                    } catch (Exception t){
                        checkpointReached=true;

                    }
                    if(checkpointReached==false)
                        try{
                            numStudents = Integer.parseInt(textfield3.getText());

                        }
                        catch (Exception t) { new Exceptions("Please enter a number(0, 1, 2, etc.)\nfor number of students.");
                            checkpointReached=true;}
                    if(checkpointReached==false)    
                        try{

                            numMeetings = Integer.parseInt(textfield4.getText());

                        } catch (Exception t){ new Exceptions("Please enter a number (0, 1, 2, etc.)\nfor number of meetings.");
                            checkpointReached=true;}
                    if(checkpointReached==false)                           
                        try {
                            File file = new File(dir, name + ".txt");
                            if (!file.exists())
                            {}
                            else {
                                checkpointReached=true;
                                throw new Exceptions("File already exists.\nPlease choose a different name.");
                            }
                        }catch (Exception t) {checkpointReached=true;}

                    if (checkpointReached==false)
                    { 
                        new NewClass(name, numStudents, numMeetings);
                        frame.dispose();}

                }});


        // GUI component for the back button
        JButton back = new JButton();
        back.setBounds(60,316,90,35);
        back.setLocation((((this.getWidth()-back.getWidth())/2)-235),((this.getHeight()-back.getHeight())/2)-30);
        back.setBackground(new Color(214,217,223));
        back.setForeground(new Color(0,0,0));
        back.setEnabled(true);
        back.setFont(new Font("sansserif",0,12));
        back.setText("Back");
        back.setVisible(true);
        back.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
                    new MainMenu();
                    frame.dispose();
                }});
        // GUI component for "Create a new class" label
        label1 = new JLabel();
        label1.setBounds(149,93,223,29);
        label1.setLocation((((this.getWidth()/2)-125)),(this.getHeight()/4));
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,24));
        label1.setText("Create a new Class");
        label1.setVisible(true);
        // GUI component for the "start date"
        label2 = new JLabel();
        label2.setBounds(55,206,195,33);
        label2.setLocation((((this.getWidth()-label2.getWidth())/2)-200),((this.getHeight()-label2.getHeight())/2)-100);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Start Date in MM-DD-YY");
        label2.setVisible(true);
        // GUI component for the "Name of the class"
        label3 = new JLabel();
        label3.setBounds(314,206,90,35);
        label3.setLocation((((this.getWidth()-label3.getWidth())/2)+200),((this.getHeight()-label3.getHeight())/2)-100);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("Name of Class");
        label3.setVisible(true);
        // GUI component for the "Number of students"
        JLabel label4 = new JLabel();
        label4.setBounds(314,206,120,35);
        label4.setLocation((((this.getWidth()-label4.getWidth())/2-90)),((this.getHeight()-label4.getHeight())/2)-100);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("sansserif",0,12));
        label4.setText("Number of Students");
        label4.setVisible(true);
        // GUI component for the "Number of Meetings"
        JLabel label5 = new JLabel();
        label5.setBounds(314,206,120,35);
        label5.setLocation((((this.getWidth()-label5.getWidth())/2+50)),((this.getHeight()-label5.getHeight())/2)-100);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(0,0,0));
        label5.setEnabled(true);
        label5.setFont(new Font("sansserif",0,12));
        label5.setText("Number of Meetings");
        label5.setVisible(true);
        //GUI component to set the text field for inputs for the user
        textfield1 = new JTextField();
        textfield1.setBounds(60,243,121,29);
        textfield1.setLocation((((this.getWidth()-textfield1.getWidth())/2)-235),((this.getHeight()-textfield1.getHeight())/2)-70);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);
        //GUI component to set the text field for inputs for the user
        textfield2 = new JTextField();
        textfield2.setBounds(60,243,121,29);
        textfield2.setLocation((((this.getWidth()-textfield2.getWidth())/2)+200),((this.getHeight()-textfield2.getHeight())/2)-70);
        textfield2.setBackground(new Color(255,255,255));
        textfield2.setForeground(new Color(0,0,0));
        textfield2.setEnabled(true);
        textfield2.setFont(new Font("sansserif",0,12));
        textfield2.setText("");
        textfield2.setVisible(true);
        //GUI component to set the text field for inputs for the user
        textfield3 = new JTextField();
        textfield3.setBounds(60,243,121,29);
        textfield3.setLocation((((this.getWidth()-textfield3.getWidth())/2-90)),((this.getHeight()-textfield3.getHeight())/2)-70);
        textfield3.setBackground(new Color(255,255,255));
        textfield3.setForeground(new Color(0,0,0));
        textfield3.setEnabled(true);
        textfield3.setFont(new Font("sansserif",0,12));
        textfield3.setText("");
        textfield3.setVisible(true);
        //GUI component to set the text field for inputs for the user
        textfield4 = new JTextField();
        textfield4.setBounds(60,243,121,29);
        textfield4.setLocation((((this.getWidth()-textfield4.getWidth())/2+50)),((this.getHeight()-textfield4.getHeight())/2)-70);
        textfield4.setBackground(new Color(255,255,255));
        textfield4.setForeground(new Color(0,0,0));
        textfield4.setEnabled(true);
        textfield4.setFont(new Font("sansserif",0,12));
        textfield4.setText("");
        textfield4.setVisible(true);

        //adding components to contentPane panel
        frame.add(button1);
        frame.add(back);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(textfield1);
        frame.add(textfield2);
        frame.add(textfield3);
        frame.add(label4);
        frame.add(textfield4);
        frame.add(label5);
        frame.add(contentPane);

        //adding panel to JFrame and setting of window position and close operation
        frame.add(contentPane);
        frame.setTitle("Student Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //method for generate menu

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new SetNewClass();
                }
            });
    }
}