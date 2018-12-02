import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LevelConfig {

    private int[][] levelArray;
    private int limColumns;
    private int limLines;
    protected boolean isPersoExist = false;
    public int[] readPlateForSpecificObject(int[][] levelGamePlate,
                                            int whichObjectLookingFor){


        int[] positionOfObject = new int[2];
        positionOfObject[0] = -1;
        positionOfObject[1] = -1;

        for(int i=0; i< levelGamePlate.length; i++) {
            for(int j=0; j < levelGamePlate[i].length; j++) {

                if(levelGamePlate[i][j] == whichObjectLookingFor) {
                    positionOfObject[0] = i;
                    positionOfObject[1] = j;
//                    System.out.print("i=" + positionOfObject[0] +
//                            "\nj="+positionOfObject[1]);
                    return positionOfObject;
                }
            }
        }

        return positionOfObject;
    }


    public int readPlateForSpecificPlace(int[][] levelGamePlate,
                                         int ligne_i, int column_j) {

        int typeOfObject = -1;
        //System.out.print(ligne_i + "|" + column_j);
        if (levelGamePlate[ligne_i][column_j] >= 0) {
            typeOfObject = levelGamePlate[ligne_i][column_j];
            return typeOfObject;
        }
        else{
            return typeOfObject;
        }
    }

    public int[] getPersoPosition() {
        return readPlateForSpecificObject(levelArray, Plateau.getPERSO());
    }

    public void setLevelPlateMove(int desire_j, int desire_i, int desire_value) {
        this.levelArray[desire_j][desire_i] = desire_value;
    }

    public int[][] loadLevelFromFile(Plateau internalCurrentPlateau) throws  IOException {


        FileInputStream azer = new FileInputStream("Plateau.txt");
        BufferedReader poiu = new BufferedReader(new InputStreamReader(azer));
        String aaa;
        List<Integer> countFileSizes = new ArrayList<>();
        while ( (aaa = poiu.readLine()) != null ){
            countFileSizes.add(aaa.length());
        }
//        System.out.print("\n:" + countFileSizes.size() + " taille=" + countFileSizes.get(0) +"\n");

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
//        System.out.print("lignes :" + limLines + "\ncolonnes : " + limColumns + "\n");
        levelArray = new int[limLines][limColumns];

        FileInputStream in = new FileInputStream("Plateau.txt");
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
                        levelArray[i][j] = internalCurrentPlateau.getPERSO();
                        isPersoExist = true;
                        break;
                    case '0':
                        levelArray[i][j] = internalCurrentPlateau.getSOL();
                        break;
                    case '1':
                        levelArray[i][j] = internalCurrentPlateau.getMUR();
                        break;
                    case '2':
                        levelArray[i][j] = internalCurrentPlateau.getCAISSE();
                        break;
                    case '3':
                        levelArray[i][j] = internalCurrentPlateau.getCaissePlacee();
                        break;
                    case '4':
                        levelArray[i][j] = internalCurrentPlateau.getGOAL();
                        break;
                    case '6':
                        levelArray[i][j] = internalCurrentPlateau.getPersoGoal();
                        break;

                    default:
                        System.out.print("\nInvalid data in source file !!! \nAt line:"
                                + (i + 1) + " column:" + (j + 1) + " data in error: \"" + readLine.charAt(j) + "\"");
                        System.exit(3);
                        break;
                }//switch

            }//for i
            i++;

        } // while j<limLines AND readBuffer not empty


//        internalCurrentPlateau.initPlateau(levelArray);
        in.close();
        flot.close();

        return levelArray;
    }//initLevel

    public int[][] getLevelArray() {
        return this.levelArray;
    }

    public int getLimColumns() {
        return limColumns;
    }

    public int getLimLines() {
        return limLines;
    }
}