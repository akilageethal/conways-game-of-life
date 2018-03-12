package main.java;

/**
 * Runner class for the game of life application.
 * Creates the grid oobject with the given width and height
 * and runs the game for given number of iterations
 */
public class GameOfLifeRunner {

    // below values should be ideally fetched from configurations.
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int ITERATIONS = 100;

    public static void main(String[] args) {
        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";

        String[] inputArray = input.split("\\],\\[");

        GameOfLifeGrid grid = new GameOfLifeGrid( inputArray, WIDTH,HEIGHT);
        grid.run(ITERATIONS);
    }
}
