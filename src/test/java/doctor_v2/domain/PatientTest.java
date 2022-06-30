package doctor_v2.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor_v2.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PatientTest {

    @DisplayName("")
    @Test
    void buyPackage_with_no_coupon() {
        final Patient patient = new Patient(Money.of(2000.0));
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(Money.of(0.0));
        final Doctor doctor = new Doctor(Money.of(1000.0));

        doctor.setReceptions(reception);
//        doctor.setCoupon();
//        doctor.setPackage(reception, 2L);

        coordinator.setReception(reception);

//        patient.buyPackage(coordinator, doctor, specialty, treatment, Count.of(2L));
        final Package actual = patient.getPackage();

        assertThat(actual).isNotEqualTo(Package.EMPTY);
    }

    @DisplayName("")
    @Test
    void buyPackage_with_coupon_no_amount() {
        final Patient patient = new Patient(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(Money.of(0.0));
        final Doctor doctor = new Doctor(Money.of(1000.0));

        doctor.setReceptions(reception);
        doctor.setCoupon(patient);
//        doctor.setPackage(reception, 2L);

        coordinator.setReception(reception);

//        patient.buyPackage(coordinator, doctor, specialty, treatment, Count.of(2L));
        final Package actual = patient.getPackage();

        assertThat(actual).isNotEqualTo(Package.EMPTY);
    }

    @DisplayName("")
    @Test
    void hasAmount() {
        final Patient patient = new Patient(Money.of(5000.0));
        final boolean actual1 = patient.hasAmount(Money.of(0.0));
        final boolean actual2 = patient.hasAmount(Money.of(5001.0));
        assertAll(
          () -> assertThat(actual1).isTrue(),
          () -> assertThat(actual2).isFalse()
        );
    }
}
