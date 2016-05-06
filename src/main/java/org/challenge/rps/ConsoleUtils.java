/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Scanner;
import java.util.function.Function;
import org.challenge.rps.exceptions.InvalidNumberException;
import org.challenge.rps.exceptions.QuitLevelException;

/**
 * Utility class with useful abstractions for console input/output.
 *
 * @author mg
 */
public class ConsoleUtils {

    /**
     * Constant with computers start message.
     */
    public static final String COMPUTERS_MSG = "Computers, Go! (Y/n)";
    /**
     * Prompt message for a player.
     */
    public static final String PROMPT = "q - quit > ";
    /**
     * Constant with OS's line separator.
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

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
     * Abstraction for some identified entity input by id from a console.
     * Detects wrong input and attempts to do it again.
     *
     * @param <T> Type of the entity.
     * @param aSource Console scanner used to input numbers - ids.
     * @param anAttemptMessge A message to be displayed when a player inputs an
     * incorrect symbols.
     * @param aFactory Predicate used for actual entity instance creation.
     * @return Creation entity instance.
     */
    public static <T> T nextId(Scanner aSource, String anAttemptMessge, Function<Integer, T> aFactory) {
        Runnable attempt = () -> {
            System.out.print(anAttemptMessge);
            System.out.print(PROMPT);
        };
        attempt.run();
        T entity = null;
        while (entity == null) {
            String line = aSource.nextLine().toLowerCase();
            if (line.startsWith(EXIT_COMMAND)) {
                throw new QuitLevelException(line);
            } else {
                try {
                    entity = aFactory.apply(Integer.valueOf(line));
                } catch (NumberFormatException ex) {
                    System.out.println(String.format(NOT_NUMBER_MSG, line));
                    attempt.run();
                } catch (InvalidNumberException ex) {
                    System.out.println(String.format(UNKNOWN_NUMBER_MSG, ex.getNumber()));
                    attempt.run();
                }
            }
        }
        return entity;
    }

    /**
     * Constrcuts a list of available commands.
     *
     * @return Description of all available commands.
     */
    public static String commandsMessage() {
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
    public static String toolsMessage() {
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
