package qdo.maze.square;

import lombok.Setter;
import qdo.maze.Maze;
import qdo.maze.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

@Setter
public class SquareRenderer implements Renderer {

    private int cellSize = 12;

    @Override
    public BufferedImage drawMaze(Maze maze) {
        requireNonNull(maze);
        BufferedImage image = new BufferedImage(maze.getWidth()*cellSize+20,maze.getHeight()*cellSize+20,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,maze.getWidth()*cellSize+20,maze.getHeight()*cellSize+20);

        graphics.setColor(Color.BLACK);
        maze.getCells().all().forEach(c ->{
            if(c.getSides()[0]==-1)
                graphics.drawLine(c.getX()*cellSize+10,c.getY()*cellSize+10,c.getX()*cellSize+(10+cellSize),c.getY()*cellSize+10);
            if(c.getSides()[1]==-1)
                graphics.drawLine(c.getX()*cellSize+(10+cellSize),c.getY()*cellSize+10,c.getX()*cellSize+(10+cellSize),c.getY()*cellSize+(10+cellSize));
            if(c.getSides()[2]==-1)
                graphics.drawLine(c.getX()*cellSize+10,c.getY()*cellSize+(10+cellSize),c.getX()*cellSize+(10+cellSize),c.getY()*cellSize+(10+cellSize));
            if(c.getSides()[3]==-1)
                graphics.drawLine(c.getX()*cellSize+10,c.getY()*cellSize+10,c.getX()*cellSize+10,c.getY()*cellSize+(10+cellSize));
        });
        return image;
    }
}
