package battleship;

public class Ship {

    private final ShipModel shipModel;
    private int lowerRowLimit,upperRowLimit,lowerColLimit,upperColLimit;
    private boolean isVertical;

    char[][] field  = GameUI.getBattleFieldArray();
    int fieldHorizontalLength = GameUI.getColumnSideLength();
    int fieldVerticalLength = GameUI.getRowSideLength();

    public Ship(final ShipModel model) {
        shipModel = model;
    }

    public void setPosition(int rowPos_1,int colPos_1,int rowPos_2,int colPos_2) throws Exception{
        lowerRowLimit = Math.min(rowPos_1,rowPos_2);
        upperRowLimit = Math.max(rowPos_1,rowPos_2);
        lowerColLimit = Math.min(colPos_1,colPos_2);
        upperColLimit = Math.max(colPos_1,colPos_2);

        validate(rowPos_1,colPos_1,rowPos_2,colPos_2);

        for(int i=lowerRowLimit;i<=upperRowLimit;i++) {
            for(int j=lowerColLimit;j<=upperColLimit;j++) {
                GameUI.getBattleFieldArray()[i][j] = 'O';
            }
        }
    }

    private void validate(int rowPos_1,int colPos_1,int rowPos_2,int colPos_2) throws Exception {

        // checking if the ship position is diagonal or not. If diagonal, throwing an Exception.
        chekingArrangement();

        // checking if the positions are not out of the field limits.
        checkingPositionInField();

        // checking if there are any ships near the position.
        // decremented the "top-left" coordinates and incremented the "bottom-right" coordinates to iterate the cells surrounding the ship position.
        // if the seatch area is out of field ((-1,5),(1,14),(40,23), etc), then we avoid them or not search them as they don't exist.
        checkingAreaCovered();

        // checking if the ship position is vertical or horizontal
        isVertical = colPos_1 == colPos_2;

        // checking if the vertical and horizontal length of the ship is same as that of its model.
        checkingAreaLength();
    }

    private void chekingArrangement() throws Exception {
        if(lowerRowLimit != upperRowLimit && lowerColLimit != upperColLimit) {
            throw new Exception("Error! Wrong ship Co-ordinates! Try again:");
        }
    }

    private void checkingPositionInField() throws Exception {
        if(lowerRowLimit > fieldHorizontalLength || lowerRowLimit < 0
                || upperRowLimit > fieldHorizontalLength || upperRowLimit < 0
                || lowerColLimit > fieldVerticalLength || lowerColLimit < 0
                || upperColLimit > fieldVerticalLength || upperColLimit < 0 )
        {
            throw new Exception("Error! Position is out of the field");
        }
    }

    private void checkingAreaCovered() throws Exception {
        for(int i=lowerRowLimit-1;i<=upperRowLimit+1;i++) {
            if(i < 0 || i >= fieldVerticalLength) {
                continue;
            }

            for(int j=lowerColLimit-1;j<=upperColLimit+1;j++) {
                if(j < 0 || j >= fieldHorizontalLength) {
                    continue;
                }
                if(field[i][j] != '~') {
                    throw new Exception("Error! You placed it too close to another one. Try again:");
                }
            }
        }
    }
    private void checkingAreaLength() throws Exception {
        if( (isVertical && (upperRowLimit-lowerRowLimit + 1) != shipModel.getModelLength())
                || (!isVertical && (upperColLimit - lowerColLimit + 1) != shipModel.getModelLength()) )
        {
            throw new Exception("Error! Wrong length of the Submarine! Try again:");
        }
    }

    public boolean isDown() {
        for(int i=lowerRowLimit;i<=upperRowLimit;i++) {
            for(int j=lowerColLimit;j<= upperColLimit;j++) {
                if(field[i][j] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    public ShipModel getShipModel() {
        return shipModel;
    }
}
