package qdo.maze.hexagon;

import lombok.extern.log4j.Log4j2;
import qdo.maze.*;
import qdo.maze.exception.InvalidDirectionException;

import java.util.Iterator;
import java.util.LinkedList;

@Log4j2
public class HexagonFactory extends MazeFactory {

    public HexagonFactory(){
        linkLines = ((cells, aboveLine, belowLine) -> {
            Iterator<Cell> iteratorPrevious = aboveLine.iterator();
            Iterator<Cell> iteratorCurrent = belowLine.iterator();
            while (iteratorPrevious.hasNext()) {
                Cell aboveCell = iteratorPrevious.next();
                Cell currentCell = iteratorCurrent.next();
                cells.makeAcquaintance(aboveCell, currentCell);
                try {
                    int position = 4;
                    if(currentCell.getY() % 2 == 0){
                        position = 1;
                    }
                    Cell cellNeighbour = currentCell.getNeighbour(position);
                    cells.makeAcquaintance(aboveCell, cellNeighbour);
                }catch (InvalidDirectionException e){
                    // this exception is normal if we are a extrem places
                }
            }
        });
    }

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
