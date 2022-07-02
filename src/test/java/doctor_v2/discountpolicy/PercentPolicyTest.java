package doctor_v2.discountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PercentPolicyTest {

    @DisplayName("")
    @Test
    void applyPolicyTo() {
        final PercentPolicy percentPolicy = new PercentPolicy(DiscountPercent.of(50));
        final Money expected = Money.of(5000.0);

        final Money actual = percentPolicy.applyPolicyTo(Money.of(10000.0));

        assertThat(actual).isEqualTo(expected);

    }
}
