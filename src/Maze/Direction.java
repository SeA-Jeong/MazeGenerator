package Maze;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction turnLeft() { ... }
    public Direction turnRight() { ... }
    public int[] toVector() { ... }  // (dx, dy)
}
