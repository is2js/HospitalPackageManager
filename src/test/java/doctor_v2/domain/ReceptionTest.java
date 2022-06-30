package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.DOCTOR_0원;
import static doctor_v2.fixture.Fixture.RECEPTION_1;
import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_10개;
import static doctor_v2.fixture.Fixture.TREATMENT_첫번째_10개;
import static org.assertj.core.api.Assertions.assertThat;

import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceptionTest {

    @DisplayName("")
    @Test
    void contract() {
        RECEPTION_1.contract(DOCTOR_0원, CommissionRate.of(10.0));
        final Map<Doctor, CommissionRate> doctors = RECEPTION_1.getDoctors();
        final boolean b = doctors.containsKey(DOCTOR_0원);
        final CommissionRate actual = doctors.get(DOCTOR_0원);
        final CommissionRate expected = CommissionRate.of(10.0);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("")
    @Test
    void calculateFee() {
        final Money actual = RECEPTION_1.calculateFee(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개, Count.of(1L));

        final Money expected = Money.of(5000.0);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("")
    @Test
    void sellPackage() {
        final Doctor doctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        doctor.addSpecialty(SPECIALTY_구안와사_5000원);
        doctor.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_첫번째_10개);
        doctor.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개);

        doctor.contract(reception, CommissionRate.of(10.0));

        final Package actual = reception.sellPackage(doctor, SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개,
            Count.of(1L));

        Assertions.assertThat(actual).isNotEqualTo(Package.EMPTY);

    }
}
