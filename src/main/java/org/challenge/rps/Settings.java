/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
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
     * warned about time of the game. codes.
     */
    static final String WARN_PERIOD_PARAM = "-warn-period";
    /**
     * Command line parameter. Colorizes the console output via ANSI/VT100 escape
     * codes. Just for fun :)
     */
    static final String COLORFUL_PARAM = "-colorful";

    private static final Map<String, BiFunction<String, Settings, Integer>> PARAMS = new HashMap<String, BiFunction<String, Settings, Integer>>() {
        {
            put(WARN_PERIOD_PARAM, (String aValue, Settings aSettings) -> {
                try {
                    aSettings.setWarnPeriod(Long.valueOf(aValue));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.WARNING, ex.toString());
                }
                return 2;
            });
            put(COLORFUL_PARAM, (String aValue, Settings aSettings) -> {
                aSettings.setColorful(true);
                return 1;
            });
        }
    };
    /**
     * Perid to play without a time warning.
     */
    private long warnPeriod = 3600000;// 1 Hour
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
        warnPeriod = aValue >= 0 ? aValue : 0;
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
     * Factory method of <code>Settings</code>. Returns default settings.
     * @return 
     */
    public static Settings defaultSettings() {
        return new Settings();
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
            BiFunction<String, Settings, Integer> handler = PARAMS.get(aParams[i]);
            if (handler != null) {
                i += handler.apply(i < aParams.length - 1 ? aParams[i + 1] : null, settings);
            } else {
                Logger.getLogger(Game.class.getName()).log(Level.WARNING, "Unknown parameter '{0}'", aParams[i]);
                i++;
            }
        }
        return settings;
    }

}
