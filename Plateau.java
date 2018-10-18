package sokoment;

import java.awt.event.KeyEvent;

public class Plateau {

    public void deplacementPerso(String chaineLue) {

        if (chaineLue.equals("z"))
            System.out.println("haut = tab[j-1][i]");

        else if (chaineLue.equals("q"))
            System.out.println("gauche = tab[j][i-1]");

        else if (chaineLue.equals("s"))
            System.out.println("bas = tab[j+1][i]");

        else if (chaineLue.equals("d"))
            System.out.println("droite = tab[j][i+1]");
        else
            System.out.println("Ceci n'est pas une direction valide !!!");

    }

}