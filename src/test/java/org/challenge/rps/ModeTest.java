package org.challenge.rps;

import org.challenge.rps.exceptions.InvalidNumberException;
import org.junit.Test;

/**
 * Tests for <code>Mode</code> enum.
 *
 * @author mg
 */
public class ModeTest {

    /**
     * Tests for <code>Mode.as()</code> predicate. Tests wrong number outcome.
     */
    @Test(expected = InvalidNumberException.class)
    public final void asTest() {
        Mode.as().apply(Mode.values().length);
    }
}
