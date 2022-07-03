package doctor.domain.business;

import static doctor.fixture.Fixture.SPECIALTY_구안와사_5000원_AMOUNT_0원_할인;
import static doctor.fixture.Fixture.TREATMENT_두번째_10개;
import static org.assertj.core.api.Assertions.assertThat;

import doctor.domain.business.Specialty;
import doctor.domain.business.discountpolicy.DiscountPolicy;
import doctor.domain.business.discountpolicy.condition.NoneCondition;
import doctor.domain.business.discountpolicy.strategy.AmountPolicyApplierFactory;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
import doctor.domain.vo.Title;
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
        final DiscountPolicy discountPolicy = new DiscountPolicy(new AmountPolicyApplierFactory(Money.of(2000.0)));
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
