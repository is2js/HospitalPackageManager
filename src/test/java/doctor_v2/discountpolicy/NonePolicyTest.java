package doctor_v2.discountpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NonePolicyTest {

    @DisplayName("")
    @Test
    void applyPolicyTo() {
        final NonePolicy nonePolicy = new NonePolicy();
        final Money expected = Money.of(10000.0);

        final Money actual = nonePolicy.applyPolicyTo(Money.of(10000.0));

        assertThat(actual).isEqualTo(expected);
    }
}
