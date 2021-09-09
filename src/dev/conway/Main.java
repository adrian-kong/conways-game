package dev.conway;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ConwaysGame game = new ConwaysGame(Arrays.asList(
                new Cell(5, 5),
                new Cell(6, 5),
                new Cell(7, 5),
                new Cell(5, 6),
                new Cell(6, 6),
                new Cell(7, 6)
        ));
        System.out.println(game.outputString());
        for (int i = 0; i < 3; i++) {
            System.out.println(game.next().outputString());
        }
    }
}
