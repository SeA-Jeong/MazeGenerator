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
        MazeGenerator generator = new MazeGenerator(10, 10);
        generator.generateMaze(); // 실제 미로 생성
        char[][] mazeData = generator.getCharMaze(); // 문자형 미로 반환

        // 플레이어 초기 위치
        Player player = new Player(0, 0);

        // MazePanel은 char[][]를 받음
        MazePanel mazePanel = new MazePanel(mazeData, player);
        add(mazePanel);

        // m 키로 전체 맵 보기 토글
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'm') {
                    mazePanel.toggleFullView(); // 반드시 MazePanel에 구현 필요
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
