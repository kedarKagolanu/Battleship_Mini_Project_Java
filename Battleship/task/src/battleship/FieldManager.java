package battleship;

public class FieldManager {

    private final Player ownerPlayer;
    private final Field field;
    private final Validator validator;

    public FieldManager(Player player,Field field) {
        ownerPlayer = player;
        this.field = field;
        this.validator = new Validator(field);
    }

    public void setPosition(int row1,int row2,int col1,int col2,ShipModel model) throws Exception {
        PositionCoordinates position = new PositionCoordinates(row1,row2,col1,col2);
        validator.checkPosition(position,model);

        //System.out.println("--------"+position.getLowerRowLimit()+" "+position.getUpperRowLimit()+" "+position.getLowerColumnLimit()+" "+position.getUpperColumnLimit());
        for(int i=position.getLowerRowLimit();i<=position.getUpperRowLimit();i++) {
            for(int j=position.getLowerColumnLimit();j<=position.getUpperColumnLimit();j++) {
                field.battleFieldArray[i][j] = 'O';
            }
        }
    }

    public void setCell(int rowCoordinate,int columnCoordinate){

        String message = null;
        if(field.battleFieldArray[rowCoordinate][columnCoordinate] == 'O' || field.battleFieldArray[rowCoordinate][columnCoordinate] == 'X')  {
            field.battleFieldArray[rowCoordinate][columnCoordinate] = 'X';

            int sunkCounter = 0;
            for(Ship ship : ownerPlayer.getShipsList()) {
                if(ship.isDown()) {
                    sunkCounter ++;
                }
            }

            if(sunkCounter == ownerPlayer.getShipsList().size()) {
                System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
            } else if(sunkCounter > ownerPlayer.getSunkShipsCount()) {
                System.out.println("\nYou sank a ship!\n");
                ownerPlayer.incrementShunkShipsCount();
            } else {
                System.out.println("\nYou hit a ship!");
            }
        } else {
            field.battleFieldArray[rowCoordinate][columnCoordinate] = 'M';
            System.out.println("\nYou missed!");
        }
    }

    public void printField() {

        // printing first row of the Field UI
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        System.out.println();

        // printing remaining rows of the field, beginning with their row Alphabet
        for(int i=0;i<field.verticalLength;i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j=0;j<field.horizontalLength;j++) {
                System.out.print(field.battleFieldArray[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void printFogField() {
        // printing first row of the Field UI
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        System.out.println();

        // printing remaining rows of the field, beginning with their row Alphabet
        for(int i=0;i<field.verticalLength;i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j=0;j<field.horizontalLength;j++) {
                System.out.print("~ ");
            }
            System.out.println();
        }
    }

    public boolean isShipDestryed(Ship ship) {
        PositionCoordinates position = ship.getShipPosition();
        for(int i=position.getLowerRowLimit();i<=position.getUpperRowLimit();i++) {
            for(int j=position.getLowerColumnLimit();j<=position.getUpperColumnLimit();j++) {
                if(field.battleFieldArray[i][j] != 'X') {
                    return false;
                }
            }
        }

        return true;
    }
}
