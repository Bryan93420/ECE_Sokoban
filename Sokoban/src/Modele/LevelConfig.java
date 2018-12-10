package Modele;

import Controleur.Plateau;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LevelConfig implements Cloneable{

    public int[][] levelArray;
    public int[][] savedBasePlateau;
    public ArrayList<String> localisationGoals, savedLocalisationGoals;
    public int limColumns;
    public int limLines;
    public boolean isPersoExist = false;
    public String wichLevel;


    public LevelConfig(String aqzsed){

        this.wichLevel = aqzsed;
        try {
            levelArray = this.loadLevelFromFile(wichLevel);
            limColumns = this.getLimColumns();
            limLines = this.getLimLines();
            savedBasePlateau = new int[limLines][limColumns];
            localisationGoals = new ArrayList<String>();
            savedLocalisationGoals = new ArrayList<String>();

            for( int i=0 ; i < levelArray.length; i++) {

                for( int j=0 ; j < levelArray[i].length; j++) {

                    savedBasePlateau[i][j] = levelArray[i][j];

                    if(levelArray[i][j] == Plateau.GOAL){
                        localisationGoals.add(i + ";" + j + ";false"); // false car on définit le goal comme non-completed
                    }
                }
                System.out.print("\n");
            }

            savedLocalisationGoals = (ArrayList<String>) localisationGoals.clone();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int[] readBoardForSpecificObject(int[][] levelGameBoard, int whichObjectLookingFor){


        int[] positionOfObject = new int[2];
        positionOfObject[0] = -1;
        positionOfObject[1] = -1;

        for(int i=0; i< levelGameBoard.length; i++) {
            for(int j=0; j < levelGameBoard[i].length; j++) {

                if(levelGameBoard[i][j] == whichObjectLookingFor) {
                    positionOfObject[0] = i;
                    positionOfObject[1] = j;
                    return positionOfObject;
                }
            }
        }
        return positionOfObject;
    }


    public int readBoardForSpecificPlace(int[][] levelGameBoard, int ligne_i, int column_j){

        int typeOfObject = -1;
        if (levelGameBoard[ligne_i][column_j] >= 0) {
            typeOfObject = levelGameBoard[ligne_i][column_j];
            return typeOfObject;
        }
        else{
            return typeOfObject;
        }
    }

    public int[] getPersoPosition() {
        return readBoardForSpecificObject(levelArray, Plateau.PERSO);
    }
    public int[] getPersoGoalPosition() {
        return readBoardForSpecificObject(levelArray, Plateau.PERSO_GOAL);
    }
    public int[] getMurPosition(){ return readBoardForSpecificObject(levelArray, Plateau.MUR); }
    public int[] getCaissePosition(){ return readBoardForSpecificObject(levelArray, Plateau.CAISSE); }
    public int[] getCaissePlaceePosition(){ return readBoardForSpecificObject(levelArray, Plateau.CAISSE_PLACEE); }

    public void setLevelPlateMove(int desire_j, int desire_i, int desire_value) {
        this.levelArray[desire_j][desire_i] = desire_value;
    }

    public void setWichLevel(String wichLevel) {
        this.wichLevel = wichLevel;
    }

    public int[][] loadLevelFromFile(String aqs) throws  IOException {


//ici on vérifie l'intégrité d'un fichier.txt plateau. On vérifie que c'est bien un rectangle parfait
        FileInputStream azer = new FileInputStream(aqs);
        BufferedReader poiu = new BufferedReader(new InputStreamReader(azer));
        String aaa;
        List<Integer> countFileSizes = new ArrayList<>();
        while ( (aaa = poiu.readLine()) != null ){
            countFileSizes.add(aaa.length());
        }

        limLines = countFileSizes.size() ;
        for ( int i = 0; i < countFileSizes.size() ; i++){

            if(countFileSizes.get(0) !=  countFileSizes.get(i)){
                System.out.print("Execution STOPPED because file source has inequals columns size\n");
                //stop the app because file source as inequals columns size
                System.exit(4);
            }
            else{
                limColumns = countFileSizes.get(0);
            }
        }
        azer.close();
        poiu.close();

        levelArray = new int[limLines][limColumns];
        //Puis on remplit un tableau qui servira de plateau de jeu
        FileInputStream in = new FileInputStream(aqs);
        BufferedReader flot = new BufferedReader(new InputStreamReader(in));
        String readLine;
        int i = 0, j;

        while( i < limLines && ( (readLine = flot.readLine() ) != null )){
            for (j = 0; j < readLine.length() ; j++) {
                switch (readLine.charAt(j)) {

                    case '5':
                        if(isPersoExist){
                            System.out.print("Error in source file ! Because the Personnage already exists.");
                            System.exit(5);
                        }
                        levelArray[i][j] = Plateau.PERSO;
                        isPersoExist = true;
                        break;

                    case '0':
                        levelArray[i][j] = Plateau.SOL;
                        break;
                    case '1':
                        levelArray[i][j] = Plateau.MUR;
                        break;
                    case '2':
                        levelArray[i][j] = Plateau.CAISSE;
                        break;
                    case '3':
                        levelArray[i][j] = Plateau.CAISSE_PLACEE;
                        break;
                    case '4':
                        levelArray[i][j] = Plateau.GOAL;
                        break;
                    case '6':
                        levelArray[i][j] = Plateau.PERSO_GOAL;
                        break;

                    default:
                        System.out.print("\nInvalid data in source file !!! \nAt line:"
                                + (i + 1) + " column:" + (j + 1) + " data in error: \"" + readLine.charAt(j) + "\"");
                        System.exit(3);
                        break;
                }//switch

            }//for j
            i++;

        } // while i<limLines et que buffer n'est pas vide
//on ferme le buffer
        in.close();
        flot.close();

        return levelArray;
    }


    public int[][] getLevelArray() {
        return levelArray;
    }

    public int getLimColumns() {
        return limColumns;
    }

    public int getLimLines() {
        return limLines;
    }

    //fonction qui replace tous les goals à l'endroits où il étaient au lancement du jeu.
    // le tableau localisationGoals est rempli au lancement du jeu et contient toutes les coordonnées des goals
    // dans un tableau de String
    public void regenerateGoalsPositions(){
        for (String oneGoalPosition: localisationGoals  ){

            String[] parts = oneGoalPosition.split(";"); //les coordonnées d'un goal sont séparées par ; .
            // La fonction split sépare une String (au niveau du séparateur) et génère un tableau
            int lineToModify = Integer.parseInt(parts[0]); // line
            int columnToModify = Integer.parseInt(parts[1]); // column
            levelArray[lineToModify][columnToModify] = Plateau.GOAL;
        }
    }

    public int isThereGoalHere(String searchedLocalisation){
        System.out.println(searchedLocalisation);

        for (String oneGoalPosition: localisationGoals  ){
            if( oneGoalPosition.equals(searchedLocalisation)){
                System.out.println(oneGoalPosition);

                int index = localisationGoals.indexOf(oneGoalPosition);
                System.out.print("\nindex:" + index+"\n");
                return index;
            }
        }
        return -1;
    }


    public int countUncompletedGoals() {

        int numberOfUncompletedGoals = 0;

        for (String oneGoalPosition : localisationGoals) {

            String[] parts = oneGoalPosition.split(";"); //les coordonnées d'un goal sont séparées par ; .
            // La fonction split sépare une String (au niveau du séparateur) et génère un tableau
            int lineToModify = Integer.parseInt(parts[0]); // line
            int columnToModify = Integer.parseInt(parts[1]); // column
            boolean isCompletedGoal = Boolean.parseBoolean((parts[2])); // column

            if (isCompletedGoal == false) {
                numberOfUncompletedGoals++;
            }
        }
        System.out.println("nb de goal restants:" + numberOfUncompletedGoals);
        return numberOfUncompletedGoals;
    }

    public boolean isFinishedGame() {
        if (countUncompletedGoals() <= 0) {
            System.out.print("OKAY GAGNE");
            return true;
        } else
            return false;
    }

    public Object clone() {
        Object o = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la méthode super.clone()
            o = super.clone();
        } catch(CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car on implémente l'interface Cloneable
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return o;
    }
}