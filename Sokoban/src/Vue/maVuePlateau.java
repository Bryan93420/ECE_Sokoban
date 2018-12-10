package Vue;

import Controleur.Main;
import Controleur.Plateau;
import Modele.LevelConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class maVuePlateau extends JFrame implements KeyListener, ActionListener {
    JLabel labelTemps;
    private Plateau currentPlateau, currentPlateau_saved;
    JPanel globalPanel;
    JMenuBar menubar;
    JMenuItem optionRestart;
    JMenuItem optionPause;
    JMenu menuViewMoves, menuViewPushes, menuGameOptions;
    //    boolean isAlreadyABoardLoaded;
    int numberOfFirstLevelOnLaunched;
    String pathOfFirstLevelLaunched; // contient le chemin du niveau chargé, utile pour restart une partie
    ImageIcon imageSol;
    ImageIcon imageMur;
    public ImageIcon imageCAISSE;
    ImageIcon imageCAISSE_PLACEE;
    ImageIcon imageGOAL;
    ImageIcon imagePERSO;
    ImageIcon imagePERSO_GOAL;
//    TestStopWatch chr;

    public maVuePlateau(Plateau plateau, boolean needACloneOfFisrtInstance) {

        globalPanel = new JPanel();
//        chr = new TestStopWatch();


        if(needACloneOfFisrtInstance){
            currentPlateau = (Plateau) plateau.clone();
            currentPlateau_saved = (Plateau) plateau.clone();
        }
        else{
            currentPlateau = plateau;
            currentPlateau_saved = (Plateau) currentPlateau.clone();
        }
System.out.println("construction: "+currentPlateau + " et " + currentPlateau_saved + "=============" +currentPlateau_saved.levelConfigBoard.limLines);

        System.out.print(new File("").getAbsolutePath() + "\n");
        imageSol = createImageIcon("./img/sol.jpg", "sol");
        imageMur = createImageIcon("./img/mur.jpg", "mur");
        imageCAISSE = createImageIcon("./img/caisse.jpg", "caisse");
        imageCAISSE_PLACEE = createImageIcon("./img/caisse_placee.jpg", "caisse placée");
        imageGOAL = createImageIcon("./img/goal.jpg", "but");
        imagePERSO = createImageIcon("./img/perso.jpg", "personnage");
        imagePERSO_GOAL = createImageIcon("./img/perso_place.jpg", "personnage victorieux");

        GridLayout experimentLayout = new GridLayout(currentPlateau.levelConfigBoard.getLimLines(), currentPlateau.levelConfigBoard.getLimColumns());
//        this.setLayout(experimentLayout);

//        System.out.print("lignes:" + plateau.limLines + " col:" + plateau.limColumns);










        labelTemps = new JLabel("0 secondes");
        Timer SimpleTimer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
    //on découpe la chaine pour séparer les (Heure:Minute:Seconde) des (.nano-secondes)
                String[] time = currentPlateau.watch.timer.toString().split("[.]");
    //ici on récupère la première partie du tableau, qui contient (Heure:Minute:Seconde)
                 labelTemps.setText(time[0]);
            }
        });
        SimpleTimer.start();


        currentPlateau.watch = new StopWatchRunner();
        currentPlateau.watch.timer.start();
//        watch.setFont(new Font("SansSerif", Font.BOLD, 24));
//        watch.setBackground(Color.LIGHT_GRAY);
//        watch.setForeground(new Color(180, 0, 0));
        currentPlateau.watch.setOpaque(true);
//        labelTemps = new JLabel(""+currentPlateau.watch.timer.getTime());
//        labelTemps.add();
        this.add(globalPanel);

        menubar = new JMenuBar();
        menuViewMoves = new JMenu();
        menuViewPushes = new JMenu();
        menuGameOptions = new JMenu("Options");
        optionRestart = new JMenuItem("Restart game");
        optionPause = new JMenuItem("Pause game");

        menuGameOptions.add(optionRestart);
        menuGameOptions.add(optionPause);
        menubar.add(menuGameOptions);
        menubar.add(menuViewMoves);
        menubar.add(menuViewPushes);
        menubar.add(labelTemps);

        this.setJMenuBar(menubar);


        optionRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
//                on kill cet objet
                dispose();
//                on créer un nouvel objet identique à ce qu'on avait au départ
                System.out.println("restart: "+currentPlateau + " et " + currentPlateau_saved + "===" +currentPlateau_saved.levelConfigBoard.limLines);
                LevelConfig newLevel = new LevelConfig(pathOfFirstLevelLaunched);
                Plateau newPlateau = new Plateau(newLevel, false);
                maVuePlateau newViewPlateau = new maVuePlateau(newPlateau, true);
                newViewPlateau.setPathOfFirstLevelLaunched(pathOfFirstLevelLaunched);
                newViewPlateau.setNumberOfFirstLevelOnLaunched(numberOfFirstLevelOnLaunched);

            }
        });
        optionPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
//                System.exit(0);
            }
        });

        globalPanel.setLayout(new BorderLayout());
        globalPanel.setLayout(experimentLayout);

        //disable window resize
        this.setResizable(false);
        this.addKeyListener(this);
//
//        WindowListener l = new WindowAdapter() {
//            public void windowClosing(WindowEvent e){
//                isAlreadyABoardLoaded = false;
//
//                System.exit(0);
////                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            }
//        };
////        addWindowListener(l);

        fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
        this.setVisible(true);
    }

    public void setNumberOfFirstLevelOnLaunched(int numberOfFirstLevelOnLaunched) {
        this.numberOfFirstLevelOnLaunched = numberOfFirstLevelOnLaunched;
    }

    protected void fillPlayableArray(int[][] arrayPlateau) {
        this.globalPanel.removeAll();

        this.setSize(Math.round(26 * arrayPlateau[0].length), Math.round(26 * arrayPlateau.length));

        for (int i = 0; i < arrayPlateau.length; i++) {
            for (int j = 0; j < arrayPlateau[i].length; j++) {

                //System.out.print("i" + i + " j:"+ j);
                if (arrayPlateau[i][j] == 0) {
                    globalPanel.add(new JLabel(imageSol));
                } else if (arrayPlateau[i][j] == 1) {
                    globalPanel.add(new JLabel(imageMur));
                } else if (arrayPlateau[i][j] == 2) {
                    globalPanel.add(new JLabel(imageCAISSE));
                } else if (arrayPlateau[i][j] == 3) {
                    globalPanel.add(new JLabel(imageCAISSE_PLACEE));
                } else if (arrayPlateau[i][j] == 4) {
//                    squares[i][j] = new JLabel(imageGOAL);
                    globalPanel.add(new JLabel(imageGOAL));
                } else if (arrayPlateau[i][j] == 5) {
//                    squares[i][j] = new JLabel(imagePERSO);
                    globalPanel.add(new JLabel(imagePERSO));

                } else if (arrayPlateau[i][j] == 6) {
//                    squares[i][j] = new JLabel(imagePERSO_GOAL);
                    globalPanel.add(new JLabel(imagePERSO_GOAL));

                } else {
                    globalPanel.add(new JLabel(imagePERSO_GOAL));
                }
            }
        }
        this.add(globalPanel);


//        menubar.remove(menuGameOptions);
        menubar.remove(menuViewMoves);
        menubar.remove(menuViewPushes);
//        menuGameOptions = new JMenu("Options");
        menuViewMoves = new JMenu("Moves: " + currentPlateau.getNbPas());
        menuViewPushes = new JMenu("Pushes: " + currentPlateau.getNbPousses());
        menubar.add(menuGameOptions);
        menubar.add(menuViewMoves);
        menubar.add(menuViewPushes);

        this.globalPanel.updateUI();
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {

            ImageIcon imageIcon = new ImageIcon(imgURL, description);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back


            return imageIcon;
        } else {
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("interface touchée");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentPlateau.levelConfigBoard.isFinishedGame()) {
            try {
                popup("C'est gagné !\n Voulez-vous jouer au niveau suivant ? ça marche pas :)");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("fin\n");
            return;
        }

        int keyCode = e.getKeyCode();


        switch (keyCode) {

            case KeyEvent.VK_UP:
                System.out.println("haut");
                currentPlateau.moveUp();
                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
//                SwingUtilities.updateComponentTreeUI(this);
//                this.repaint();
//                this.revalidate();


                break;
            case KeyEvent.VK_DOWN:
                System.out.println("bas");
                currentPlateau.moveBottom();

                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("gauche");
                currentPlateau.moveLeft();

                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("droite");
                currentPlateau.moveRight();
                System.out.print(
                        currentPlateau.levelConfigBoard.localisationGoals.toString() +
                                " ici il y a ça:" +
                                currentPlateau.levelConfigBoard.levelArray[3][10] + "\n");

                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (currentPlateau.levelConfigBoard.isFinishedGame()) {
            try {
                popup("C'est gagné !\n Voulez-vous jouer au niveau suivant ? ça marche pas :)");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("fin\n");
        }
    }

    public void popup(String messageToShow) throws IOException {
        globalPanel.removeAll();
        globalPanel.add(Box.createHorizontalStrut(15)); // a spacer
        globalPanel.add(new JLabel(messageToShow));


        //Custom button text
        Object[] options = {"Yes, go to the next level !",
                "Retry this level",
                "I quit !"};

        int result = JOptionPane.showOptionDialog(this,
                "Would you like some green eggs to go "
                        + "with that ham?",
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);


//        int result = JOptionPane.showConfirmDialog(null, globalPanel,
//                "Niveau terminé !", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) { // si bouton de gauche clické


            //on découpe le chemin dès autour du mot "level" car chaque plateau a un nom du type "level.txt"
            String[] parsedFilePath = pathOfFirstLevelLaunched.split("level");
            String pathOfTheLevelWithFileExtension = parsedFilePath[0];
            String numberOfTheLevelWithFileExtension = parsedFilePath[parsedFilePath.length - 1];
//on retire l'extension .txt pour ne garder que le numéro

            int numberOfTheLevel = Integer.parseInt(
                    numberOfTheLevelWithFileExtension.substring
                            (0, numberOfTheLevelWithFileExtension.length() - 4));
//
//
//            System.out.print("ezrtgfvdsfg1111: "+ pathOfFirstLevelLaunched);
//            System.out.print("ezrtgfvdsfg2222: "+ pathOfTheLevelWithFileExtension + "level"+(numberOfTheLevel+1) +".txt");
            String nextLevel = pathOfTheLevelWithFileExtension + "level" + (numberOfTheLevel + 1) + ".txt";
            LevelConfig currentLevel = new LevelConfig(nextLevel);
            Plateau currentPlateau = new Plateau(currentLevel, false); // on choisira le niveau qu'on veut (ici le 1)
            new maVuePlateau(currentPlateau, false);


        }else if (result == 1) { //bouton du milieu clické
            currentPlateau.restartGame();
            fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);

            System.out.print("retry");
        }else if (result == 2) { //bouton de droite clické
            System.exit(0);
        }
    }

    public void setPathOfFirstLevelLaunched(String pathOfFirstLevelLaunched) {
        this.pathOfFirstLevelLaunched = pathOfFirstLevelLaunched;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


//    public class TestStopWatch {

//        private void buildchiff() {
//
//            setVisible(true);
//            setTitle("jeu des chiffres et des lettres");
//            setSize(437, 600);
//            setLocationRelativeTo(null);
//            setResizable(false);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setContentPane(buildContentPane());
//        }

//        @SuppressWarnings("deprecation")
//        private Container buildContentPane() {
//            JPanel panel = new JPanel();
//            StopWatchRunner watch = new StopWatchRunner();
//            watch.setFont(new Font("SansSerif", Font.BOLD, 24));
//            watch.setBackground(Color.LIGHT_GRAY);
//            watch.setForeground(new Color(180, 0, 0));
//            watch.setOpaque(true);
//            panel.add(watch, BorderLayout.CENTER);

//            return panel;
//        }

//    }



//    public Object clone() {
//        maVuePlateau maPETITEvuePlateau = null;
//        try {
//            // On récupère l'instance à renvoyer par l'appel de la
//            // méthode super.clone()
//            maPETITEvuePlateau = (maVuePlateau) super.clone();
//        } catch(CloneNotSupportedException cnse) {
//            // Ne devrait jamais arriver car nous implémentons
//            // l'interface Cloneable
//            cnse.printStackTrace(System.err);
//        }
//
//        // On clone l'attribut de type Patronyme qui n'est pas immuable.
//        maPETITEvuePlateau.currentPlateau_saved = (Plateau) this.currentPlateau.clone();
//
//        // on renvoie le clone
//        return maPETITEvuePlateau;
//    }
}

