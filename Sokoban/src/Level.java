import java.io.*;




public class Level {

    private static Level l;

    public static Level getInstance() {
        if (l == null) {
            l = new Level();
        }
        return l;
    }

    public static void initLevel(Plateau p) throws  IOException {
        int[][] niveau = new int[19][12];
        //InputStream in = getResourceAsStream(level);
        FileInputStream in = new FileInputStream("Plateau.txt");
        BufferedReader flot = new BufferedReader(new InputStreamReader(in));
        int ligneCourante = 0;
        String ligne = flot.readLine();
        //while (ligne.charAt(0) != 'A') {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 19; j++) {


                    //System.out.println(ligne.charAt(0));


                    switch (ligne.charAt(i)) {
                        case '5':
                            niveau[ligneCourante][i] = p.getPERSO();
                            break;
                        case '0':
                            niveau[ligneCourante][i] = p.getSOL();
                            break;
                        case '1':
                            niveau[ligneCourante][i] = p.getMUR();
                            break;
                        case '2':
                            niveau[ligneCourante][i] = p.getCAISSE();
                            break;
                        case '3':
                            niveau[ligneCourante][i] = p.getCaissePlacee();
                            break;
                        case '4':
                            niveau[ligneCourante][i] = p.getGOAL();
                            break;
                        case '6':
                            niveau[ligneCourante][i] = p.getPersoGoal();
                            break;

                    }//switch
                    System.out.print(ligne.charAt(i));
                    System.out.print(ligne.charAt(j));
                    //System.out.print(ligne.charAt(j));
                }//for

            }
            /*
            for (int i = 0; i < 19; i++) {
                niveau[ligneCourante][i] = new Case(p, Case.SOL);
            }//for
*/
            ligneCourante++;
            ligne = flot.readLine();
       // }//while



        p.initPlateau(niveau);
        flot.close();
    }//initLevel

    public void print(){


        for(int i = 0; i < 19; i++){
            for(int j = 0; j<10; j++){

            }
        }
    }

}