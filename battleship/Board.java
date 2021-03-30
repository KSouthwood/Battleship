package battleship;

import java.util.Arrays;

public class Board {
    private final char[][] board;
    private final int ROWS = 10;
    private final int COLS = 10;

    Board() {
        board = new char[ROWS][COLS];
        for (var row : board) {
            Arrays.fill(row, Marks.FOG);
        }
    }

    public void printBoard(boolean mask) {
        System.out.print("\n  ");
        for (int col = 0; col < COLS; col++) {
            System.out.printf("%d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.printf("%c ", "ABCDEFGHIJ".charAt(row));
            for (int col = 0; col < COLS; col++) {
                System.out.printf("%c ", getCell(row, col, mask));
            }
            System.out.println();
        }

        System.out.println();
    }

    public char getCell(int row, int col, boolean mask) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return Marks.OFF;
        }

        char cell = board[row][col];
        if (cell == Marks.HIT || cell == Marks.MISS || cell == Marks.FOG) {
            return cell;
        }

        return mask ? Marks.SHIP : cell;
    }

    public void setCell(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public boolean checkShipPlacement(int rowStart, int rowEnd, int colStart, int colEnd) {
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
