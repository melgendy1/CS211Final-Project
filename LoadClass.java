import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Represents the Load class for our grading system.
 * We use JFrame to help construct this.
 */
@SuppressWarnings("serial")
public class LoadClass extends JFrame{
	/**
	 * The Load class has many attributes.
	 * We Initialize them being private.
	 * Meaning they are accessible within the same class only.
	 */
	private JMenuBar menuBar;
    private JButton button1;
    @SuppressWarnings("rawtypes")
	private JComboBox combobox1;
    private JLabel label1;
    /**
     * The constructor for the Load class.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public LoadClass(){
        File folder = new File("./txtFiles");
        File[] files = folder.listFiles();
        this.setTitle("Load a Class");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        /**
         * The menu generate method.
         * @param is our menu bar.
         */
        this.setJMenuBar(menuBar);
        /**
         * The pane with the null layout.
         */
        JPanel contentPane = new JPanel(null);
        JFrame frame = new JFrame();
        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
        frame.setResizable(true);
        /**
         * The "Load" Button 
         * Along with its functionalities
         */
        button1 = new JButton();
        button1.setBounds(296,234,90,35);
        button1.setLocation(((((this.getWidth()-button1.getWidth())/2)+200)),((this.getHeight()/2)-100));
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Load");
        button1.setVisible(true);
        /**
         * if and when the user interacts with the Load button,
         * the program will be able to notice and run accordingly.
         */
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        if (combobox1.getSelectedIndex() < 0)
                            throw new Exception();

                        String name=combobox1.getSelectedItem().toString();
                        /**
                         * for the inputs of students,meetings,weights, etc
                         */
                        File file = new File("./txtFiles/"+name+".txt");
                        BufferedReader read = new BufferedReader(new FileReader(file));
                        String students =read.readLine();
                        String meetings =read.readLine();
                        String partWeight = read.readLine();
                        String assWeight = read.readLine();
                        String quizWeight = read.readLine();
                        String examWeight = read.readLine();
                        read.close();
                        /**
                         * to ensure the user inputs for these are integers
                         */
                        int numStudents = Integer.parseInt(students);
                        int numMeetings = Integer.parseInt(meetings);
                        int weightPart = Integer.parseInt(partWeight);
                        int weightAss = Integer.parseInt(assWeight);
                        int weightQuiz = Integer.parseInt(quizWeight);
                        int weightExam = Integer.parseInt(examWeight);
                        new LoadedClass(name,numStudents,numMeetings, weightPart, weightAss, weightQuiz, weightExam);
                        frame.dispose();

                    }
                    catch (Exception t){new Exceptions("You either forgot to save this file,\n you have not created your first file yet,\n or this file was saved empty.");}
                }

            });
        /**
         * The "Back" button
         * Along with its functionalities
         */
        JButton back = new JButton();
        back.setBounds(200,334,90,35);
        back.setLocation((((this.getWidth()-back.getWidth())/2)),((this.getHeight()/2)+100));
        back.setBackground(new Color(214,217,223));
        back.setForeground(new Color(0,0,0));
        back.setEnabled(true);
        back.setFont(new Font("sansserif",0,12));
        back.setText("Back");
        back.setVisible(true);
        back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new MainMenu();
                    frame.dispose();
                }
            });
        /**
         * the method for loading the created classes.
         */
        String[] txtFiles=new String[0];
        if(files!=null)
        { txtFiles = new String[files.length];
            for (int i=0;i<files.length;i++)
            {
                txtFiles[i]=files[i].getName().substring(0,files[i].getName().indexOf("."));
            }
            combobox1 = new JComboBox(txtFiles);}
        else
            combobox1 = new JComboBox();
        /**
         * The selection to pick which class to Load.
         * This serves as the drop down box with a list of all the files.
         */
        combobox1.setBounds(91,233,120,35);
        combobox1.setLocation(((((this.getWidth()-combobox1.getWidth())/2)-200)),((this.getHeight()/2)-100));
        combobox1.setBackground(new Color(214,217,223));
        combobox1.setForeground(new Color(0,0,0));
        combobox1.setEnabled(true);
        combobox1.setFont(new Font("sansserif",0,12));
        combobox1.setVisible(true);

        label1 = new JLabel();
        label1.setBounds(139,85,230,53);
        label1.setLocation((((this.getWidth()-label1.getWidth())/2)),(this.getHeight()/4));
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,24));
        label1.setText("Pick a class to load");
        label1.setVisible(true);
        /**
         * adding the components to content pane panel
         */
        contentPane.add(button1);
        contentPane.add(back);
        contentPane.add(combobox1);
        contentPane.add(label1);
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
                    new LoadClass();
                }
            });
    }

}

