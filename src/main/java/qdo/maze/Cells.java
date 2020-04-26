package qdo.maze;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Cells {
    List<Cell> cellList;
    @Getter
    @Setter
    private Cell currentCell;

    public Cells(List<Cell> cells) {
        this.cellList = cells;
    }

    public List<Cell> all() {
        return cellList;
    }

    public void setCellList(Cell initialCell) {
        this.currentCell = initialCell;
        this.currentCell.visited();
    }

    public Cell goTo(int position){
        currentCell = currentCell.getNeighbour(position);
        return currentCell;
    }

    public void makeAcquaintance(Cell cell1, Cell cell2) {
        cell1.makeAcquaintance(cell2);
        cell2.makeAcquaintance(cell1);
    }

    public void createPath(Cell to) {
        Cell from = getCurrentCell();
        from.createPath(to);
        to.createPath(from);
        currentCell=to;
        to.visited();
    }

}
