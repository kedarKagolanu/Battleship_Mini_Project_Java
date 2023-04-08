package battleship;

class Field {

    final char[][] battleFieldArray;

    final int horizontalLength;
    final int verticalLength;

    public Field(int rowLimit, int colLimit) {
        battleFieldArray = new char[rowLimit][colLimit];
        horizontalLength = colLimit;
        verticalLength = rowLimit;

        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                battleFieldArray[i][j] = '~';
            }
        }
    }
}
