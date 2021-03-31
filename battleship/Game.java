package battleship;

public class Game {
    private static final Player[] players = new Player[2];
    private static final InputHandler input = new InputHandler();

    void startGame() {
        getPlayerNames();
        placePlayerShips(players[0]);
        input.waitForEnter(String.format("Press Enter and let %s place their ships.", players[1].name));
        input.clearScreen();
        placePlayerShips(players[1]);
        input.waitForEnter(String.format("Press Enter to start the game. %s's up!", players[0].name));
        input.clearScreen();
        playGame();
    }

    private void getPlayerNames() {
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
    }

    /**
     * Place ships on the board.
     *
     * Goes through and places each ship one by one until all are successfully placed
     * for each player.
     *
     * @param player for whom we're placing ships
     */
    private void placePlayerShips(Player player) {
        String[] playerInput;
        Coordinate[] coordinates = new Coordinate[2];

        System.out.printf("%s, place your ships on the game field.%n%n", player.name);
        player.shipsBoard.printBoard();

        for (Ship ship : player.ships) {
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

            } while (!shipPlacement(player.shipsBoard, coordinates, ship));
            player.shipsBoard.printBoard();
            System.out.println();
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

    private boolean shipPlacement(Board shipBoard, Coordinate[] coordinates, Ship ship) {
        // check that either the rows or columns are the same in the coordinates. If both are
        // different, the coordinates are invalid for placing a ship
        if (coordinates[0].getRow() != coordinates[1].getRow() &&
                coordinates[0].getCol() != coordinates[1].getCol()) {
            System.out.println("Error: Ship must be placed vertically or horizontally, not diagonally.\n");
            return false;
        }

        // check that the coordinates entered are the same spaces as the ship length
        if ((coordinates[1].getRow() - coordinates[0].getRow()) +
                (coordinates[1].getCol() - coordinates[0].getCol()) + 1 != ship.getLength()) {
            System.out.printf("Error: Length is wrong for the %s.\n\n", ship.getName());
            return false;
        }

        // checks if we're placing on/up against another ship
        if (!shipBoard.checkShipPlacement(coordinates[0].getRow(), coordinates[1].getRow(),
                coordinates[0].getCol(), coordinates[1].getCol())) {
            return false;
        }

        // places the ship in the correct spaces, using the ship ID to mark the spot
        for (int row = coordinates[0].getRow(); row <= coordinates[1].getRow(); row++) {
            for (int col = coordinates[0].getCol(); col <= coordinates[1].getCol(); col++) {
                shipBoard.setCell(row, col, Character.forDigit(ship.getId(), 10));
            }
        }

        return true;
    }

    private void playGame() {
        int playerUp = 0;
        boolean gameOver;

        do {
            playerTurn(playerUp);
            playerUp = (playerUp + 1) % 2;
            gameOver = shipsFloating(playerUp);
            if (gameOver) {
                input.waitForEnter("Press Enter and pass the move to another player");
            }
        } while (gameOver);

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void playerTurn(int playerUp) {
        players[playerUp].shotsBoard.printBoard();
        System.out.println("---------------------");
        players[playerUp].shipsBoard.printBoard();
        System.out.printf("\n%s, it's your turn. ", players[playerUp].name);
        playerFires(playerUp);
    }

    private void playerFires(int shooting) {
        Coordinate shot = input.getShot();
        int target = (shooting + 1) % 2;
        char masked = players[target].shipsBoard.getCell(shot.row, shot.col, true);
        char actual = players[target].shipsBoard.getCell(shot.row, shot.col, false);

        switch (masked) {
            case (Marks.SHIP):
                int id = Character.digit(actual, 10);
                markCells(shooting, target, shot.row, shot.col, Marks.HIT);
                String result = "hit";
                Ship ship = players[target].ships[id];
                ship.shipHit();
                if (ship.isSunk()) {
                    result = "sank";
                }
                System.out.printf("You %s a ship!\n", result);
                break;
            case (Marks.FOG):
                markCells(shooting, target, shot.getRow(), shot.getCol(), Marks.MISS);
                System.out.println("You missed!");
                break;
            default:
                System.out.println("You already took that shot!");
                break;
        }
    }

    private void markCells(int shooter, int target, int row, int col, char mark) {
        players[target].shipsBoard.setCell(row, col, mark);
        players[shooter].shotsBoard.setCell(row, col, mark);
    }

    private boolean shipsFloating(int target) {
        boolean afloat = false;
        for (var ship : players[target].ships) {
            if (!ship.isSunk()) {
                afloat = true;
                break;
            }
        }

        return afloat;
    }
}
