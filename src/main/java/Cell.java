package main.java;

import java.util.Observable;
import java.util.Observer;

public class Cell extends Observable implements Observer{

    private boolean alive;
    private int x;
    private int y;

    private int currentLiveNeighbourCount;
    private int nextLiveNeighbourCount;

    public Cell(int xCoordinate, int yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    @Override
    public void update(Observable o, Object arg) {
        Cell cell = (Cell)o;
        if(cell.isAlive()) {
            this.incrementLiveNeighbours();
        } else {
            this.decrementLiveNeighbours();
        }
    }

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

    public void setState(boolean alive) {
        this.setAlive(alive);
        this.setChanged();
        this.notifyObservers();
    }

    private void incrementLiveNeighbours() {
        this.setNextLiveNeighbourCount(this.getNextLiveNeighbourCount() + 1);
    }

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
