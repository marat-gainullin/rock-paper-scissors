package org.challenge.rps;

import java.io.InputStream;
import org.challenge.rps.strategies.Strategy;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import org.challenge.rps.strategies.NonUniformRangeStrategy;

/**
 * Main class of the program. Contains high level logic and input/output
 * configuration.
 *
 * @author mg
 */
public class Game {

    /**
     * Computer wins message.
     */
    private static final String COMPUTER_WINS_MSG = " Computer wins.";
    /**
     * You win message.
     */
    private static final String YOU_WIN_MSG = " You win!";
    /**
     * Your computer win message.
     */
    private static final String YOUR_COMPUTER_WIN_MSG
            = " Your computer wins!";
    /**
     * Your computer win message.
     */
    private static final String FOREIGN_COMPUTER_WIN_MSG
            = " Foreign computer wins!";
    /**
     * Dead heat message.
     */
    private static final String DEAD_HEAT_MSG = " Dead heat!";
    /**
     * Good bye message.
     */
    private static final String GOOD_BYE_MSG
            = "Good bye. See you next time :)";
    /**
     * 'y' answer message.
     */
    private static final String Y_ANSWER = "y";
    /**
     * 'n' answer message.
     */
    private static final String N_ANSWER = "n";
    /**
     * Message displayed, when neither 'y', nor 'n' is entered by a player.
     */
    private static final String UNKNOWN_CHOICE_MSG = "Unknown command: %s";
    /**
     * Constant with computers start message.
     */
    private static final String COMPUTERS_ROUND_MSG
            = "Let computer make your choice? (Y/n)";
    /**
     * Hello message, printed at the begining.
     */
    private static final String HELLO_MSG
            = "Let's play \"rock, paper, scissors\" :)";
    /**
     * General strings encoding name.
     */
    private static final String UTF8 = "utf-8";
    /**
     * Message with time warning. It is displayed when period of time intended
     * to play is elapsed.
     */
    private static final String TIME_WARN_MESSAGE
            = "The time is up. Let's call it a day.";
    /**
     * Nice game message. It is displayed when Computer vs. Computer or Player
     * vs. Computer session ends.
     */
    private static final String NICE_GAME_MSG = "It was a nice game!";

    /**
     * Entry-point method of the program.
     *
     * @param args Command line arguments passed from outside by operating
     * system.
     */
    public static void main(final String[] args) {
        run(args, System.in, System.out);
    }

    /**
     * Runs the game with arnitrary input and output streams and arguments.
     * @param args Command line arguments passed from client code.
     * @param aIn An InputStream the input to be read from.
     * @param aOut A PrintStream the output to be written to.
     */
    public static void run(final String[] args,
            final InputStream aIn, final PrintStream aOut) {
        try (Scanner source = new Scanner(aIn, UTF8)) {
            Settings parsed = Settings.parse(args);
            Game game = new Game(source, aOut, parsed);
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
     * Timestamp in milliseconds.
     */
    private long started;
    /**
     * HumanStrategy wich selects a tool for the next move for the first player.
     * It is renewed each game.
     */
    private Strategy first;
    /**
     * HumanStrategy wich selects a tool for the next move for the second
     * player. It is renewed each game.
     */
    private Strategy second;

    /**
     * Game constructor.
     *
     * @param aInput Console input source.
     * @param aOutput Console output destination.
     * @param aSettings Settins to be used by this instanceof game.
     */
    public Game(final Scanner aInput,
            final PrintStream aOutput,
            final Settings aSettings) {
        super();
        input = aInput;
        output = aOutput;
        settings = aSettings;
    }

    /**
     * Starts a new game.
     */
    public final void start() {
        started = System.currentTimeMillis();
        output.println(Game.HELLO_MSG);
        output.println();
        selectMode();
        goodBye();
    }

    /**
     * Invites a player to make a choice of who will make his moves.
     */
    private void selectMode() {
        String modes = Console.modes();
        Optional<Mode> mode = Console.from(input, output, modes, Mode.as());
        while (mode.isPresent()) {
            long now = System.currentTimeMillis();
            if (now - started >= settings.getWarnPeriod()) {
                output.println(TIME_WARN_MESSAGE);
            }
            first = new NonUniformRangeStrategy();
            second = new NonUniformRangeStrategy();
            switch (mode.get()) {
                case COMP:
                    compComp();
                    break;
                case PLAYER:
                    playerComp();
                    break;
                default:
                    break;
            }
            mode = Console.from(input, output, modes, Mode.as());
        }
    }

    /**
     * Plays Computer vs. Computer game.
     */
    private void compComp() {
        output.print(COMPUTERS_ROUND_MSG);
        String line = input.nextLine();
        while (!line.equalsIgnoreCase(N_ANSWER)) {
            if (line.isEmpty() || line.equalsIgnoreCase(Y_ANSWER)) {
                makeRound(first.next(), second.next(),
                        YOUR_COMPUTER_WIN_MSG, FOREIGN_COMPUTER_WIN_MSG);
            } else {
                output.println(String.format(UNKNOWN_CHOICE_MSG, line));
            }
            output.print(COMPUTERS_ROUND_MSG);
            line = input.nextLine();
        }
    }

    /**
     * Plays Player vs. Computer game.
     */
    private void playerComp() {
        String tools = Console.tools();
        Optional<Tool> tool = Console.from(input, output, tools, Tool.as());
        while (tool.isPresent()) {
            makeRound(tool.get(), second.next(),
                    YOU_WIN_MSG, COMPUTER_WINS_MSG);
            tool = Console.from(input, output, tools, Tool.as());
        }
        report();
    }

    /**
     * Creates a round, adds it to rounds history and reports a result of the
     * round to the player.
     *
     * @param aFirstTool A tool selected by a first player for the round.
     * @param aSecondTool A tool selected by a second player for the round.
     * @param aFirstMessage Message displayed when the first tool wins.
     * @param aSecondMessage Message displayed when the second tool wins.
     */
    private void makeRound(final Tool aFirstTool, final Tool aSecondTool,
            final String aFirstMessage, final String aSecondMessage) {
        Round round = new Round(aFirstTool, aSecondTool);
        String roundText = Console.to(round, settings.isColorful());
        Optional<Tool> winner = round.winner();
        if (winner.isPresent()) {
            if (winner.get() == aFirstTool) {
                first.gain(aFirstTool);
                second.penalty(aFirstTool);
                output.println(roundText + aFirstMessage);
            } else {
                first.penalty(aFirstTool);
                second.gain(aFirstTool);
                output.println(roundText + aSecondMessage);
            }
        } else {
            output.println(roundText + DEAD_HEAT_MSG);
        }
    }

    /**
     * Displays a game report. Total number of played rounds, wins count, loses
     * count and success rate.
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
