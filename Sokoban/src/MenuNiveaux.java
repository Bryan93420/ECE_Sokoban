import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

public class MenuNiveaux {

    maVuePlateau aze;
    boolean isAlreadyABoardLoaded;
    public MenuNiveaux() {

        JList displayList = new JList(new File("./Sokoban/lvl").listFiles());
        displayList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        displayList.setCellRenderer(new MyCellRenderer());
        displayList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        displayList.setName("Liste des niveaux disponibles");
        JFrame f = new JFrame("Liste des niveaux disponibles");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(500, 300));
        displayList.setVisibleRowCount(-1);

        if(displayList == null){
            //pas de niveau a charger car le dossier est vide !!!
        }
        else {
            f.add(new JScrollPane(displayList));

            displayList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    String oneFile = displayList.getSelectedValue().toString().replace("\\", "/");

                    //on découpe le chemin dès autour du mot "level" car chaque plateau a un nom du type "level.txt"
                    String[] parseFilePath = oneFile.split("level");
                    String numberOfTheLevelWithFileExtension = parseFilePath[parseFilePath.length - 1];
//on retire l'extension .txt pour ne garder que le numéro
                    int numberOfTheLevel = Integer.parseInt(
                            numberOfTheLevelWithFileExtension.substring
                                    (0, numberOfTheLevelWithFileExtension.length() - 4));


                    System.out.print("ezrtgfvdsfg: " + numberOfTheLevel);
                    //si aucune vue n'a déjà été créée, alors on peut en créer une
                    if (!isAlreadyABoardLoaded) {
                        LevelConfig currentLevel = new LevelConfig(oneFile);
                        Plateau currentPlateau = new Plateau(currentLevel, false); // on choisira le niveau qu'on veut (ici le 1)

                        if (Main.consoleMode) {
                            currentPlateau.showBoardInConsole(Main.consoleMode);

                            for (int i = 0; i < 1000; i++) {
                                System.out.println("You can move with ZQSD keys");
                                Scanner scanner = new Scanner(System.in);
                                String chaineLue = scanner.next();

                                if (chaineLue.length() <= 0) {
                                    System.out.println("Please choose a direction !!!");
                                } else if (chaineLue.length() == 1) {
                                    currentPlateau.deplacementPerso(chaineLue);
                                } else {
                                    System.out.println("Unable to have several directions for an unique move !!!");
                                }
                            }
                            isAlreadyABoardLoaded = true;

                        } else {
                            aze = new maVuePlateau(currentPlateau, false);
                            aze.setNumberOfFirstLevelOnLaunched(numberOfTheLevel);
                            aze.setPathOfFirstLevelLaunched(oneFile);


                            //quand une vue est créée, on bascule la variable pour ne pas en charger d'autres
                            //sinon il y a plusieurs jeux en même temps !!
                            isAlreadyABoardLoaded = true;
                            //listener qui écoute la fermture de la vue: alors on basculera la variable pour autoriser
                            // la création d'une nouvelle vue de plateau
                            WindowListener l = new WindowAdapter() {
                                public void windowClosing(WindowEvent e) {
                                    isAlreadyABoardLoaded = false;
                                }
                            };
                            aze.addWindowListener(l);
//                            aze.addComponentListener();
//                    Main.consoleMode = true; // force l'affichage de la console même en mode Graphique, très utile pour débug

                        }
                    }
                    else{
                        //une instance existe de maVuePlateau existe déjà
                        System.out.println("on va voir ça: " +aze.pathOfFirstLevelLaunched);
                    }

//                System.out.println("sélectionné: " + oneFile);
                    return;
                }
            }

            );
        }
        f.pack();
        f.setVisible(true);

    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                MenuNiveaux fITJL = new MenuNiveaux();
//            }
//        });
//    }

    private static class MyCellRenderer extends DefaultListCellRenderer  {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof File) {
                File file = (File) value;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }
    }








    /* import java.awt.*;
import javax.swing.*;

public class TestStopWatch extends JPanel {

public void init() {




}


}*/

}