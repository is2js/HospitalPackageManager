package doctor_v2.discountpolicy.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NonePolicyApplierTest {

    @DisplayName("")
    @Test
    void applyPolicyTo() {
        final NonePolicyApplier nonePolicyApplier = new NonePolicyApplier();
        final Money expected = Money.of(10000.0);

        final Money actual = nonePolicyApplier.apply(Money.of(10000.0));

        assertThat(actual).isEqualTo(expected);
    }
}
