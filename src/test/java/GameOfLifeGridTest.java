package test.java;

import main.java.GameOfLifeGrid;
import main.java.model.Cell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameOfLifeGridTest {

    @Test
    public void testSetupCells() {
        GameOfLifeGrid grid = new GameOfLifeGrid();
        grid.setWidth(150);
        grid.setHeight(150);
        grid.setUpCells();

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                assertTrue(grid.getCellGird()[i][j].getX() == i);
                assertTrue(grid.getCellGird()[i][j].getY() == j);
            }
        }
    }

    @Test
    public void testSetInitialStates() {
        GameOfLifeGrid grid = new GameOfLifeGrid();
        grid.setWidth(200);
        grid.setHeight(200);
        grid.setUpCells();

        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";
        String[] inputArray = input.split("\\],\\[");

        grid.setInitialStates(inputArray);
        assertTrue(grid.getCellGird()[5][5].isAlive());
        assertTrue(grid.getCellGird()[6][5].isAlive());
        assertTrue(grid.getCellGird()[7][5].isAlive());
        assertTrue(grid.getCellGird()[5][6].isAlive());
        assertTrue(grid.getCellGird()[6][6].isAlive());
        assertTrue(grid.getCellGird()[7][6].isAlive());

        assertFalse(grid.getCellGird()[0][0].isAlive());
        assertFalse(grid.getCellGird()[100][100].isAlive());
        assertFalse(grid.getCellGird()[199][199].isAlive());
        assertFalse(grid.getCellGird()[58][68].isAlive());
    }

    @Test
    public void testSetUpNeighbourObservers() {

        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 200, 200);
        gameOfLifeGrid.setUpNeighbourObservers();

        assertTrue(gameOfLifeGrid.getCellGird()[0][0].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[199][199].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[1][0].countObservers() == 5);
        assertTrue(gameOfLifeGrid.getCellGird()[0][1].countObservers() == 5);
        assertTrue(gameOfLifeGrid.getCellGird()[52][68].countObservers() == 8);
        assertTrue(gameOfLifeGrid.getCellGird()[1][1].countObservers() == 8);
        assertTrue(gameOfLifeGrid.getCellGird()[199][0].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[0][199].countObservers() == 3);

    }

    @Test
    public void testSetUpNeighbourObserversFor2By2() {

        String input = "[0,0]";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 2, 2);
        gameOfLifeGrid.setUpNeighbourObservers();

        assertTrue(gameOfLifeGrid.getCellGird()[0][0].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[0][1].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[1][0].countObservers() == 3);
        assertTrue(gameOfLifeGrid.getCellGird()[1][1].countObservers() == 3);
    }

    @Test
    public void testSetUpNeighbourObserversForOneElement() {

        String input = "[0,0]";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 1, 1);
        gameOfLifeGrid.setUpNeighbourObservers();
        assertTrue(gameOfLifeGrid.getCellGird()[0][0].countObservers() == 0);
    }

    @Test
    public void testGenerateNextState() {
        GameOfLifeGrid grid = new GameOfLifeGrid();
        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 200, 200);
        assertEquals("[[5, 5], [5, 6], [6, 4], [6, 7], [7, 5], [7, 6]]", gameOfLifeGrid.generateNextState().toString());
        assertEquals("[[5, 5], [5, 6], [6, 4], [6, 7], [7, 5], [7, 6]]", gameOfLifeGrid.generateNextState().toString());
        assertEquals("[[5, 5], [5, 6], [6, 4], [6, 7], [7, 5], [7, 6]]", gameOfLifeGrid.generateNextState().toString());
        assertEquals("[[5, 5], [5, 6], [6, 4], [6, 7], [7, 5], [7, 6]]", gameOfLifeGrid.generateNextState().toString());
    }

    @Test
    public void testGenerateNextStateForEmptyArray() {
        GameOfLifeGrid grid = new GameOfLifeGrid();
        String input = "";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 200, 200);
        System.out.println(gameOfLifeGrid.generateNextState().toString());
        assertEquals("[]", gameOfLifeGrid.generateNextState().toString());
    }

    @Test
    public void testGenerateOutput() {
        GameOfLifeGrid grid = new GameOfLifeGrid();
        String input = "[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]";
        String[] inputArray = input.split("\\],\\[");
        GameOfLifeGrid gameOfLifeGrid = new GameOfLifeGrid(inputArray, 200, 200);
        assertEquals("[[5, 5], [5, 6], [6, 5], [6, 6], [7, 5], [7, 6]]", gameOfLifeGrid.generateOutPut().toString());
    }
}