package doctor.discountpolicy.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor.discountpolicy.DiscountPolicy;
import doctor.discountpolicy.condition.SequenceCondition;
import doctor.fixture.Fixture;
import doctor.domain.vo.Money;
import doctor.domain.vo.Sequence;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AmountPolicyApplierTest {

    public static Stream<Arguments> applyPolicyTo() {
        return Stream.of(
            Arguments.of(Fixture.MONEY_만원, Fixture.MONEY_천원, Fixture.MONEY_만원.minus(Fixture.MONEY_천원)),
            Arguments.of(Fixture.MONEY_만원, Money.of(11000.0), Money.ZERO)
        );
    }

    @ParameterizedTest
    @MethodSource
    void applyPolicyTo(final Money beforeDiscountAmount, final Money discountAmount, final Money expected) {
        final SequenceCondition sequenceCondition = new SequenceCondition(Sequence.of(3L));
        final PolicyApplier amountPolicyApplierFactory = new AmountPolicyApplierFactory(discountAmount);
        final DiscountPolicy discountPolicy = new DiscountPolicy(amountPolicyApplierFactory);
        discountPolicy.addCondition(sequenceCondition);

        final Money actual = discountPolicy.calculateFee(Fixture.TREATMENT_첫번째_시퀀스, Fixture.COUNT_1개, beforeDiscountAmount);

        assertThat(actual).isEqualTo(expected);
    }
}
