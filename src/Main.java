import javax.swing.*;

/**
 * Created by Константин on 14.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        JFrame simpleWindow = new Window().createFrame("MainView");
        simpleWindow.setVisible(true);
        simpleWindow.setLocationRelativeTo(null);
        simpleWindow.setResizable(true);

    }
}
