package qdo.maze.hexagon;

import lombok.extern.log4j.Log4j2;
import qdo.maze.Cells;
import qdo.maze.Maze;
import qdo.maze.MazeFactory;

import java.util.LinkedList;

@Log4j2
public class HexagonFactory implements MazeFactory {
    @Override
    public Maze createMaze(int width, int height) {
        HexagonMaze maze = HexagonMaze.builder()
                .width(width)
                .height(height)
                .cells(new Cells(new LinkedList<>()))
                .renderer(new HexagonRenderer())
                .build();
        initCellsForMaze(maze,HexagonCell::new);
        return maze;
    }

}
