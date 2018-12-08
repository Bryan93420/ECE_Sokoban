import javax.swing.*;
import java.io.File;

public class MenuNiveaux extends  JFrame{
    JPanel globalPanel;
    JMenuBar menubar;

    public MenuNiveaux() {

        File folder = new File("src/img");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        }
    }
}
