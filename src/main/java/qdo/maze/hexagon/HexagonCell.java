package qdo.maze.hexagon;

import qdo.maze.Cell;


public class HexagonCell extends Cell {

    public HexagonCell(int x, int y) {
        this.x=x;
        this.y=y;
        cells = new HexagonCell[6];
        sides=new int[]{-1,-1,-1,-1,-1,-1};
    }

    @Override
    protected int getIndexNeighbourCell(Cell neighbour){
        int diffX = x - neighbour.getX();
        int diffY = y - neighbour.getY();
        if(Math.abs(diffX + diffY) > 2)
            throw new IllegalArgumentException("This cell is not a neighbour");
        if(diffX + diffY == -2) return 2;
        else if(diffX + diffY == 0 ) return 0;
        else if(diffX + diffY == 1 && diffX == 0 ) return 5;
        else if(diffX + diffY == 1 && diffX == 1 ) return 4;
        else if(diffX + diffY == -1 && diffX == -1 ) return 1;
        else if(diffX + diffY == -1 && diffX == 0 ) return 3;
        else throw new IllegalArgumentException("This cell is not a neighbour, position not found");
    }





}
