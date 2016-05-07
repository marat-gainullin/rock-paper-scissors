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
     * Error message, when unknown number is entered.
     */
    private static final String UNKNOWN_NUMBER_MSG = "Unknown number %d. Try again, please.";
    /**
     * Error message, when not a number is entered.
     */
    private static final String NOT_NUMBER_MSG = "'%s' is not a nunmber. Try again, please.";
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
     * Abstraction for some identified entity from by id from a console.
     * Detects wrong from and attempts to do it again.
     *
     * @param <T> Type of the entity.
     * @param aSource A scanner used to from numbers - ids.
     * @param aOut An output print stream for messages.
     * @param anAttemptMessge A message to be displayed when a player inputs an
     * incorrect symbols.
     * @param aFactory Predicate used for actual entity instance creation.
     * @return Creation entity instance.
     */
    public static <T> Optional<T> from(Scanner aSource, PrintStream aOut, String anAttemptMessge, Function<Integer, Optional<T>> aFactory) {
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
                    aOut.println(String.format(NOT_NUMBER_MSG, line));
                    attempt.run();
                } catch (InvalidNumberException ex) {
                    aOut.println(String.format(UNKNOWN_NUMBER_MSG, ex.getNumber()));
                    attempt.run();
                }
            }
        }
        return entity;
    }

    public static String to(Round aRound, boolean aColorful) {
        StringBuilder content = new StringBuilder();
        if (aColorful) {
            content // TODO: add a palette to tools.
                    .append(aRound.getPlayer1Tool().getName())
                    .append(DELIMITER)
                    .append(aRound.getPlayer2Tool().getName())
                    .append(PERIOD);
            return content.toString();
        } else {
            content
                    .append(aRound.getPlayer1Tool().getName())
                    .append(DELIMITER)
                    .append(aRound.getPlayer2Tool().getName())
                    .append(PERIOD);
            return content.toString();
        }
    }

    /**
     * Constrcuts a list of available commands.
     *
     * @return Description of all available commands.
     */
    public static String commands() {
        StringBuilder message = new StringBuilder();
        for (Command cmd : Command.values()) {
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
