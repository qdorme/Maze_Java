package qdo.maze;

import org.junit.Assert;
import org.junit.Test;
import qdo.maze.hexagon.HexagonFactory;
import qdo.maze.square.SquareFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RendererTest {

    @Test
    public void should_display_a_square_grid() throws IOException {
        // Given
        BufferedImage target = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
        Graphics gTarget = target.getGraphics();
        gTarget.setColor(Color.WHITE);
        gTarget.fillRect(0,0,40,40);
        gTarget.setColor(Color.BLACK);
        gTarget.drawLine(10,10,30,10);
        gTarget.drawLine(10,20,30,20);
        gTarget.drawLine(10,30,30,30);
        gTarget.drawLine(10,10,10,30);
        gTarget.drawLine(20,10,20,30);
        gTarget.drawLine(30,10,30,30);

//        ImageIO.write(target,"png",new File("/test.png"));

        SquareFactory factory = new SquareFactory();
        Maze maze = factory.createMaze(2, 2);
        // when
        BufferedImage result = maze.draw();
        // Then
        Assert.assertTrue(compareImages(target, result));
    }

    @Test
    public void should_display_a_square_path() throws IOException {
        // Given
        BufferedImage target = new BufferedImage(40,40,BufferedImage.TYPE_INT_RGB);
        Graphics gTarget = target.getGraphics();
        gTarget.setColor(Color.WHITE);
        gTarget.fillRect(0,0,40,40);
        gTarget.setColor(Color.BLACK);
        gTarget.drawLine(10,10,30,10);
        gTarget.drawLine(10,20,20,20);
        gTarget.drawLine(10,30,30,30);
        gTarget.drawLine(10,10,10,30);
        gTarget.drawLine(30,10,30,30);

        MazeFactory factory = new SquareFactory();
        Maze maze = factory.createMaze(2, 2);
        Cells cells = maze.getCells();
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(3));
        // when
        BufferedImage result = maze.draw();
        // Then
        Assert.assertTrue(compareImages(target, result));
    }

    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void should_display_an_hexagone() throws IOException {
        // Given
        BufferedImage target = new BufferedImage(34,36,BufferedImage.TYPE_INT_RGB);
        Graphics gTarget = target.getGraphics();
        gTarget.setColor(Color.WHITE);
        gTarget.fillRect(0,0,34,36);
        gTarget.setColor(Color.BLACK);
        gTarget.drawLine(17,10,24,14);
        gTarget.drawLine(24,14,24,22);
        gTarget.drawLine(24,22,17,26);
        gTarget.drawLine(17,26,10,22);
        gTarget.drawLine(10,22,10,14);
        gTarget.drawLine(10,14,17,10);


        MazeFactory factory = new HexagonFactory();
        Maze maze = factory.createMaze(1, 1);
        // when
        BufferedImage result = maze.draw();
        // Then
        Assert.assertTrue(compareImages(target, result));
    }

    @Test
    public void should_display_an_haxagon_path() throws IOException {
        // Given
        BufferedImage target = new BufferedImage(55,48,BufferedImage.TYPE_INT_RGB);
        Graphics gTarget = target.getGraphics();
        gTarget.setColor(Color.WHITE);
        gTarget.fillRect(0,0,target.getWidth(),target.getHeight());
        gTarget.setColor(Color.BLACK);
        gTarget.drawLine(10,14,17,10);
        gTarget.drawLine(17,10,24,14);
        gTarget.drawLine(24,14,31,10);
        gTarget.drawLine(31,10,38,14);
        gTarget.drawLine(10,14,10,22);
        gTarget.drawLine(24,14,24,22);
        gTarget.drawLine(38,14,38,22);
        gTarget.drawLine(10,22,17,26);
        gTarget.drawLine(24,22,31,26);
        gTarget.drawLine(38,22,45,26);
        gTarget.drawLine(17,26,17,34);
        gTarget.drawLine(45,26,45,34);
        gTarget.drawLine(17,34,24,38);
        gTarget.drawLine(24,38,31,34);
        gTarget.drawLine(31,34,38,38);
        gTarget.drawLine(38,38,45,34);

        MazeFactory factory = new HexagonFactory();
        Maze maze = factory.createMaze(2, 2);
        Cells cells = maze.getCells();
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(5));
        // when
        BufferedImage result = maze.draw();
        // Then
        Assert.assertTrue(compareImages(target, result));

    }

    @Test
    public void produce_my_path_image() throws IOException {

        MazeFactory factory = new HexagonFactory();
        Maze maze = factory.createMaze(3, 4);
        Cells cells = maze.getCells();
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(3));
        cells.createPath(cells.getCurrentCell().getNeighbour(2));
        cells.createPath(cells.getCurrentCell().getNeighbour(4));
        cells.createPath(cells.getCurrentCell().getNeighbour(4));
        cells.createPath(cells.getCurrentCell().getNeighbour(5));
        cells.createPath(cells.getCurrentCell().getNeighbour(0));
        cells.createPath(cells.getCurrentCell().getNeighbour(1));
        cells.createPath(cells.getCurrentCell().getNeighbour(3));

        BufferedImage result = maze.draw();

        ImageIO.write(result,"png",new File("/my_path.png"));
    }
}
