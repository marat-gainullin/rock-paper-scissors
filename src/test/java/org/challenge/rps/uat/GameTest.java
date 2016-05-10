/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.uat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import org.challenge.rps.Game;
import org.challenge.rps.Settings;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mg
 */
public class GameTest {

    private String makeGame(String aPlayerInput, Settings aSettings) throws UnsupportedEncodingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Game game = new Game(new Scanner(aPlayerInput), new PrintStream(out, true), aSettings);
        game.start();
        return new String(out.toByteArray(), "utf-8");
    }

    @Test
    public void freshness() throws UnsupportedEncodingException {
        String sameInput = "1\n0\n0\nq\nq\n";
        int sameGames = 0;
        for (int g = 0; g < 100000; g++) {
            String gameOutput1 = makeGame(sameInput, Settings.defaultSettings());
            String gameOutput2 = makeGame(sameInput, Settings.defaultSettings());
            sameGames += gameOutput1.equals(gameOutput2) ? 1 : 0;
        }
        assertTrue(sameGames < 10);
    }

    @Test
    public void compInsteadOfPlayer() throws UnsupportedEncodingException {
        String gameOutput = makeGame("0\n\ny\ny\nn\nq\n", Settings.defaultSettings());
        assertTrue(gameOutput.contains("Let computer make your choice?"));
    }

    @Test
    public void playerHimself() throws UnsupportedEncodingException {
        String gameOutput = makeGame("1\n0\n0\n0\n0\n0\n0\n0\n0\nq\nq\n", Settings.defaultSettings());
        assertTrue(gameOutput.contains("You win!") || gameOutput.contains("Computer wins."));
    }

    @Test
    public void timePeriod() throws UnsupportedEncodingException {
        String gameOutput = makeGame("1\n0\n0\nq\n1\n0\nq\nq\n", Settings.parse("-warn-period", "0"));
        assertTrue(gameOutput.contains("time is up"));
    }
}
