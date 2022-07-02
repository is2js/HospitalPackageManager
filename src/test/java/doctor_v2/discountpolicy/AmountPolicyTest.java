package doctor_v2.discountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.discountpolicy.condition.SequenceCondition;
import doctor_v2.fixture.Fixture;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AmountPolicyTest {

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
        final DiscountPolicy amountPolicy = new AmountPolicy(discountAmount);
        amountPolicy.addCondition(sequenceCondition);

        final Money actual = amountPolicy.calculateFee(Fixture.TREATMENT_첫번째_시퀀스, Fixture.COUNT_1개, beforeDiscountAmount);

        assertThat(actual).isEqualTo(expected);
    }
}
