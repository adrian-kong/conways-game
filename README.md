# Conway's Game of Life

A simple Java implementation of Conway's game of life

## Note

- Only tested on 200x200 grid
- Input is taken as a list of `Cells`, a record for x and y data (easier to parse and understand)
- Output is presented as `states: list of active cells` ordered by their x then y coordinate

## Download

See releases: https://github.com/adrian-kong/conways-game/releases/tag/v1.0

## Setup

Uses *Java 16*, simply clone this project and run via `DemoMain` or use as a library (See `DemoMain`)

Sample Usage:

```java
        // list of Cell objects with x, y data used as input
        List<Cell> input=Arrays.asList(
            new Cell(5, 5),
            new Cell(6, 5),
            new Cell(7, 5),
            new Cell(5, 6),
            new Cell(6, 6),
            new Cell(7, 6)
        );

        // initialize the game of life implementation
        ConwaysGame game = new ConwaysGame(input);

        // state 0
        System.out.println(game.outputString());

        // state 1 - 100
        for (int i=0; i < 100; i++) {
            // ConwaysGame#next() provides the next state of grid and returns itself
            System.out.println(game.next().outputString());
        }
```

