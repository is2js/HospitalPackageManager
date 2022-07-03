package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원_AMOUNT_0원_할인;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_10개;
import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.discountpolicy.DiscountPolicy;
import doctor_v2.discountpolicy.condition.NoneCondition;
import doctor_v2.discountpolicy.strategy.AmountPolicyApplier;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialtyTest {

    @DisplayName("")
    @Test
    void calculateFee() {
        final Money expected = Money.of(10000.0);

        final Money actual = SPECIALTY_구안와사_5000원_AMOUNT_0원_할인.calculateFee(TREATMENT_두번째_10개, Count.of(2L));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("")
    @Test
    void multiple_calculateFee() {
        final DiscountPolicy discountPolicy = new DiscountPolicy(new AmountPolicyApplier(Money.of(2000.0)));
        discountPolicy.addCondition(new NoneCondition());

        final Specialty specialty = new Specialty(
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22),
            discountPolicy);

        final Money expected = Money.of(5000.0 - 2000.0).multi(Count.of(2L));
        final Money actual = specialty.calculateFee(TREATMENT_두번째_10개, Count.of(2L));

        assertThat(actual).isEqualTo(expected);
    }


}