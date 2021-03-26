package battleship;

public class Ship {
    private final String name;
    private final int shipNum;
    private final int length;

    Ship(String name, int len, int num) {
        this.name = name;
        this.length = len;
        this.shipNum = num;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
