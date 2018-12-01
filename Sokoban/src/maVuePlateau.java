import javax.swing.*;
import java.awt.event.*;

public class maVuePlateau extends JFrame {

    public maVuePlateau() {
        super("titre de l'application");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(200, 100);
        setVisible(true);
    }
}

