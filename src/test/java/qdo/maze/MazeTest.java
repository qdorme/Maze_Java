package qdo.maze;

import org.junit.Test;
import qdo.maze.hexagon.HexagonFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

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

}
