package qdo.maze;

import org.junit.Before;
import org.junit.Test;
import qdo.maze.exception.InvalidDirectionException;
import qdo.maze.hexagon.HexagonCell;
import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.square.SquareCell;
import qdo.maze.square.SquareFactory;

import java.util.LinkedList;

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
        assertNotNull(cells.currentCell);
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
    public void hexagonCell(){
        // When
        MazeFactory factory = new HexagonFactory();
        // Given
        cells=factory.createMaze(4,4).getCells();
        // Then
        assertNotNull( cells.currentCell);
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
