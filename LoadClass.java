import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.*;

public class LoadClass extends JFrame{
    private JMenuBar menuBar;
    private JButton button1;
    private JComboBox combobox1;
    private JLabel label1;

    /*
    *Constructor
    */
    public LoadClass(){
        File folder = new File("./txtFiles");
        File[] files = folder.listFiles();
        
        /*
        * Setting title and size for the page
        */
        
        this.setTitle("Load a Class");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //menu generate method

        this.setJMenuBar(menuBar);
        /*
        *pane with null layout
        */
        JPanel contentPane = new JPanel(null);
        JFrame frame = new JFrame();
        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
        frame.setResizable(true);
        
        /*
        * Setting the location, colors, font, and text size for the button
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

        /*
        * This handles an error message that a user might come across also adds information into the combo box.
        */
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        if (combobox1.getSelectedIndex() < 0)
                            throw new Exception();

                        String name=combobox1.getSelectedItem().toString();

                        File file = new File("./txtFiles/"+name+".txt");
                        BufferedReader read = new BufferedReader(new FileReader(file));
                        String students =read.readLine();
                        String meetings =read.readLine();
                        read.close();
                        int numStudents = Integer.parseInt(students);
                        int numMeetings = Integer.parseInt(meetings);
                        new LoadedClass(name,numStudents,numMeetings);
                        frame.dispose();

                    }
                    catch (Exception t){new Exceptions("You either forgot to save this file,\n you have not created your first file yet,\n or this file was saved empty.");}
                }

            });
            
        /*
        * Setting the bounds for the 'back' button
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
        
        /*
        * This reads the text files and if there is nothing inside the folder, it gives an empty combo box.
        * Otherwise it makes a combo box that is essentially a drop down menu. It will contain the name of the text file
        * Then you have the option to click on it and it redirects you to said text file.
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

        /*
        *adding components to contentPane panel
        */
        contentPane.add(button1);
        contentPane.add(back);
        contentPane.add(combobox1);
        contentPane.add(label1);
        

        /*
        *adding panel to JFrame and seting of window position and close operation
        */
        frame.add(contentPane);
        frame.setTitle("Student Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /*
    *method for generate menu
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

