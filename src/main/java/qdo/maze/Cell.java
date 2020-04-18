package qdo.maze;


import lombok.Getter;
import qdo.maze.exception.InvalidDirectionException;
import qdo.maze.square.SquareCell;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
public abstract class Cell {
    protected Cell[] cells;
    protected int x,y;
    protected int[] sides;

    public void setNeighbour(Cell cell, int position){
        if(position>=cells.length) throw new IllegalArgumentException();
        if(nonNull(cells[position])) throw new InvalidDirectionException(position);
        cells[position]=cell;
    }

    public Cell getNeighbour(int position) throws InvalidDirectionException {
        if(position>=cells.length) throw new IllegalArgumentException();
        if(isNull(cells[position])) throw new InvalidDirectionException(position);
        return cells[position];
    }

    public void makeAcquaintance(Cell newNeighbour) {
        cells[getIndexNeighbourCell(newNeighbour)]=newNeighbour;
    }

    public void createPath(Cell neighbour) {
        sides[getIndexNeighbourCell(neighbour)]=0;
    }

    protected abstract int getIndexNeighbourCell(Cell neighbour);

}
