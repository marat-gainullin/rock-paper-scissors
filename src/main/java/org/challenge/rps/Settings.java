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
public final class Settings {

    /**
     * Unknown parameter message.
     */
    private static final String UNKNOWN_PARAM_MSG = ""
            + "Unknown parameter \"{0}\"";
    /**
     * Command line parameter. Sets time interval after wich, the player is
     * warned about time of the game. codes.
     */
    static final String WARN_PERIOD_PARAM = "-warn-period";
    /**
     * Command line parameter. Colorizes the console output via ANSI/VT100
     * escape codes. Just for fun :)
     */
    static final String COLORFUL_PARAM = "-colorful";
    /**
     * Default value for period to play without a time warning parameter - 1
     * hour.
     */
    static final int DEFAULT_WARN_PERIOD = 3600000;

    /**
     * Handler interface of Settings parsers.
     */
    private interface SettingsHandler
            extends BiFunction<String, Settings, Integer> {
    }

    /**
     * Params -> actions mapping.
     */
    private static final Map<String, SettingsHandler> PARAMS
            = new HashMap<String, SettingsHandler>() {
        {
            put(WARN_PERIOD_PARAM, (String aValue, Settings aSettings) -> {
                try {
                    aSettings.setWarnPeriod(Long.valueOf(aValue));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(Game.class.getName())
                            .log(Level.WARNING, ex.toString());
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
     * Period to play without a time warning. Default is one hour.
     */
    private long warnPeriod = DEFAULT_WARN_PERIOD;
    /**
     * Whether console output should be colorized.
     */
    private boolean colorful;

    /**
     * Default constructor, hidden because of factoy nature of settings
     * construction.
     */
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
    private void setWarnPeriod(final long aValue) {
        if (aValue >= 0) {
            warnPeriod = aValue;
        } else {
            warnPeriod = 0;
        }
    }

    /**
     * Settings setter method for "colorful" option.
     *
     * @param aValue True if console output should be colorized.
     */
    private void setColorful(final boolean aValue) {
        colorful = aValue;
    }

    /**
     * Factory method of <code>Settings</code>. Returns default settings.
     *
     * @return <code>Settings</code> instance with default values.
     */
    public static Settings defaultSettings() {
        return new Settings();
    }

    /**
     * Factory method of <code>Settings</code>. Parses Settings from an array of
     * string arguments.
     *
     * @param aParams An array of string arguments.
     * @return Settings instance with values parsed from a parameter.
     */
    public static Settings parse(final String... aParams) {
        Settings settings = new Settings();
        int i = 0;
        while (i < aParams.length) {
            BiFunction<String, Settings, Integer> handler
                    = PARAMS.get(aParams[i]);
            if (handler != null) {
                String paramValue;
                if (i < aParams.length - 1) {
                    paramValue = aParams[i + 1];
                } else {
                    paramValue = null;
                }
                i += handler.apply(paramValue, settings);
            } else {
                Logger.getLogger(Game.class.getName())
                        .log(Level.WARNING, UNKNOWN_PARAM_MSG, aParams[i]);
                i++;
            }
        }
        return settings;
    }

}
