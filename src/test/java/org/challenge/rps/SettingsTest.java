/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mg
 */
public class SettingsTest {

    @Test
    public void parseEmpty() {
        Settings empty = Settings.parse();
        assertEquals(3600000, empty.getWarnPeriod());
        assertFalse(empty.isColorful());
    }

    @Test
    public void parseWarnPeriod() {
        long expected = 150;
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM, String.valueOf(expected));
        assertEquals(expected, parsed.getWarnPeriod());
    }

    @Test
    public void parseWrongWarnPeriod() {
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM, String.valueOf("-1"));
        assertEquals(0, parsed.getWarnPeriod());
    }

    @Test
    public void parseBadWarnPeriod() {
        Settings parsed = Settings.parse(Settings.WARN_PERIOD_PARAM, "NaN");
        assertEquals(3600000, parsed.getWarnPeriod());
    }

    @Test
    public void parseColorful() {
        Settings parsed = Settings.parse(Settings.COLORFUL_PARAM);
        assertTrue(parsed.isColorful());
    }
    
    @Test
    public void parseBadColorful() {
        Settings parsed = Settings.parse("first-bad-param", Settings.COLORFUL_PARAM, "second-bad-param");
        assertTrue(parsed.isColorful());
    }
}
