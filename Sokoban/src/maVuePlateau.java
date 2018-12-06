import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class maVuePlateau extends JFrame implements KeyListener {

    JPanel globalPanel;
    JMenuBar menubar;
    JMenuItem optionRestart;
    JMenu menuViewMoves, menuViewPushes, menuGameOptions;
    //    JLabel squares[][];
//    Frame frame;
    private Plateau currentPlateau;
    ImageIcon imageSol;
    ImageIcon imageMur;
    public ImageIcon imageCAISSE;
    ImageIcon imageCAISSE_PLACEE;
    ImageIcon imageGOAL;
    ImageIcon imagePERSO;
    ImageIcon imagePERSO_GOAL;

    public maVuePlateau(Plateau plateau) {

        currentPlateau = plateau;
        System.out.print(new File("").getAbsolutePath()+"\n");
        imageSol = createImageIcon("/img/sol.png","ez");
        imageMur = createImageIcon("/img/mur.gif","ez");
        imageCAISSE = createImageIcon("/img/caisse.gif","ez");
        imageCAISSE_PLACEE = createImageIcon("/img/caisse.gif","ez");
        imageGOAL = createImageIcon("/img/goal.gif","ez");
        imagePERSO = createImageIcon("/img/perso.gif","ez");
        imagePERSO_GOAL = createImageIcon("/img/perso_place.jpg","ez");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(currentPlateau.getLimLines(),currentPlateau.getLimColumns());
//        this.setLayout(experimentLayout);

//        System.out.print("lignes:" + plateau.limLines + " col:" + plateau.limColumns);
        globalPanel = new JPanel();
        this.add(globalPanel);

        menubar = new JMenuBar();
        menuViewMoves = new JMenu();
        menuViewPushes = new JMenu();
        menuGameOptions = new JMenu("Options");
        optionRestart = new JMenuItem("Restart");

        menuGameOptions.add(optionRestart);
        menubar.add(menuGameOptions);
        menubar.add(menuViewMoves);
        menubar.add(menuViewPushes);

        this.setJMenuBar(menubar);

        optionRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
//                System.exit(0);
            }
        });
//        globalPanel.add(menubar, BorderLayout.NORTH);
        globalPanel.setLayout(new BorderLayout());
        globalPanel.setLayout(experimentLayout);
//        globalPanel.setSize(500,500);

        //disable window resize
        this.setResizable(false);
        this.addKeyListener(this);

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };
        addWindowListener(l);

        fillPlayableArray(plateau.getArrayPlateau());
        this.setVisible(true);
    }

    private void fillPlayableArray(int[][] arrayPlateau) {
        this.globalPanel.removeAll();

        this.setSize(Math.round(26*arrayPlateau[0].length), Math.round(26*arrayPlateau.length));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < arrayPlateau.length ; i++) {
            for (int j = 0; j < arrayPlateau[i].length; j++) {

                //System.out.print("i" + i + " j:"+ j);
                if ( arrayPlateau[i][j] == 0) {
                    globalPanel.add(new JLabel(imageSol));
                }
                else if ( arrayPlateau[i][j] == 1) {
                    globalPanel.add(new JLabel(imageMur));
                }
                else if ( arrayPlateau[i][j] == 2) {
                    globalPanel.add(new JLabel(imageCAISSE));
                }
                else if ( arrayPlateau[i][j] == 3) {
                    globalPanel.add(new JLabel(imageCAISSE_PLACEE));
                }
                else if ( arrayPlateau[i][j] == 4) {
//                    squares[i][j] = new JLabel(imageGOAL);
                    globalPanel.add(new JLabel(imageGOAL));
                }
                else if ( arrayPlateau[i][j] == 5) {
//                    squares[i][j] = new JLabel(imagePERSO);
                    globalPanel.add(new JLabel(imagePERSO));

                }
                else if ( arrayPlateau[i][j] == 6) {
//                    squares[i][j] = new JLabel(imagePERSO_GOAL);
                    globalPanel.add(new JLabel(imagePERSO_GOAL));

                }
                else {
                    globalPanel.add(new JLabel(imagePERSO_GOAL));
                }
            }
        }
        this.add(globalPanel);


//        menubar.remove(menuGameOptions);
        menubar.remove(menuViewMoves);
        menubar.remove(menuViewPushes);
//        menuGameOptions = new JMenu("Options");
        menuViewMoves = new JMenu("Moves: " + currentPlateau.getNbPas());
        menuViewPushes = new JMenu("Pushes: " + currentPlateau.getNbPousses());
        menubar.add(menuGameOptions);
        menubar.add(menuViewMoves);
        menubar.add(menuViewPushes);

this.globalPanel.updateUI();
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {

            ImageIcon imageIcon = new ImageIcon(imgURL, description);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back


            return imageIcon;
        } else {
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("interface touchée");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch( keyCode ) {

            case KeyEvent.VK_UP:
                System.out.println("haut");
                currentPlateau.moveUp();
                fillPlayableArray(currentPlateau.getArrayPlateau());
//                SwingUtilities.updateComponentTreeUI(this);
//                this.repaint();
//                this.revalidate();


                break;
            case KeyEvent.VK_DOWN:
                System.out.println("bas");
                currentPlateau.moveBottom();
                fillPlayableArray(currentPlateau.getArrayPlateau());
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("gauche");
                currentPlateau.moveLeft();
                fillPlayableArray(currentPlateau.getArrayPlateau());
                break;

            case KeyEvent.VK_RIGHT :
                System.out.println("droite");
                currentPlateau.moveRight();
                fillPlayableArray(currentPlateau.getArrayPlateau());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("interface touchée");
    }



/*





















    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
//        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }










    public void TestLayout20() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblname = new JLabel("Name");
            gbc.gridx = 0;
            gbc.gridy = 0;

            add(lblname, gbc);

            JTextField textname = new JTextField(11);
            gbc.gridx = 1;
            gbc.gridy = 0;

            add(textname, gbc);

            JLabel lblEmail = new JLabel("Email ");
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(lblEmail, gbc);

            JTextField TextEmail = new JTextField(11);
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(TextEmail, gbc);

            JLabel lblgender = new JLabel("Gender");
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(lblgender, gbc);

            JTextField TextGender = new JTextField(11);
            gbc.gridx = 1;
            gbc.gridy = 2;
            add(TextGender, gbc);

            JButton New = new JButton("New");
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 12, 0, 0);
            gbc.gridx = 2;
            gbc.gridy = 0;
            add(New, gbc);

            JButton edit = new JButton("Edit");
            gbc.gridx = 2;
            gbc.gridy = 1;

            add(edit, gbc);

            JButton Gender = new JButton("Gender");
            gbc.gridx = 2;
            gbc.gridy = 2;

            add(Gender, gbc);

            JPanel pnlNav = new JPanel(new GridBagLayout());
            gbc.insets = new Insets(12, 0, 0, 0);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add(pnlNav, gbc);

            JTextField count = new JTextField(5);
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            pnlNav.add(count, gbc);

            JButton pre = new JButton("<<");
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            pnlNav.add(pre, gbc);

            JButton next = new JButton(">>");
            gbc.anchor = GridBagConstraints.EAST;
            gbc.gridx = 2;
            gbc.gridy = 0;
            pnlNav.add(next, gbc);

        }
    }

    */
}

