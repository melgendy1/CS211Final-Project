/**
 * Exception class that handles any exception and provides
 * the related dialog box to the user
 */


import javax.swing.*;

public class Exceptions extends Exception{
    public Exceptions(String error){
        JOptionPane.showMessageDialog(null, error);
        // pops up a standard dialog box that prompts the user
    }
}
