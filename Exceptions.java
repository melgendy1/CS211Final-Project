import javax.swing.*;
/**
 * Represents the exceptions will be using.
 */
@SuppressWarnings("serial")
public class Exceptions extends Exception{
	/**
	 * The constructor for the error(s)
	 * @param holds error message(s) as a string
	 */
    public Exceptions(String error){
        JOptionPane.showMessageDialog(null, error);

    }
}
