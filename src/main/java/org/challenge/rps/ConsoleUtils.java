/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Utility class with useful abstractions for console input/output.
 * @author mg
 */
public class ConsoleUtils {

    /**
     * Constant with hello message.
     */
    public static final String COMMANDS_MSG = "How do you want to play:";
    /**
     * Prompt message for a player.
     */
    public static final String PROMPT = "play > ";
    /**
     * Constant with OS's line separator.
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * Abstraction for some identified entity input by id from a console.
     * Detects wrong input and attempts to so it again.
     * @param <T> Type of the entity.
     * @param aSource Console scanner used to input numbers - ids.
     * @param aFactory Predicate used for actual entity instance creation.
     * @return Creation entity instance.
     */
    public static <T> T byId(Scanner aSource, Function<Integer, T> aFactory) {
        Runnable attempt = () -> {
            aSource.nextLine();
            printCommands();
            System.out.print(PROMPT);
        };
        T command = null;
        while (command == null) {
            try {
                int commandId = aSource.nextInt();
                T cmd = aFactory.apply(commandId);
                if (cmd != null) {
                    command = cmd;
                } else {
                    //System.out.println(String.format("Unknown number %d. Try again, please.", commandId));
                    attempt.run();
                }
            } catch (InputMismatchException ex) {
                attempt.run();
            }
        }
        return command;
    }

    /**
     * Prints a list of available commands.
     */
    public static void printCommands() {
        System.out.println(COMMANDS_MSG);
        StringBuilder message = new StringBuilder();
        for (Command cmd : Command.values()) {
            message
                    .append(cmd.ordinal())
                    .append(" - ")
                    .append(cmd.getName())
                    .append(LINE_SEP);
        }
        System.out.print(message.toString());
    }
}
