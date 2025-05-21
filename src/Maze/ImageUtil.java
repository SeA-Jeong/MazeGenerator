package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public class ImageUtil {
    private static final Map<String, String> shapeToImageMap = Map.of(
        "t_down", "T.png",
        "t_left", "t_left.png",
        "t_right", "t_right.png",
        "deadend_up", "deadend.png",
        "corner_upright", "left.png",
        "corner_upleft", "right.png",
        "cross", "+.png",
        "straight_vertical", "up.png"
    );

    private static final String BASE_PATH = "C:/HSJ/OpenSouceProject/src/Maze/MazeImage/";

    // 방향 정보 포함 버전
    public static Image getBackgroundImage(char[][] maze, int x, int y, Direction dir) {
        String shape = MazeAnalyzer.getShape(maze, x, y);
        String imageFile = shapeToImageMap.getOrDefault(shape, "deadend.png");

        File file = new File(BASE_PATH + imageFile);
        if (!file.exists()) {
            System.err.println("⚠ 이미지 파일을 찾을 수 없습니다: " + file.getAbsolutePath());
            return null;
        }

        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        double angle = switch (dir) {
            case UP -> 0;
            case RIGHT -> 90;
            case DOWN -> 180;
            case LEFT -> 270;
        };

        return rotateImage(icon, angle).getImage();
    }

    public static ImageIcon rotateImage(ImageIcon icon, double angleDegrees) {
        if (icon == null || icon.getImage() == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.err.println("⚠ 잘못된 이미지 아이콘입니다. 회전 생략");
            return icon;
        }

        Image image = icon.getImage();
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();

        AffineTransform at = AffineTransform.getRotateInstance(
                Math.toRadians(angleDegrees), w / 2.0, h / 2.0
        );
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(rotated);
    }
} 
