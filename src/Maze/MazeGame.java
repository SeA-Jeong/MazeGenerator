package Maze;

import javax.swing.*;

public class MazeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        MazeGenerator generator = new MazeGenerator(15);
        generator.generateMaze();

        Player player = new Player(0, 0);
        char[][] charMaze = generator.getCharMaze();

        MazePanel panel = new MazePanel(charMaze, player);  // 인자 필요
        frame.add(panel);
        frame.setVisible(true);
    }
}
