package org.challenge.rps.strategies;

import org.challenge.rps.Tool;

/**
 * Interface for strategies in the game.
 *
 * @author mg
 */
public interface Strategy {

    /**
     * Reports a success while using <code>aTool</code> in a round.
     *
     * @param aTool A successfull tool.
     */
    void gain(Tool aTool);

    /**
     * Reports a failure while using <code>aTool</code> in a round.
     *
     * @param aTool A failed tool.
     */
    void penalty(Tool aTool);

    /**
     * Automatically selects a tool for the next round.
     *
     * @return A tool selected according to the strategy.
     */
    Tool next();
}
