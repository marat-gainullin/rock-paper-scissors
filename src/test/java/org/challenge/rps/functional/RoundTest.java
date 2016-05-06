/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.functional;

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
    
    @Test
    public void beatLeft() {
        Round round = new Round(Tool.PAPER, Tool.ROCK);
        Optional<Tool> winner = round.winner();
        assertSame(Tool.PAPER, winner.get());
        Round round1 = new Round(Tool.ROCK, Tool.SCISSORS);
        Optional<Tool> winner1 = round1.winner();
        assertSame(Tool.ROCK, winner1.get());
        Round round2 = new Round(Tool.SCISSORS, Tool.PAPER);
        Optional<Tool> winner2 = round2.winner();
        assertSame(Tool.SCISSORS, winner2.get());
    }
    
    @Test
    public void beatRight() {
        Round round = new Round(Tool.ROCK, Tool.PAPER);
        Optional<Tool> winner = round.winner();
        assertSame(Tool.PAPER, winner.get());
        Round round1 = new Round(Tool.SCISSORS, Tool.ROCK);
        Optional<Tool> winner1 = round1.winner();
        assertSame(Tool.ROCK, winner1.get());
        Round round2 = new Round(Tool.PAPER, Tool.SCISSORS);
        Optional<Tool> winner2 = round2.winner();
        assertSame(Tool.SCISSORS, winner2.get());
    }
    
}
