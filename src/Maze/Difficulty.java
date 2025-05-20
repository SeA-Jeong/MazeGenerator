package Maze;

public enum Difficulty {
    LEVEL1(30, 30), LEVEL2(50, 50), LEVEL3(80, 80);
    public final int width;
    public final int height;
    Difficulty(int w, int h) {
        this.width = w;
        this.height = h;
    }
}
