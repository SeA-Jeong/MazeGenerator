package Maze;
import java.util.Deque;
import java.util.ArrayDeque;
import java.awt.Point;

public class Player {
    private Deque<Point> moveHistory = new ArrayDeque<>();
    private int x, y;
    private Direction direction;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.direction = Direction.UP;  // 기본 방향
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void move(Direction dir) {
        this.direction = dir;
        moveHistory.push(new Point(x, y));  // 이동 전 위치 저장
        int[] delta = dir.toDelta();
        x += delta[0];
        y += delta[1];
    }

    public void undo() {
        if (!moveHistory.isEmpty()) {
            Point prev = moveHistory.pop();
            this.x = prev.x;
            this.y = prev.y;
        }
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
