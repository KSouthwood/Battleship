package battleship;

public enum Ship {
    CARRIER("Aircraft Carrier", 5, 0),
    BATTLESHIP("Battleship", 4, 1),
    SUBMARINE("Submarine", 3, 2),
    CRUISER("Cruiser", 3, 3),
    DESTROYER("Destroyer", 2, 4);

    private final String shipName;
    private final int length;
    private final int id;
    private int hits;

    Ship(String shipName, int len, int num) {
        this.shipName = shipName;
        this.length = len;
        this.id = num;
        this.hits = len;
    }

    public String getShipName() {
        return shipName;
    }

    public int getLength() {
        return length;
    }

    public int getId() {
        return id;
    }

    public void shipHit() {
        hits--;
    }

    public boolean isSunk() {
        return hits == 0;
    }
}