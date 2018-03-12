package main.java;

import main.java.io.InputReader;

import java.util.Scanner;

/**
 * Runner class for the game of life application.
 * Creates the grid oobject with the given width and height
 * and runs the game for given number of iterations
 */
public class GameOfLifeRunner {

    // below values should be [],ideally fetched from configurations.
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int ITERATIONS = 100;

    public static void main(String[] args) {

        String inputString = null;
        // Read the input.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputString = scanner.nextLine();
            System.out.println(inputString);
            if (inputString == null || inputString.isEmpty()) {
                System.out.println("Invalid Input");
                scanner.close();
                return;
            }

            String[] inputArray = inputString.split("\\],\\[");

            GameOfLifeGrid grid = new GameOfLifeGrid(inputArray, WIDTH, HEIGHT);
            grid.run(ITERATIONS);
        }
        scanner.close();
    }
}
