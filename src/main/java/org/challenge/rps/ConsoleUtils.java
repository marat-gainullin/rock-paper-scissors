/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.io.PrintStream;
import java.util.Optional;
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
     * Prompt message for a player.
     */
    private static final String PROMPT = "q - quit > ";

    /**
     * Time is up message for a player.
     */
    private static final String TIMEOUT_MSG = "hurry up! ";

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
     * @param aSource A scanner used to input numbers - ids.
     * @param aOut An output print stream for messages.
     * @param anAttemptMessge A message to be displayed when a player inputs an
     * incorrect symbols.
     * @param aFactory Predicate used for actual entity instance creation.
     * @return Creation entity instance.
     */
    public static <T> T nextId(Scanner aSource, PrintStream aOut, String anAttemptMessge, Function<Integer, Optional<T>> aFactory) {
        Runnable attempt = () -> {
            aOut.print(anAttemptMessge);
            long begining = System.currentTimeMillis();
            if(System.currentTimeMillis() - begining > TIMEOUT){
                aOut.print(TIMEOUT_MSG);
            }
            aOut.print(PROMPT);
        };
        attempt.run();
        Optional<T> entity = Optional.empty();
        while (!entity.isPresent()) {
            String line = aSource.nextLine().toLowerCase();
            if (line.startsWith(EXIT_COMMAND)) {
                throw new QuitLevelException(line);
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
        return entity.get();
    }
    private static final int TIMEOUT = 3600000;

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
