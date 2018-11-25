import javax.swing.text.PlainDocument;
import java.io.*;
import java.util.*;

public class VuePlateau extends FileNotFoundException{

    public static void main(String args[]){
        HashMap<Integer, String> plateau = new HashMap<Integer, String>();
        int Case;

        int tableau[][] = new int[10][10];

        plateau.put(0,"SOL");
        plateau.put(1,"MUR");
        plateau.put(2,"CAISSE");
        plateau.put(3,"CAISSEPLACEE");
        plateau.put(4,"GOAL");
        plateau.put(5,"PERSO");

        Set set = plateau.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();

           /* for(int i=0; i < 1; i++){
                for(int j=0; j < 9; j++){
                    //if (i == 0 && j == 0){
                        System.out.print(tableau[i][j]);
                        //System.out.println(tableau[0][j]);
                   // }
                }
                System.out.println(0);
            }*/
        }

        try {
            File file = new File("Plateau.txt");
            BufferedReader bis = new BufferedReader(new FileReader(file));
            //FileOutputStream fos = new FileOutputStream(new File("destin.txt"));
            //BufferedOutputStream bos = new BufferedOutputStream(fos);


            String data;
            while ((data = bis.readLine()) != null) {

                //for (String chaine : data.split("\\s+")){
                   //Case = Integer.valueOf(chaine);
                    //tableau.add(Case);
                    System.out.println(data);
                    bis.close();
              //  }
                //Case.add();


                //bos.close()
                // ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void initialiser_plateau(){

    }



}



