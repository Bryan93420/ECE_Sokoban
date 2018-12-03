import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class maVuePlateau extends JFrame {

    ImageIcon monImage;
    JPanel squares[][];
    Frame frame;
    private Plateau currentPlateau;
    ImageIcon imageSol;
    ImageIcon imageMur;
    public ImageIcon imageCAISSE;
    ImageIcon imageCAISSE_PLACEE;
    ImageIcon imageGOAL;
    ImageIcon imagePERSO;
    ImageIcon imagePERSO_GOAL;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    public maVuePlateau(Plateau plateau) {

        System.out.print(new File("").getAbsolutePath()+"\n");
        imageSol = createImageIcon("/img/sol.png","ez");
        imageMur = createImageIcon("/img/mur.gif","ez");
        imageCAISSE = createImageIcon("/img/caisse.gif","ez");
        imageCAISSE_PLACEE = createImageIcon("/img/caisse.gif","ez");
        imageGOAL = createImageIcon("/img/goal.gif","ez");
        imagePERSO = createImageIcon("/img/perso.gif","ez");
        imagePERSO_GOAL = createImageIcon("/img/perso.gif","ez");


//        width=10;
//        height=10;
//        for(x=0;x<10;x++)
//        {
//            for(y=0;y<10;y++)
//            {
//                g.drawRect(x*width,y*height,width,height);
//            }
//        }

        frame = new Frame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        System.out.print("lignes:" + plateau.limLines + " col:" + plateau.limColumns);
        squares = new JPanel[plateau.getLimLines()][plateau.getLimColumns()];
        frame.setLayout(new GridLayout(8, 8));
//        frame.setResizable(false);

//        WindowListener l = new WindowAdapter() {
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        };
//        addWindowListener(l);
        int[][] arrayFigures;
        arrayFigures = plateau.getArrayPlateau();

        float ratio =  arrayFigures.length / arrayFigures[0].length;
        this.frame.setSize(Math.round(500*ratio), Math.round(500*ratio));
        GridLayout experimentLayout = new GridLayout(arrayFigures.length,arrayFigures[0].length);
//        GridBagConstraints c = new GridBagConstraints();
        frame.setLayout(experimentLayout);

        for (int i = 0; i < arrayFigures.length ; i++) {
            for (int j = 0; j < arrayFigures[i].length; j++) {
                squares[i][j] = new JPanel(new GridBagLayout());




//                compsToExperiment.setLayout(experimentLayout);

//                squares[i][j] = new JPanel();
//                c.gridx = 1;
//                c.gridy = 1;
//                squares[i][j].setSize(50,50);

                if ( arrayFigures[i][j] == 0) {
                    squares[i][j].add(new JLabel(imageSol));
                }
                else if ( arrayFigures[i][j] == 1) {
                    squares[i][j].add(new JLabel(imageMur));
                }
                else if ( arrayFigures[i][j] == 2) {
                    squares[i][j].add(new JLabel(imageCAISSE));
                }
                else if ( arrayFigures[i][j] == 3) {
                    squares[i][j].add(new JLabel(imageCAISSE_PLACEE));
                }
                else if ( arrayFigures[i][j] == 4) {
                    squares[i][j].add(new JLabel(imageGOAL));
                }
                else if ( arrayFigures[i][j] == 5) {
                    squares[i][j].add(new JLabel(imagePERSO));
                }
                else if ( arrayFigures[i][j] == 6) {
                    squares[i][j].add(new JLabel(imagePERSO_GOAL));
                }
                else {
                    System.out.print("o");
                    squares[i][j].setBackground(Color.orange);
                    squares[i][j].add(new JLabel(monImage));
                }
                frame.add(squares[i][j]);
            }
        }
//        squares[5][4].add(new JLabel(monImage));
        frame.setVisible(true);
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

