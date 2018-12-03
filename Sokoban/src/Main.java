import javax.swing.*;
import java.util.Scanner;

public class Main {

//    static boolean consoleMode = true;
    static boolean consoleMode = false;

    public static void main(String[] args)throws Exception {
        LevelConfig currentLevel = new LevelConfig();
        Plateau currentPlateau = new Plateau(currentLevel);

        if(consoleMode) {
            currentPlateau.showPlate();

            for (int i = 0; i < 1000; i++) {
                System.out.println("Youcan move with ZQSD keys");
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
            JFrame frame = new maVuePlateau(currentPlateau);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setResizable(false);

//            ((maVuePlateau) frame).TestLayout20();

        }
    }
}