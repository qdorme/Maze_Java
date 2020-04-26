package qdo.maze;


import lombok.Getter;
import qdo.maze.exception.InvalidDirectionException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;


public abstract class Cell {
    protected Cell[] cells;
    @Getter protected int x;
    @Getter protected int y;
    @Getter protected int[] sides;
    private boolean visited = false;

    public Cell getNeighbour(int position) {
        if (position >= cells.length) throw new IllegalArgumentException();
        if (isNull(cells[position])) throw new InvalidDirectionException(position);
        return cells[position];
    }

    public void makeAcquaintance(Cell newNeighbour) {
        cells[getIndexNeighbourCell(newNeighbour)] = newNeighbour;
    }

    public void createPath(Cell neighbour) {
        sides[getIndexNeighbourCell(neighbour)] = 0;
    }

    public abstract int getIndexNeighbourCell(Cell neighbour);

    public void visited() {
        this.visited = true;
    }

    public boolean isNotVisited() {
        return !visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean hasUnvisitedNeighbour(){
        Optional<Cell> any = Arrays.asList(cells).stream().filter(Objects::nonNull).filter(Cell::isNotVisited).findAny();
        return any.isPresent();
    }

    public List<Cell> getCells() {
        return Arrays.asList(cells);
    }
}
