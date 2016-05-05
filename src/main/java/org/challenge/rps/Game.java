package org.challenge.rps;

import java.util.Scanner;

/**
 *
 * @author mg
 */
public class Game {

    private static Tool autoSelectTool(Tool anOpponentTool) {
        return anOpponentTool;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, Utils.UTF8);
        System.out.println(Utils.HELLO_MSG);
        Utils.printHelp();
        Command[] commands = Command.values();
        boolean newRound = true;
        while (newRound) {
            System.out.print(Utils.PROMPT);
            Command command = Utils.nextOrdinal(in, (Integer aOrdinal) -> {
                return aOrdinal > 0 && aOrdinal < commands.length ? commands[aOrdinal] : null;
            });
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
