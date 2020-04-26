package qdo.maze;

import java.util.List;

@FunctionalInterface
public interface LinkLines {
    public void doIt(Cells cells, List<Cell> aboveLine, List<Cell> belowLine);
}
