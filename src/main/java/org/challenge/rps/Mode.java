package org.challenge.rps;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Enum of available modes for the first player.
 *
 * @author mg
 */
public enum Mode {

    /**
     * Mode to start a game in computer vs. computer mode.
     */
    COMP("Computer instead of me", "1"),
    /**
     * Mode to start a game in player vs. computer mode.
     */
    PLAYER("Will play by myself", "2");

    /**
     * Name of the mode element.
     */
    private final String description;

    /**
     * Nnumber used for input.
     */
    private final String value;

    /**
     * Mode enumeration element constructor.
     *
     * @param aName Name of the mode.
     * @param aValue Value of the mode.
     */
    Mode(final String aName, final String aValue) {
        description = aName;
        value = aValue;
    }

    /**
     * Element's name property getter.
     *
     * @return Enumeration element's name.
     */
    public String getName() {
        return description;
    }

    /**
     * Element's name property getter.
     *
     * @return Enumeration element's value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Array of enumeration element by ordinal position. It is used in as()
     * predicate.
     *
     * @see #as()
     */
    private static final Map<String, Mode> MODES = new HashMap<String, Mode>() {
        {
            for (Mode mode : Mode.values()) {
                put(mode.getValue().toLowerCase(), mode);
            }
        }
    };

    /**
     * Predicate for <code>ConsoleUtils.nextId()</code>.
     *
     * @return Predicate for <code>ConsoleUtils.nextId()</code>.
     * @see ConsoleUtils#nextId(java.util.Scanner, java.util.function.Function)
     */
    public static Function<String, Optional<Mode>> as() {
        return (String aValue) -> {
            if (aValue == null) {
                return Optional.empty();
            } else {
                String lowerValue = aValue.toLowerCase();
                if (MODES.containsKey(lowerValue)) {
                    return Optional.of(MODES.get(lowerValue));
                } else {
                    return Optional.empty();
                }
            }
        };
    }
}
