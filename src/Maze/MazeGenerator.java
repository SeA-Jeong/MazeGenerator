package Maze;

import java.util.*;

public class MazeGenerator {
    private int width;
    private int height;
    private int[][] maze;
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[height][width];
    }

    public void generateMaze() {
        stack.push(new Node(0, 0));

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (isValid(current)) {
                maze[current.y][current.x] = 1;
                List<Node> neighbors = getNeighbors(current);
                Collections.shuffle(neighbors);
                stack.addAll(neighbors);
            }
        }

        // 입구/출구 보장
        maze[0][0] = 1;
        maze[height - 1][width - 1] = 1;
    }

    private boolean isValid(Node node) {
        if (!inBounds(node.x, node.y) || maze[node.y][node.x] == 1) return false;

        int count = 0;
        for (int[] dir : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) {
            int nx = node.x + dir[0];
            int ny = node.y + dir[1];
            if (inBounds(nx, ny) && maze[ny][nx] == 1) {
                count++;
            }
        }
        return count < 2;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> list = new ArrayList<>();
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] d : dirs) {
            int nx = node.x + d[0];
            int ny = node.y + d[1];
            if (inBounds(nx, ny)) {
                list.add(new Node(nx, ny));
            }
        }
        return list;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
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

    // 내부 Node 클래스
    private static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
