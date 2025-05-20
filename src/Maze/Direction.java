package Maze;
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public int[] toDelta() {
        switch (this) {
            case UP:    return new int[]{0, -1};
            case DOWN:  return new int[]{0, 1};
            case LEFT:  return new int[]{-1, 0};
            case RIGHT: return new int[]{1, 0};
            default:    return new int[]{0, 0};
        }
    }
}
