package Maze;

import javax.swing.*;

class MazeGame {
    enum Difficulty {
        LEVEL1(30, 30), LEVEL2(50, 50), LEVEL3(80, 80);
        public final int width;
        public final int height;
        Difficulty(int w, int h) {
            this.width = w;
            this.height = h;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        MazeGenerator generator = new MazeGenerator(Difficulty.LEVEL2.width, Difficulty.LEVEL2.height);
        generator.generateMaze();

        Player player = new Player(0, 0);
        char[][] charMaze = generator.getCharMaze();

        MazePanel panel = new MazePanel(charMaze, player);  // 인자 필요
        frame.add(panel);
        frame.setVisible(true);
    }
}

