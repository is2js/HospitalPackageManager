package doctor_v2.discountpolicy.amount;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DayOfWeekAmountDiscountTest {

    public static Stream<Arguments> isSatisfiedBy() {
        return Stream.of(
            // 출시날짜, 이벤트요일, expected
            Arguments.of(LocalDate.now(), LocalDate.now().getDayOfWeek().minus(1), false),
            Arguments.of(LocalDate.now(), LocalDate.now().getDayOfWeek(), true),
            Arguments.of(LocalDate.now(), LocalDate.now().getDayOfWeek().plus(1), false));
    }

    @ParameterizedTest
    @MethodSource
    void isSatisfiedBy(final LocalDate releaseDate, final DayOfWeek dayOfWeek, final boolean expected) {
        final Treatment treatment = new Treatment(Sequence.of(1L), Title.of("치료"), Description.of("설명입니다"),
            Count.of(10L), releaseDate);

        final DayOfWeekAmountDiscount dayOfWeekAmountDiscount = new DayOfWeekAmountDiscount(Money.of(0.0), dayOfWeek);

        assertThat(dayOfWeekAmountDiscount.isSatisfiedBy(treatment)).isEqualTo(expected);
    }

    public static Stream<Arguments> isSatisfiedBy____2개_요일_배열() {
        final LocalDate releaseDate = LocalDate.now();
        return Stream.of(
            // 출시날짜, 이벤트요일(가변배열), expected
            Arguments.of(releaseDate, new DayOfWeek[]{releaseDate.getDayOfWeek().minus(1),
                releaseDate.getDayOfWeek().minus(2)}, false),
            Arguments.of(releaseDate, new DayOfWeek[]{releaseDate.getDayOfWeek().minus(1), releaseDate.getDayOfWeek()}, true),
            Arguments.of(releaseDate, new DayOfWeek[]{releaseDate.getDayOfWeek(), releaseDate.getDayOfWeek().plus(1)}, true));
    }

    @ParameterizedTest
    @MethodSource
    void isSatisfiedBy____2개_요일_배열(final LocalDate releaseDate, final DayOfWeek[] dayOfWeek, final boolean expected) {
        final Treatment treatment = new Treatment(Sequence.of(1L), Title.of("치료"), Description.of("설명입니다"),
            Count.of(10L), releaseDate);

        final DayOfWeekAmountDiscount dayOfWeekAmountDiscount = new DayOfWeekAmountDiscount(Money.of(0.0), dayOfWeek);

        assertThat(dayOfWeekAmountDiscount.isSatisfiedBy(treatment)).isEqualTo(expected);
    }
}
