package battleship;

enum ShipSpecs {
    CARRIER("Aircraft Carrier", 5, 0),
    BATTLESHIP("Battleship", 4, 1),
    SUBMARINE("Submarine", 3, 2),
    CRUISER("Cruiser", 3, 3),
    DESTROYER("Destroyer", 2, 4);

    private final String shipName;
    private final int length;
    private final int id;

    ShipSpecs(String shipName, int len, int num) {
        this.shipName = shipName;
        this.length = len;
        this.id = num;
    }

    String getShipName() {
        return shipName;
    }

    int getLength() {
        return length;
    }

    int getId() {
        return id;
    }
}