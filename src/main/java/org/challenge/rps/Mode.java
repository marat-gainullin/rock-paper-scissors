package org.challenge.rps;

import java.util.Optional;
import java.util.function.Function;
import org.challenge.rps.exceptions.InvalidNumberException;

/**
 * Enum of available modes for the first player.
 *
 * @author mg
 */
public enum Mode {

    /**
     * Mode to start a game in computer vs. computer mode.
     */
    COMP("Computer instead of me"),
    /**
     * Mode to start a game in player vs. computer mode.
     */
    PLAYER("Will play by myself");

    /**
     * Name of the mode element.
     */
    private final String name;

    /**
     * Mode enumeration element constructor.
     *
     * @param aName Name of the mode.
     */
    Mode(final String aName) {
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
    private static final Mode[] MODES = Mode.values();

    /**
     * Predicate for <code>ConsoleUtils.nextId()</code>.
     *
     * @return Predicate for <code>ConsoleUtils.nextId()</code>.
     * @see ConsoleUtils#nextId(java.util.Scanner, java.util.function.Function)
     */
    public static Function<Integer, Optional<Mode>> as() {
        return (Integer aOrdinal) -> {
            try {
                return Optional.of(MODES[aOrdinal]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new InvalidNumberException(aOrdinal);
            }
        };
    }
}
