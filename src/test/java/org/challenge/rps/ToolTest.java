package org.challenge.rps;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

/**
 * Tests for <code>Tool</code> enum.
 * @author mg
 */
public class ToolTest {

    /**
     * Tests for <code>Tool.as()</code> predicate. Tests wrong input outcome.
     */
    @Test
    public final void asCrazyTest() {
        Optional<Tool> crazy = Tool.as().apply("crazy-tool");
        assertFalse(crazy.isPresent());
    }

    /**
     * Tests for <code>Tool.as()</code> predicate. Tests null input outcome.
     */
    @Test
    public final void asNullTest() {
        Optional<Tool> nulled = Tool.as().apply(null);
        assertFalse(nulled.isPresent());
    }
}
