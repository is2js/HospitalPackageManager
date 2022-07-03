package doctor.domain.business.discountpolicy.strategy;

import static doctor.fixture.Fixture.MONEY_만원;
import static doctor.fixture.Fixture.MONEY_천원;
import static org.assertj.core.api.Assertions.*;

import doctor.domain.business.discountpolicy.strategy.AmountPolicyApplierFactory;
import doctor.domain.vo.Money;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AmountPolicyApplierFactoryTest {

    public static Stream<Arguments> apply() {
        return Stream.of(
            Arguments.of(MONEY_만원, MONEY_만원.minus(MONEY_천원))
        );
    }

    @ParameterizedTest
    @MethodSource
    void apply(final Money currentFee, final Money expected) {
        final AmountPolicyApplierFactory factory = new AmountPolicyApplierFactory(MONEY_천원);
        final Money actual = factory.apply(currentFee);

        assertThat(actual).isEqualTo(expected);
    }
}
