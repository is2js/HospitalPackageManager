package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.DOCTOR_0원;
import static doctor_v2.fixture.Fixture.PATIENT_0원;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import org.assertj.core.api.Assertions;
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

    @DisplayName("")
    @Test
    void hasCoupons() {
        PATIENT_0원.addCoupon(new Coupon(DOCTOR_0원));

        assertAll(
          () -> assertThat(PATIENT_0원.hasCoupons(Count.of(1L))).isTrue(),
          () -> assertThat(PATIENT_0원.hasCoupons(Count.of(2L))).isFalse()
        );
    }

    @DisplayName("")
    @Test
    void minusCoupon() {
        PATIENT_0원.addCoupon(new Coupon(DOCTOR_0원));

        PATIENT_0원.minusCoupon(Count.of(1L));
        final boolean actual = PATIENT_0원.hasCoupons(Count.of(1L));

        Assertions.assertThat(actual).isFalse();
    }
}
