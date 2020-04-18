package qdo.maze;

import org.junit.Before;
import org.junit.Test;
import qdo.maze.square.SquareMaze;
import qdo.maze.square.SquareFactory;
import qdo.maze.square.SquareCell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquareMazeFactoryTest {

    private SquareFactory factory;

    @Before
    public void setUp(){
        factory = new SquareFactory();
    }

    @Test
    public void factoryShouldCreateSquareMaze(){
        // When
        Maze maze = factory.createMaze(10,10);
        // Then
        assertTrue(maze instanceof SquareMaze);
        assertEquals(10, maze.getWidth());
        assertEquals(10, maze.getHeight());
    }

    @Test
    public void factoryShouldCreateSquareCells(){
        // Given
        Maze maze = factory.createMaze(10,10);
        // When
        Cells cells = maze.getCells();
        // Then
        assertTrue(cells.getCurrentCell() instanceof SquareCell);
        assertEquals(100, cells.all().size());
    }
}
