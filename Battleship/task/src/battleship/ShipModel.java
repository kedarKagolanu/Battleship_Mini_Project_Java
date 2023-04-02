package battleship;

public enum ShipModel {

    AIRCRAFTCARRIER("Aircraft Carrier",5),BATTLESHIP("Battleship",4),SUBMARINE("Submarine",3),CRUISER("Cruiser",3),DESTROYER("Destroyer",2);

    String modelName;
    int modelLength;

    ShipModel(String modelName,int modelLength) {
        this.modelName = modelName;
        this.modelLength = modelLength;
    }

    public String getModelName() {
        return modelName;
    }

    public int getModelLength() {
        return modelLength;
    }
}
