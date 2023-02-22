package Backend;

import GUI.Category;
import Main.Main;

import java.util.ArrayList;

public class Maze {
    private ArrayList<ArrayList<Integer>> board;
    private int columns;
    private int rows;
    public MazeTree mazeTree = new MazeTree();

    public Maze(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        setBoard(columns,rows);

    }

    public void addBoard(ArrayList<ArrayList<Integer>> board){
        this.board=board;
        this.columns=board.size();
        this.rows=board.get(0).size();
        Main.refresh();
    }

    public void setBoard(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        board = new ArrayList<>();
        for (int column = 0; column < columns; column++) {
            board.add(new ArrayList<>());
            for (int row = 0; row < rows; row++) {
                board.get(column).add(Category.WALL.ordinal());
            }
        }
    }

    public int getField(int column, int row) {
        return board.get(column).get(row);
    }

    public void setField(int i, int column, int row) {
        board.get(column).set(row, i);
        Main.refresh();

    }

    public void clear() {
        for (ArrayList<Integer> row : board) {
            for (int field = 0; field < row.size(); field++) {
                row.set(field, Category.WALL.ordinal());
            }
        }
        mazeTree.clear();
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public boolean canGoUp(int x, int y) {
        boolean answer = false;
        try {
            answer = getField(x, y - 1) == Category.EMPTY.ordinal();
        } catch (IndexOutOfBoundsException e) {
        }

        return answer;
    }

    public boolean canGoRight(int x, int y) {
        boolean answer = false;
        try {
            answer = getField(x + 1, y) == Category.EMPTY.ordinal();
        } catch (IndexOutOfBoundsException e) {
        }

        return answer;
    }

    public boolean canGoLeft(int x, int y) {
        boolean answer = false;
        try {
            answer = getField(x - 1, y) == Category.EMPTY.ordinal();
        } catch (IndexOutOfBoundsException e) {
        }

        return answer;

    }

    public boolean canGoDown(int x, int y) {
        boolean answer = false;
        try {
            answer = getField(x, y + 1) == Category.EMPTY.ordinal();
        } catch (IndexOutOfBoundsException e) {
        }

        return answer;

    }

    public void clearPath() {
        for (ArrayList<Integer> row : board) {
            for (int field = 0; field < row.size(); field++) {
                if (row.get(field) == Category.WAY.ordinal() || row.get(field) == Category.RIGHT_WAY.ordinal())
                    row.set(field, Category.EMPTY.ordinal());
            }
        }
        mazeTree.clear();
    }

    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }
}
