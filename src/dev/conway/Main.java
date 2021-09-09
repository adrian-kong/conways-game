package dev.conway;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // list of input
        List<Cell> input = Arrays.asList(
                new Cell(5, 5),
                new Cell(6, 5),
                new Cell(7, 5),
                new Cell(5, 6),
                new Cell(6, 6),
                new Cell(7, 6)
        );

        // game of life
        ConwaysGame game = new ConwaysGame(input);

        // state 0
        System.out.println(game.outputString());

        // state 1 - 100
        for (int i = 0; i < 100; i++) {
            System.out.println(game.next().outputString());
        }
    }
}
