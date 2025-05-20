package Maze;
public class Player {
    int x, y;
    Direction facing;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.facing = Direction.NORTH;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
