package qdo.maze.square;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import qdo.maze.Cells;
import qdo.maze.Maze;
import qdo.maze.Renderer;

@Getter
@Log4j2
public class SquareMaze extends Maze {

    @Builder
    public SquareMaze(int width, int height, Cells cells, Renderer renderer) {
        super(width,height,cells,renderer);
    }
}
