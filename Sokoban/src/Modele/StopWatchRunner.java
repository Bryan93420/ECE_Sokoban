package Modele;

import org.apache.commons.lang3.time.StopWatch;

import java.awt.event.*;
import javax.swing.*;

//import static javax.swing.TimerQueue.now;

public class StopWatchRunner extends JLabel
        implements MouseListener, ActionListener, WindowListener, KeyListener,
WindowStateListener, FocusListener {

    private static final long serialVersionUID = 1L;
    protected long startTime; // Start time of stopwatch.
// (Time is measured in milliseconds.)
    private boolean running; // True when the stopwatch is running.
    public StopWatch timer; // A timer that will generate events
// while the stopwatch is running



    public StopWatchRunner() {
// Constructor.
        super("Chrono", JLabel.CENTER);
//        addMouseListener(this);
//        timer = new Timer(100,this);
//        startTime = now();
        timer = new StopWatch();


    }


    public void actionPerformed(ActionEvent evt) {
// This will be called when an event from the
// timer is received. It just sets the stopwatch
// to show the amount of time that it has been running.
// Time is rounded down to the nearest second.
        long time = (System.currentTimeMillis() - startTime) / 1000;
        setText("" + time + " s");
    }


    public void mousePressed(MouseEvent evt) {
// React when user presses the mouse by
// starting or stopping the stopwatch. Also start
// or stop the timer.
        if (running == false) {
// Record the time and start the stopwatch.
            running = true;
            startTime = evt.getWhen(); // Time when mouse was clicked.
            setText(" 0 s");
            if (timer == null) {
//                timer = new Timer(100,this);
                timer.start();
            }
        }
        else {
// Stop the stopwatch. Compute the elapsed time since the
// stopwatch was started and display it.
            timer.stop();
            running = false;
            long endTime = evt.getWhen();
            double seconds = (endTime - startTime) / 1000.0;
            setText("Temps: " + seconds + " sec.");
        }
    }

    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }

    @Override
    public void windowOpened(WindowEvent e) {
        timer.start();
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        timer.start();
    }

    @Override
    public void focusGained(FocusEvent e) {
        timer.start();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }


// end Modele.StopWatchRunner

}