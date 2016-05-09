/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Optional;
import org.challenge.rps.Round;
import org.challenge.rps.Tool;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mg
 */
public class RoundTest {

    @Test
    public void winnerIsPresent() {
        Round round = new Round(Tool.PAPER, Tool.ROCK);
        Optional<Tool> winner = round.winner();
        assertTrue(winner.isPresent());
    }

    @Test
    public void deadHeat() {
        Round round = new Round(Tool.ROCK, Tool.ROCK);
        Optional<Tool> winner = round.winner();
        assertFalse(winner.isPresent());
    }

    private void beat(Tool aLeft, Tool aRight, Tool aWinner) {
        Round round = new Round(aLeft, aRight);
        Optional<Tool> winner = round.winner();
        assertSame(aWinner, winner.get());
    }

    @Test
    public void beatLeft() {
        beat(Tool.PAPER, Tool.ROCK, Tool.PAPER);
        beat(Tool.ROCK, Tool.SCISSORS, Tool.ROCK);
        beat(Tool.SCISSORS, Tool.PAPER, Tool.SCISSORS);
    }

    @Test
    public void beatRight() {
        beat(Tool.ROCK, Tool.PAPER, Tool.PAPER);
        beat(Tool.SCISSORS, Tool.ROCK, Tool.ROCK);
        beat(Tool.PAPER, Tool.SCISSORS, Tool.SCISSORS);
    }

}
