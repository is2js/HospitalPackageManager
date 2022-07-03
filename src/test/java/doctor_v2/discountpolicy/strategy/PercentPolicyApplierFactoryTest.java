package doctor_v2.discountpolicy.strategy;

import static doctor_v2.fixture.Fixture.MONEY_만원;
import static org.assertj.core.api.Assertions.*;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PercentPolicyApplierFactoryTest {

    public static Stream<Arguments> apply() {
        return Stream.of(
          Arguments.of(10, Money.of(9000.0)),
          Arguments.of(0, Money.of(10000.0)),
          Arguments.of(100, Money.ZERO)
        );
    }

    @ParameterizedTest
    @MethodSource
    void apply(final int percent, final Money expected) {

        final PercentPolicyApplierFactory percentPolicyApplierFactory = new PercentPolicyApplierFactory(DiscountPercent.of(
            percent));

        final Money actual = percentPolicyApplierFactory.apply(MONEY_만원);

        assertThat(actual).isEqualTo(expected);

    }
}
