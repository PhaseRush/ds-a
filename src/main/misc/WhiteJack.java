package misc;

import java.util.Random;
import java.util.Scanner;

public class WhiteJack {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner kbReader = new Scanner(System.in);
        int wins = 0;
        int losses = 0;

        System.out.println("Would you like to play a game? 1) Yes 2) No");
        int yesNo = kbReader.nextInt();
        while (yesNo == 1) {
            int playerTotal = 0;
            int comTotal = 0;

            // give first card to player
            int newCard = random.nextInt(13) + 1;
            playerTotal = playerTotal + newCard;
            System.out.println("You've drawn " + newCard + " for a total of " + playerTotal);

            // while player wants more
            System.out.println("Do you want another number? 1) Yes 2) No");
            int playerMoreInput = kbReader.nextInt();
            while (playerMoreInput == 1) {
                newCard = random.nextInt(13) + 1;
                playerTotal = playerTotal + newCard;
                if (playerTotal > 21) {
                    break;
                } else {
                    System.out.println("Do you want another number? 1) Yes 2) No");
                    playerMoreInput = kbReader.nextInt();
                }
            }

            // player doesnt want more

            // -- this game is done
            // preserve state
            yesNo = kbReader.nextInt();
        }
        System.out.println("Thanks for Playing");
    }
}
