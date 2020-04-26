package qdo.maze.hexagon;

import qdo.maze.Maze;
import qdo.maze.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.requireNonNull;

public class HexagonRenderer implements Renderer {

    @Override
    public BufferedImage drawMaze(Maze maze) {
        requireNonNull(maze);
        BufferedImage image = new BufferedImage(getImageWidth(maze), getImageHeight(maze), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        graphics.setColor(Color.BLACK);
        maze.getCells().all().forEach(c -> {
            int offsetLeft = 0;
            if (c.getY() % 2 == 1) offsetLeft = 7;
            if (c.getSides()[0] == -1)
                graphics.drawLine(c.getX() * 14 + 17 + offsetLeft, c.getY() * 12 + 10, c.getX() * 14 + 24 + offsetLeft, c.getY() * 12 + 14);
            if (c.getSides()[1] == -1)
                graphics.drawLine(c.getX() * 14 + 24 + offsetLeft, c.getY() * 12 + 14, c.getX() * 14 + 24 + offsetLeft, c.getY() * 12 + 22);
            if (c.getSides()[2] == -1)
                graphics.drawLine(c.getX() * 14 + 24 + offsetLeft, c.getY() * 12 + 22, c.getX() * 14 + 17 + offsetLeft, c.getY() * 12 + 26);
            if (c.getSides()[3] == -1)
                graphics.drawLine(c.getX() * 14 + 17 + offsetLeft, c.getY() * 12 + 26, c.getX() * 14 + 10 + offsetLeft, c.getY() * 12 + 22);
            if (c.getSides()[4] == -1)
                graphics.drawLine(c.getX() * 14 + 10 + offsetLeft, c.getY() * 12 + 22, c.getX() * 14 + 10 + offsetLeft, c.getY() * 12 + 14);
            if (c.getSides()[5] == -1)
                graphics.drawLine(c.getX() * 14 + 10 + offsetLeft, c.getY() * 12 + 14, c.getX() * 14 + 17 + offsetLeft, c.getY() * 12 + 10);
        });
        return image;
    }

    private int getImageWidth(Maze maze) {
        int plus = 0;
        if (maze.getHeight() > 1) plus = 7;
        return 14 * maze.getWidth() + 20 + plus;
    }

    private int getImageHeight(Maze maze) {
        return 20 + 12 * maze.getHeight() + 4;
    }
}
