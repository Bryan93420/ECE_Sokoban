package Vue;

import Controleur.Plateau;
import Modele.LevelConfig;
import Modele.StopWatchRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class maVuePlateau extends JFrame implements KeyListener, ActionListener {
    JLabel labelTemps, labelScore, labelViewMoves, labelViewPushes;
    private Plateau currentPlateau, currentPlateau_saved;
    JPanel globalPanel;
    JMenuBar menubar;
    JMenuItem optionRestart;
    JMenuItem optionPause;
    JMenu menuGameOptions;

    ImageIcon imageSol;
    ImageIcon imageMur;
    ImageIcon imageCAISSE;
    ImageIcon imageCAISSE_PLACEE;
    ImageIcon imageGOAL;
    ImageIcon imagePERSO;
    ImageIcon imagePERSO_GOAL;

    int numberOfFirstLevelOnLaunched;
    String pathOfFirstLevelLaunched; // contient le chemin du niveau chargé, utile pour restart une partie
    int score;


    public maVuePlateau(Plateau plateau, boolean needACloneOfFisrtInstance, int Score) {


        score = Score;

        System.out.println("mon Score : " + score);
        globalPanel = new JPanel();

        if (needACloneOfFisrtInstance) {
            currentPlateau = (Plateau) plateau.clone();
            currentPlateau_saved = (Plateau) plateau.clone();
        } else {
            currentPlateau = plateau;
            currentPlateau_saved = (Plateau) currentPlateau.clone();
        }

        imageSol = createImageIcon("./img/sol.jpg", "sol");
        imageMur = createImageIcon("./img/mur.jpg", "mur");
        imageCAISSE = createImageIcon("./img/caisse.jpg", "caisse");
        imageCAISSE_PLACEE = createImageIcon("./img/caisse_placee.jpg", "caisse placée");
        imageGOAL = createImageIcon("./img/goal.jpg", "but");
        imagePERSO = createImageIcon("./img/perso.jpg", "personnage");
        imagePERSO_GOAL = createImageIcon("./img/perso_place.jpg", "personnage victorieux");

        GridLayout experimentLayout = new GridLayout(currentPlateau.levelConfigBoard.getLimLines(), currentPlateau.levelConfigBoard.getLimColumns());

        labelScore = new JLabel("   Score:" + score);
        labelTemps = new JLabel("   00:00:00");
        Timer SimpleTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //on découpe la chaine pour séparer les (Heure:Minute:Seconde) des (.nano-secondes)
                String[] time = currentPlateau.watch.timer.toString().split("[.]");
                //ici on récupère la première partie du tableau, qui contient (Heure:Minute:Seconde)
                labelTemps.setText("   " + time[0]);
            }
        });
        SimpleTimer.start();


        currentPlateau.watch = new StopWatchRunner();
        currentPlateau.watch.timer.start();
        currentPlateau.watch.setOpaque(true);
        this.add(globalPanel);

        menubar = new JMenuBar();
        menuGameOptions = new JMenu("   Options");
        labelViewMoves = new JLabel();
        labelViewPushes = new JLabel();
        optionRestart = new JMenuItem("Restart game");
        optionPause = new JMenuItem("Pause game");

        this.setJMenuBar(menubar);
        menubar.add(menuGameOptions);
        menuGameOptions.add(optionRestart);
        menuGameOptions.add(optionPause);
        menubar.add(labelViewMoves);
        menubar.add(labelViewPushes);
        menubar.add(labelScore);
        menubar.add(labelTemps);

        optionRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
//                on kill cet objet
                dispose();
//                on créer un nouvel objet identique à ce qu'on avait au départ
                System.out.println("restart: " + currentPlateau + " et " +
                        currentPlateau_saved + "===" + currentPlateau_saved.levelConfigBoard.limLines);
                LevelConfig newLevel = new LevelConfig(pathOfFirstLevelLaunched);
                Plateau newPlateau = new Plateau(newLevel, false);
                maVuePlateau newViewPlateau = new maVuePlateau(newPlateau, true, score);
                newViewPlateau.setPathOfFirstLevelLaunched(pathOfFirstLevelLaunched);
                newViewPlateau.setNumberOfFirstLevelOnLaunched(numberOfFirstLevelOnLaunched);

            }
        });

        optionPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {


                currentPlateau.watch.timer.suspend();
                //oui, on a besoin de mettre en pause
                JFrame frame = new JFrame("Parent");
                frame.setSize(Math.round(26 * currentPlateau.levelConfigBoard.limLines),
                        Math.round(26 * currentPlateau.levelConfigBoard.limColumns));
                frame.setVisible(true);

                final JDialog dialog = new JDialog(frame, "Child", true);
                dialog.setSize(Math.round(26 * currentPlateau.levelConfigBoard.limLines),
                        Math.round(26 * currentPlateau.levelConfigBoard.limColumns));
                dialog.setLocationRelativeTo(frame);
                JButton button = new JButton("Reprendre le jeu");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        frame.dispose();
                        currentPlateau.watch.timer.resume();
                    }
                });
                dialog.add(button);
                dialog.setUndecorated(true);
                dialog.setVisible(true);
            }
        });

        globalPanel.setLayout(new BorderLayout());
        globalPanel.setLayout(experimentLayout);

        //désactiver resize de la fenêtre
        this.setResizable(false);
        this.addKeyListener(this);
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
        labelViewMoves.setText("   Pas:" + currentPlateau.getNbPas());
        labelViewPushes.setText("   Pousses:" + currentPlateau.getNbPousses());

        menubar.add(menuGameOptions);
        menubar.add(labelViewMoves);
        menubar.add(labelViewPushes);
        this.globalPanel.updateUI();
    }

    /**
     * Retourne un ImageIcon seulement si l'image existe dans le répertoire.
     */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            ImageIcon imageIcon = new ImageIcon(imgURL, description);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back
            return imageIcon;
        } else {
            System.out.println("Le fichier n'a pas été trouvé: " + path);
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentPlateau.levelConfigBoard.isFinishedGame()) {
            return;
        }

        int keyCode = e.getKeyCode();
        switch (keyCode) {

            case KeyEvent.VK_UP:
                System.out.println("haut");
                currentPlateau.moveUp();
                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
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
                fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (currentPlateau.levelConfigBoard.isFinishedGame()) {
            try {
                String[] time = currentPlateau.watch.timer.toString().split(":");
                //ici on récupère la première partie du tableau, qui contient (Heure:Minute:Seconde)
                int heures = Integer.parseInt(time[0]); // on récupère les minutes
                int minutes = Integer.parseInt(time[1]); // on récupère les minutes
                System.out.println("ici heures:" + heures);
                System.out.println("ici minutes:" + minutes);

                int minutesEcoulees = 60 * heures + minutes;
                int localScore = (1000 - minutesEcoulees -
                        (2 * currentPlateau.getNbPas()) - (4 * currentPlateau.getNbPousses()));

                score += localScore;

                popup("C'est gagné !"
                        + "\nVotre temps :" + currentPlateau.watch.timer.toString()
                        + "\nNombre de pas :" + currentPlateau.getNbPas()
                        + "\nNombre de pousses :" + currentPlateau.getNbPousses()
                        + "\nVotre score pour ce niveau :" + localScore
                        + "\nVotre score cumulé :" + score
                        + "\nVoulez-vous jouer au niveau suivant ?");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("fin\n");
            return;
        }
    }

    public void popup(String messageToShow) throws IOException {
        globalPanel.removeAll();
        globalPanel.add(Box.createHorizontalStrut(15)); // a spacer

        //Boutons d'action fin de partie
        Object[] options = {"Oui, aller au prochain niveau !",
                "Réessayer celui-ci",
                "Je pars !"};

        int result = JOptionPane.showOptionDialog(this,
                messageToShow,
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        if (result == 0) { // si bouton de gauche clické

            dispose();
            //on découpe le chemin dès autour du mot "level" car chaque plateau a un nom du type "level.txt"
            String[] parsedFilePath = pathOfFirstLevelLaunched.split("level");
            String pathOfTheLevelWithFileExtension = parsedFilePath[0];
            String numberOfTheLevelWithFileExtension = parsedFilePath[parsedFilePath.length - 1];
//on retire l'extension .txt pour ne garder que le numéro

            int numberOfTheLevel = Integer.parseInt(
                    numberOfTheLevelWithFileExtension.substring
                            (0, numberOfTheLevelWithFileExtension.length() - 4));

            String nextLevel = pathOfTheLevelWithFileExtension + "level" + (numberOfTheLevel + 1) + ".txt";
            LevelConfig currentLevel = new LevelConfig(nextLevel);
            Plateau currentPlateau = new Plateau(currentLevel, false); // on choisira le niveau qu'on veut (ici le 1)
            maVuePlateau newVuePlateau = new maVuePlateau(currentPlateau,
                    false, score);
            newVuePlateau.setPathOfFirstLevelLaunched(pathOfFirstLevelLaunched);

        } else if (result == 1) { //bouton du milieu clické
            currentPlateau.restartGame();
            fillPlayableArray(currentPlateau.levelConfigBoard.levelArray);

        } else if (result == 2) { //bouton de droite clické
            System.exit(0);
        }

    }

    public void setPathOfFirstLevelLaunched(String pathOfFirstLevelLaunched) {
        this.pathOfFirstLevelLaunched = pathOfFirstLevelLaunched;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

