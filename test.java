package sokoment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

public class test extends JFrame {


String arg;

/*
    public test(){

        arg = "C:\\Users\\Steph\\Desktop\\ic_sokoban_6.gif";

        JPanel panel = new JPanel();
        panel.setSize(500,640);
        panel.setBackground(Color.CYAN);
        ImageIcon icon = new ImageIcon(arg);
        JLabel label = new JLabel();
        label.setIcon(icon);
        panel.add(label);
        this.getContentPane().add(panel);
    }


    public static void main(String[] args) {
        new test().setVisible(true);
    }
*/


    public test() {
        super("titre de l'application");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);

        ImageIcon img = new ImageIcon("C:\\Users\\Steph\\Desktop\\ic_sokoban_6.gif");
        ImageIcon img2 = new ImageIcon("C:\\Users\\Steph\\Desktop\\ic_sokoban_2.gif");
        JButton bouton = new JButton(null, img);
        JButton bouton2 = new JButton(null, img2);
        JPanel panneau = new JPanel();
        panneau.add(bouton);
        panneau.add(bouton2);
        setContentPane(panneau);
        setSize(200, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new test();
    }





/*
    public test() {
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

    public static void main(String[] args) {
        JFrame frame = new test();
    }
    
    */
}