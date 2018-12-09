import java.io.IOException;
import java.util.ArrayList;

public class Plateau  {

    public LevelConfig levelConfigBoard;
    private int nbPas;
    private int nbPousses;
    public boolean fin_partie = false;

    public static int CASE;
    public static final int SOL = 0;
    public static final int MUR = 1;
    public static final int CAISSE = 2;
    public static final int CAISSE_PLACEE = 3;
    public static final int GOAL = 4;
    public static final int PERSO = 5;
    public static final int PERSO_GOAL = 6;


    public Plateau( LevelConfig myBoard) {

        levelConfigBoard = myBoard;
//        levelConfigBoard.wichLevel = a;
        levelConfigBoard.setWichLevel(levelConfigBoard.wichLevel);
//        levelGamePlate = new LevelConfig();
        System.out.println("sélectionné la: " + levelConfigBoard.wichLevel);

    }


    public void moveUp() {
        levelConfigBoard.isFinishedGame(); // on check si tous les goals on étés remplis
        int[] positionPerso = levelConfigBoard.getPersoPosition();
//        System.out.print("ligne:" + positionPerso[0] + " colonnes:" + positionPerso[1]);
        if ((positionPerso[0] > 0) && (positionPerso[0] - 1 > 0)) { // if Perso on the gameboard and the case above too

            int typeObjectAbovePerso = levelConfigBoard.readBoardForSpecificPlace(
                    levelConfigBoard.levelArray, (positionPerso[0] - 1), positionPerso[1]);

            if (typeObjectAbovePerso == SOL) { //si SOL: on se déplace: intervertion de PERSO et SOL
                levelConfigBoard.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], PERSO);
                levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                levelConfigBoard.regenerateGoalsPositions();
                nbPas++;
            } else if (typeObjectAbovePerso == MUR) { //si MUR, on se déplace: intervertion de PERSO et SOL
                levelConfigBoard.regenerateGoalsPositions();
                nbPas++;
            } else if (typeObjectAbovePerso == CAISSE) { //si CAISSE, on se déplace: intervertion de PERSO et SOL

//on récupère le type d'objet qu'il y a derrière la caisse. Si c'est un SOL on fait l'action "bouger"
                if ((positionPerso[0] - 2) >= 0) { //if object above is on the gameboard
                    int typeObjectAboveObject =
                            levelConfigBoard.readBoardForSpecificPlace(
                                    levelConfigBoard.levelArray,
                                    (positionPerso[0] - 2),
                                    positionPerso[1]);

                    if ( typeObjectAboveObject != MUR && typeObjectAboveObject != CAISSE) {
                        //interdit de déplacer si un mur ou une caisse est derrière
                        //si CAISSE, on se déplace: intervertion de PERSO et SOL

                        if(levelConfigBoard.levelArray[(positionPerso[0] - 2)][(positionPerso[1])] == GOAL){
                            //Si l'objet derrière la caisse est un BUT,
                            // alors on met la caisse sur le but puis on fixe cette caisse a jamais
                            //pour cela on modifie le  tableau localisationGoals[Y_caisse][X_caisse]

                            int indexMatchedWithLocalisationGoals = levelConfigBoard.isThereGoalHere(
                                    (positionPerso[0] - 2) +";"+ (positionPerso[1]) + ";false");

                            System.out.print("Cet index:"+indexMatchedWithLocalisationGoals + " correspond a la case qu'on va définir comme CAISSE_GOAL");

                                if(indexMatchedWithLocalisationGoals >= 0) { // si le nombre correspond a
//                                    un index du tableau contenant les GOALs non-remplit
//on remplace le GOAL par un CAISSE_PLACEE
                                    levelConfigBoard.localisationGoals.set(
                                            indexMatchedWithLocalisationGoals,
                                            (positionPerso[0] - 2) + ";" + ((positionPerso[1]) + ";true"));

                                    //move the perso on the up case
                                    levelConfigBoard.setLevelPlateMove((positionPerso[0]) - 2, positionPerso[1], CAISSE_PLACEE);
                                    levelConfigBoard.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], PERSO);
                                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                                }
                        }
                        else{
                            //move the perso on the up case
                            levelConfigBoard.setLevelPlateMove((positionPerso[0]) - 2, positionPerso[1], CAISSE);
                            levelConfigBoard.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], PERSO);
                            levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                            levelConfigBoard.regenerateGoalsPositions();
                        }





                        showBoardInConsole(Main.consoleMode);
                        System.out.print("You moved up\n");
                        nbPas++;
                        nbPousses++;
                    }
                }
                else {
                    System.out.print("You can't move this box like this");
                    levelConfigBoard.regenerateGoalsPositions();
                }
            } else if (typeObjectAbovePerso == GOAL) { //si GOAL, on déplace uniquement le Perso
                // SURTOUT ON NE regenerateGoalsPositions(); PAS !!! Sinon ça efface le perso,
                // car il se trouve sur les coordonnées originales d'un goal
                levelConfigBoard.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], PERSO);
                levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                nbPas++;
            }
            else if  (typeObjectAbovePerso == CAISSE_PLACEE) {
                System.out.print("You can't move a well placed box\n");

            }
            else {
//                regenerateGoalsPositions();
                System.out.print("You've reached the edge ! You can't move up anymore\n");
            }

        }
    }

    public void moveBottom() {

        int[] positionPerso = levelConfigBoard.getPersoPosition();
        int[] positionCaisse = levelConfigBoard.getCaissePosition();
        if (positionPerso[0] < levelConfigBoard.limLines - 1) {


            int typeObjectBelowPerso =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0] + 1),
                            positionPerso[1]);

            int typeObjectBelowObject =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0] + 2),
                            positionPerso[1]);




            System.out.println("Cet objet est" + typeObjectBelowObject);

            if (typeObjectBelowPerso != MUR) {

                if(typeObjectBelowPerso == CAISSE && typeObjectBelowObject != MUR) {


                    levelConfigBoard.setLevelPlateMove((positionPerso[0]) + 2, positionPerso[1], CAISSE);
                    levelConfigBoard.setLevelPlateMove((positionPerso[0] + 1), positionPerso[1], PERSO);


//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                    nbPas++;
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved down\n");

                }else if (typeObjectBelowPerso == CAISSE && typeObjectBelowObject == MUR) {



                }else{


                    levelConfigBoard.setLevelPlateMove((positionPerso[0] + 1), positionPerso[1], PERSO);

//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);


                    nbPas++;
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved down\n");
                }

            }else{
                showBoardInConsole(Main.consoleMode);
                System.out.print("Une caisse est juste en dessous !\n");
            }


        } else {
            showBoardInConsole(Main.consoleMode);
            System.out.print("You've reached the edge ! You can't move down anymore\n");
        }
    }

    public void moveLeft() {

        int[] positionPerso = levelConfigBoard.getPersoPosition();
        int[] positionCaisse = levelConfigBoard.getCaissePosition();
        // Si perso touche le bord du plateau
        if (positionPerso[1] > 0) {


            int typeObjectLeftPerso =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0]),
                            (positionPerso[1] - 1));

            int typeObjectLeftObject =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0]),
                            positionPerso[1] - 2);

            if (typeObjectLeftPerso != MUR ) {

                if(typeObjectLeftPerso == CAISSE && typeObjectLeftObject != MUR) {
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1] - 2, CAISSE);
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), (positionPerso[1] - 1), PERSO);

//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                    nbPas++;
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved left\n");
                }else if(typeObjectLeftPerso == CAISSE && typeObjectLeftObject == MUR)    {

                }else{
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1] - 1, PERSO);
//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);


                    nbPas++;
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved left\n");
                }


            }
            else{
                showBoardInConsole(Main.consoleMode);
                System.out.print("Une caisse est juste à gauche !\n");
            }

        } else {
            showBoardInConsole(Main.consoleMode);
            System.out.print("You've reached the edge ! You can't move left anymore\n");
        }
    }

    public void moveRight() {

        int[] positionPerso = levelConfigBoard.getPersoPosition();
        int[] positionCaisse = levelConfigBoard.getCaissePosition();

        if (positionPerso[1] < levelConfigBoard.limColumns - 1) {

            int typeObjectRightPerso = levelConfigBoard.readBoardForSpecificPlace(
                    levelConfigBoard.levelArray,
                    (positionPerso[0]),
                    (positionPerso[1] + 1));

            int typeObjectRightObject =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0]),
                            positionPerso[1] + 2);
            int typeObjectRightGoal =
                    levelConfigBoard.readBoardForSpecificPlace(
                            levelConfigBoard.levelArray,
                            (positionPerso[0]),
                            positionPerso[1] + 2);


            if (typeObjectRightPerso != MUR) {

                if(typeObjectRightPerso == CAISSE && typeObjectRightObject != MUR) {

                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1] + 2, CAISSE);
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), (positionPerso[1] + 1), PERSO);

//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved right\n");


                }else if (typeObjectRightPerso == CAISSE && typeObjectRightObject == MUR){

                }
                else{
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1] + 1, PERSO);
//old case where perso was is replaced by the up case
                    levelConfigBoard.setLevelPlateMove((positionPerso[0]), positionPerso[1], SOL);


                    nbPas++;
                    showBoardInConsole(Main.consoleMode);
                    System.out.print("You moved right\n");
                }
            }else{
                showBoardInConsole(Main.consoleMode);
                System.out.print("Une caisse est juste à droite !\n");
            }
        } else {
            showBoardInConsole(Main.consoleMode);
            System.out.print("You've reached the edge ! You can't move right anymore\n");
        }
    }

    public void deplacementPerso(String chaineLue) {

        if (chaineLue.equals("z")) {
            moveUp();
            System.out.println("Moves :" +nbPas+ "\nPushes: "+nbPousses);
        } else if (chaineLue.equals("q")) {
            moveLeft();
            System.out.println("Moves :" +nbPas+ "\nPushes: "+nbPousses);
        } else if (chaineLue.equals("s")) {
            moveBottom();
            System.out.println("Moves :" +nbPas+ "\nPushes: "+nbPousses);
        } else if (chaineLue.equals("d")) {
            moveRight();
            System.out.println("Moves :" +nbPas+ "\nPushes: "+nbPousses);
        }
        else {
            System.out.println("Invalid move !!!");
        }
    }



    public int getNbPas() {
        return nbPas;
    }

    public int getNbPousses() {
        return nbPousses;
    }



    public void showBoardInConsole(boolean isConsoleMode) {

        if (isConsoleMode) {
            int[][] tempArray = levelConfigBoard.levelArray;
//        System.out.print("longeur: " + tempArray);
            for (int i = 0; i < tempArray.length; i++) {
                for (int j = 0; j < tempArray[i].length; j++) {
                    System.out.print(tempArray[i][j]);
                }
                System.out.print("\n");
            }
        }
    }


    public void setNbPas(int nbPas) {
        this.nbPas = nbPas;
    }

    public void setNbPousses(int nbPousses) {
        this.nbPousses = nbPousses;
    }

    public void restartGame() throws IOException {

        fin_partie = false;
        setNbPas(0);
        setNbPousses(0);
        levelConfigBoard.isPersoExist = false;
//        levelArray = savedBasePlateau.clone();

//on clone le tableau sauvegardé au lancement du jeu. On le réinjecte dans le tableau qui sert de plateau
        for( int i=0 ; i < levelConfigBoard.savedBasePlateau.length; i++) {
            for( int j=0 ; j < levelConfigBoard.savedBasePlateau[i].length; j++) {
                levelConfigBoard.levelArray[i][j] = levelConfigBoard.savedBasePlateau[i][j];
            }
        }


        levelConfigBoard.localisationGoals = (ArrayList<String>) levelConfigBoard.savedLocalisationGoals.clone();

    }
}