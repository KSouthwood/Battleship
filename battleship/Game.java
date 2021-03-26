package battleship;

import java.util.regex.Pattern;

public class Game {
    private static Board shipBoard;
    private static Board shotBoard;
    private static final InputHandler input = new InputHandler();
    private final Ship[] armada = new Ship[5];

    Game() {
        shipBoard = new Board();
        shotBoard = new Board();
    }

    public void startGame() {
        setArmada();
        shipBoard.printBoard();
        placePlayerShips();
    }

    private void placePlayerShips() {
        String[] playerInput;
        Coordinate[] coordinates = new Coordinate[2];
        int placeShip = 0;

        do {
            playerInput = getShipCoordinates(armada[placeShip]);

            for (int index = 0; index < 2; index++) {
                coordinates[index] = new Coordinate(playerInput[index]);
            }

            if (doSwap(coordinates)) {
                Coordinate temp = coordinates[0];
                coordinates[0] = coordinates[1];
                coordinates[1] = temp;
            }

            if (shipPlacement(coordinates, armada[placeShip])) {
                placeShip++;
                shipBoard.printBoard();
            }
        } while (placeShip < 5);

    }

    private boolean doSwap(Coordinate[] coordinates) {
        if (coordinates[0].getRow() == coordinates[1].getRow()) {
            if (coordinates[0].getCol() > coordinates[1].getCol()) {
                return true;
            }
        }

        return coordinates[0].getRow() > coordinates[1].getRow();
    }

    private boolean shipPlacement(Coordinate[] coordinates, Ship ship) {
        if (coordinates[0].getRow() != coordinates[1].getRow() &&
                coordinates[0].getCol() != coordinates[1].getCol()) {
            System.out.println("Error: Ship must be placed vertically or horizontally, not diagonally.");
            return false;
        }

        if ((coordinates[1].getRow() - coordinates[0].getRow() +
                coordinates[1].getCol() - coordinates[0].getCol()) + 1 != ship.getLength()) {
            System.out.println("Error: Length is wrong for the " + ship.getName());
            return false;
        }

        if (!shipBoard.checkShipPlacement(coordinates[0].getRow(), coordinates[1].getRow(),
                coordinates[0].getCol(), coordinates[1].getCol())) {
            return false;
        }

        for (int row = coordinates[0].getRow(); row <= coordinates[1].getRow(); row++) {
            for (int col = coordinates[0].getCol(); col <= coordinates[1].getCol(); col++) {
                shipBoard.setSHP(row, col);
            }
        }

        return true;
    }

    /**
     * Gets a pair of coordinates from the player.
     *
     * Get a pair of coordinates from the player for placing their ships and ensures they are valid. Does not check
     * if they are valid for ship length, etc. Only that the coordinates are valid for the board.
     *
     * @return String array guaranteed to be a length of two (2) and sorted
     * @param ship details of the ship being placed
     */
    private String[] getShipCoordinates(Ship ship) {
        String[] coordinates;

        while (true) {
            String msg = String.format("Enter the coordinates of %s (%d cells)", ship.getName(), ship.getLength());
            coordinates = input.prompt(msg).split(" ");
            if (coordinates.length == 2) {
                if (Pattern.matches("[A-J]([1-9]|10)$", coordinates[0]) &&
                        Pattern.matches("[A-J]([1-9]|10)$", coordinates[1])) {
                    break;
                }
                System.out.println("Error! Please enter a valid coordinate.");
            } else {
                System.out.println("Error! Please enter exactly TWO coordinates.");
            }
        }

        return coordinates;
    }

    private void setArmada() {
        armada[0] = new Ship("Aircraft Carrier", 5, 0);
        armada[1] = new Ship("Battleship", 4, 1);
        armada[2] = new Ship("Submarine", 3, 2);
        armada[3] = new Ship("Cruiser", 3, 3);
        armada[4] = new Ship("Destroyer", 2, 4);
    }
}
