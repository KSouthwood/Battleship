package battleship;

import java.util.Arrays;

class Board {
    private final char[][] board;
    private final int ROWS = 10;
    private final int COLS = 10;

    Board() {
        board = new char[ROWS][COLS];
        for (var row : board) {
            Arrays.fill(row, Marks.FOG);
        }
    }

    /**
     * Output the board in a nice formatted grid.
     *
     * Handles printing the board to System.out in a formatted grid.
     */
    void printBoard() {
        System.out.print("  ");
        for (int col = 0; col < COLS; col++) {
            System.out.printf("%d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.printf("%c ", "ABCDEFGHIJ".charAt(row));
            for (int col = 0; col < COLS; col++) {
                System.out.printf("%c ", getCell(row, col, true));
            }
            System.out.println();
        }
    }

    /**
     * Returns the contents of the specified cell.
     *
     * Gets the contents of the specified cell (with row and col) and returns it.
     * Translates the ship ID (0-4) to a SHIP if the mask parameter is true.
     * @param row cell row to get
     * @param col cell column to get
     * @param mask boolean to hide/not hide the ship ID
     * @return contents of the cell
     */
    char getCell(int row, int col, boolean mask) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return Marks.OFF;
        }

        char cell = board[row][col];
        if (cell == Marks.HIT || cell == Marks.MISS || cell == Marks.FOG) {
            return cell;
        }

        return mask ? Marks.SHIP : cell;
    }

    void setCell(int row, int col, char mark) {
        board[row][col] = mark;
    }

    boolean checkShipPlacement(int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                if (getCell(row - 1, col, true) == Marks.SHIP || getCell(row + 1, col, true) == Marks.SHIP ||
                        getCell(row, col - 1, true) == Marks.SHIP || getCell(row, col + 1, true) == Marks.SHIP) {
                    System.out.println("Error: Ship is placed too close to another ship! Try again.\n");
                    return false;
                }
            }
        }

        return true;
    }
}
