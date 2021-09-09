package dev.conway;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Kong
 * @project conways-game
 */
public class ConwaysGame {

    // constant height and width
    private final static int WIDTH = 200, HEIGHT = 200;

    // list of currently alive cells
    private List<Cell> aliveCells;

    // game grid defined by true(alive) and false(dead) states
    private final boolean[][] grid;

    // state counter
    private int state;

    /**
     * Constructor for Conways Game
     * Initializes a 200 x 200 boolean matrix
     *
     * @param aliveCells list of alive Cells as the initial input
     */
    public ConwaysGame(List<Cell> aliveCells) {
        this.aliveCells = aliveCells;
        // initializes all cells to be dead
        grid = new boolean[WIDTH][HEIGHT];

        updateGrid(aliveCells);
    }

    /**
     * Determines whether the cell at given coordinate should survive for next state
     *
     * @param x location of target cell
     * @param y location of target cell
     * @return whether the target cell should survive
     */
    private boolean shouldLive(int x, int y) {
        int aliveNeighbours = 0;

        // all the possible directions
        for (Direction direction : Direction.values()) {
            if (direction.withinBounds(x, y)) {
                Cell neighbour = direction.apply(x, y);
                if (grid[neighbour.getX()][neighbour.getY()]) {
                    aliveNeighbours += 1;
                }
            }
        }
        // cell survives if there are 3 neighbours alive OR is alive and 2 neighbours are alive
        return aliveNeighbours == 3 || (aliveNeighbours == 2 && grid[x][y]);
    }

    /**
     * Check all the cells in the grid for next state changes
     */
    private void updateCells() {
        // list of changes that will be applied in the next state
        List<Cell> nextGen = new ArrayList<>();

        // iterate through entire board and append next generation cells
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (shouldLive(i, j)) {
                    nextGen.add(new Cell(i, j));
                }
            }
        }

        // update the grid
        updateGrid(nextGen);
    }

    /**
     * Update the boolean grid with respect to the new cell states
     *
     * @param aliveCells list of alive cell locations to be toggled in grid
     */
    private void updateGrid(List<Cell> aliveCells) {
        // need to remove the previous alive cells / reset the grid
        if (this.aliveCells != aliveCells) {
            this.aliveCells.forEach(cell -> grid[cell.getX()][cell.getY()] = false);
            this.aliveCells = aliveCells;
        }
        // update all the alive cells to the grid
        aliveCells.forEach(cell -> grid[cell.getX()][cell.getY()] = true);
    }

    /**
     * [[x, y], [x, y] ...] etc
     *
     * @return pretty print of the current alive cells as intended in instructions
     */
    public String outputString() {
        // maps the alive cells to a list of '[x, y]' strings
        String[] array = aliveCells.stream().map(Cell::toString).toArray(String[]::new);
        return String.format("%d: [%s]", state, String.join(", ", array));
    }

    /**
     * @return the next state of the game
     */
    public ConwaysGame next() {
        updateCells();
        state += 1;
        return this;
    }

    /**
     * Possible adjacent direction vectors
     */
    enum Direction {
        TOP_LEFT(-1, 1),
        TOP_CENTER(0, 1),
        TOP_RIGHT(1, 1),

        MID_LEFT(-1, 0),
        MID_RIGHT(1, 0),

        BOT_LEFT(-1, -1),
        BOT_CENTER(0, -1),
        BOT_RIGHT(1, -1);

        int dx;
        int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        /**
         * @param x location of the current cell
         * @param y location of the current cell
         * @return whether the neighbour cell would be out of bounds
         */
        public boolean withinBounds(int x, int y) {
            return x + dx >= 0 && x + dx < WIDTH && y + dy >= 0 && y + dy < HEIGHT;
        }

        /**
         * @param x location of the current cell
         * @param y location of the current cell
         * @return the neighbour cell [x + dx, y + dy]
         */
        public Cell apply(int x, int y) {
            return new Cell(x + dx, y + dy);
        }
    }
}
