package qdo.maze.square;

import qdo.maze.Cell;

public class SquareCell extends Cell {

    public SquareCell(int x, int y) {
        this.x=x;
        this.y=y;
        cells = new SquareCell[4];
        sides=new int[]{-1,-1,-1,-1};
    }

    @Override
    public int getIndexNeighbourCell(Cell neighbour){
        int diffX = x - neighbour.getX();
        int diffY = y - neighbour.getY();
        if(Math.abs(diffX + diffY) != 1)
            throw new IllegalArgumentException("This cell is not a neighbour");
        if(diffY == 1) return 0;
        else if(diffX == -1) return 1;
        else if(diffY == -1) return 2;
        else if(diffX == 1) return 3;
        else throw new IllegalArgumentException("This cell is not a neighbour, position not found");
    }

}
