package GUI.Panels;


import Backend.Maze;
import Backend.algoritms.Algoritm;
import Backend.algoritms.AlgoritmBFS;
import Backend.algoritms.AlgoritmDFS;
import Backend.save.SaveMaze;
import GUI.Category;
import GUI.Frame;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EditionPanel extends JPanel {
    private static final JSlider rows = new JSlider(1, 40, 4);
    private static final String rowsText = "Liczba rzędów: ";
    private static final JLabel rowsNumber = new JLabel(rowsText);
    private static final JSlider columns = new JSlider(1, 70, 4);
    private static final String columnsText = "Liczba kolumn: ";
    private static final JLabel columnsNumber = new JLabel(columnsText);
    private final JComboBox<String> algoritms = new JComboBox<>();
    private final JButton start = new JButton("Start");
    private final JButton clean = new JButton("Resetuj");
    private static final JButton save = new JButton("Zapisz");
    private final JButton exit = new JButton("Powrót");
    private final Dimension buttonsSize = new Dimension(200, 50);
    private final ImageIcon image;
    private int x = 0;
    private int y = 0;
    private static int existingInstances = 0;
    private static Maze maze;
    boolean writing;
    public static boolean running;
    int minThreads;

    private int fieldSize = 20;


    private AlgoritmBFS algoritmBFS = new AlgoritmBFS();
    private AlgoritmDFS algoritmDFS = new AlgoritmDFS();


    public EditionPanel(int width, int height, ImageIcon image) {
        this.image = image;
        maze = new Maze(columns.getValue(), rows.getValue());

        algoritms.addItem("BTS algprytm");
        algoritms.addItem("DTS algprytm");

        columnsNumber.setOpaque(true);
        columnsNumber.setText(columnsText + columns.getValue());
        rowsNumber.setOpaque(true);
        rowsNumber.setText(rowsText + rows.getValue());

        algoritms.setBounds(10, 10, buttonsSize.width, buttonsSize.height);
        start.setBounds(220, 10, buttonsSize.width, buttonsSize.height);
        clean.setBounds(430, 10, buttonsSize.width, buttonsSize.height);
        save.setBounds(640, 10, buttonsSize.width, buttonsSize.height);
        exit.setBounds(850, 10, buttonsSize.width, buttonsSize.height);

        Color color =new Color(255, 255, 230);
        algoritms.setBackground(color);
        start.setBackground(color);
        clean.setBackground(color);
        save.setBackground(color);
        exit.setBackground(color);

        add(columns);
        add(columnsNumber);
        add(rows);
        add(rowsNumber);
        add(algoritms);
        add(start);
        add(clean);
        add(save);
        add(exit);


        columns.addChangeListener(e -> {
            columnsNumber.setText(columnsText + columns.getValue());
            maze.setBoard(columns.getValue(), rows.getValue());
            repaint();
        });

        rows.addChangeListener(e -> {
            rowsNumber.setText(rowsText + rows.getValue());
            maze.setBoard(columns.getValue(), rows.getValue());
            repaint();
        });
        // TODO: 21.02.2023 sprobowac zaimplementowac analize zdjecia 

        start.addActionListener(e -> {
            running=true;
            maze.clearPath();
            existingInstances=0;
            int choice = algoritms.getSelectedIndex();
            if (choice == 0) startAlgoritm(algoritmBFS);
            else if (choice == 1) startAlgoritm(algoritmDFS);
        });


        clean.addActionListener(e -> {
            running=false;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
            while (Thread.activeCount()>minThreads){

            }
            maze.setBoard(columns.getValue(), rows.getValue());

            repaint();
            columns.setEnabled(true);
            rows.setEnabled(true);

            columns.setVisible(true);
            columnsNumber.setVisible(true);
            rows.setVisible(true);
            rowsNumber.setVisible(true);
        });


        save.addActionListener(e -> new Thread(new Runnable() {
            @Override
            public void run() {
                new SaveMaze(maze.getBoard());
            }
        }).start());



        exit.addActionListener(e -> {
            running=false;
            while (Thread.activeCount()>minThreads){

            }
            maze.setBoard(columns.getValue(), rows.getValue());
            Main.changePanel();
            maze.clear();
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1 || e.getButton() == 3) {
                    writing = true;
                    columns.setVisible(false);
                    columnsNumber.setVisible(false);
                    rows.setVisible(false);
                    rowsNumber.setVisible(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (writing) {
                                Point clickPoint = new Point(getMousePosition());
                                int column = (int) ((clickPoint.x - x) / fieldSize);
                                int row = (int) ((clickPoint.y - y) / fieldSize);

                                try {
                                    if (e.getButton() == 1) {
                                        maze.setField(1, column, row);
                                    } else if (e.getButton() == 3) {
                                        maze.setField(0, column, row);
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                }

                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                writing = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        minThreads=Thread.activeCount();
    }


    public synchronized static void changeInstancesNumber(int i) {
        existingInstances+=i;
    }


    public static int getColumns() {
        return columns.getValue();
    }

    public static void setSaveBTN(Color color) {
        save.setBackground(color);
    }

    public static void setNewBoard(ArrayList<ArrayList<Integer>> board) {
        columns.setVisible(false);
        columns.setValue(board.size());
        columnsNumber.setVisible(false);
        rows.setVisible(false);
        rows.setValue(board.get(0).size());
        rowsNumber.setVisible(false);

        maze.addBoard(board);


    }

    private void startAlgoritm(Algoritm algoritm) {
        existingInstances++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                algoritm.start(maze, 0, maze.getRows() - 1, maze.mazeTree.setId(0), 0);
//                algoritm.start(maze, 0, 0, maze.mazeTree.setId(0), 0);
// TODO: 22.02.2023 dodac punkt startowy 

                while (existingInstances > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                ArrayList<Integer> goodWayList;
                try {
                    goodWayList = maze.mazeTree.showRightPath();
                }catch (IndexOutOfBoundsException e) {
//                    System.err.println("brak sciezki");
                    return;
                }
                HashMap<Integer, ArrayList<Point>> waysList = maze.mazeTree.getWays();

                try {
                    for (int i = goodWayList.size() - 1; i >= 0; i--) {
                        for (Point point : waysList.get(goodWayList.get(i))) {
                            if (!running) break;
                            maze.setField(Category.RIGHT_WAY.ordinal(), point.x, point.y);
                            if (point.equals(new Point(columns.getValue() - 1, 0))) return;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }catch (IndexOutOfBoundsException e){}

            }
        }).start();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
    }


    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paint(g);


        x = (getWidth() - fieldSize * columns.getValue()) / 2;
        y = (getHeight() - fieldSize * rows.getValue()) / 2 + 20;

        int componentNumber;

        for (int column = 0; column < columns.getValue(); column++) {
            for (int row = 0; row < rows.getValue(); row++) {

                componentNumber = maze.getField(column, row);
                if (componentNumber == Category.WALL.ordinal()) {
                    g.setColor(new Color(75, 51, 51, 230));
                } else if (componentNumber == Category.EMPTY.ordinal()) {
                    g.setColor(new Color(5, 241, 11, 204));

                } else if (componentNumber == Category.WAY.ordinal()) {
                    g.setColor(new Color(241, 5, 5, 204));
                } else g.setColor(new Color(255, 239, 26, 204));

                g.fillRect(x + fieldSize * column, y + fieldSize * row, fieldSize, fieldSize);
            }
        }


        g.setColor(Color.red);

//pionowe
        for (int i = 0; i <= columns.getValue(); i++) {
            g.drawLine(x + fieldSize * i, y, x + fieldSize * i, y + fieldSize * rows.getValue());
        }
//poziome
        for (int i = 0; i <= rows.getValue(); i++) {
            g.drawLine(x, y + fieldSize * i, x + fieldSize * columns.getValue(), y + fieldSize * i);
        }
        //start stop
        g.setColor(Color.blue);
        g.fillRect(x - fieldSize, y + fieldSize * (rows.getValue() - 1), fieldSize, fieldSize);
        g.fillRect(x + fieldSize * columns.getValue(), y, fieldSize, fieldSize);


    }
}
