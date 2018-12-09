import java.util.Scanner;

public class Main {

    //    static boolean consoleMode = true;
    static boolean consoleMode = false;

    public static void main(String[] args) throws Exception {

        new MenuNiveaux();
//        Plateau currentPlateau = new Plateau("esdf"); // on choisira le niveau qu'on veut (ici le 1)
/*
        if(consoleMode) {
            currentPlateau.showBoardInConsole(consoleMode);

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
        }
        else{
            new MenuNiveaux();
//            new  maVuePlateau(currentPlateau);
//            consoleMode = true; // force l'affichage de la console même en mode Graphique, très utile pour débug

    }
    */
    }
}