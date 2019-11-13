import javax.swing.*;

public class Exceptions extends Exception{
    public Exceptions(String error){
        JOptionPane.showMessageDialog(null, error);

    }
}
