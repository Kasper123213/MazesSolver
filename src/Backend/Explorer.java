package Backend;


public class Explorer {
    private int x;
    private int y;
    Maze maze;

    public Explorer(int x, int y, Maze maze) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public boolean[] checkMoves() {
        boolean[] moves = new boolean[4];
        moves[0] = maze.canGoUp(x, y);
        moves[1] = maze.canGoRight(x, y);
        moves[2] = maze.canGoDown(x, y);
        moves[3] = maze.canGoLeft(x, y);
        return moves;
    }

    public int getX() {
        return x;
    }

    public void incrementX() {
        x++;
    }

    public void decrementX() {
        x--;
    }


    public int getY() {
        return y;
    }

    public void incrementY() {
        y++;
    }

    public void decrementY() {
        y--;
    }
}
