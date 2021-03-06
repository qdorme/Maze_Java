package qdo.maze;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

public abstract class MazeFactory {
    public abstract Maze createMaze(int width, int height);
    protected LinkLines linkLines = null;

    protected void initCellsForMaze(Maze maze, BiFunction<Integer, Integer, Cell> constructor) {
        int lineSize = maze.getWidth();
        int nbLine = maze.getHeight();
        Cells cells = maze.getCells();
        Cell firstCell = constructor.apply(0, 0);
        LinkedList<Cell> line = new LinkedList<>();
        LinkedList<Cell> previousLine = new LinkedList<>();
        IntStream.range(0,nbLine).forEach(l->{
            AtomicReference<Cell> previous = new AtomicReference<>();
            IntStream.range(0,lineSize).forEach(c->{
                Cell current = (l+c == 0) ? firstCell : constructor.apply(c, l);
                line.add(current);
                cells.all().add(current);
                if(nonNull(previous.get()))
                    cells.makeAcquaintance(previous.get(),current);
                previous.set(current);
            });
            if(!previousLine.isEmpty() && nonNull(linkLines)) {
                linkLines.doIt(cells,previousLine,line);
            }
            previousLine.clear();
            previousLine.addAll(line);
            line.clear();
        });

        cells.setCellList(firstCell) ;
    }
}
