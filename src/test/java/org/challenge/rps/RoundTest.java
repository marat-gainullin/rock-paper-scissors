package org.challenge.rps;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

/**
 * Tests for <code>Round</code> class.
 * @author mg
 */
public class RoundTest {

    /**
     * Tests weither winner is present.
     */
    @Test
    public final void winnerIsPresent() {
        Round round = new Round(Tool.PAPER, Tool.ROCK);
        Optional<Tool> winner = round.winner();
        assertTrue(winner.isPresent());
    }

    /**
     * Tests the ability of <code>Round</code> to detect a dead heat.
     */
    @Test
    public final void deadHeat() {
        Round round = new Round(Tool.ROCK, Tool.ROCK);
        Optional<Tool> winner = round.winner();
        assertFalse(winner.isPresent());
    }

    /**
     * Utility method for beat chacks.
     * @param aLeft A tool on the left side.
     * @param aRight A tool on the right side.
     * @param aWinner A expected winner tool.
     */
    private void beat(final Tool aLeft, final Tool aRight, final Tool aWinner) {
        Round round = new Round(aLeft, aRight);
        Optional<Tool> winner = round.winner();
        assertSame(aWinner, winner.get());
    }

    /**
     * Tests weither one tool is ablbe to beat another tool from left to right.
     */
    @Test
    public final void beatLeft() {
        beat(Tool.PAPER, Tool.ROCK, Tool.PAPER);
        beat(Tool.ROCK, Tool.SCISSORS, Tool.ROCK);
        beat(Tool.SCISSORS, Tool.PAPER, Tool.SCISSORS);
    }

    /**
     * Tests weither one tool is ablbe to beat another tool from right to left.
     */
    @Test
    public final void beatRight() {
        beat(Tool.ROCK, Tool.PAPER, Tool.PAPER);
        beat(Tool.SCISSORS, Tool.ROCK, Tool.ROCK);
        beat(Tool.PAPER, Tool.SCISSORS, Tool.SCISSORS);
    }

}
