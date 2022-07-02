package doctor_v2.discountpolicy.amount;

import static org.assertj.core.api.Assertions.*;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SequenceAmountDiscountTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1, 3, true",
        "3, 3, true",
        "4, 3, false"
    })
    void isSatisfiedBy(final Long currentSequence, final Long eventMaxSequence, final boolean expected) {
        final Treatment treatment = new Treatment(Sequence.of(currentSequence),
            Title.of("치료"),
            Description.of("설명입니다"),
            Count.of(10L),
            LocalDate.of(2022, 07, 01));

        final SequenceAmountDiscount sequenceAmountDiscount = new SequenceAmountDiscount(Money.of(0.0),
            Sequence.of(eventMaxSequence));

        final boolean actual = sequenceAmountDiscount.isSatisfiedBy(treatment);

        assertThat(actual).isEqualTo(expected);

    }

}
