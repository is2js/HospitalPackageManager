package doctor_v2.discountpolicy.condition;

import static org.assertj.core.api.Assertions.*;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SequenceConditionTest {

    @ParameterizedTest
    @CsvSource({"1, 3, true", "3, 3, true", "4, 3, false"})
    void isSatisfiedBy(final Long sequence, final long eventMaxSequence, final boolean expected) {

        final Treatment treatment = new Treatment(Sequence.of(sequence), Title.of("123"), Description.of("12345"),
            Count.of(1L), LocalDate.now());

        final SequenceCondition sequenceCondition = new SequenceCondition(Sequence.of(eventMaxSequence));

        final boolean actual = sequenceCondition.isSatisfiedBy(treatment, Count.of(1L));

        assertThat(actual).isEqualTo(expected);
    }
}
