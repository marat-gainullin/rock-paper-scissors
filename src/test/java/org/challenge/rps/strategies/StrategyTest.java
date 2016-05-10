/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.strategies;

import org.challenge.rps.Tool;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author mg
 */
public abstract class StrategyTest {

    public void gain(Strategy aSubject) {
        int rockSuccess = 100;
        for (int s = 0; s < rockSuccess; s++) {
            aSubject.gain(Tool.ROCK);
        }
        int paperSuccess = 50;
        for (int s = 0; s < paperSuccess; s++) {
            aSubject.gain(Tool.PAPER);
        }
        aSubject.gain(Tool.SCISSORS);// success = 1
        int totalCases = 1000000;
        int rockCases = 0;
        int paperCases = 0;
        int scissorsCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (aSubject.next()) {
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

    public void penalty(Strategy aSubject) {
        int rockSuccess = 100;
        for (int s = 0; s < rockSuccess; s++) {
            aSubject.penalty(Tool.ROCK);
        }
        int paperSuccess = 50;
        for (int s = 0; s < paperSuccess; s++) {
            aSubject.penalty(Tool.PAPER);
        }
        aSubject.penalty(Tool.SCISSORS);// success = -1
        int totalCases = 1000000;
        int rockCases = 0;
        int paperCases = 0;
        int scissorsCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (aSubject.next()) {
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

    public void uniform(Strategy subject) {
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
