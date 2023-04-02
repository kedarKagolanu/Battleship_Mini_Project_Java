package battleship;

public class GameUI {
    private static char[][] battleFieldArray;

    private static int rowSideLength;
    private static int columnSideLength;

    public GameUI(int rowLimit, int colLimit) {
        battleFieldArray = new char[rowLimit][colLimit];
        rowSideLength = rowLimit;
        columnSideLength = colLimit;

        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                battleFieldArray[i][j] = '~';
            }
        }

        GameUI.printField();
    }

    public static void printField() {

        // printing first row of the Field UI
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        /*or(int i=2;i<=columnLength;i++) {
            System.out.print(" "+i);
        }*/
        System.out.println();

        // printing remaining rows of the field, beginning with their row Alphabet
        for(int i=0;i<rowSideLength;i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j=0;j<columnSideLength;j++) {
                System.out.print(battleFieldArray[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void printFogField() {
        // printing first row of the Field UI
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        /*or(int i=2;i<=columnLength;i++) {
            System.out.print(" "+i);
        }*/
        System.out.println();

        // printing remaining rows of the field, beginning with their row Alphabet
        for(int i=0;i<rowSideLength;i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j=0;j<columnSideLength;j++) {
                System.out.print("~ ");
            }
            System.out.println();
        }
    }

    public static char[][] getBattleFieldArray() {
        return battleFieldArray;
    }

    public static int getRowSideLength() {
        return rowSideLength;
    }

    public static int getColumnSideLength() {
        return columnSideLength;
    }
}
