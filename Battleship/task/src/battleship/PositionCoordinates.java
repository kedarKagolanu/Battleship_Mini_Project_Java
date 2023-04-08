package battleship;

public class PositionCoordinates {

    int lowerRowLimit;
    int lowerColumnLimit;
    int upperRowLimit;
    int upperColumnLimit;

    public PositionCoordinates(int row1,int row2,int col1,int col2) {
        lowerRowLimit = Math.min(row1,row2);
        upperRowLimit = Math.max(row1,row2);
        lowerColumnLimit = Math.min(col1,col2);
        upperColumnLimit = Math.max(col1,col2);
    }

    public int getLowerRowLimit() {
        return lowerRowLimit;
    }

    public int getLowerColumnLimit() {
        return lowerColumnLimit;
    }

    public int getUpperRowLimit() {
        return upperRowLimit;
    }

    public int getUpperColumnLimit() {
        return upperColumnLimit;
    }

}
