package org.challenge.rps;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import org.challenge.rps.exceptions.InvalidNumberException;

/**
 * Utility class with useful abstractions for console from/output.
 *
 * @author mg
 */
public class Console {

    /**
     * Prompt message for a player.
     */
    private static final String PROMPT = "q - quit > ";
    /**
     * Tail of some error messages.
     */
    private static final String TRY_AGAIN_MSG = "Try again, please.";
    /**
     * Error message, when unknown number is entered.
     */
    private static final String UNKNOWN_NUMBER_MSG = "Unknown number %d. " + TRY_AGAIN_MSG;
    /**
     * Error message, when not a number is entered.
     */
    private static final String NOT_NUMBER_MSG = "'%s' is not a nunmber. " + TRY_AGAIN_MSG;
    /**
     * Textual prefix of quit level command.
     */
    private static final String EXIT_COMMAND = "q";
    /**
     * Round string representation delimiter.
     */
    private static final String DELIMITER = " - ";
    /**
     * Period character string.
     */
    private static final String PERIOD = ".";
    /**
     * ANSI/VT100 escaped clear of attributes. Used for tools highlighting.
     */
    private static final String VT100_END = "\u001b[0m";
    /**
     * ANSI/VT100 escaped palette. Used for tools highlighting.
     */
    private static final String[] VT100_PALETTE = new String[]{
        "\u001b[35m" // Magenta
        , "\u001b[36m" // Cyan
        , "\u001b[32m" // Green
    };

    /**
     * Hidden because of utility nature of the <code>Console</code> class.
     */
    private Console() {
    }

    /**
     * Abstraction for some identified entity from by id from a console. Detects
     * wrong from and attempts to do it again.
     *
     * @param <T> Type of the entity.
     * @param aSource A scanner used to from numbers - ids.
     * @param aOut An output print stream for messages.
     * @param anAttemptMessge A message to be displayed when a player inputs an
     * incorrect symbols.
     * @param aFactory Predicate used for actual entity instance creation.
     * @return Creation entity instance.
     */
    public static <T> Optional<T> from(final Scanner aSource, final PrintStream aOut, final String anAttemptMessge, final Function<Integer, Optional<T>> aFactory) {
        Runnable attempt = () -> {
            aOut.print(anAttemptMessge);
            aOut.print(PROMPT);
        };
        attempt.run();
        Optional<T> entity = Optional.empty();
        while (!entity.isPresent()) {
            String line = aSource.nextLine().toLowerCase();
            if (line.startsWith(EXIT_COMMAND)) {
                break;
            } else {
                try {
                    entity = aFactory.apply(Integer.valueOf(line));
                } catch (NumberFormatException ex) {
                    aOut.println(line.isEmpty() ? TRY_AGAIN_MSG : String.format(NOT_NUMBER_MSG, line));
                    attempt.run();
                } catch (InvalidNumberException ex) {
                    aOut.println(String.format(UNKNOWN_NUMBER_MSG, ex.getNumber()));
                    attempt.run();
                }
            }
        }
        return entity;
    }

    /**
     * Transforms a <code>Round</code> instance to string representation taking
     * into account 'colorful' feature.
     *
     * @param aRound A <code>Round</code> instance to be transformed to a
     * string.
     * @param aColorful True if transformd <code>Round</code> instance should be
     * rounded with ANSI/VT100 escape sequences.
     * @return Result of transformation.
     */
    public static String to(final Round aRound, final boolean aColorful) {
        StringBuilder content = new StringBuilder();
        if (aColorful) {
            content
            .append(VT100_PALETTE[aRound.getFirstPlayerTool().ordinal() % VT100_PALETTE.length])
                    .append(aRound.getFirstPlayerTool().getName()).append(VT100_END)
            .append(DELIMITER)
            .append(VT100_PALETTE[aRound.getSecondPlayerTool().ordinal() % VT100_PALETTE.length])
                    .append(aRound.getSecondPlayerTool().getName()).append(VT100_END)
            .append(PERIOD);
            return content.toString();
        } else {
            content
                    .append(aRound.getFirstPlayerTool().getName())
                    .append(DELIMITER)
                    .append(aRound.getSecondPlayerTool().getName())
                    .append(PERIOD);
            return content.toString();
        }
    }

    /**
     * Constrcuts a list of available modes.
     *
     * @return Description of all available modes.
     */
    public static String modes() {
        StringBuilder message = new StringBuilder();
        for (Mode cmd : Mode.values()) {
            message
                    .append(cmd.getName())
                    .append(" - ")
                    .append(cmd.ordinal())
                    .append(" / ");
        }
        return message.toString();
    }

    /**
     * Constrcuts a message with a list of available tools.
     *
     * @return Description of all available tools.
     */
    public static String tools() {
        StringBuilder message = new StringBuilder();
        for (Tool tool : Tool.values()) {
            message
                    .append(tool.getName())
                    .append(" - ")
                    .append(tool.ordinal())
                    .append(", ");
        }
        return message.toString();
    }

}
