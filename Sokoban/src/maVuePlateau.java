import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class maVuePlateau extends JFrame {

    ImageIcon monImage;
    JLabel label;
    JPanel squares[][];


    public maVuePlateau() {

        super("Sokoban");

//        WindowListener l = new WindowAdapter() {
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        };
        setSize(500,500);
//        addWindowListener(l);

        monImage = createImageIcon("/footer.png","ez");

        label = new JLabel(monImage);
        add(label, BorderLayout.CENTER);

        setVisible(true);

    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }
}

