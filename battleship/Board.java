package battleship;

import java.util.Arrays;

public class Board {
    private final char[][] board;
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
        System.out.print("\n  ");
        for (int col = 0; col < COLS; col++) {
            System.out.printf("%d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.printf("%c ", "ABCDEFGHIJ".charAt(row));
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public char getCell(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return OFF;
        }

        return board[row][col];
    }

    public void setSHP(int row, int col) {
        board[row][col] = SHP;
    }

    public void setHIT(int row, int col) {
        board[row][col] = HIT;
    }

    public void setMIS(int row, int col) {
        board[row][col] = MIS;
    }

    public boolean checkShipPlacement(int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                if (getCell(row - 1, col) == SHP || getCell(row + 1, col) == SHP ||
                        getCell(row, col - 1) == SHP || getCell(row, col + 1) == SHP) {
                    System.out.println("Error: Ship is placed too close to another ship! Try again.\n");
                    return false;
                }
            }
        }

        return true;
    }
}
