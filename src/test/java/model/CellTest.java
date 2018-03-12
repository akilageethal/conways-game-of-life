package test.java.model;

import main.java.model.Cell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CellTest {

    @Test
    public void testIncrementLiveNeighbours() {
        Cell cell = new Cell(1,1);
        cell.incrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 1);

        cell.incrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 2);

        cell.incrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 3);
    }

    @Test
    public void testDecrementLiveNeighbours() {
        Cell cell = new Cell(1,1);
        cell.setNextLiveNeighbourCount(2);
        cell.decrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 1);

        cell.decrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 0);

        cell.decrementLiveNeighbours();
        assertTrue(cell.getNextLiveNeighbourCount() == 0);
    }

    @Test
    public void testSetState() {
        Cell cell = new Cell(1,1);
        cell.setState(true);
        assertTrue(cell.isAlive());

        cell.setState(false);
        assertFalse(cell.isAlive());
    }
}
