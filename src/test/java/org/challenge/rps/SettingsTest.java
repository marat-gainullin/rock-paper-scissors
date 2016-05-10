package org.challenge.rps;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Tests for <code>Settings</code> class.
 *
 * @author mg
 */
public class SettingsTest {

    /**
     * Warn period default value.
     */
    private static final int DEFAULT_WARN_PERIOD = 3600000;
    /**
     * Warn period sample value.
     */
    private static final int GOOD_WARN_PERIOD = 150;

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with
     * default values from empty arguments.
     */
    @Test
    public final void parseEmpty() {
        Settings empty = Settings.parse();
        assertEquals(DEFAULT_WARN_PERIOD, empty.getWarnPeriod());
        assertFalse(empty.isColorful());
    }

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with only
     * "-warn-period" parameter present.
     */
    @Test
    public final void parseWarnPeriod() {
        long expected = GOOD_WARN_PERIOD;
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM,
                String.valueOf(expected));
        assertEquals(expected, parsed.getWarnPeriod());
    }

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with
     * wrong "-warn-period" parameter.
     */
    @Test
    public final void parseWrongWarnPeriod() {
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM,
                String.valueOf("-1"));
        assertEquals(0, parsed.getWarnPeriod());
    }

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with bad
     * "-warn-period" parameter input string.
     */
    @Test
    public final void parseBadWarnPeriod() {
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM, "NaN");
        assertEquals(DEFAULT_WARN_PERIOD, parsed.getWarnPeriod());
    }

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with only
     * "-colorful" parameter present.
     */
    @Test
    public final void parseColorful() {
        Settings parsed = Settings.parse(Settings.COLORFUL_PARAM);
        assertTrue(parsed.isColorful());
    }

    /**
     * Tests weither <code>Settings.parse()</code> can prouce settings with bad
     * "-colorful" parameter input string.
     */
    @Test
    public final void parseBadColorful() {
        Settings parsed = Settings.parse("first-bad-param",
                Settings.COLORFUL_PARAM, "second-bad-param");
        assertTrue(parsed.isColorful());
    }
}
