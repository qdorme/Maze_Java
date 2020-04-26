package qdo.maze;

import lombok.Builder;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import qdo.maze.exception.InvalidDirectionException;
import qdo.maze.hexagon.HexagonCell;
import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.square.SquareCell;
import qdo.maze.square.SquareFactory;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CellsTest {
    Cells cells;

    @Before
    public void setUp(){
        cells = new Cells(new LinkedList<>());
    }

    @Test(expected = InvalidDirectionException.class)
    public void shouldThrowExceptionIfGoUp(){
        // When
        MazeFactory factory = new SquareFactory();
        cells = factory.createMaze(4,4).getCells();
        // Given
        cells.goTo(0);
    }

    @Test(expected = InvalidDirectionException.class)
    public void shouldThrowExceptionIfGoRight(){
        // When
        MazeFactory factory = new SquareFactory();
        cells=factory.createMaze(4,4).getCells();
        // Given
        cells.goTo(3);
    }

    @Test
    public void shouldGiveOppositeDirection(){
        // Given
        SquareCell sq = new SquareCell(0,0);
        SquareCell sq2 = new SquareCell(1,0);
        HexagonCell hex = new HexagonCell(0,1);
        HexagonCell hex2 = new HexagonCell(1,1);
        // When
        sq.makeAcquaintance(sq2);
        sq2.makeAcquaintance(sq);
        hex.makeAcquaintance(hex2);
        hex2.makeAcquaintance(hex);
        // Then
        assertNotNull(sq.getNeighbour(1));
        assertNotNull(sq2.getNeighbour(3));
        assertNotNull(hex.getNeighbour(1));
        assertNotNull(hex2.getNeighbour(4));
    }

    @Test
    public void squareCell(){
        // When
        MazeFactory factory = new SquareFactory();
        // Given
        cells=factory.createMaze(4,4).getCells();
        // Then
        assertNotNull(cells.getCurrentCell());
        assertNotNull(cells.goTo(1));
        assertNotNull(cells.goTo(1));
        assertNotNull(cells.goTo(1));
        assertNotNull(cells.goTo(2));
        assertNotNull(cells.goTo(2));
        assertNotNull(cells.goTo(2));
        assertNotNull(cells.goTo(3));
        assertNotNull(cells.goTo(3));
        assertNotNull(cells.goTo(3));
        assertNotNull(cells.goTo(0));
        assertNotNull(cells.goTo(0));
        assertNotNull(cells.goTo(0));
    }

    @Test
    public void verify_index_neighbours_for_hexagon(){
        // Given
        Cell cell10 = new HexagonCell(1,0);
        Cell cell20 = new HexagonCell(2,0);
        Cell cell01 = new HexagonCell(0,1);
        Cell cell11 = new HexagonCell(1,1);
        Cell cell21 = new HexagonCell(2,1);
        Cell cell02 = new HexagonCell(0,2);
        Cell cell12 = new HexagonCell(1,2);
        Cell cell22 = new HexagonCell(2,2);
        Cell cell03 = new HexagonCell(0,3);
        Cell cell13 = new HexagonCell(1,3);
        // Then
        assertEquals(0,cell11.getIndexNeighbourCell(cell20));
        assertEquals(1,cell11.getIndexNeighbourCell(cell21));
        assertEquals(2,cell11.getIndexNeighbourCell(cell22));
        assertEquals(3,cell11.getIndexNeighbourCell(cell12));
        assertEquals(4,cell11.getIndexNeighbourCell(cell01));
        assertEquals(5,cell11.getIndexNeighbourCell(cell10));
        assertEquals(0,cell12.getIndexNeighbourCell(cell11));
        assertEquals(1,cell12.getIndexNeighbourCell(cell22));
        assertEquals(2,cell12.getIndexNeighbourCell(cell13));
        assertEquals(3,cell12.getIndexNeighbourCell(cell03));
        assertEquals(4,cell12.getIndexNeighbourCell(cell02));
        assertEquals(5,cell12.getIndexNeighbourCell(cell01));
    }




    private List<Couple> getHexaExpected(){
        List<Couple> couples = new LinkedList<>();
        couples.add(Couple.builder().direction(1).coord(Map.of(
                1,new Integer[]{2,0},
                3,new Integer[]{0,1},
                4,new Integer[]{0,0},
                2,new Integer[]{1,1})).build());
        couples.add(Couple.builder().direction(1).coord(Map.of(
                2,new Integer[]{2,1},
                3,new Integer[]{1,1},
                4,new Integer[]{1,0})).build());
        couples.add(Couple.builder().direction(2).coord(Map.of(
                3,new Integer[]{2,2},
                4,new Integer[]{1,1},
                5,new Integer[]{2,0})).build());
        couples.add(Couple.builder().direction(4).coord(Map.of(
                0,new Integer[]{2,0},
                1,new Integer[]{2,1},
                2,new Integer[]{2,2},
                3,new Integer[]{1,2},
                4,new Integer[]{0,1},
                5,new Integer[]{1,0})).build());
        couples.add(Couple.builder().direction(3).coord(Map.of(
                0,new Integer[]{1,1},
                1,new Integer[]{2,2},
                4,new Integer[]{0,2},
                5,new Integer[]{0,1})).build());
        return couples;
    }

    @Test
    public void hexagonCell(){
        // When
        MazeFactory factory = new HexagonFactory();
        List<Couple> couples = getHexaExpected();
        // Given
        cells=factory.createMaze(3,3).getCells();
        // Then
        assertNotNull( cells.getCurrentCell()); // 0 0
        couples.stream().forEach(c->{
            cells.goTo(c.direction);
            Iterator<Map.Entry<Integer, Integer[]>> iterator = c.coord.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer[]> entry = iterator.next();
                int dir = entry.getKey();
                Integer[] coord = entry.getValue();
                assertEquals(coord[0].intValue(),cells.getCurrentCell().getNeighbour(dir).x);
                assertEquals(coord[1].intValue(),cells.getCurrentCell().getNeighbour(dir).y);
            }
        });
    }

    @Test
    public void pathCreated(){
        // Given
        SquareCell sq = new SquareCell(0,0);
        SquareCell sq2 = new SquareCell(1,0);
        sq.makeAcquaintance(sq2);
        sq2.makeAcquaintance(sq);
        // When
        sq.createPath(sq2);
        sq2.createPath(sq);
        // Then
        assertEquals(0,sq.getSides()[1]);
        assertEquals(0,sq2.getSides()[3]);
    }

}
@Builder
@Getter
class Couple{
    int direction;
    Map<Integer,Integer[]> coord;
}
