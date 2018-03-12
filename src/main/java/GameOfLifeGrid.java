package main.java;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeGrid {

    private int width;
    private int height;

    private Cell[][] cellGird;

    public GameOfLifeGrid(String[] tempInputArr, int width, int height) {
        this.width = width;
        this.height = height;
        setUpCells();
        setUpNeighbourObservers();
        setInitialStates(tempInputArr);
    }

    private void setUpCells() {
        cellGird = new Cell[width][height];

        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
                cellGird[xCoordinate][yCoordinate] = new Cell(xCoordinate, yCoordinate);
            }
        }
    }

    private void setInitialStates(String[] tempInputArr) {
        tempInputArr[0] = tempInputArr[0].replace("[","");
        tempInputArr[tempInputArr.length -1 ] = tempInputArr[tempInputArr.length -1 ].replace("]","");
        for (String liveCoordinate : tempInputArr) {
            String[] coordinates = liveCoordinate.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            cellGird[x][y].setState(true);
        }
        generateOutPut();
    }

    public void generateNextState(int iteration) {

        for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
                cellGird[xCoordinate][yCoordinate].generateNextState();
            }
        }
        List<String> output = generateOutPut();
        System.out.println(iteration + ": " + output);
    }

    private List<String> generateOutPut() {
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

    private void setUpNeighbourObservers () {

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
}
