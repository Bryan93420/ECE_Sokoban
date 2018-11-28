import java.io.*;



public class Level {

    private static Level l;

    public static Level getInstance() {
        if (l == null) {
            l = new Level();
        }
        return l;
    }

    public static void initLevel(Plateau p, String level) throws FileNotFoundException, IOException {
        int[][] niveau = new int[19][10];
        //InputStream in = getResourceAsStream(level);
        FileInputStream in = new FileInputStream("Plateau.txt");
        BufferedReader flot = new BufferedReader(new InputStreamReader(in));
        int ligneCourante = 0;
        String ligne = flot.readLine();
        //while (ligne.charAt(0) != 'A') {
            for (int i = 0; i < 19; i++) {

                System.out.println(ligne.charAt(0));

                switch (ligne.charAt(i)) {
                    case '5':
                        niveau[ligneCourante][i] = new int(p, Case.PERSO);
                        break;
                    case '0':
                        niveau[ligneCourante][i] = new Case(p, Case.SOL);
                        break;
                    case '1':
                        niveau[ligneCourante][i] = new Case(p, Case.MUR);
                        break;
                    case '2':
                        niveau[ligneCourante][i] = new Case(p, Case.CAISSE);
                        break;
                    case '3':
                        niveau[ligneCourante][i] = new Case(p, Case.CAISSE_PLACEE);
                        break;
                    case '4':
                        niveau[ligneCourante][i] = new Case(p, Case.GOAL);
                        break;
                    case '6':
                        niveau[ligneCourante][i] = new Case(p, Case.PERSO_GOAL);
                        break;


                }//switch

            }//for

            /*
            for (int i = 0; i < 19; i++) {
                niveau[ligneCourante][i] = new Case(p, Case.SOL);
            }//for
*/
            ligneCourante++;
            ligne = flot.readLine();
       // }//while


        for (int i = ligneCourante; i < 19; i++) {
            for (int j = 0; j < 10; j++) {
                niveau[i][j] = new int[p.getSOL()][p.getMUR()];
            }//for
        }//for
        p.initPlateau(niveau);
        flot.close();
    }//initLevel


}