package sokoment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;

public class premiere extends JPanel {

    public static void main(String[] args) {



        /*


         super("titre de l'application");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(200,100);
        setVisible(true);
    }

    public static void main(String [] args){
        JFrame frame = new swing1();
    }



         */

        Plateau monPlateau = new Plateau();
        vuePlateau maVuePlateau = new vuePlateau();
        JFrame frame = new JFrame("Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(maVuePlateau, java.awt.BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.addKeyListener(maVuePlateau);

// affiche des images contenues dans des JButton
//                new test();

//        File file = new File("C:\\Users\\Steph\\IdeaProjects\\ECE_Sokoban\\niveaux\\niveau_1.txt");
//        BufferedReader br;
//        try {
//            br = new BufferedReader(new FileReader(file));
//            String st;
//            while ((st = br.readLine()) != null) {
//                System.out.println(st);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Déplacez vous à l'aide des touches ZQSD");
            Scanner scanner = new Scanner(System.in);
            String chaineLue = scanner.next();

            if (chaineLue.length() <= 0) {
                System.out.println("Veuillez saisir une direction !!!");
            } else if (chaineLue.length() == 1) {
                monPlateau.deplacementPerso(chaineLue);
            } else {
                System.out.println("Impossible d'avoir plusieurs directions pour la même poussée !!!");
            }
        }

    }
}