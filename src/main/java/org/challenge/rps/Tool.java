/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Optional;
import java.util.function.Function;
import org.challenge.rps.exceptions.InvalidNumberException;

/**
 * Enum of available tools for player to select from. Each tool has assoiated
 * tools wich beta it.
 *
 * @author mg
 */
public enum Tool {
    /**
     * The "Rock" tool.
     */
    ROCK("Rock"),
    /**
     * The "Scissors" tool.
     */
    SCISSORS("Scissors"),
    /**
     * The "Paper" tool.
     */
    PAPER("Paper");

    /**
     * Name of the tool element.
     */
    private final String name;

    /**
     * Tool enumeration element constructor.
     *
     * @param aName Name of the tool.
     */
    Tool(String aName) {
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
    private static final Tool[] TOOLS = Tool.values();

    /**
     * Predicate for <code>ConsoleUtils.nextId()</code>.
     *
     * @return Predicate for <code>ConsoleUtils.nextId()</code>.
     * @see ConsoleUtils#nextId(java.util.Scanner, java.util.function.Function)
     */
    public static Function<Integer, Optional<Tool>> as() {
        return (Integer aOrdinal) -> {
            try {
                return Optional.of(TOOLS[aOrdinal]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new InvalidNumberException(aOrdinal);
            }
        };
    }
}
