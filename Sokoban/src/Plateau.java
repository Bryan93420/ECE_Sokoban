
import javafx.beans.Observable;

import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Array;

public class Plateau {




    private int Ligne;
    private int colonne;
    private int nbPas;
    private int nbPousses;



    private int[][] plateau = new int[19][10];

    private int[][] coordPerso = new int[1][1];
    private int lignePerso;
    private int colonnePerso;


    private boolean mur = false;
    private boolean caisse_goal = false;
    private boolean goal = false;


    public static int CASE;

    public static final int SOL = 0;
    public static final int MUR = 1;
    public static final int CAISSE = 2;
    public static final int CAISSE_PLACEE = 3;
    public static final int GOAL = 4;
    public static final int PERSO = 5;
    public static final int PERSO_GOAL = 6;

    private boolean fin_partie = false;


    public static final int CONSOLE = 1;
    public static final int GRAPHIQUE = 2;

    private Level l;

    public Plateau(Level l){
        this.l = l;
    }

    /*public void initPlateau(){
        try{
            nbPas = 0;
            nbPousses = 0;

            l.initLevel(plateau);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void initPlateau(int c[][]) throws IOException {

        Ligne = 0;
        colonne = 0;
        //reinit = true;
        for(int i =0; i < 19; i++) {
            for(int j=0; j < 10;j++) {
                if ((c[i][j]) == MUR) {
                    Ligne = i;
                    colonne = (j >= colonne)?j:colonne;
                }//if
                if (c[i][j] == PERSO || c[i][j] == PERSO_GOAL) {
                    lignePerso = i;
                    colonnePerso = j;
                }
               //this.plateau[i][j] = new int[i][j];
            }//for
        }//for
       //setChanged();
        //notifyObservers();
        fin_partie = false;
    }//initialiserPlateau






   /*public void print() {
        int i;
        int j;
        for(i=0;i<19;i++) {
            for (j = 0; j <10;j++) {
                System.out.print(plateau[i][j]);

            }
            System.out.print("\n");

        }

        System.out.println(i);
    }*/



    public void print() {

        System.out.print(getPlateau().toString());
        System.out.print("\n");

        }

    public int[][] getPlateau() {
        return plateau;
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



}
