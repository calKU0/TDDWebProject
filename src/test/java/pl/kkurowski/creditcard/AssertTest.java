package pl.kkurowski.creditcard;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.assertj.core.api.Assertions.*;

public class AssertTest {

    @Test
    void notContains() {
        var hello = "Test";

        assertThat(hello).doesNotContain("1");
    }

    @Test
    void testListExpression() {
        var names = Collections.singleton("Klaudia");

        assertThat(names)
                .isUnmodifiable()
                .hasSize(1)
                .containsAnyElementsOf(Arrays.asList("Klaudia", "Michal"));
    }
}
