package battleship;

public enum Ship {
    CARRIER("Aircraft Carrier", 5, 0),
    BATTLESHIP("Battleship", 4, 1),
    SUBMARINE("Submarine", 3, 2),
    CRUISER("Cruiser", 3, 3),
    DESTROYER("Destroyer", 2, 4);

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

    public int getShipNum() {
        return shipNum;
    }
}
