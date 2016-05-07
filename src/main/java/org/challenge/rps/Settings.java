/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Settings, containing options of a game.
 *
 * @author mg
 */
public class Settings {

    /**
     * Command line parameter. Sets time interval after wich, the player is
     * warned about time of the game. codes. Just for fun :)
     */
    public static final String TIME_WARN_PARAM = "-warn-period";
    /**
     * Command line parameter. Sets length of history for alaysis while computer's moves.
     */
    public static final String HISTORY_LENGTH_PARAM = "-history-length";
    /**
     * Command line parameter. Colorizes the console output via ANSI escape
     * codes. Just for fun :)
     */
    public static final String COLORFUL_PARAM = "-colorful";
    /**
     * Perid to play without a time warning.
     */
    private long warnPeriod = 3600000;// 1 Hour
    /**
     * Length of history for alaysis while computer's moves.
     */
    private int historyLength = 100;
    /**
     * Whether console output should be colorized.
     */
    private boolean colorful;

    private Settings() {
        super();
    }

    /**
     * The "warnPeriod" parameter getter.
     *
     * @return Period of time the player to be warned about when elapsed.
     */
    public long getWarnPeriod() {
        return warnPeriod;
    }

    /**
     * The "historyLength" parameter getter.
     *
     * @return Number of round to be remembered for history analysis while
     * computer's moves.
     */
    public int getHistoryLength() {
        return historyLength;
    }

    /**
     * The "colorful" parameter getter.
     *
     * @return True if colorful.
     */
    public boolean isColorful() {
        return colorful;
    }

    /**
     * Settings setter method for "warnPeriod" option.
     *
     * @param aValue Period of time in milliseconds.
     */
    private void setWarnPeriod(long aValue) {
        warnPeriod = aValue;
    }

    /**
     *
     * Settings setter method for "historyLength" option.
     *
     * @param aValue Length of rounds history. Can't be less then zero.
     */
    public void setHistoryLength(int aValue) {
        historyLength = aValue >= 0 ? aValue : 0;
    }

    /**
     * Settings setter method for "colorful" option.
     *
     * @param aValue True if console output should be colorized.
     */
    private void setColorful(boolean aValue) {
        colorful = aValue;
    }

    /**
     * Factory method of <code>Settings</code>. Parses Settings from an array of
     * string arguments.
     *
     * @param aParams An array of string arguments.
     * @return Settings instance.
     */
    public static Settings parse(String... aParams) {
        Settings settings = new Settings();
        int i = 0;
        while (i < aParams.length) {
            switch (aParams[i]) {
                case TIME_WARN_PARAM:
                    if (i < aParams.length - 1) {
                        try {
                            settings.setWarnPeriod(Long.valueOf(aParams[i + 1]));
                            i++;
                        } catch (NumberFormatException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.WARNING, ex.toString());
                        }
                    }
                    break;
                case HISTORY_LENGTH_PARAM:
                    if (i < aParams.length - 1) {
                        try {
                            settings.setHistoryLength(Integer.valueOf(aParams[i + 1]));
                            i++;
                        } catch (NumberFormatException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.WARNING, ex.toString());
                        }
                    }
                    break;
                case COLORFUL_PARAM:
                    settings.setColorful(true);
                    break;
                default:
                    Logger.getLogger(Game.class.getName()).log(Level.WARNING, "Unknown parameter '{0}'", aParams[i]);
            }
            i++;
        }
        return settings;
    }

}
