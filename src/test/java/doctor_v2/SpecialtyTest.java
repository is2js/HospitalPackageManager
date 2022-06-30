package doctor_v2;

import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_10개;
import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialtyTest {

    @DisplayName("")
    @Test
    void calculateFee() {
        final Money expected = Money.of(10000.0);

        final Money actual = SPECIALTY_구안와사_5000원.calculateFee(TREATMENT_두번째_10개, Count.of(2L));

        assertThat(actual).isEqualTo(expected);
    }
}
