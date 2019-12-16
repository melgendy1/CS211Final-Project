import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents the Main menu class for our grading system.
 * We use JFrame to help construct this.
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame {
	/**
	 * The Main Menu has a couple of attributes
	 * We initialize them being private.
	 * Meaning they are accessible within the same class only.
	 */
	private JButton button1;
    private JButton button2;
    private JLabel label1;
    /**
     * the constructor for the Main Menu
     */
    public MainMenu() {

        this.setTitle("Grading");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        /**
         * the pane with the null layout
         */
        JPanel contentPane = new JPanel(null);
        JFrame frame = new JFrame();
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setAlwaysOnTop(false);

        frame.setBackground(new Color(192, 192, 192));
        /**
         * the New Class Button
         * Along with its functionalities
         */
        button1 = new JButton();
        button1.setBounds(SwingConstants.CENTER+200, SwingConstants.CENTER, 160, 46);
        button1.setLocation((((this.getWidth()-button1.getWidth())/2)+200),(this.getHeight()-button1.getHeight())/2);
        button1.setBackground(new Color(214, 217, 223));
        button1.setForeground(new Color(0, 0, 0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif", Font.PLAIN, 12));
        button1.setText("New Class");
        button1.setVisible(true);
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new SetNewClass();
                    frame.dispose();
                }
            });
        /**
         * the Load Class Button
         * Along with its functionalities
         */
        button2 = new JButton();
        button2.setBounds(SwingConstants.CENTER+200, SwingConstants.CENTER, 160, 46);
        button2.setLocation((((this.getWidth()-button2.getWidth())/2)-200),(this.getHeight()-button2.getHeight())/2);
        button2.setBackground(new Color(214, 217, 223));
        button2.setForeground(new Color(0, 0, 0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif", 0, 12));
        button2.setText("Load Class");
        button2.setVisible(true);
        button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    new LoadClass();

                    frame.dispose();
                }
            });
        /**
         * the Select an option Header
         */
        label1 = new JLabel();
        label1.setBounds(390, 99, 600, 33);
        label1.setLocation((((this.getWidth()/2)-125)),(this.getHeight()/4));
        label1.setBackground(new Color(214, 217, 223));
        label1.setForeground(new Color(0, 0, 0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif", 0, 34));
        label1.setText("Select an option");
        label1.setVisible(true);
        /**
         * adding the components to content pane panel
         */
        frame.add(button1);
        frame.add(button2);
        frame.add(label1);
        /**
         * adding panel to JFrame and setting of the window position
         * Closing operation
         */
        frame.add(contentPane);
        frame.setTitle("Student Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
