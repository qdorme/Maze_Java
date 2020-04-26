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
    public int getIndexNeighbourCell(Cell neighbour){
        int diffX = x - neighbour.getX();
        int diffY = y - neighbour.getY();
        if(Math.abs(diffX + diffY) > 2)
            throw new IllegalArgumentException("This cell is not a neighbour");
        if(diffY == -1){ // 2 / 3
            if(this.y % 2 == 0){
                if(diffX == 0) return 2;
                else return 3;
            }else{
                if(diffX == 0) return 3;
                else return 2;
            }
        }else if(diffY == 1){ // 0 / 5
            if(this.y % 2 == 0){
                if(diffX == 0) return 0;
                else return 5;
            }else{
                if(diffX == 0) return 5;
                else return 0;
            }
        }else{ // 1 / 4
            if(diffX == 1) return 4;
            else return 1;
        }
    }





}
