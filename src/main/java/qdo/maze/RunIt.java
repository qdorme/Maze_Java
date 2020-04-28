package qdo.maze;

import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.square.SquareFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RunIt {
    public static void main(String[] args) throws IOException {
        makeIt(new SquareFactory(), "square");
        makeIt(new HexagonFactory(), "hexa");
    }

    public static void makeIt(MazeFactory factory, String type) throws IOException {
        Maze maze = factory.createMaze(30, 30);
        BufferedImage image = maze.generate().findExits().draw();
        ImageIO.write(image,"png",new File("/maze_"+type+".png"));
    }
}
