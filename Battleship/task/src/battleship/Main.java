package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        Player player1 = new Player("Player 1");
        System.out.println("Press Enter and pass the move to another player\n");
        in.nextLine();
        System.out.println("............");

        Player player2 = new Player("Player 2");
        System.out.println("Press Enter and pass the move to another player\n");
        in.nextLine();
        System.out.println("............");

        player1.setOpponentPlayer(player2);
        player2.setOpponentPlayer(player1);

        boolean GameIsOver = false;

        System.out.println("\nThe game starts!\n");
        System.out.println("\nTake a shot!\n");

        Player gamePlayer = player1;
        while(!GameIsOver) {
            gamePlayer.getOpponentPlayer().getFieldManager().printFogField();
            System.out.println("---------------------");
            gamePlayer.getFieldManager().printField();

            GameIsOver = gamePlayer.play();
            System.out.println("Press Enter and pass the move to another player\n");
            in.nextLine();
            System.out.println("............");

            gamePlayer = gamePlayer.getOpponentPlayer();
        }
    }
}
