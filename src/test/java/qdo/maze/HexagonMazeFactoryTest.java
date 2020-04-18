package qdo.maze;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import qdo.maze.hexagon.HexagonCell;
import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.hexagon.HexagonMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HexagonMazeFactoryTest {
    MazeFactory factory;

    @Before
    public void setUp(){
        factory = new HexagonFactory();
    }

    @Test
    public void factoryShouldCreateHexagonMaze(){
        // When
        Maze maze = factory.createMaze(10,10);
        // Then
        Assert.assertTrue(maze instanceof HexagonMaze);
        assertEquals(10, maze.getWidth());
        assertEquals(10, maze.getHeight());
    }

    @Test
    public void factoryShouldCreateHexagonCells(){
        // Given
        Maze maze = factory.createMaze(10,10);
        // When
        Cells cells = maze.getCells();
        // Then
        assertTrue(cells.getCurrentCell() instanceof HexagonCell);
        assertEquals(100, cells.all().size());
    }
}
