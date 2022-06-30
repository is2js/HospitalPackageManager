package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.RECEPTION_1;
import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째;

import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceptionTest {

    @DisplayName("")
    @Test
    void calculateFee() {
        final Money actual = RECEPTION_1.calculateFee(SPECIALTY_구안와사_5000원, TREATMENT_두번째, Count.of(1L));

        final Money expected = Money.of(5000.0);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
