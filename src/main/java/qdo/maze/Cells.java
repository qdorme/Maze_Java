package qdo.maze;

import lombok.Getter;
import qdo.maze.exception.InvalidDirectionException;

import java.util.List;


public class Cells {
    List<Cell> cells;
    @Getter
    Cell currentCell;

    public Cells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> all() {
        return cells;
    }

    public void setCells(Cell initialCell) {
        this.currentCell = initialCell;
    }

    public Cell goTo(int position) throws InvalidDirectionException {
        currentCell = currentCell.getNeighbour(position);
        return currentCell;
    }

    public void makeAcquaintance(Cell cell1, Cell cell2) {
        cell1.makeAcquaintance(cell2);
        cell2.makeAcquaintance(cell1);
    }

    public void createPath(Cell cell1, Cell cell2) {
        cell1.createPath(cell2);
        cell2.createPath(cell1);
    }

    public void createPath(int direction) {
        Cell start = getCurrentCell();
        goTo(direction);
        Cell end = getCurrentCell();
        start.createPath(end);
        end.createPath(start);
    }



}
