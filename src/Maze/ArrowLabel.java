package Maze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArrowLabel extends JLabel {
    private Direction direction;
    private ImageIcon normalIcon;
    private ImageIcon hoverIcon;

    public ArrowLabel(ImageIcon normalIcon, ImageIcon hoverIcon, Direction direction, Runnable onClickAction) {
        this.normalIcon = normalIcon;
        this.hoverIcon = hoverIcon;
        this.direction = direction;

        setIcon(normalIcon);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(normalIcon);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                onClickAction.run(); // 클릭 시 동작 실행
            }
        });
    }

    public Direction getDirection() {
        return direction;
    }
}
