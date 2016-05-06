/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.function.Function;
import org.challenge.rps.exceptions.InvalidNumberException;

/**
 * Enum of available commands for player. Each command has an action assoiated
 * with it.
 *
 * @author mg
 */
public enum Command {

    /**
     * Command to start a game in computer vs. computer mode.
     */
    COMP_COMP("Computer"),
    /**
     * Command to start s game in player vs. computer mode.
     */
    PLAYER_COMP("Player");

    /**
     * Name of the command element.
     */
    private final String name;

    /**
     * Command enumeration element constructor.
     *
     * @param aName
     */
    private Command(String aName) {
        name = aName;
    }

    /**
     * Enumeration element's name property getter.
     *
     * @return Enumeration element's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Array of enumeration element by ordinal position. It is used in as()
     * predicate.
     *
     * @see #as()
     */
    private static final Command[] COMMANDS = Command.values();

    /**
     * Predicate for <code>ConsoleUtils.nextId()</code>.
     *
     * @return Predicate for <code>ConsoleUtils.nextId()</code>.
     * @see ConsoleUtils#nextId(java.util.Scanner, java.util.function.Function)
     */
    public static Function<Integer, Command> as() {
        return (Integer aOrdinal) -> {
            try {
                return COMMANDS[aOrdinal];
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new InvalidNumberException(aOrdinal);
            }
        };
    }
}
