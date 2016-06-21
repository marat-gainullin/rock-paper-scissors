package org.challenge.rps;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

/**
 * Tests for <code>Mode</code> enum.
 *
 * @author mg
 */
public class ModeTest {

    /**
     * Tests for <code>Mode.as()</code> predicate. Tests wrong text outcome.
     */
    @Test
    public final void asCrazyTest() {
        Optional crazy = Mode.as().apply("crazy-mode");
        assertFalse(crazy.isPresent());
    }

    /**
     * Tests for <code>Mode.as()</code> predicate. Tests null input outcome.
     */
    @Test
    public final void asNullTest() {
        Optional<Mode> nulled = Mode.as().apply(null);
        assertFalse(nulled.isPresent());
    }
}
