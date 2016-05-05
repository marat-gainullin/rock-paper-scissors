/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.function.Function;

/**
 * Enum of available tools for player to select from. Each tool has assoiated tools wich beta it.
 * @author mg
 */
public enum Tool {
    /**
     * The "Rock" tool.
     */
    ROCK("Rock"),
    /**
     * The "Paper" tool
     */
    PAPER("Paper"),
    /**
     * The "Scossors" tool
     */
    SCISSORS("Scissors");

    /**
     * Name of the tool element.
     */
    private final String name;

    /**
     * Tool enumeration element constructor.
     * @param aName 
     */
    private Tool(String aName) {
        name = aName;
    }

    /**
     * Enumeration element's name property getter.
     * @return Enumeration element's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Array of enumeration element by ordinal position.
     * It is used in as() predicate.
     * @see #as() 
     */
    private static final Tool[] TOOLS = Tool.values();

    /**
     * Predicate for <code>ConsoleUtils.nextOrdinal()</code>.
     * @return Predicate for <code>ConsoleUtils.nextOrdinal()</code>.
     * @see ConsoleUtils#nextOrdinal(java.util.Scanner, java.util.function.Function) 
     */
    public static Function<Integer, Tool> as() {
        return (Integer aOrdinal) -> {
            return aOrdinal >= 0 && aOrdinal < TOOLS.length ? TOOLS[aOrdinal] : null;
        };
    }
}
