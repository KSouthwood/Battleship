package battleship;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {
    private final static Scanner GET_INPUT = new Scanner(System.in);

    public String prompt(String message) {
        System.out.printf("%s ", message);
        return GET_INPUT.nextLine().toUpperCase();
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
    public String[] getShipCoordinates(Ship ship) {
        String[] coordinates;

        while (true) {
            String msg = String.format("Enter the coordinates of %s (%d cells):", ship.getName(), ship.getLength());
            coordinates = prompt(msg).split(" ");
            if (coordinates.length == 2) {
                if (Pattern.matches("[A-J]([1-9]|10)$", coordinates[0]) &&
                        Pattern.matches("[A-J]([1-9]|10)$", coordinates[1])) {
                    break;
                }
                System.out.println("Error! Please enter a valid coordinate.\n");
            } else {
                System.out.println("Error! Please enter exactly TWO coordinates.\n");
            }
        }

        return coordinates;
    }

    public Coordinate getShot() {
        String coordinate;

        while (true) {
            coordinate = prompt("Where do you fire?");
            if (Pattern.matches("[A-J]([1-9]|10)$", coordinate)) {
                break;
            }
            System.out.println("Error! Invalid coordinate entered.\n");
        }

        return new Coordinate(coordinate);
    }
}
