package Maze;
public class Player {
    private int x, y;
    private Direction direction;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.direction = Direction.UP;  // 기본 방향
    }

    // 현재 위치
    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }

    // 방향 설정
    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    // 현재 방향으로 한 칸 이동
    public void moveForward() {
        int[] delta = direction.toDelta();
        x += delta[0];
        y += delta[1];
    }

    // 지정된 방향으로 이동
    public void move(Direction dir) {
        this.direction = dir;
        int[] delta = dir.toDelta();
        x += delta[0];
        y += delta[1];
    }

    // 위치 초기화
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}

