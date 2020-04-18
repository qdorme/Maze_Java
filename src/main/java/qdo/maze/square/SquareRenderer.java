package qdo.maze.square;

import qdo.maze.Maze;
import qdo.maze.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

public class SquareRenderer extends Renderer {

    @Override
    public BufferedImage drawMaze(Maze maze) {
        requireNonNull(maze);
        BufferedImage image = new BufferedImage(maze.getWidth()*10+20,maze.getHeight()*10+20,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,maze.getWidth()*10+20,maze.getHeight()*10+20);

        graphics.setColor(Color.BLACK);
        maze.getCells().all().forEach(c ->{
            if(c.getSides()[0]==-1)
                graphics.drawLine(c.getX()*10+10,c.getY()*10+10,c.getX()*10+20,c.getY()*10+10);
            if(c.getSides()[1]==-1)
                graphics.drawLine(c.getX()*10+20,c.getY()*10+10,c.getX()*10+20,c.getY()*10+20);
            if(c.getSides()[2]==-1)
                graphics.drawLine(c.getX()*10+10,c.getY()*10+20,c.getX()*10+20,c.getY()*10+20);
            if(c.getSides()[3]==-1)
                graphics.drawLine(c.getX()*10+10,c.getY()*10+10,c.getX()*10+10,c.getY()*10+20);
        });
        return image;
    }
}
