package battleship;

class Ship {
    private final String name;
    private final int length;
    private final int id;
    private int hits;

    Ship(String pName, int pLen, int pID) {
        this.name = pName;
        this.length = pLen;
        this.hits = pLen;
        this.id = pID;
    }

    String getName() {
        return name;
    }

    int getLength() {
        return length;
    }

    int getId() {
        return id;
    }

    void shipHit() {
        if (hits > 0) {
            hits--;
        }
    }

    boolean isSunk() {
        return hits == 0;
    }
}
