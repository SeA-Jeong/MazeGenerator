package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static ImageIcon rotateImage(ImageIcon icon, double angleDegrees) {
        Image image = icon.getImage();
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();

        AffineTransform at = AffineTransform.getRotateInstance(
            Math.toRadians(angleDegrees),
            w / 2.0,
            h / 2.0
        );
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(rotated);
    }
}
