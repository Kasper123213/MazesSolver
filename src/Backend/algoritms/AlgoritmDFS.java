package Backend.algoritms;

import Backend.Explorer;
import Backend.Maze;
import GUI.Category;
import GUI.Panels.EditionPanel;

public class AlgoritmDFS extends Algoritm {
    private Explorer explorer;
    private int speed = 50;
    private int id = 0;
    private int sequenceNumber = 0;
    @Override
    public boolean start(Maze maze, int x, int y, int id, int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        this.id = id;

        explorer = new Explorer(x, y, maze);

        boolean[] moves;
        while ((explorer.getX() != maze.getColumns() - 1 || explorer.getY() != 0)&& EditionPanel.running) {

            maze.mazeTree.addField(explorer.getX(), explorer.getY(), id);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            maze.setField(Category.WAY.ordinal(), explorer.getX(), explorer.getY());
            moves = explorer.checkMoves();//zwraca tablice boolea[gowa,prawo,dol,lewo]


            int newPossibleMoves = (moves[0] ? 1 : 0) + (moves[1] ? 1 : 0) + (moves[2] ? 1 : 0) + (moves[3] ? 1 : 0);


            if (moves[0]) {
                if (newPossibleMoves >= 2) {
                    int newX = explorer.getX();
                    int newY = explorer.getY();
                    EditionPanel.changeInstancesNumber(1);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                            int newId = maze.mazeTree.setId(sequenceNumber + 1);
                            maze.mazeTree.addField(newX, newY, newId);
                            if(new AlgoritmDFS().start(maze, newX, newY - 1, newId, sequenceNumber + 1)){
                                EditionPanel.changeInstancesNumber(-1);
                                return true;
                            }
//                        }
//                    }).start();


                } else {
                    explorer.decrementY();
                }
            }
            if (moves[1]) {
                if (newPossibleMoves >= 2) {
                    int newX = explorer.getX();
                    int newY = explorer.getY();
                    EditionPanel.changeInstancesNumber(1);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                            int newId = maze.mazeTree.setId(sequenceNumber + 1);
                            maze.mazeTree.addField(newX, newY, newId);
                            if(new AlgoritmDFS().start(maze, newX + 1, newY, newId, sequenceNumber + 1)){
                                EditionPanel.changeInstancesNumber(-1);
                                return true;
                            }
//                        }
//                    }).start();

                } else {
                    explorer.incrementX();
                }
            }
            if (moves[2]) {
                if (newPossibleMoves >= 2) {
                    int newX = explorer.getX();
                    int newY = explorer.getY();
                    EditionPanel.changeInstancesNumber(1);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                            int newId = maze.mazeTree.setId(sequenceNumber + 1);
                            maze.mazeTree.addField(newX, newY, newId);
                            if(new AlgoritmDFS().start(maze, newX, newY + 1, newId, sequenceNumber + 1)){
                                EditionPanel.changeInstancesNumber(-1);
                                return true;
                            }
//                        }
//                    }).start();

                } else {
                    explorer.incrementY();
                }
            }

            if (moves[3]) {
                if (newPossibleMoves >= 2) {
                    int newX = explorer.getX();
                    int newY = explorer.getY();
                    EditionPanel.changeInstancesNumber(1);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                            int newId = maze.mazeTree.setId(sequenceNumber + 1);
                            maze.mazeTree.addField(newX, newY, newId);
                            if(new AlgoritmDFS().start(maze, newX - 1, newY, newId, sequenceNumber + 1)){
                                EditionPanel.changeInstancesNumber(-1);
                                return true;
                            }
//                        }
//                    }).start();

                } else {
                    explorer.decrementX();
                }

            }


            if (newPossibleMoves != 1) {
                break;
            }


        }
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        maze.setField(Category.WAY.ordinal(), explorer.getX(), explorer.getY());


        if (explorer.getX() == maze.getColumns() - 1 && explorer.getY() == 0) {
            maze.mazeTree.addField(explorer.getX(), explorer.getY(), id);
            EditionPanel.changeInstancesNumber(-1);
            return true;


        }
        EditionPanel.changeInstancesNumber(-1);
        return false;
    }
}
