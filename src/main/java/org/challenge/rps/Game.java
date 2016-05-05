package org.challenge.rps;

import java.util.Scanner;

/**
 * Main class of the program.
 * Contains high level logic and input/output configuration.
 * @author mg
 */
public class Game {

    /**
     * Entry-point method of the program.
     * @param args 
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, GameConstants.UTF8);
        System.out.println(GameConstants.HELLO_MSG);
        ConsoleUtils.printCommands();
        boolean newRound = true;
        while (newRound) {
            System.out.print(ConsoleUtils.PROMPT);
            Command command = ConsoleUtils.byId(in, Command.as());
            switch (command) {
                case EXIT:
                    goodBye();
                    newRound = false;
                    break;
                case COMP_COMP:
                    compComp();
                    break;
                case PLAYER_COMP:
                    playerComp();
                    break;
            }
        }
    }

    private static void compComp() {
    }

    private static void playerComp() {
    }

    private static void goodBye() {
    }

}
