package Maze;
import java.util.*;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class MazeGenerator {
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;

    MazeGenerator(int dim) {
        this.dimension = dim;
        this.maze = new int[dim][dim];
    }

    public void generateMaze() {
        stack.push(new Node(0, 0));

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (isValid(current)) {
                maze[current.y][current.x] = 1;
                ArrayList<Node> neighbors = getNeighbors(current);
                Collections.shuffle(neighbors); // 랜덤 순서
                for (Node neighbor : neighbors) {
                    stack.push(neighbor);
                }
            }
        }

        // 입구, 출구 설정 (선택 사항)
        maze[0][0] = 1;
        maze[dimension - 1][dimension - 1] = 1;
    }

    private boolean isValid(Node node) {
        if (!inBounds(node.x, node.y) || maze[node.y][node.x] == 1) return false;

        int count = 0;
        for (int[] dir : new int[][] {{0,-1},{0,1},{-1,0},{1,0}}) {
            int nx = node.x + dir[0], ny = node.y + dir[1];
            if (inBounds(nx, ny) && maze[ny][nx] == 1) {
                count++;
            }
        }
        return count < 2;
    }

    private ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> list = new ArrayList<>();
        int[][] directions = {{0,-1}, {0,1}, {-1,0}, {1,0}}; // 상하좌우
        for (int[] d : directions) {
            int nx = node.x + d[0];
            int ny = node.y + d[1];
            if (inBounds(nx, ny)) {
                list.add(new Node(nx, ny));
            }
        }
        return list;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            for (int cell : row) {
                sb.append(cell == 1 ? "🟩" : "⬛"); // 보기 좋게 색 표시
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
