package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        GameUI field = new GameUI(10,10);
        Player player1 = new Player();
        boolean continueGame = true;

        System.out.println("\nThe game starts!\n");
        GameUI.printFogField();
        System.out.println("\nTake a shot!\n");
        while(continueGame) {
            try {
                continueGame = player1.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
