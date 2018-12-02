import javax.swing.*;
import java.util.Scanner;

public class Main {

//static boolean console = true;
static boolean console = false;

    public static void main(String[] args)throws Exception {
        Level level = new Level();
        Plateau p = new Plateau(level);
        level.initializationLevel(p);
        p.showPlate();

        if(console) {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Déplacez vous à l'aide des touches ZQSD");
                Scanner scanner = new Scanner(System.in);
                String chaineLue = scanner.next();

                if (chaineLue.length() <= 0) {
                    System.out.println("Veuillez saisir une direction !!!");
                } else if (chaineLue.length() == 1) {
                    p.deplacementPerso(chaineLue);
                } else {
                    System.out.println("Impossible d'avoir plusieurs directions pour la même poussée !!!");
                }
            }
        }
        else{
            JFrame frame = new maVuePlateau();
        }
    }
}
