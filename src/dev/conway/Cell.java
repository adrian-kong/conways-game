package dev.conway;

/**
 * Cell record used to determine coordinate of each alive cell;
 * replacement of int[] as coordinates, easier to read and parse
 */
public record Cell(int x, int y) {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @return [x, y] as intended in instructions
     */
    @Override
    public String toString() {
        return String.format("[%d, %d]", x, y);
    }
}
