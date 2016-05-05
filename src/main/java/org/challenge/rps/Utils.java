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
 *
 * @author mg
 */
public class Utils {

    public static final String HELLO_MSG = "Rock, paper, scissors game :)\n";
    public static final String HELP_MSG = "Do your game:";
    public static final String PROMPT = "play > ";
    public static final String UTF8 = "utf-8";
    public static final String LINE_SEP = System.getProperty("line.separator");

    public static <T> T nextOrdinal(Scanner aSource, Function<Integer, T> aFactory) {
        Runnable attempt = () -> {
            aSource.nextLine();
            printHelp();
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
                    System.out.println(String.format("Unknown number %d. Try again, please.", commandId));
                    attempt.run();
                }
            } catch (InputMismatchException ex) {
                attempt.run();
            }
        }
        return command;
    }

    public static void printHelp() {
        System.out.println(HELP_MSG);
        StringBuilder message = new StringBuilder();
        for (Command cmd : Command.values()) {
            message
                    .append(cmd.ordinal())
                    .append(", ")
                    .append(cmd.getName())
                    .append(" - ")
                    .append(cmd.getName())
                    .append(LINE_SEP);
        }
        System.out.print(message.toString());
    }
}
