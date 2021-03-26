package battleship;

public class Coordinate {
    private final int row;
    private final int col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Coordinate(String coord) {
        this.row = "ABCDEFGHIJ".indexOf(coord.charAt(0));
        this.col = Integer.parseInt(coord.substring(1)) - 1;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
