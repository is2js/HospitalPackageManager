package doctor_v2.discountpolicy.condition;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PeriodConditionTest {

    public static Stream<Arguments> isSatisfiedBy() {
        return Stream.of(
            Arguments.of(LocalDate.now().minusDays(1), 1, LocalDate.now(), false),
            Arguments.of(LocalDate.now().minusDays(1), 2, LocalDate.now(), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSatisfiedBy(final LocalDate releaseDate, final long eventDays, final LocalDate purchaseDate,
                       final boolean expected) {
        final Treatment treatment = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L),
            releaseDate);;
        final PeriodCondition periodCondition = new PeriodCondition(eventDays, purchaseDate);
        final boolean actual = periodCondition.isSatisfiedBy(treatment, Count.of(1L));

        assertThat(actual).isEqualTo(expected);
    }
}
