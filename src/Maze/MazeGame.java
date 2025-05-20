package Maze;

public class MazeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        MazePanel panel = new MazePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
