package org.challenge.rps;

import java.util.Optional;

/**
 * Round of a game session.
 *
 * @author mg
 */
public class Round {

    /**
     * First player tool for this round.
     */
    private final Tool firstTool;
    /**
     * Second player tool for this round.
     */
    private final Tool secondTool;

    /**
     * Constructs a round with a pair of tools, selected by players.
     *
     * @param aFirstTool A tool, selected by a first player.
     * @param aSecondTool A tool, selected by a second player.
     */
    public Round(final Tool aFirstTool, final Tool aSecondTool) {
        firstTool = aFirstTool;
        secondTool = aSecondTool;
    }

    /**
     * Getter method for first player's tool.
     *
     * @return First player's tool.
     */
    public final Tool getFirstTool() {
        return firstTool;
    }

    /**
     * Getter method for second player's tool.
     *
     * @return Second player's tool.
     */
    public final Tool getSecondTool() {
        return secondTool;
    }

    /**
     * Detemines a winner of this round. In case of tools(weapons) set
     * extension, this method should be re-implemented.
     *
     * @return A winner of this round.
     */
    public final Optional<Tool> winner() {
        if (firstTool == secondTool) {
            return Optional.empty();
        } else if (firstTool.ordinal() - secondTool.ordinal() == -1
                || firstTool.ordinal() - secondTool.ordinal() == 2) {
            return Optional.of(firstTool);

        } else {
            return Optional.of(secondTool);
        }
    }

}
