package qdo.maze;

import org.junit.Test;
import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.square.SquareFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

public class MazeTest {

    @Test
    public void createMaze_should_visit_all_cells() throws IOException {
        // Given
        MazeFactory factory = new HexagonFactory();
        Maze maze = factory.createMaze(3, 3);
        // When
        maze.generate();
        // Then
        Optional<Cell> any = maze.cells.all().stream().filter(Cell::isNotVisited).findAny();
        assertFalse(any.isPresent());

        BufferedImage result = maze.draw();
        ImageIO.write(result,"png",new File("/test3.png"));
    }

    @Test
    public void should_find_exits() throws IOException {
        // Given
        Maze maze = new SquareFactory().createMaze(4, 4);
        Cells cells = maze.getCells();
        Cell firstExit = cells.getCurrentCell();
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(3));
        cells.createPath(cells.getCurrentCell().getNeighbour(3));
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        Cell fork = cells.getCurrentCell();
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(0));
        cells.createPath(cells.getCurrentCell().getNeighbour(0));
        Cell secondExit = cells.getCurrentCell();
        cells.setCurrentCell(fork);
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.setCurrentCell(firstExit);
        // When
        maze.findExits();
        // Then
        assertTrue(firstExit.isExit());
        assertTrue(secondExit.isExit());
        assertEquals(2, maze.getCells().all().stream().filter(Cell::isExit).count());

        BufferedImage result = maze.draw();
        ImageIO.write(result,"png",new File("/test_with_exits.png"));
    }

}
