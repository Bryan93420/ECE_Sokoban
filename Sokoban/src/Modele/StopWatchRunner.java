package Modele;

import org.apache.commons.lang3.time.StopWatch;
import javax.swing.*;


public class StopWatchRunner extends JLabel{

    public StopWatch timer; // A timer that will generate events

    public StopWatchRunner() {
        super("Chrono", JLabel.CENTER);
        timer = new StopWatch();
    }

}