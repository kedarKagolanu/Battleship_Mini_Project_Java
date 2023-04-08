package battleship;

public class Validator {

    private final Field field;

    private ShipModel tempShipModel;
    private int tempLowerRowLimit;
    private int tempLowerColumnLimit;
    private int tempUpperRowLimit;
    private int tempUpperColumnLimit;

    public Validator(Field field) {
        this.field = field;
    }

    public void checkPosition(PositionCoordinates position,ShipModel shipModel) throws Exception{

        tempLowerRowLimit = position.getLowerRowLimit();
        tempLowerColumnLimit = position.getLowerColumnLimit();
        tempUpperRowLimit = position.getUpperRowLimit();
        tempUpperColumnLimit = position.getUpperColumnLimit();
        tempShipModel = shipModel;

        validatePosition();
    }

    private void validatePosition() throws Exception{
        // checking if the ship position is diagonal or not. If diagonal, throwing an Exception.
        checkingArrangement();

        // checking if the positions are not out of the field limits.
        checkingPositionInField();

        // checking if there are any ships near the position.
        // decremented the "top-left" coordinates and incremented the "bottom-right" coordinates to iterate the cells surrounding the ship position.
        // if the seatch area is out of field ((-1,5),(1,14),(40,23), etc), then we avoid them or not search them as they don't exist.
        checkingAreaCovered();

        // checking if the ship position is vertical or horizontal

        // checking if the vertical and horizontal length of the ship is same as that of its model.
        checkingAreaLength();
    }

    private void checkingArrangement() throws Exception {
        if(tempLowerRowLimit != tempUpperRowLimit && tempLowerColumnLimit != tempUpperColumnLimit) {
            throw new Exception("Error! Wrong ship Co-ordinates! Try again:");
        }
    }

    private void checkingPositionInField() throws Exception {
        if(tempLowerRowLimit > field.verticalLength || tempLowerRowLimit < 0
                || tempUpperRowLimit > field.verticalLength || tempUpperRowLimit < 0
                || tempLowerColumnLimit > field.horizontalLength || tempLowerColumnLimit < 0
                || tempUpperColumnLimit > field.horizontalLength || tempUpperColumnLimit < 0 )
        {
            throw new Exception("Error! Position is out of the field");
        }
    }

    private void checkingAreaCovered() throws Exception {
        for(int i=tempLowerRowLimit-1;i<=tempUpperRowLimit+1;i++) {
            if(i < 0 || i >= field.verticalLength) {
                continue;
            }

            for(int j=tempLowerColumnLimit-1;j<=tempUpperColumnLimit+1;j++) {
                if(j < 0 || j >= field.horizontalLength) {
                    continue;
                }
                if(field.battleFieldArray[i][j] != '~') {
                    throw new Exception("Error! You placed it too close to another one. Try again:");
                }
            }
        }
    }

    private void checkingAreaLength() throws Exception {
        boolean isVertical = tempUpperColumnLimit == tempLowerColumnLimit;

        if( (isVertical && (tempUpperRowLimit-tempLowerRowLimit + 1) != tempShipModel.getModelLength())
                || (!isVertical && (tempUpperColumnLimit - tempLowerColumnLimit + 1) != tempShipModel.getModelLength()) )
        {
            throw new Exception("Error! Wrong length of the Submarine! Try again:");
        }
    }
}
