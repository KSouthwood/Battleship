package battleship;

import java.util.Arrays;

public class Board {
    private final char[][] board;
    // todo: can we move most of the following variables to a common class that we extend? Most of these are needed
    //  elsewhere or could be used to avoid duplication
    private final String ROW_ID = "ABCDEFGHIJ";
    private final int ROWS = 10;
    private final int COLS = 10;
    private final char FOG = '~';
    private final char HIT = 'X';
    private final char MIS = 'M';
    private final char SHP = 'O';
    private final char OFF = '#';

    Board() {
        board = new char[ROWS][COLS];
        for (var row : board) {
            Arrays.fill(row, FOG);
        }
    }

    public void printBoard() {
        System.out.print("  ");
        for (int col = 0; col < COLS; col++) {
            System.out.printf("%d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.printf("%c ", ROW_ID.charAt(row));
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public char getCell(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return '#';
        }

        return board[row][col];
    }

    public void setSHP(int row, int col) {
        board[row][col] = SHP;
    }

    public boolean checkShipPlacement(int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int row = rowStart - 1; row <= rowEnd + 1; row++) {
            for (int col = colStart - 1; col <= colEnd + 1; col++) {
                if (getCell(row, col) != FOG && getCell(row, col) != OFF) {
                    System.out.println("Error: Ship is placed too close to another ship! Try again.");
                    return false;
                }
            }
        }

        return true;
    }
}
