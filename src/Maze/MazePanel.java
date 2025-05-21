package Maze;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private Player player;
    private char[][] maze;
    private Image backgroundImage;
    private boolean fullView = false;

    private ArrowLabel arrowUp, arrowDown, arrowLeft, arrowRight;

    private static final String IMAGE_PATH = "C:/HSJ/OpenSouceProject/src/Maze/MazeImage/";

    public MazePanel(char[][] maze, Player player) {
        this.maze = maze;
        this.player = player;
        setLayout(null);
        setPreferredSize(new Dimension(400, 400));

        updateBackground();
        setupArrowLabels();
        repositionArrows(player.getDirection());
    }

    private void setupArrowLabels() {
        ImageIcon baseIcon = new ImageIcon(IMAGE_PATH + "arrow.png");
        ImageIcon hoverIcon = new ImageIcon(IMAGE_PATH + "arrow_active.png");

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

        arrowUp.setSize(60, 60);
        arrowDown.setSize(60, 60);
        arrowLeft.setSize(60, 60);
        arrowRight.setSize(60, 60);

        add(arrowUp);
        add(arrowDown);
        add(arrowLeft);
        add(arrowRight);
    }

    private void repositionArrows(Direction facing) {
        Point up = new Point(170, 30);
        Point down = new Point(170, 310);
        Point left = new Point(70, 170);
        Point right = new Point(270, 170);

        switch (facing) {
            case UP -> {
                arrowUp.setLocation(up);
                arrowDown.setLocation(down);
                arrowLeft.setLocation(left);
                arrowRight.setLocation(right);
            }
            case DOWN -> {
                arrowUp.setLocation(down);
                arrowDown.setLocation(up);
                arrowLeft.setLocation(right);
                arrowRight.setLocation(left);
            }
            case LEFT -> {
                arrowUp.setLocation(right);
                arrowDown.setLocation(left);
                arrowLeft.setLocation(up);
                arrowRight.setLocation(down);
            }
            case RIGHT -> {
                arrowUp.setLocation(left);
                arrowDown.setLocation(right);
                arrowLeft.setLocation(down);
                arrowRight.setLocation(up);
            }
        }
    }

    private void move(Direction dir) {
        int[] d = dir.toDelta();
        int newX = player.getX() + d[0];
        int newY = player.getY() + d[1];

        if (newY >= 0 && newY < maze.length && newX >= 0 && newX < maze[0].length && maze[newY][newX] == '□') {
            player.move(dir);
            updateBackground();
            repositionArrows(player.getDirection());
            repaint();
        }
    }

    private void updateBackground() {
        backgroundImage = ImageUtil.getBackgroundImage(
            maze,
            player.getX(),
            player.getY(),
            player.getDirection()
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fullView) {
            g.setColor(Color.WHITE);
            int cellWidth = getWidth() / maze[0].length;
            int cellHeight = getHeight() / maze.length;
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[0].length; x++) {
                    if (maze[y][x] == '□') {
                        g.setColor(Color.LIGHT_GRAY);
                    } else {
                        g.setColor(Color.DARK_GRAY);
                    }
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
            }

            g.setColor(Color.RED);
            g.fillOval(player.getX() * cellWidth, player.getY() * cellHeight, cellWidth, cellHeight);
        } else {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void toggleFullView() {
        fullView = !fullView;
        repaint();
    }
}
