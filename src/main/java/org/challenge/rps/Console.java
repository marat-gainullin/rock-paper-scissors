package org.challenge.rps;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Utility class with useful abstractions for console from/output.
 *
 * @author mg
 */
public final class Console {

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
    private static final String UNKNOWN_CHOICE_MSG = "Unknown choice %s. "
            + TRY_AGAIN_MSG;
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
        "\u001b[35m", // Magenta
        "\u001b[36m", // Cyan
        "\u001b[32m" // Green
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
    public static <T> Optional<T> from(final Scanner aSource,
            final PrintStream aOut, final String anAttemptMessge,
            final Function<String, Optional<T>> aFactory) {
        Optional<T> entity = Optional.empty();
        while (!entity.isPresent()) {
            aOut.print(anAttemptMessge);
            aOut.print(PROMPT);
            String line = aSource.nextLine();
            if (!EXIT_COMMAND.equalsIgnoreCase(line)) {
                entity = aFactory.apply(line);
                if (!entity.isPresent()) {
                    aOut.println(String.format(
                            UNKNOWN_CHOICE_MSG, line));
                }
            } else {
                break;
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
                    .append(VT100_PALETTE[aRound
                            .getFirstTool()
                            .ordinal() % VT100_PALETTE.length])
                    .append(aRound
                            .getFirstTool()
                            .getName()).append(VT100_END)
                    .append(DELIMITER)
                    .append(VT100_PALETTE[aRound
                            .getSecondTool()
                            .ordinal() % VT100_PALETTE.length])
                    .append(aRound
                            .getSecondTool()
                            .getName()).append(VT100_END)
                    .append(PERIOD);
            return content.toString();
        } else {
            content
                    .append(aRound.getFirstTool().getName())
                    .append(DELIMITER)
                    .append(aRound.getSecondTool().getName())
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
                    .append(cmd.getValue())
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
                    .append(tool.getValue())
                    .append(", ");
        }
        return message.toString();
    }

}
