package Maze;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public MazeGenerator maze;
    public Player player;
    public List<String> log = new ArrayList<>();
    public boolean showFullMap = false;

    public GameState(MazeGenerator maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    // 로그 추가 메서드 (선택사항)
    public void logMove(String msg) {
        log.add(msg);
    }

    // 전체맵 토글
    public void toggleMapView() {
        showFullMap = !showFullMap;
    }
}
