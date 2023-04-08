package battleship;

public class Ship {

    private final ShipModel shipModel;
    private Player player;

    PositionCoordinates shipPosition;

    public Ship(ShipModel model) {
        shipModel = model;
    }

    public void setPositionInField(Player p,int row1,int row2,int col1,int col2) throws Exception{
        this.player = p;
        this.shipPosition = new PositionCoordinates(row1,row2,col1,col2);
        player.getFieldManager().setPosition(row1,row2,col1,col2,shipModel);
    }

    public boolean isDown() {
        return player.getFieldManager().isShipDestryed(this);
    }

    public ShipModel getShipModel() {
        return shipModel;
    }


    public PositionCoordinates getShipPosition() {
        return shipPosition;
    }
}
