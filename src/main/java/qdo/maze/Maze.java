package qdo.maze;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
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

    public Maze findExits() {
        cells.setCurrentCell(findOneExit());
        cells.all().stream().forEach(c-> c.setWeight(0));
        findOneExit();
        return this;
    }

    public Cell findOneExit() {
        List<Cell> nextGeneration = new LinkedList<>();
        nextGeneration.add(cells.getCurrentCell());
        AtomicInteger weight = new AtomicInteger(1);
        while(!nextGeneration.isEmpty()){
            List<Cell> tmp = new LinkedList<>();
            nextGeneration.stream().forEach(c->{
                c.setWeight(weight.get());
                c.path().stream().filter(u-> u.getWeight() == 0).forEach(tmp::add);
            });
            weight.incrementAndGet();
            nextGeneration.clear();
            nextGeneration.addAll(tmp);
        }

        Optional<Cell> furthest = cells.all().parallelStream().filter(Cell::isBorderCell)
                .sorted((cell, t1) -> t1.getWeight() - cell.getWeight())
        .findFirst();
        furthest.get().setExit(true);
        return furthest.get();
    }
}
