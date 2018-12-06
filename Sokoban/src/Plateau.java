import java.io.*;

public class Plateau extends LevelConfig {


    private int limColumns;
    private int limLines;
    private int nbPas;
    private int nbPousses;
    private int[][] arrayPlateau = new int[limLines][10];
    private boolean fin_partie = false;
    private LevelConfig levelGamePlate;

    public static int CASE;
    public static final int SOL = 0;
    public static final int MUR = 1;
    public static final int CAISSE = 2;
    public static final int CAISSE_PLACEE = 3;
    public static final int GOAL = 4;
    public static final int PERSO = 5;
    public static final int PERSO_GOAL = 6;



    public static final int CONSOLE = 1;
    public static final int GRAPHIQUE = 2;


    public Plateau(LevelConfig levelGamePlate){
        this.levelGamePlate = levelGamePlate;
        try {
            arrayPlateau = levelGamePlate.loadLevelFromFile(this);
            limColumns = levelGamePlate.getLimColumns();
            limLines = levelGamePlate.getLimLines();
            fin_partie = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void initPlateau(int c[][]) throws IOException {
//        limColumns = levelGamePlate.getLimColumns();
//        limLines = levelGamePlate.getLimLines();
//        fin_partie = false;
    }//initialiserPlateau



    public int[][] getArrayPlateau() {
        return arrayPlateau;
    }

    public static int getSOL() {
        return SOL;
    }

    public static int getMUR() {
        return MUR;
    }

    public static int getCAISSE() {
        return CAISSE;
    }

    public static int getCaissePlacee() {
        return CAISSE_PLACEE;
    }

    public static int getGOAL() {
        return GOAL;
    }

    public static int getPERSO() {
        return PERSO;
    }

    public static int getPersoGoal() {
        return PERSO_GOAL;
    }

    public static int getCASE() {
        return CASE;
    }


    public void moveUp() {

        int[] positionPerso = levelGamePlate.getPersoPosition();
        int[] positionCaisse = levelGamePlate.getCaissePosition();
//    System.out.println("perso : i:" + (positionPerso[0]+1) + " j:"+ (positionPerso[1]+1) + "\n"+
//            "limLines:" +limLines+ "limColumns:" +limColumns);
        if (positionPerso[0] > 0 ) {

            int typeObjectAbovePerso =
                    levelGamePlate.readPlateForSpecificPlace(
                            levelGamePlate.getLevelArray(),
                            (positionPerso[0] - 1),
                            positionPerso[1]);

            if (typeObjectAbovePerso == levelGamePlate.getMurPosition()[0]) {


//    System.out.println("objet au dessus : i:" + (positionPerso[0]) + " j:"+(positionPerso[1]+1) + " est: " + typeObjectAbovePerso + "\n");

                if(typeObjectAbovePerso == levelGamePlate.getCaissePosition()[0]){
                    //move the perso on the up case
                    levelGamePlate.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], getPERSO());
                    levelGamePlate.setLevelPlateMove((positionCaisse[0]) - 1, positionCaisse[1], getCAISSE());
//old case where perso was replaced by the up case
                //    levelGamePlate.setLevelPlateMove((positionPerso[0]), positionPerso[1], typeObjectAbovePerso);


                    nbPas++;
                    showPlate();
                    System.out.print("You moved up\n");
                }else {


//move the perso on the up case
                    levelGamePlate.setLevelPlateMove((positionPerso[0] - 1), positionPerso[1], getPERSO());
//old case where perso was is replaced by the up case
                    levelGamePlate.setLevelPlateMove((positionPerso[0]), positionPerso[1], typeObjectAbovePerso);


                    nbPas++;
                    showPlate();
                    System.out.print("You moved up\n");
                }
            }
            else{
                showPlate();
                System.out.print("Une caisse est juste au dessus !\n");
            }
            int[] positionPerso_VERIF = levelGamePlate.getPersoPosition();
//    System.out.println("VERIF : perso : i:" + (positionPerso_VERIF[0]+1) + " j:" + (positionPerso_VERIF[1]+1) + "\n");

//    System.out.println("VERIF : objet au dessus : i:" + (positionPerso_VERIF[0]+2) + " j:"+ (positionPerso_VERIF[1]+1)+ " est: " + typeObjectThatWASAbovePerso_VERIF + "\n");



        } else {
            showPlate();
            System.out.print("You've reached the edge ! You can't move up anymore\n");
        }
    }

    public void moveBottom() {

        int[] positionPerso = levelGamePlate.getPersoPosition();
        if (positionPerso[0] < limLines - 1) {


            int typeObjectBelowPerso =
                    levelGamePlate.readPlateForSpecificPlace(
                            levelGamePlate.getLevelArray(),
                            (positionPerso[0] + 1),
                            positionPerso[1]);

            if (typeObjectBelowPerso == levelGamePlate.getMurPosition()[0]) {

                levelGamePlate.setLevelPlateMove((positionPerso[0] + 1), positionPerso[1], getPERSO());
//old case where perso was is replaced by the up case
                levelGamePlate.setLevelPlateMove((positionPerso[0]), positionPerso[1], typeObjectBelowPerso);
                nbPas++;
                showPlate();
                System.out.print("You moved down\n");

            }else{
                showPlate();
                System.out.print("Une caisse est juste en dessous !\n");
            }

        } else {
            showPlate();
            System.out.print("You've reached the edge ! You can't move down anymore\n");
        }
    }

    public void moveLeft() {

        int[] positionPerso = levelGamePlate.getPersoPosition();
        // Si perso touche le bord du plateau
        if (positionPerso[1] > 0) {


            int typeObjectLeftPerso =
                    levelGamePlate.readPlateForSpecificPlace(
                            levelGamePlate.getLevelArray(),
                            (positionPerso[0]),
                            (positionPerso[1] - 1));

            if (typeObjectLeftPerso == levelGamePlate.getMurPosition()[1]) {

                levelGamePlate.setLevelPlateMove((positionPerso[0]), (positionPerso[1] - 1), getPERSO());
//old case where perso was is replaced by the up case
                levelGamePlate.setLevelPlateMove((positionPerso[0]), positionPerso[1], typeObjectLeftPerso);
                nbPas++;
                showPlate();
                System.out.print("You moved left\n");
            }
            else{
                showPlate();
                System.out.print("Une caisse est juste à gauche !\n");
            }

        } else {
            showPlate();
            System.out.print("You've reached the edge ! You can't move left anymore\n");
        }
    }

    public void moveRight() {

        int[] positionPerso = levelGamePlate.getPersoPosition();

        if (positionPerso[1] < limColumns - 1) {

            int typeObjectRightPerso = levelGamePlate.readPlateForSpecificPlace(
                    levelGamePlate.getLevelArray(),
                    (positionPerso[0]),
                    (positionPerso[1] + 1));


            if (typeObjectRightPerso == levelGamePlate.getMurPosition()[1]) {


                levelGamePlate.setLevelPlateMove((positionPerso[0]), (positionPerso[1] + 1), getPERSO());
//old case where perso was is replaced by the up case
                levelGamePlate.setLevelPlateMove((positionPerso[0]), positionPerso[1], typeObjectRightPerso);
                showPlate();
                System.out.print("You moved right\n");
            }else{
                showPlate();
                System.out.print("Une caisse est juste à droite !\n");
            }
        } else {
            showPlate();
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


    public int getLimLines() {
        return limLines;
    }

    public int getLimColumns() {
        return limColumns;
    }

    public int getNbPas() {
        return nbPas;
    }

    public int getNbPousses() {
        return nbPousses;
    }



    public void showPlate() {

        int[][] tempArray = levelGamePlate.getLevelArray();
//        System.out.print("longeur: " + tempArray);
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray[i].length; j++) {
                System.out.print(tempArray[i][j]);
            }
            System.out.print("\n");
        }
    }
}