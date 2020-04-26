package qdo.maze;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor()
public abstract class Maze {
    protected final int width;
    protected final int height;

    protected final Cells cells;

    protected final Renderer renderer;

    public BufferedImage draw(){
        return renderer.drawMaze(this);
    }

    public Maze generate() {
        for (int i = 0; i < cells.all().size(); i++) {
            if(!cells.getCurrentCell().hasUnvisitedNeighbour()){
                Optional<Cell> choosenOne = changeCurrentcell();
                if(choosenOne.isEmpty()) break;
                cells.setCellList(choosenOne.get());
            }
            List<Cell> unvisitedNeighbour = cells.getCurrentCell().getCells().stream()
                    .filter(Objects::nonNull)
                    .filter(Cell::isNotVisited)
                    .collect(Collectors.toList());
            Cell choosenCell = unvisitedNeighbour.get(ThreadLocalRandom.current().nextInt(unvisitedNeighbour.size()));
            cells.createPath(choosenCell);
        }
        return this;
    }

    private Optional<Cell> changeCurrentcell() {
        return cells.all().stream().parallel()
                            .filter(Cell::isVisited)
                            .filter(Cell::hasUnvisitedNeighbour)
                            .findAny();
    }


}
