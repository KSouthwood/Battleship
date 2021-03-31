package battleship;

class Player {
    final Board shipsBoard;
    final Board shotsBoard;
    final String name;
    Ship[] ships;

    Player(String pName) {
        this.shipsBoard = new Board();
        this.shotsBoard = new Board();
        this.name = pName;
        ships = new Ship[ShipSpecs.values().length];
        for (var ship : ShipSpecs.values()) {
            ships[ship.getId()] = new Ship(ship.getShipName(), ship.getLength(), ship.getId());
        }
    }
}
