package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String name;
    private ArrayList<Ship> shipsList;
    int sunkShipCount = 0;
    private final Field field;
    private final FieldManager fieldManager;

    private Player opponentPlayer;

    Scanner in = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        field = new Field(10,10);
        fieldManager = new FieldManager(this,field);

        initField();
    }

    public void setOpponentPlayer(Player opponent) {
        opponentPlayer = opponent;
    }

    private void initField() {
        Scanner in = new Scanner(System.in);
        shipsList = new ArrayList<>();

        for(ShipModel model : ShipModel.values()) {
            Ship ship = new Ship(model);
            shipsList.add(ship);
        }

        System.out.println(name+", place your ships on the game field");
        fieldManager.printField();

        for(Ship s : shipsList) {
            int rowPos1,colPos1,rowPos2,colPos2;

            System.out.println("\nEnter the coordinates of the "+ s.getShipModel().getModelName() +" ("+ s.getShipModel().getModelLength() +" cells):\n");
            String[] inputs;

            while(true) {
                inputs = in.nextLine().toUpperCase().split(" ");

                try {
                    if (inputs.length == 2) {
                        rowPos1 = (((int) inputs[0].charAt(0)) - 65);
                        colPos1 = getColPosition(inputs[0]);

                        rowPos2 = (((int) inputs[1].charAt(0)) - 65);
                        colPos2 = getColPosition(inputs[1]);

                        s.setPositionInField(this,rowPos1,rowPos2,colPos1,colPos2);

                        fieldManager.printField();
                        break;
                    } else {
                        throw new Exception("Error! Wrong no of inputs. Try again:");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public boolean play() {

        System.out.println(name+", it's your turn:\n");
        String input;
        int rowCoordinate,colCoordinate;
        boolean GameIsOver = false;
        String message;

        while(true) {
            input = in.next().toUpperCase();
            rowCoordinate = (int)input.charAt(0)-65;
            colCoordinate = getColPosition(input);

            if(rowCoordinate < 0 || rowCoordinate >= field.verticalLength
                    || colCoordinate < 0 || colCoordinate >= field.horizontalLength )
            {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                continue;
            }

            break;
        }

        attackOnOpponentCell(rowCoordinate,colCoordinate);

        if(allShipsAreDown()) {
            GameIsOver = true;
        }

        return GameIsOver;
    }

    private void attackOnOpponentCell(int row,int column) {
        opponentPlayer.getFieldManager().setCell(row,column);
    }

    private boolean allShipsAreDown() {
        int i = 0;
        for (Ship s: shipsList) {
            if(s.isDown()) {
                i++;
            }
        }

        return i == shipsList.size();
    }

    private int getColPosition(String input) {
        int colPos = 0;
        for(int i=1;i<input.length();i++) {
            colPos = colPos*10 + ((int)(input.charAt(i))-48);
        }
        return colPos-1;
    }

    public FieldManager getFieldManager() {
        return fieldManager;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public ArrayList<Ship> getShipsList() {
        return shipsList;
    }

    public int getSunkShipsCount() {
        return sunkShipCount;
    }

    public void incrementShunkShipsCount() {
        sunkShipCount++;
    }

}
