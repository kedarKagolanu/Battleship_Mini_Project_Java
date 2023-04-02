package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    ArrayList<Ship> shipsList;

    Scanner in = new Scanner(System.in);
    char[][] field = GameUI.getBattleFieldArray();

    public Player(){
        Scanner in = new Scanner(System.in);
        shipsList = new ArrayList<>();

        for(ShipModel model : ShipModel.values()) {
            Ship ship = new Ship(model);
            shipsList.add(ship);
        }

        for(Ship s : shipsList) {
            int rowPos1,colPos1,rowPos2,colPos2;
            System.out.println("\nEnter the coordinates of the "+ s.getShipModel().getModelName() +" ("+ s.getShipModel().getModelLength() +" cells):");
            String[] inputs;

            while(true) {
                inputs = in.nextLine().toUpperCase().split(" ");

                try {
                    if (inputs.length == 2) {
                        rowPos1 = (((int) inputs[0].charAt(0)) - 65);
                        colPos1 = getColPosition(inputs[0]);

                        rowPos2 = (((int) inputs[1].charAt(0)) - 65);
                        colPos2 = getColPosition(inputs[1]);

                        s.setPosition(rowPos1, colPos1-1, rowPos2, colPos2-1);

                        GameUI.printField();
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

    public boolean play() throws Exception {

        String input = in.next().toUpperCase();
        int rowCoordinate = (int)input.charAt(0)-65;
        int colCoordinate = getColPosition(input)-1;
        boolean continueGame = true;

        if(rowCoordinate < 0 || rowCoordinate >= GameUI.getRowSideLength()
            || colCoordinate < 0 || colCoordinate >= GameUI.getColumnSideLength())
        {
            throw new Exception("\nError! You entered the wrong coordinates! Try again:\n");
        }

        String message = null;
        if(field[rowCoordinate][colCoordinate] == 'O' || field[rowCoordinate][colCoordinate] == 'X')  {
            field[rowCoordinate][colCoordinate] = 'X';
            message = "\nYou hit a ship!\n";
        } else {
            field[rowCoordinate][colCoordinate] = 'M';
            message = "\nYou missed!\n";
        }

        if(allShipsAreDown()) {
            message = "\nYou sank the last ship. You won. Congratulations!\n";
            continueGame = false;
        }

        GameUI.printField();
        System.out.println(message);
        return continueGame;
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
            colPos = colPos*10 + (int)(input.charAt(i))-48;
        }
        return colPos;
    }

}
