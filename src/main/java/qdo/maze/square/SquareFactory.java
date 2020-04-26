package qdo.maze.square;

import lombok.extern.log4j.Log4j2;
import qdo.maze.Cell;
import qdo.maze.Cells;
import qdo.maze.Maze;
import qdo.maze.MazeFactory;

import java.util.Iterator;
import java.util.LinkedList;

@Log4j2
public class SquareFactory extends MazeFactory {

    public SquareFactory(){
        linkLines = ((cells, aboveLine, belowLine) -> {
            Iterator<Cell> iteratorPrevious = aboveLine.iterator();
            Iterator<Cell> iteratorCurrent = belowLine.iterator();
            while (iteratorPrevious.hasNext()) {
                cells.makeAcquaintance(iteratorPrevious.next(), iteratorCurrent.next());
            }
        });
    }

    @Override
    public Maze createMaze(int width, int height) {
        log.debug("Square Maze creation %d,%d ",width,height);
        SquareMaze maze = SquareMaze.builder()
                .width(width)
                .height(height)
                .cells(new Cells(new LinkedList<>()))
                .renderer(new SquareRenderer())
                .build();
        initCellsForMaze(maze,SquareCell::new);
        return maze;
    }

}
