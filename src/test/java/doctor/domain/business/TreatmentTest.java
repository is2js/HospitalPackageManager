package doctor.domain.business;

import static doctor.fixture.Fixture.TREATMENT_첫번째_10개;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor.domain.vo.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TreatmentTest {

    @DisplayName("")
    @Test
    void hasCount() {
        final boolean actual1 = TREATMENT_첫번째_10개.hasCount(Count.of(10L));
        final boolean actual2 = TREATMENT_첫번째_10개.hasCount(Count.of(15L));

        assertAll(
          () -> assertThat(actual1).isTrue(),
          () -> assertThat(actual2).isFalse()
        );
    }

    @DisplayName("")
    @Test
    void minusCount() {
        TREATMENT_첫번째_10개.minusCount(Count.of(1L));

        final boolean actual = TREATMENT_첫번째_10개.hasCount(Count.of(10L));

        assertThat(actual).isFalse();
    }
}
