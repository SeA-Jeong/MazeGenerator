package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazeGame extends JFrame {
    public MazeGame() {
        setTitle("미로 탐색 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // 미로 생성
        MazeGenerator generator = new MazeGenerator(10, 10); // 10x10 크기
        generator.generateMaze();
        char[][] mazeData = generator.getCharMaze();

        // 유효한 시작 위치 찾기 (가장 위쪽의 '□')
        Player player = null;
        outer:
        for (int y = 0; y < mazeData.length; y++) {
            for (int x = 0; x < mazeData[0].length; x++) {
                if (mazeData[y][x] == '□') {
                    player = new Player(x, y);
                    break outer;
                }
            }
        }

        if (player == null) {
            throw new IllegalStateException("유효한 시작 위치를 찾을 수 없습니다.");
        }

        // MazePanel 생성 및 등록
        MazePanel mazePanel = new MazePanel(mazeData, player);
        add(mazePanel);

        // 키 입력 리스너 등록
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'm') {
                    mazePanel.toggleFullView();
                }
            }
        });

        setVisible(true);
        setFocusable(true);
    }

    public static void main(String[] args) {
        new MazeGame();
    }
}
