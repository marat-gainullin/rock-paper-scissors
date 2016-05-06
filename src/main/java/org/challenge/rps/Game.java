package org.challenge.rps;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class of the program. Contains high level logic and input/output
 * configuration.
 *
 * @author mg
 */
public class Game {

    /**
     * Computer vs. Computer round message.
     */
    public static final String COMPUTER_WINS_MSG = " Computer wins.";
    /**
     * You win message.
     */
    public static final String YOU_WIN_MSG = " You win!";
    /**
     * Dead heat message.
     */
    private static final String DEAD_HEAT_MSG = " Dead heat!";
    /**
     * Good bye message.
     */
    private static final String GOOD_BYE_MSG = "Good bye. See you next time :)";
    /**
     * 'y' answer message.
     */
    private static final String Y_ANSWER = "y";
    /**
     * Constant with computers start message.
     */
    public static final String COMPUTERS_ROUND_MSG = "Computers, Go! (Y/n)";
    /**
     * Hello message, printed at the begining.
     */
    public static final String HELLO_MSG = "Rock, paper, scissors game :)";
    /**
     * General strings encoding name.
     */
    public static final String UTF8 = "utf-8";

    /**
     * Entry-point method of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        modes(System.in, System.out);
        goodBye(System.out);
    }

    private static void modes(InputStream aIn, PrintStream aOut) {
        try (Scanner source = new Scanner(aIn, Game.UTF8)) {
            String commandsMessage = ConsoleUtils.commandsMessage();
            aOut.println(Game.HELLO_MSG);
            Optional<Command> command = ConsoleUtils.nextId(source, aOut, commandsMessage, Command.as());
            while (command.isPresent()) {
                switch (command.get()) {
                    case COMP_COMP:
                        compComp(source, aOut);
                        break;
                    case PLAYER_COMP:
                        playerComp(source, aOut);
                        break;
                }
                command = ConsoleUtils.nextId(source, aOut, commandsMessage, Command.as());
            }
        }
    }

    private static void compComp(Scanner aIn, PrintStream aOut) {
        boolean newRound = true;
        while (newRound) {
            aOut.print(COMPUTERS_ROUND_MSG);
            String line = aIn.nextLine();
            if (line.isEmpty() || line.toLowerCase().startsWith(Y_ANSWER)) {
                makeRound(selectToolFromHistory(), aOut);
            } else {
                newRound = false;
            }
        }
    }

    private static void playerComp(Scanner aIn, PrintStream aOut) {
        String toolsMessage = ConsoleUtils.toolsMessage();
        Optional<Tool> tool = ConsoleUtils.nextId(aIn, aOut, toolsMessage, Tool.as());
        while (tool.isPresent()) {
            makeRound(tool.get(), aOut);
            tool = ConsoleUtils.nextId(aIn, aOut, toolsMessage, Tool.as());
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
                out.println(round.toString() + YOU_WIN_MSG);
            } else {
                out.println(round.toString() + COMPUTER_WINS_MSG);
            }
        } else {
            out.println(round.toString() + DEAD_HEAT_MSG);
        }
    }

    private static void goodBye(PrintStream out) {
        out.println(GOOD_BYE_MSG);
    }

}
