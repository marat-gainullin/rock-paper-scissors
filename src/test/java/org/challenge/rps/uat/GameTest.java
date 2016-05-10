package org.challenge.rps.uat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import org.challenge.rps.Game;
import org.challenge.rps.Settings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests for <code>Game</code> class.
 *
 * @author mg
 */
public class GameTest {

    /**
     * Sample count. Used in freshness test.
     */
    private static final int SAMPLES_COUNT = 1000;

    /**
     * Utility method for making game.
     *
     * @param aPlayerInput A sample player's input.
     * @param aSettings A settings to use with a game.
     * @return Output of the game.
     *
     * @throws UnsupportedEncodingException From <code>String</code>
     * constructor.
     * @see String#String(byte[], java.lang.String)
     */
    private String makeGame(final String aPlayerInput,
            final Settings aSettings) throws UnsupportedEncodingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Game game = new Game(new Scanner(aPlayerInput),
                new PrintStream(out, true), aSettings);
        game.start();
        return new String(out.toByteArray(), "utf-8");
    }

    /**
     * Tests user acceptance criterion - "Can I play a different game each
     * time?".
     *
     * @throws UnsupportedEncodingException From <code>makeGame</code>
     * constructor.
     * @see #makeGame(java.lang.String, org.challenge.rps.Settings)
     */
    @Test
    public final void freshness() throws UnsupportedEncodingException {
        String sample = "1\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n"
                + "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\nq\nq\n";
        int sameGames = 0;
        for (int g = 0; g < SAMPLES_COUNT; g++) {
            String gameOutput1 = makeGame(sample, Settings.defaultSettings());
            String gameOutput2 = makeGame(sample, Settings.defaultSettings());
            if (gameOutput1.equals(gameOutput2)) {
                sameGames++;
            }
        }
        assertEquals(0, sameGames);
    }

    /**
     * Tests user acceptance criterion - "Can I play Computer v Computer?".
     *
     * @throws UnsupportedEncodingException From <code>makeGame</code>
     * constructor.
     * @see #makeGame(java.lang.String, org.challenge.rps.Settings)
     */
    @Test
    public final void compInsteadOfUser() throws UnsupportedEncodingException {
        String gameOutput = makeGame("0\n\ny\ny\nn\nq\n",
                Settings.defaultSettings());
        assertTrue(gameOutput.contains("Let computer make your choice?"));
    }

    /**
     * Tests user acceptance criterion - "Can I play Player v Computer?".
     *
     * @throws UnsupportedEncodingException From <code>makeGame</code>
     * constructor.
     * @see #makeGame(java.lang.String, org.challenge.rps.Settings)
     */
    @Test
    public final void playerHimself() throws UnsupportedEncodingException {
        String gameOutput = makeGame("1\n0\n0\n0\n0\n0\n0\n0\n0\nq\nq\n",
                Settings.defaultSettings());
        assertTrue(gameOutput.contains("You win!")
                || gameOutput.contains("Computer wins."));
    }

    /**
     * Tests "hidden" user acceptance criterion - "... I can spend an hour of my
     * day having fun.".
     *
     * @throws UnsupportedEncodingException From <code>makeGame</code>
     * constructor.
     * @see #makeGame(java.lang.String, org.challenge.rps.Settings)
     */
    @Test
    public final void timePeriod() throws UnsupportedEncodingException {
        String gameOutput = makeGame("1\n0\n0\nq\n1\n0\nq\nq\n",
                Settings.parse("-warn-period", "0"));
        assertTrue(gameOutput.contains("time is up"));
    }
}
