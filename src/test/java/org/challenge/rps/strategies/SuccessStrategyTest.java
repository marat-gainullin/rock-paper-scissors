/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.strategies;

import org.challenge.rps.Tool;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author mg
 */
public class SuccessStrategyTest {

    @Test
    public void gainTest() {
        Strategy subject = new ExponentialStrategy();
        int rockSuccess = 100;
        for (int s = 0; s < rockSuccess; s++) {
            subject.gain(Tool.ROCK);
        }
        int paperSuccess = 50;
        for (int s = 0; s < paperSuccess; s++) {
            subject.gain(Tool.PAPER);
        }
        subject.gain(Tool.SCISSORS);// success = 1
        int totalCases = 1000000;
        int rockCases = 0;
        int paperCases = 0;
        int scissorsCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (subject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissorsCases++;
                    break;
            }
        }
        assertTrue(rockCases > paperCases);
        assertTrue(paperCases > scissorsCases);
        assertTrue(Math.abs(rockCases - paperCases) / (float) totalCases > 0.1f);
        assertTrue(Math.abs(paperCases - scissorsCases) / (float) totalCases > 0.1f);
    }

    @Test
    public void penaltyTest() {
        Strategy subject = new ExponentialStrategy();
        int rockSuccess = 100;
        for (int s = 0; s < rockSuccess; s++) {
            subject.penalty(Tool.ROCK);
        }
        int paperSuccess = 50;
        for (int s = 0; s < paperSuccess; s++) {
            subject.penalty(Tool.PAPER);
        }
        subject.penalty(Tool.SCISSORS);// success = -1
        int totalCases = 1000000;
        int rockCases = 0;
        int paperCases = 0;
        int scissorsCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (subject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissorsCases++;
                    break;
            }
        }
        assertTrue(rockCases < paperCases);
        assertTrue(paperCases < scissorsCases);
        assertTrue(Math.abs(rockCases - paperCases) / (float) totalCases > 0.1f);
        assertTrue(Math.abs(paperCases - scissorsCases) / (float) totalCases > 0.1f);
    }

    @Test
    public void uniformTest() {
        Strategy subject = new ExponentialStrategy();
        int totalCases = 1000000;
        int rockCases = 0;
        int paperCases = 0;
        int scissorsCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (subject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissorsCases++;
                    break;
            }
        }
        assertTrue(Math.abs(rockCases - paperCases) / (float) totalCases < 0.01f);
        assertTrue(Math.abs(paperCases - scissorsCases) / (float) totalCases < 0.01f);
    }
}
