import javax.swing.*;
import java.awt.*;

public class maVuePlateau extends JFrame {

    ImageIcon monImage;
    JPanel squares[][];
    Frame frame;
    private Plateau currentPlateau;
    ImageIcon imageSol;
    ImageIcon imageMur;
    ImageIcon imageCAISSE;
    ImageIcon imageCAISSE_PLACEE;
    ImageIcon imageGOAL;
    ImageIcon imagePERSO;
    ImageIcon imagePERSO_GOAL;

    public maVuePlateau(Plateau plateau) {
        imageSol = createImageIcon("C:/Users/Steph/IdeaProjects/ECE_Sokoban_28_nov/Sokoban/img/mur.gif","ez");
        imageMur = createImageIcon("/img/mur.gif","ez");
        imageCAISSE = createImageIcon("../img/caisse.gif","ez");
        imageCAISSE_PLACEE = createImageIcon("../img/caisse_placee.gif","ez");
        imageGOAL = createImageIcon("../img/goal.gif","ez");
        imagePERSO = createImageIcon("../img/perso.gif","ez");
        imagePERSO_GOAL = createImageIcon("../img/perso_goal.gif","ez");


        frame = new Frame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        System.out.print("lignes:" + plateau.limLines + " col:" + plateau.limColumns);
        squares = new JPanel[plateau.getLimLines()][plateau.getLimColumns()];
        frame.setLayout(new GridLayout(8, 8));

//        WindowListener l = new WindowAdapter() {
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        };
//        addWindowListener(l);
        int[][] arrayFigures;
        arrayFigures = plateau.getArrayPlateau();
        this.frame.setSize(arrayFigures.length*40, arrayFigures[0].length*30);

        for (int i = 0; i < arrayFigures.length ; i++) {
            for (int j = 0; j < arrayFigures[i].length; j++) {
                squares[i][j] = new JPanel();


                if ( arrayFigures[i][j] == 0) {
                    squares[i][j].setBackground(Color.darkGray);
                    squares[i][j].add(new JLabel(imageSol));
                }
                else if ( arrayFigures[i][j] == 1) {
                    squares[i][j].setBackground(Color.darkGray);
                    squares[i][j].add(new JLabel(imageMur));
                }
                else if ( arrayFigures[i][j] == 2) {
                    System.out.print("g");
                    squares[i][j].setBackground(Color.green);
                    squares[i][j].add(new JLabel(imageCAISSE));
                }
                else if ( arrayFigures[i][j] == 3) {
                    System.out.print("g");
                    squares[i][j].setBackground(Color.green);
                    squares[i][j].add(new JLabel(imageCAISSE_PLACEE));
                }
                else if ( arrayFigures[i][j] == 4) {
                    System.out.print("g");
                    squares[i][j].setBackground(Color.green);
                    squares[i][j].add(new JLabel(imageGOAL));
                }
                else if ( arrayFigures[i][j] == 5) {
                    System.out.print("g");
                    squares[i][j].setBackground(Color.green);
                    squares[i][j].add(new JLabel(imagePERSO));
                }
                else if ( arrayFigures[i][j] == 6) {
                    System.out.print("g");
                    squares[i][j].setBackground(Color.green);
                    squares[i][j].add(new JLabel(imagePERSO_GOAL));
                }
                else {
                    System.out.print("o");
                    squares[i][j].setBackground(Color.orange);
                    squares[i][j].add(new JLabel(monImage));
                }
                frame.add(squares[i][j]);
            }
        }
//        squares[5][4].add(new JLabel(monImage));
        frame.setVisible(true);
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

