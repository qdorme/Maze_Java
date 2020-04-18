package qdo.maze;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@Getter
@RequiredArgsConstructor()
public abstract class Maze {
    protected final int width;
    protected final int height;

    protected final Cells cells;

    protected final Renderer renderer;

    public BufferedImage draw(){
        return renderer.drawMaze(this);
    };
}
