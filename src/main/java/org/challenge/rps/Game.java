package org.challenge.rps;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import org.challenge.rps.exceptions.QuitLevelException;

/**
 * Main class of the program. Contains high level logic and input/output
 * configuration.
 *
 * @author mg
 */
public class Game {

    /**
     * Entry-point method of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        modes(System.in, System.out);
    }

    private static void modes(InputStream aIn, PrintStream out) {
        try (Scanner in = new Scanner(aIn, GameConstants.UTF8)) {
            String commandsMessage = ConsoleUtils.commandsMessage();
            out.println(GameConstants.HELLO_MSG);
            boolean lastCommand = false;
            while (!lastCommand) {
                try {
                    Command command = ConsoleUtils.nextId(in, commandsMessage, Command.as());
                    switch (command) {
                        case COMP_COMP:
                            compComp(in, out);
                            break;
                        case PLAYER_COMP:
                            playerComp(in, out);
                            break;
                    }
                } catch (QuitLevelException ex) {
                    goodBye(out);
                    lastCommand = true;
                }
            }
        }
    }

    private static void compComp(Scanner in, PrintStream out) {
        boolean newRound = true;
        while (newRound) {
            out.print(ConsoleUtils.COMPUTERS_MSG);
            String line = in.nextLine();
            if (line.isEmpty() || line.toLowerCase().startsWith("y")) {
                makeRound(selectToolFromHistory(), out);
            } else {
                newRound = false;
            }
        }
    }

    private static void playerComp(Scanner in, PrintStream out) {
        String toolsMessage = ConsoleUtils.toolsMessage();
        boolean newRound = true;
        while (newRound) {
            try {
                Tool tool = ConsoleUtils.nextId(in, toolsMessage, Tool.as());
                makeRound(tool, out);
            } catch (QuitLevelException ex) {
                newRound = false;
            }
        }
    }

    private static Tool selectToolFromHistory() {
        return Tool.PAPER;
    }

    private static void makeRound(Tool aPlayerTool, PrintStream out) {
        Tool computerTool = selectToolFromHistory();
        Round round = new Round(aPlayerTool, computerTool);
        Optional<Tool> winner = round.winner();
        if (winner.isPresent()) {
            if (winner.get() == aPlayerTool) {
                out.println(round.toString() + " You win!");
            } else {
                out.println(round.toString() + " Computer wins.");
            }
        } else {
            out.println(round.toString() + " Dead heat!");
        }
    }

    private static void goodBye(PrintStream out) {
        out.println("Good bye. See you next time :)");
    }

}
