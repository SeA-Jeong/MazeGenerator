package Maze;
import javax.swing.*;
import Maze.ImageUtil;
import java.awt.*;

public class MazePanel extends JPanel {
    private Player player;
    private char[][] maze;
    private Image backgroundImage;

    private ArrowLabel arrowUp, arrowDown, arrowLeft, arrowRight;

    public MazePanel(char[][] maze, Player player) {
        this.maze = maze;
        this.player = player;
        setLayout(null); // 절대 위치
        setPreferredSize(new Dimension(400, 400));

        updateBackground();
        setupArrowLabels();
    }

    private void setupArrowLabels() {
    	ImageIcon baseIcon = new ImageIcon(getClass().getResource("/Maze/MazeImage/arrow.png"));
    	ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Maze/MazeImage/arrow_active.png"));

        arrowUp = new ArrowLabel(
            ImageUtil.rotateImage(baseIcon, -90),
            ImageUtil.rotateImage(hoverIcon, -90),
            Direction.UP,
            () -> move(Direction.UP)
        );

        arrowDown = new ArrowLabel(
            ImageUtil.rotateImage(baseIcon, 90),
            ImageUtil.rotateImage(hoverIcon, 90),
            Direction.DOWN,
            () -> move(Direction.DOWN)
        );

        arrowLeft = new ArrowLabel(
            ImageUtil.rotateImage(baseIcon, 180),
            ImageUtil.rotateImage(hoverIcon, 180),
            Direction.LEFT,
            () -> move(Direction.LEFT)
        );

        arrowRight = new ArrowLabel(
            baseIcon,
            hoverIcon,
            Direction.RIGHT,
            () -> move(Direction.RIGHT)
        );

        arrowUp.setBounds(170, 30, 60, 60);
        arrowDown.setBounds(170, 310, 60, 60);
        arrowLeft.setBounds(70, 170, 60, 60);
        arrowRight.setBounds(270, 170, 60, 60);

        add(arrowUp);
        add(arrowDown);
        add(arrowLeft);
        add(arrowRight);
    }


    private void move(Direction dir) {
        int[] d = dir.toDelta();
        int newX = player.getX() + d[0];
        int newY = player.getY() + d[1];

        if (newY >= 0 && newY < maze.length && newX >= 0 && newX < maze[0].length && maze[newY][newX] == '□') {
            player.move(dir);
            updateBackground();
            repaint();
        }
    }

    private void updateBackground() {
        backgroundImage = ImageUtil.getBackgroundImage(maze, player.getX(), player.getY());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
