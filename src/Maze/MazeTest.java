package Maze;
import Maze.MazeGenerator;

public class MazeTest {
    public static void main(String[] args) {
        MazeGenerator maze = new MazeGenerator(15);  // 미로 크기 설정
        maze.generateMaze();                         // 미로 생성

        System.out.println(maze.getRawMaze());       // 숫자 배열 출력
        System.out.println(maze.getSymbolicMaze());  // 심볼 미로 출력
    }
}
