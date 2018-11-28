public class Main {


    public static void main(String[] args)throws Exception {
        Level level = new Level();
        Plateau p = new Plateau(level);

        p.initPlateau();
        p.print();



    }

}
