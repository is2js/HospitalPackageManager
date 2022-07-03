package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;
import doctor.domain.vo.Description;
import doctor.domain.vo.Sequence;
import doctor.domain.vo.Title;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DayOfWeekConditionTest {

    public static Stream<Arguments> isSatisfiedBy() {
        return Stream.of(
            Arguments.of(LocalDate.of(2022, 7, 1), false), // 금요일
            Arguments.of(LocalDate.of(2022, 7, 2), true), // 토요일
            Arguments.of(LocalDate.of(2022, 7, 3), true) // 일요일
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSatisfiedBy(final LocalDate purchaseDate, final boolean expected) {
        final DayOfWeekCondition dayOfWeekCondition = new DayOfWeekCondition(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        final Treatment treatment = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L),
            purchaseDate);

        final boolean actual = dayOfWeekCondition.isSatisfiedBy(treatment, Count.of(1L));

        Assertions.assertThat(actual).isEqualTo(expected);

    }
}
