package qdo.maze;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellsTest.class,
        SquareMazeFactoryTest.class,
        HexagonMazeFactoryTest.class,
        RendererTest.class,
        MazeTest.class
})
public class SuiteTests {
}
