package doctor.discountpolicy.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor.domain.DiscountPercent;
import doctor.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PercentPolicyApplierTest {

    @DisplayName("")
    @Test
    void applyPolicyTo() {
        final PercentPolicyApplier percentPolicyApplier = new PercentPolicyApplier(DiscountPercent.of(50));
        final Money expected = Money.of(5000.0);

        final Money actual = percentPolicyApplier.apply(Money.of(10000.0));

        assertThat(actual).isEqualTo(expected);

    }
}
