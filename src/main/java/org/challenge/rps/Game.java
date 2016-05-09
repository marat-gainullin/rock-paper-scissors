package org.challenge.rps;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
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
    private static final String COMPUTER_WINS_MSG = " Computer wins.";
    /**
     * You win message.
     */
    private static final String YOU_WIN_MSG = " You win!";
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
    private static final String COMPUTERS_ROUND_MSG = "My comp, Go! (Y/n)";
    /**
     * Hello message, printed at the begining.
     */
    private static final String HELLO_MSG = "Let's play \"rock, paper, scissors\" :)";
    /**
     * General strings encoding name.
     */
    private static final String UTF8 = "utf-8";
    /**
     * Message with time warning. It is displayed when period of time intended to play is elapsed.
     */
    private static final String TIME_WARN_MESSAGE = "The time is up. Let's call it a day.";
    /**
     * Nice game message. It is displayed when Computer vs. Computer or Player vs. Computer session ends.
     */
    private static final String NICE_GAME_MSG = "It was a nice game!";

    /**
     * Entry-point method of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner source = new Scanner(System.in, UTF8)) {
            Game game = new Game(source, System.out, Settings.parse(args));
            game.start();
        }
    }

    /**
     * The source of console input from a player.
     */
    private final Scanner input;
    /**
     * The output for a player.
     */
    private final PrintStream output;
    /**
     * Settings of the game. They are parsed from an arguments array.
     */
    private final Settings settings;
    /**
     * History of rounds.
     */
    private final Queue<Round> history = new LinkedList<>();
    /**
     * Timestamp in milliseconds.
     */
    private long started;
    /**
     * Pseudo random numbers generator.
     * It is renewed each game.
     */
    private Random uniform;

    /**
     * Game constructor.
     * @param aInput Console input source.
     * @param aOutput Console output destination.
     * @param aSettings Settins to be used by this instanceof game.
     */
    public Game(Scanner aInput, PrintStream aOutput, Settings aSettings) {
        super();
        input = aInput;
        output = aOutput;
        settings = aSettings;
    }

    /**
     * Starts a new game.
     */
    public void start() {
        started = System.currentTimeMillis();
        modes();
        goodBye();
    }

    /**
     * Invites a player to make a choice of what game he wants to play.
     */
    private void modes() {
        String commands = Console.commands();
        output.println(Game.HELLO_MSG);
        Optional<Command> command = Console.from(input, output, commands, Command.as());
        while (command.isPresent()) {
            if (System.currentTimeMillis() - started > settings.getWarnPeriod()) {
                output.println(TIME_WARN_MESSAGE);
            }
            history.clear();
            uniform = new Random();
            switch (command.get()) {
                case COMP_COMP:
                    compComp();
                    break;
                case PLAYER_COMP:
                    playerComp();
                    break;
            }
            command = Console.from(input, output, commands, Command.as());
        }
    }

    /**
     * Plays Computer vs. Computer game.
     */
    private void compComp() {
        boolean newRound = true;
        while (newRound) {
            output.print(COMPUTERS_ROUND_MSG);
            String line = input.nextLine();
            if (line.isEmpty() || line.toLowerCase().startsWith(Y_ANSWER)) {
                makeRound(autoSelectTool());
            } else {
                newRound = false;
            }
        }
    }

    /**
     * Plays Player vs. Computer game.
     */
    private void playerComp() {
        String tools = Console.tools();
        Optional<Tool> tool = Console.from(input, output, tools, Tool.as());
        while (tool.isPresent()) {
            makeRound(tool.get());
            tool = Console.from(input, output, tools, Tool.as());
        }
        report();
    }

    /**
     * Selects a tool from avaliable tools in the following manner: - If history
     * is empty, then pseudo random number with uniform distribution is used to
     * select a tool. - If history is not empty, then the selection process
     * prefers successfull in the past tools.
     *
     * @return Automatically selected tool.
     */
    private Tool autoSelectTool() {
        return Tool.SCISSORS;// Tool.TOOLS[uniform.nextInt(Tool.TOOLS.length)];
    }

    /**
     * Creates a round, adds it to rounds history and reports a result of the
     * round to the player.
     *
     * @param aPlayerTool A tool selected by a player for the round.
     */
    private void makeRound(Tool aPlayerTool) {
        Tool computerTool = autoSelectTool();
        Round round = new Round(aPlayerTool, computerTool);
        String roundText = Console.to(round, settings.isColorful());
        Optional<Tool> winner = round.winner();
        if (winner.isPresent()) {
            if (winner.get() == aPlayerTool) {
                output.println(roundText + YOU_WIN_MSG);
            } else {
                output.println(roundText + COMPUTER_WINS_MSG);
            }
        } else {
            output.println(roundText + DEAD_HEAT_MSG);
        }
        history.offer(round);
        if (history.size() > settings.getHistoryLength()) {
            history.poll();
        }
    }

    /**
     * Displays a game report. Total number of played rounds, wins count, loses count and
     * success rate.
     */
    private void report() {
        output.println(NICE_GAME_MSG);
        output.println();
    }

    /**
     * Says the player good bye. It is called at the end of the game. Displays a
     * good bye message.
     */
    private void goodBye() {
        output.println(GOOD_BYE_MSG);
    }

}
