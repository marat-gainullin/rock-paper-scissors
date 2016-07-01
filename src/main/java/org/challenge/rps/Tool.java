package org.challenge.rps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Enum of available tools for player to select from. Each tool has associated
 * tools wich it beats.
 *
 * @author mg
 */
public enum Tool {
    /**
     * The "Rock" tool.
     */
    ROCK("Rock", "1"),
    /**
     * The "Scissors" tool.
     */
    SCISSORS("Scissors", "2"),
    /**
     * The "Paper" tool.
     */
    PAPER("Paper", "3");

    /**
     * Name of the tool element.
     */
    private final String name;

    /**
     * Value of the tool element.
     */
    private final String value;

    /**
     * Tool enumeration element constructor.
     *
     * @param aName Name of the tool.
     * @param aValue Value of the tool.
     */
    Tool(final String aName, final String aValue) {
        name = aName;
        value = aValue;
    }

    /**
     * Tool's name getter.
     *
     * @return Enumeration element's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Tool's value getter.
     *
     * @return Enumeration element's name.
     */
    public String getValue() {
        return value;
    }

    /**
     * Detemines weither this tool wins <code>aTool</code> tool.
     *
     * @param aTool A tool to be tested if it is beated by this tool.
     * @return True if <code>aTool</code> tool is beated and false otherwise.
     */
    public boolean wins(final Tool aTool) {
        return BEATED.get(this).contains(aTool);
    }

    /**
     * Scheme of tools (in values) beated by other tools (in keys). If you whant
     * to extend the game, you should add elements to the enumeration and to
     * this structure.
     */
    private static final Map<Tool, Set<Tool>> BEATED
            = new HashMap<Tool, Set<Tool>>() {
        {
            put(Tool.ROCK, new HashSet<>(Arrays.asList(Tool.SCISSORS)));
            put(Tool.PAPER, new HashSet<>(Arrays.asList(Tool.ROCK)));
            put(Tool.SCISSORS, new HashSet<>(Arrays.asList(Tool.PAPER)));
        }
    };

    /**
     * Map of enumeration elements by value. It is used in as()
     * predicate.
     *
     * @see #as()
     */
    private static final Map<String, Tool> TOOLS = new HashMap<String, Tool>() {
        {
            for (Tool tool : Tool.values()) {
                put(tool.getValue(), tool);
            }
        }
    };

    /**
     * Predicate for <code>ConsoleUtils.nextId()</code>.
     *
     * @return Predicate for <code>ConsoleUtils.nextId()</code>.
     * @see ConsoleUtils#nextId(java.util.Scanner, java.util.function.Function)
     */
    public static Function<String, Optional<Tool>> as() {
        return (String aValue) -> {
            if (aValue == null) {
                return Optional.empty();
            } else {
                String lowerValue = aValue.toLowerCase();
                if (TOOLS.containsKey(lowerValue)) {
                    return Optional.of(TOOLS.get(lowerValue));
                } else {
                    return Optional.empty();
                }
            }
        };
    }
}
