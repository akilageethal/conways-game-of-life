package main.java.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a cell in the gird with the status
 */
public class Cell extends Observable implements Observer{

    /**
     * state of the cell. alive = true, dead = false
     */
    private boolean alive;

    /**
     * x,y coordinate values of the cell
     */
    private int x;
    private int y;

    /**
     * Current live neighbour count of the cell
     */
    private int currentLiveNeighbourCount;

    /**
     * Next live neighbour count. Will be updated as the observing cells change the statuses
     */
    private int nextLiveNeighbourCount;

    public Cell(int xCoordinate, int yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    /**
     * When notified from other cell state changes, update the next neighbour count
     * @param o Cell which changed the state
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Cell cell = (Cell)o;
        if(cell.isAlive()) {
            this.incrementLiveNeighbours();
        } else {
            this.decrementLiveNeighbours();
        }
    }

    /**
     * Generate next state of the cell using the current alive neighbout count.
     * Set the state only when changing from the current
     */
    public void generateNextState() {
        if(this.isAlive()) {
            if(getCurrentLiveNeighbourCount() > 3 || getCurrentLiveNeighbourCount() < 2 ) {
                this.setState(false);
            }
        } else {
            if (getCurrentLiveNeighbourCount() == 3 ) {
                this.setState(true);
            }
        }
    }

    /**
     * Set new cell state and notify the observers.
     * @param alive new cell state
     */
    public void setState(boolean alive) {
        this.setAlive(alive);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Increment next live neighbour count. will be called when an observing dead cell turns alive
     */
    private void incrementLiveNeighbours() {
        this.setNextLiveNeighbourCount(this.getNextLiveNeighbourCount() + 1);
    }

    /**
     * Decrement next live neighbour count. WIll be called when an observing live cell turns dead
     */
    private void decrementLiveNeighbours() {
        int currentCount = this.getNextLiveNeighbourCount();
        if(currentCount > 0 ) {
            this.setNextLiveNeighbourCount(--currentCount);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getCurrentLiveNeighbourCount() {
        return currentLiveNeighbourCount;
    }

    public void setCurrentLiveNeighbourCount(int currentLiveNeighbourCount) {
        this.currentLiveNeighbourCount = currentLiveNeighbourCount;
    }

    public int getNextLiveNeighbourCount() {
        return nextLiveNeighbourCount;
    }

    public void setNextLiveNeighbourCount(int nextLiveNeighbourCount) {
        this.nextLiveNeighbourCount = nextLiveNeighbourCount;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
