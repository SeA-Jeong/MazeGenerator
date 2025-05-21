package Maze;

import java.util.*;

public class MazeGenerator {
    private int width;
    private int height;
    private int[][] maze;
    private Random rand = new Random();

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = 0; // 전부 벽으로 초기화
            }
        }
    }

    public void generateMaze() {
        boolean[][] visited = new boolean[height][width];
        dfs(1, 1, visited); // (1,1)에서 시작하도록 수정

        // 입구/출구 하나씩만 생성
        maze[1][0] = 1; // 좌측 벽에 입구
        maze[height - 2][width - 1] = 1; // 우측 벽에 출구
    }

    private void dfs(int x, int y, boolean[][] visited) {
        visited[y][x] = true;
        maze[y][x] = 1;

        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        List<int[]> dirList = Arrays.asList(directions);
        Collections.shuffle(dirList, rand);

        for (int[] dir : dirList) {
            int nx = x + dir[0] * 2;
            int ny = y + dir[1] * 2;

            if (inBounds(nx, ny) && !visited[ny][nx]) {
                int betweenX = x + dir[0];
                int betweenY = y + dir[1];
                maze[betweenY][betweenX] = 1;
                dfs(nx, ny, visited);
            }
        }
    }

    private boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < width - 1 && y < height - 1;
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            for (int cell : row) {
                sb.append(cell == 1 ? "□" : "■");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getCharMaze() {
        char[][] result = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = maze[y][x] == 1 ? '□' : '■';
            }
        }
        return result;
    }
} 
