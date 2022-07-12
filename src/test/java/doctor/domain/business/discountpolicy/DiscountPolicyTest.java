package doctor.domain.business.discountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor.domain.business.DiscountPercent;
import doctor.domain.business.discountpolicy.condition.SequenceCondition;
import doctor.domain.business.discountpolicy.strategy.AmountPolicyApplierFactory;
import doctor.domain.business.discountpolicy.strategy.PercentPolicyApplierFactory;
import doctor.domain.vo.Money;
import doctor.domain.vo.Sequence;
import doctor.fixture.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountPolicyTest {

    @DisplayName("")
    @Test
    void setNext_and_calculateFee() {

        final DiscountPolicy discountPolicy = new DiscountPolicy(new AmountPolicyApplierFactory(Money.of(1000D)))
            .addPolicy(new PercentPolicyApplierFactory(DiscountPercent.of(10)))
            .addCondition(new SequenceCondition(Sequence.of(3L)));

        final Money actual = discountPolicy.calculateFee(Fixture.TREATMENT_첫번째_시퀀스, Fixture.COUNT_1개, Money.of(10000D));

        final Money expected = Money.of((10000D - 1000D) * 0.9);

        assertThat(actual).isEqualTo(expected);
    }
}
