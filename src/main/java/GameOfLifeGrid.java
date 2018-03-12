package main.java;

import main.java.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Grid which holds the cells and performs the actions on the grid
 * such as generating next states and printing output
 */
public class GameOfLifeGrid {

    /**
     * width of the grid
     */
    private int width;

    /**
     * height of the grid
     */
    private int height;

    private Cell[][] cellGird;

    public GameOfLifeGrid() {
    }

    public GameOfLifeGrid(String[] tempInputArr, int width, int height) {
        this.width = width;
        this.height = height;
        setUpCells();
        setUpNeighbourObservers();
        setInitialStates(tempInputArr);
    }

    /**
     * Setup the initial grid for given width and height.
     * All cell will be in dead state initially
     */
    public void setUpCells() {
        cellGird = new Cell[width][height];

        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
                cellGird[xCoordinate][yCoordinate] = new Cell(xCoordinate, yCoordinate);
            }
        }
    }

    /**
     * Set the initial alive cells according to the input.
     * @param tempInputArr string array with coordinates of the initial alive cells
     */
    public void setInitialStates(String[] tempInputArr) {
        tempInputArr[0] = tempInputArr[0].replace("[","");
        tempInputArr[tempInputArr.length -1 ] = tempInputArr[tempInputArr.length -1 ].replace("]","");
        for (String liveCoordinate : tempInputArr) {
            if (liveCoordinate != null && !liveCoordinate.isEmpty() && liveCoordinate.contains(",")) {
                String[] coordinates = liveCoordinate.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                cellGird[x][y].setState(true);
            }
        }
        generateOutPut(); // will call once to initially set the new neighbour count as the current neighbour count
    }

    /**
     * Generate next state. Will be called once for each iteration
     */
    public List<String> generateNextState() {

        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
                cellGird[xCoordinate][yCoordinate].generateNextState();
            }
        }
        return generateOutPut();
   }

    /**
     * Print the output for single iteration
     * @param iteration iteration number
     * @param output single iteration output
     */
    private void printStdOutput(int iteration, List<String> output)
    {
        System.out.println(iteration + ": " + output);
    }

    /**
     * Generate the output string from the live cells. Also set the new neighbour count as the current neighbour count
     * @return Output for single iteration.
     */
    public List<String> generateOutPut() {
        List<String> output = new ArrayList<String>();
        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
                Cell currentCell = cellGird[xCoordinate][yCoordinate];
                if(currentCell.isAlive()) {
                    output.add("[" + xCoordinate + ", " + yCoordinate + "]");
                }
                currentCell.setCurrentLiveNeighbourCount(currentCell.getNextLiveNeighbourCount());
            }
        }
        return output;
    }

    /**
     * Setup neighbour observers. Each cell will have upto 8 neighbours depending
     * on the location. Every time a state changes in one cell all the neighbours will
     * be notified so they will update their alive neighbour count for the next iteration
     */
    public void setUpNeighbourObservers () {

        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for ( int yCoordinate = 0; yCoordinate < height; yCoordinate++) {

                Cell cell = cellGird[xCoordinate][yCoordinate];

                if (xCoordinate > 0 && yCoordinate > 0){ // North West
                    cell.addObserver(cellGird[xCoordinate-1][yCoordinate-1]);
                }

                if (yCoordinate > 0){ // North
                    cell.addObserver(cellGird[xCoordinate][yCoordinate-1]);
                }

                if (yCoordinate > 0 && xCoordinate < width-1){ //North east
                    cell.addObserver(cellGird[xCoordinate+1][yCoordinate-1]);

                }
                if (xCoordinate > 0){ // West
                    cell.addObserver(cellGird[xCoordinate-1][yCoordinate]);
                }

                if (xCoordinate < width-1){ //East
                    cell.addObserver(cellGird[xCoordinate+1][yCoordinate]);
                }

                if (yCoordinate < height-1 && xCoordinate > 0){ //South west
                    cell.addObserver(cellGird[xCoordinate-1][yCoordinate+1]);
                }

                if (yCoordinate < height-1){ //South
                    cell.addObserver(cellGird[xCoordinate][yCoordinate+1]);
                }

                if (yCoordinate < height-1 && xCoordinate < width-1){ //South east
                    cell.addObserver(cellGird[xCoordinate+1][yCoordinate+1]);
                }
            }
        }
    }

    /**
     * Run the game for given number of iterations
     * @param iterations iterations
     */
    public void run(int iterations) {
        for (int iteration = 1; iteration<= iterations; iteration++) {
            List<String> iterationOutput = generateNextState();
            printStdOutput(iteration, iterationOutput);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Cell[][] getCellGird() {
        return cellGird;
    }

    public void setCellGird(Cell[][] cellGird) {
        this.cellGird = cellGird;
    }
}
