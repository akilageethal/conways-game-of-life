package main.java;

public class GameOfLifeRunner {

    public static void main(String[] args) {
        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";
        int iterations = 100;

        String[] tempInputArr = input.split("\\],\\[");

        GameOfLifeGrid grid = new GameOfLifeGrid( tempInputArr, 9,9);

        for (int iteration = 1; iteration <= iterations; iteration++) {
            grid.generateNextState(iteration);
        }

    }
}
