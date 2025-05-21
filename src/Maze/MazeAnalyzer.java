package Maze;

public class MazeAnalyzer {

    public static String getShape(char[][] maze, int x, int y) {
        int rows = maze.length;
        int cols = maze[0].length;

        boolean up    = isPath(maze, x, y - 1);
        boolean down  = isPath(maze, x, y + 1);
        boolean left  = isPath(maze, x - 1, y);
        boolean right = isPath(maze, x + 1, y);

        int count = (up ? 1 : 0) + (down ? 1 : 0) + (left ? 1 : 0) + (right ? 1 : 0);

        // 형태 판별
        if (count == 4) return "cross";

        if (count == 3) {
            if (!up) return "t_down";
            if (!down) return "t_up";
            if (!left) return "t_right";
            return "t_left"; // !right
        }

        if (count == 2) {
            if (up && down) return "straight_vertical";
            if (left && right) return "straight_horizontal";
            if (up && right) return "corner_upright";
            if (up && left) return "corner_upleft";
            if (down && right) return "corner_downright";
            if (down && left) return "corner_downleft";
        }

        if (count == 1) {
            if (up) return "deadend_up";
            if (down) return "deadend_down";
            if (left) return "deadend_left";
            return "deadend_right";
        }

        return "isolated"; // 길이 없으면
    }

    private static boolean isPath(char[][] maze, int x, int y) {
        if (x < 0 || y < 0 || y >= maze.length || x >= maze[0].length) return false;
        return maze[y][x] == '□';
    }
}
