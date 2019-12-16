import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Represents the New Class we are trying to set up.
 */
@SuppressWarnings("serial")
public class SetNewClass extends JFrame {
	/**
	 * The Set New class has many attributes.
	 * We Initialize them being private
	 * Meaning they are accessible within the same class only.
	 */
	private JMenuBar menuBar;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textfield1;
    private JTextField textfield2;
    private JTextField textfield3;
    private JTextField textfield4;
    private JTextField textfield5;
    private JTextField textfield6;
    private JTextField textfield7;
    private JTextField textfield8;
    /**
     * The Constructor for the Set New Class.
     */
    public SetNewClass(){

        this.setTitle("SetNewClass");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        /**
         * The menu generate method
         */
        this.setJMenuBar(menuBar);
        /**
         * The pane with null layout.
         */
        JFrame frame = new JFrame();
        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
        frame.setResizable(true);

        JPanel contentPane = new JPanel(null);
        /**
         * This is for the "Create" Button.
         */
        button1 = new JButton();
        button1.setBounds(299,316,90,35);
        button1.setLocation((((this.getWidth()-button1.getWidth())/2)+200),((this.getHeight()-button1.getHeight())/2)+150);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Create");
        button1.setVisible(true);
        /**
         * This is to get the response of the user.
         */
        button1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
                    String name = textfield2.getText() + "_" + textfield1.getText();
                    int numMeetings= -1;
                    int numStudents = -1;
                    int partWeight = -1;
                    int assWeight = -1;
                    int quizWeight = -1;
                    int examWeight = -1;
                    File dir = new File("./txtFiles");
                    dir.mkdir();
                    boolean checkpointReached=false;
                    /**
                     * Exception handling for the Date to ensure
                     * the user inputs the date in the correct format.
                     */  
                    try {
                    	if(textfield1.getText().length() != 8) {
                    		throw new Exceptions("Please change format of the date to MM-DD-YY");
                    	}
                    	if(textfield1.getText().charAt(2)!='-' || textfield1.getText().charAt(5)!='-') {
                    		throw new Exceptions("Please change format of the date to MM-DD-YY");
                    	}
                    	if(Integer.parseInt(textfield1.getText().substring(0,2)) > 12 || Integer.parseInt(textfield1.getText().substring(0,2)) < 1) {
                    		throw new Exceptions("Make sure its a valid month between 1-12");
                    	}
                    	if(Integer.parseInt(textfield1.getText().substring(3,5)) > 31 || Integer.parseInt(textfield1.getText().substring(3,5)) < 1) {
                    		throw new Exceptions("Make sure its a valid day btween 1-31");
                    	}
                    	if(Integer.parseInt(textfield1.getText().substring(6)) > 99 || Integer.parseInt(textfield1.getText().substring(6)) < 0) {
                    		throw new Exceptions("Make sure its a valid year between 00-99");
                    	}
                    }
                    catch(Exception t) {
                    	checkpointReached=true;
                    }
                    /**
                     * Exception handling for all other purposes.
                     */
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
                        try{

                            partWeight = Integer.parseInt(textfield5.getText());

                        } catch (Exception t){ new Exceptions("Please enter a number (0, 1, 2, etc.)\nfor Participation Weight.");
                            checkpointReached=true;}
                            if(checkpointReached==false)    
                        try{

                            examWeight = Integer.parseInt(textfield6.getText());

                        } catch (Exception t){ new Exceptions("Please enter a number (0, 1, 2, etc.)\nfor Assignment Weight.");
                            checkpointReached=true;}
                            if(checkpointReached==false)    
                        try{

                            assWeight = Integer.parseInt(textfield7.getText());

                        } catch (Exception t){ new Exceptions("Please enter a number (0, 1, 2, etc.)\nfor Quiz Weight.");
                            checkpointReached=true;}
                            if(checkpointReached==false)    
                        try{

                            quizWeight = Integer.parseInt(textfield8.getText());

                        } catch (Exception t){ new Exceptions("Please enter a number (0, 1, 2, etc.)\nfor Exam Weight.");
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
                        new NewClass(name, numStudents, numMeetings, partWeight,assWeight,quizWeight,examWeight);
                        frame.dispose();}

                }});
        /**
         * The "Back" Button.
         */
        JButton back = new JButton();
        back.setBounds(60,316,90,35);
        back.setLocation((((this.getWidth()-back.getWidth())/2)-235),((this.getHeight()-back.getHeight())/2)+150);
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
        /**
         * The "Create new Class" Header.
         */
        label1 = new JLabel();
        label1.setBounds(149,93,223,29);
        label1.setLocation((((this.getWidth()/2)-125)),(this.getHeight()/4));
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,24));
        label1.setText("Create a new Class");
        label1.setVisible(true);
        /**
         * The "Start Date in MM-DD-YY" text displayed.
         */
        label2 = new JLabel();
        label2.setBounds(55,206,195,33);
        label2.setLocation((((this.getWidth()-label2.getWidth())/2)-200),((this.getHeight()-label2.getHeight())/2)-100);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Start Date in MM-DD-YY");
        label2.setVisible(true);
        /**
         * The "Name of Class" text displayed.
         */
        label3 = new JLabel();
        label3.setBounds(314,206,90,35);
        label3.setLocation((((this.getWidth()-label3.getWidth())/2)+200),((this.getHeight()-label3.getHeight())/2)-100);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("Name of Class");
        label3.setVisible(true);
        /**
         * The "Number of Students" text displayed.
         */
        JLabel label4 = new JLabel();
        label4.setBounds(314,206,120,35);
        label4.setLocation((((this.getWidth()-label4.getWidth())/2-90)),((this.getHeight()-label4.getHeight())/2)-100);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("sansserif",0,12));
        label4.setText("Number of Students");
        label4.setVisible(true);
        /**
         * The "Number of Meetings" text displayed.
         */
        JLabel label5 = new JLabel();
        label5.setBounds(314,206,120,35);
        label5.setLocation((((this.getWidth()-label5.getWidth())/2+50)),((this.getHeight()-label5.getHeight())/2)-100);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(0,0,0));
        label5.setEnabled(true);
        label5.setFont(new Font("sansserif",0,12));
        label5.setText("Number of Meetings");
        label5.setVisible(true);
        /**
         * The "Participation Weight" text displayed.
         */
        JLabel label6 = new JLabel();
        label6.setBounds(55,206,195,33);
        label6.setLocation((((this.getWidth()-label6.getWidth())/2)-200),((this.getHeight()-label6.getHeight())/2));
        label6.setBackground(new Color(214,217,223));
        label6.setForeground(new Color(0,0,0));
        label6.setEnabled(true);
        label6.setFont(new Font("sansserif",0,12));
        label6.setText("Participation Weight");
        label6.setVisible(true);
        /**
         * The "Exam Weight" text displayed.
         */
        JLabel label7 = new JLabel();
        label7.setBounds(314,206,90,35);
        label7.setLocation((((this.getWidth()-label7.getWidth())/2)+200),((this.getHeight()-label7.getHeight())/2));
        label7.setBackground(new Color(214,217,223));
        label7.setForeground(new Color(0,0,0));
        label7.setEnabled(true);
        label7.setFont(new Font("sansserif",0,12));
        label7.setText("Exam Weight");
        label7.setVisible(true);
        /**
         * The "Assignment Weight" text displayed.
         */
        JLabel label8 = new JLabel();
        label8.setBounds(314,206,120,35);
        label8.setLocation((((this.getWidth()-label8.getWidth())/2-90)),((this.getHeight()-label8.getHeight())/2));
        label8.setBackground(new Color(214,217,223));
        label8.setForeground(new Color(0,0,0));
        label8.setEnabled(true);
        label8.setFont(new Font("sansserif",0,12));
        label8.setText("Assignment Weight");
        label8.setVisible(true);
        /**
         * The "Quiz Weight" text displayed.
         */
        JLabel label9 = new JLabel();
        label9.setBounds(314,206,120,35);
        label9.setLocation((((this.getWidth()-label9.getWidth())/2+75)),((this.getHeight()-label9.getHeight())/2));
        label9.setBackground(new Color(214,217,223));
        label9.setForeground(new Color(0,0,0));
        label9.setEnabled(true);
        label9.setFont(new Font("sansserif",0,12));
        label9.setText("Quiz Weight");
        label9.setVisible(true);
        /**
         * The "%" text displayed.
         */
        JLabel label10 = new JLabel();
        label10.setBounds(55,206,195,33);
        label10.setLocation((((this.getWidth()-label6.getWidth())/2-75)),((this.getHeight()-label6.getHeight())/2+30));
        label10.setBackground(new Color(214,217,223));
        label10.setForeground(new Color(0,0,0));
        label10.setEnabled(true);
        label10.setFont(new Font("sansserif",0,12));
        label10.setText("%");
        label10.setVisible(true);
        /**
         * The "%" text displayed.
         */
        JLabel label11 = new JLabel();
        label11.setBounds(314,206,90,35);
        label11.setLocation((((this.getWidth()-label7.getWidth())/2)+305),((this.getHeight()-label7.getHeight())/2+30));
        label11.setBackground(new Color(214,217,223));
        label11.setForeground(new Color(0,0,0));
        label11.setEnabled(true);
        label11.setFont(new Font("sansserif",0,12));
        label11.setText("%");
        label11.setVisible(true); 
        /**
         * The "%" text displayed.
         */
        JLabel label12 = new JLabel();
        label12.setBounds(314,206,120,35);
        label12.setLocation((((this.getWidth()-label8.getWidth())/2+30)),((this.getHeight()-label8.getHeight())/2+30));
        label12.setBackground(new Color(214,217,223));
        label12.setForeground(new Color(0,0,0));
        label12.setEnabled(true);
        label12.setFont(new Font("sansserif",0,12));
        label12.setText("%");
        label12.setVisible(true);
        /**
         * The "%" text displayed.
         */
        JLabel label13 = new JLabel();
        label13.setBounds(314,206,120,35);
        label13.setLocation((((this.getWidth()-label9.getWidth())/2+170)),((this.getHeight()-label9.getHeight())/2+30));
        label13.setBackground(new Color(214,217,223));
        label13.setForeground(new Color(0,0,0));
        label13.setEnabled(true);
        label13.setFont(new Font("sansserif",0,12));
        label13.setText("%");
        label13.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield1 = new JTextField();
        textfield1.setBounds(60,243,121,29);
        textfield1.setLocation((((this.getWidth()-textfield1.getWidth())/2)-235),((this.getHeight()-textfield1.getHeight())/2)-70);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield2 = new JTextField();
        textfield2.setBounds(60,243,121,29);
        textfield2.setLocation((((this.getWidth()-textfield2.getWidth())/2)+200),((this.getHeight()-textfield2.getHeight())/2)-70);
        textfield2.setBackground(new Color(255,255,255));
        textfield2.setForeground(new Color(0,0,0));
        textfield2.setEnabled(true);
        textfield2.setFont(new Font("sansserif",0,12));
        textfield2.setText("");
        textfield2.setVisible(true);
        /**
         * The "" for a space.
         */

        textfield3 = new JTextField();
        textfield3.setBounds(60,243,121,29);
        textfield3.setLocation((((this.getWidth()-textfield3.getWidth())/2-90)),((this.getHeight()-textfield3.getHeight())/2)-70);
        textfield3.setBackground(new Color(255,255,255));
        textfield3.setForeground(new Color(0,0,0));
        textfield3.setEnabled(true);
        textfield3.setFont(new Font("sansserif",0,12));
        textfield3.setText("");
        textfield3.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield4 = new JTextField();
        textfield4.setBounds(60,243,121,29);
        textfield4.setLocation((((this.getWidth()-textfield4.getWidth())/2+50)),((this.getHeight()-textfield4.getHeight())/2)-70);
        textfield4.setBackground(new Color(255,255,255));
        textfield4.setForeground(new Color(0,0,0));
        textfield4.setEnabled(true);
        textfield4.setFont(new Font("sansserif",0,12));
        textfield4.setText("");
        textfield4.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield5 = new JTextField();
        textfield5.setBounds(60,243,121,29);
        textfield5.setLocation((((this.getWidth()-textfield1.getWidth())/2)-235),((this.getHeight()-textfield1.getHeight())/2)+30);
        textfield5.setBackground(new Color(255,255,255));
        textfield5.setForeground(new Color(0,0,0));
        textfield5.setEnabled(true);
        textfield5.setFont(new Font("sansserif",0,12));
        textfield5.setText("");
        textfield5.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield6 = new JTextField();
        textfield6.setBounds(60,243,121,29);
        textfield6.setLocation((((this.getWidth()-textfield2.getWidth())/2)+200),((this.getHeight()-textfield2.getHeight())/2)+30);
        textfield6.setBackground(new Color(255,255,255));
        textfield6.setForeground(new Color(0,0,0));
        textfield6.setEnabled(true);
        textfield6.setFont(new Font("sansserif",0,12));
        textfield6.setText("");
        textfield6.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield7 = new JTextField();
        textfield7.setBounds(60,243,121,29);
        textfield7.setLocation((((this.getWidth()-textfield3.getWidth())/2-90)),((this.getHeight()-textfield3.getHeight())/2)+30);
        textfield7.setBackground(new Color(255,255,255));
        textfield7.setForeground(new Color(0,0,0));
        textfield7.setEnabled(true);
        textfield7.setFont(new Font("sansserif",0,12));
        textfield7.setText("");
        textfield7.setVisible(true);
        /**
         * The "" for a space.
         */
        textfield8 = new JTextField();
        textfield8.setBounds(60,243,121,29);
        textfield8.setLocation((((this.getWidth()-textfield4.getWidth())/2+50)),((this.getHeight()-textfield4.getHeight())/2)+30);
        textfield8.setBackground(new Color(255,255,255));
        textfield8.setForeground(new Color(0,0,0));
        textfield8.setEnabled(true);
        textfield8.setFont(new Font("sansserif",0,12));
        textfield8.setText("");
        textfield8.setVisible(true);
        /**
         * adding all the components to content Pane panel
         */  
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
        frame.add(label6);
        frame.add(label7);
        frame.add(label8);
        frame.add(label9);
        frame.add(textfield5);
        frame.add(textfield6);
        frame.add(textfield7);
        frame.add(textfield8);
        frame.add(label10);
        frame.add(label11);
        frame.add(label12);
        frame.add(label13);
        frame.add(contentPane);
        /**
         * adding panel to JFrame and setting of the window position
         * as well as the Closing operation
         */
        frame.add(contentPane);
        frame.setTitle("Student Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * the main method to generate the menu
     */
    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new SetNewClass();
                }
            });
    }
}
