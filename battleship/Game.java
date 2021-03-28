package battleship;

public class Game {
    private static Board shipBoard;
    private static Board shotBoard;
    private static final InputHandler input = new InputHandler();

    Game() {
        shipBoard = new Board();
        shotBoard = new Board();
    }

    public void startGame() {
        shipBoard.printBoard();
        placePlayerShips();
        playGame();
    }

    private void placePlayerShips() {
        String[] playerInput;
        Coordinate[] coordinates = new Coordinate[2];

        for (Ship ship : Ship.values()) {
            do {
                playerInput = input.getShipCoordinates(ship);
                for (int index = 0; index < playerInput.length; index++) {
                    coordinates[index] = new Coordinate(playerInput[index]);
                }

                if (doSwap(coordinates)) {
                    Coordinate temp = coordinates[0];
                    coordinates[0] = coordinates[1];
                    coordinates[1] = temp;
                }

            } while (!shipPlacement(coordinates, ship));
            shipBoard.printBoard();
        }
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
            System.out.println("Error: Ship must be placed vertically or horizontally, not diagonally.\n");
            return false;
        }

        if ((coordinates[1].getRow() - coordinates[0].getRow() +
                coordinates[1].getCol() - coordinates[0].getCol()) + 1 != ship.getLength()) {
            System.out.printf("Error: Length is wrong for the %s.\n\n", ship.getName());
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

    private void playGame() {
        System.out.println("The game starts!");
        playerFires();
    }

    private void playerFires() {
        shotBoard.printBoard();
        System.out.println("Take a shot!");
        Coordinate shot = input.getShot();
        if (shipBoard.getCell(shot.getRow(), shot.getCol()) == 'O') {
            shipBoard.setHIT(shot.getRow(), shot.getCol());
            shotBoard.setHIT(shot.getRow(), shot.getCol());
            shotBoard.printBoard();
            System.out.println("You hit a ship!");
            shipBoard.printBoard();
        }
        if (shipBoard.getCell(shot.getRow(), shot.getCol()) == '~') {
            shipBoard.setMIS(shot.getRow(), shot.getCol());
            shotBoard.setMIS(shot.getRow(), shot.getCol());
            shotBoard.printBoard();
            System.out.println("You missed!");
            shipBoard.printBoard();
        }
    }
}
