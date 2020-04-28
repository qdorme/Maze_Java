package qdo.maze;


import lombok.Getter;
import lombok.Setter;
import qdo.maze.exception.InvalidDirectionException;

import java.util.*;

import static java.util.Objects.isNull;


public abstract class Cell {
    protected Cell[] cells;
    @Getter protected int x;
    @Getter protected int y;
    @Getter protected int[] sides;
    @Getter @Setter private boolean exit = false;
    private boolean visited = false;
    @Getter
    private int weight;
    private Boolean isBorderCell=null;

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

    public void unVisited(){
        this.visited = false;
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
        List asList = new LinkedList();
        for (Cell cell : cells) {
            if(Objects.nonNull(cell)) asList.add(cell);
        }
        return asList;
    }

    public List<Cell> path(){
        List<Cell> path = new LinkedList<>();
        for (int index = 0; index < cells.length ; index++) {
            if(sides[index] != -1) path.add(cells[index]);
        }
        return path;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        getCells().parallelStream().filter(Cell::isNotVisited).forEach(c->{
            if(Objects.isNull(c)) System.out.println("c'est tout pourri");
            if(Objects.nonNull(c)) c.setWeight(weight+1);
        });
    }

    public boolean isBorderCell() {
        if(isBorderCell == null){
            isBorderCell = getCells().size() != cells.length;
        }
        return isBorderCell;
    }
}
