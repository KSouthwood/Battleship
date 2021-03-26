package battleship;

import java.util.Scanner;

public class InputHandler {
    private final static Scanner GET_INPUT = new Scanner(System.in);

    public String prompt(String message) {
        System.out.printf("%s: ", message);
        return GET_INPUT.nextLine();
    }
}
