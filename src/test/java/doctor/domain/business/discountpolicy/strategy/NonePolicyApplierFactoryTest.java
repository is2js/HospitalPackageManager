package doctor.domain.business.discountpolicy.strategy;

import static doctor.fixture.Fixture.MONEY_만원;
import static doctor.fixture.Fixture.MONEY_천원;
import static org.assertj.core.api.Assertions.*;

import doctor.domain.business.discountpolicy.strategy.NonePolicyApplierFactory;
import doctor.domain.vo.Money;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NonePolicyApplierFactoryTest {

    public static Stream<Arguments> apply() {
        return Stream.of(
          Arguments.of(MONEY_만원, MONEY_만원),
          Arguments.of(MONEY_천원, MONEY_천원)
        );
    }

    @ParameterizedTest
    @MethodSource
    void apply(final Money currentFee, final Money expected) {

        final NonePolicyApplierFactory nonePolicyApplierFactory = new NonePolicyApplierFactory();
        final Money actual = nonePolicyApplierFactory.apply(currentFee);

        assertThat(actual).isEqualTo(expected);
    }
}