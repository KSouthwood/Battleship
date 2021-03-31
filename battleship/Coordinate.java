package battleship;

class Coordinate {
    final int row;
    final int col;

    Coordinate(String coord) {
        this.row = "ABCDEFGHIJ".indexOf(coord.charAt(0));
        this.col = Integer.parseInt(coord.substring(1)) - 1;

    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}
