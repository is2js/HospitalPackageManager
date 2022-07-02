package doctor_v2.discountpolicy.amount;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PeriodAmountDiscountTest {

    @ParameterizedTest
    @CsvSource(value = {"0, false", "1, true"})
    void isSatisfiedBy(final long eventDays, final boolean expected) {
        final Treatment treatment = new Treatment(Sequence.of(1L),
            Title.of("치료"),
            Description.of("설명입니다"),
            Count.of(10L),
            LocalDate.of(2022, 07, 01));

        final PeriodAmountDiscount periodAmountDiscount = new PeriodAmountDiscount(Money.of(0.0), eventDays);

        assertThat(periodAmountDiscount.isSatisfiedBy(treatment)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, false", "1, true"})
    void isSatisfiedBy____now입력없이_내부자동(final long eventDays, final boolean expected) {
        final Treatment treatment = new Treatment(Sequence.of(1L),
            Title.of("치료"),
            Description.of("설명입니다"),
            Count.of(10L),
            LocalDate.now().minusDays(1));

        final PeriodAmountDiscount periodAmountDiscount = new PeriodAmountDiscount(Money.of(0.0), eventDays);

        assertThat(periodAmountDiscount.isSatisfiedBy(treatment)).isEqualTo(expected);
    }
}
