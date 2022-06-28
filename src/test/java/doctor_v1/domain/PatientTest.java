package doctor_v1.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PatientTest {

    @DisplayName("")
    @Test
    void buyPackage_with_no_coupon() {
        final Patient patient = new Patient(2000L);
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(0L);
        final Doctor doctor = new Doctor(1000L);

        doctor.setReceptions(reception);
//        doctor.setCoupon();
        doctor.setPackage(reception, 2L);

        coordinator.setReception(reception);

        patient.buyPackage(coordinator);
        final Package actual = patient.getPackage();

        assertThat(actual).isNotEqualTo(Package.EMPTY);
    }

    @DisplayName("")
    @Test
    void buyPackage_with_coupon_no_amount() {
        final Patient patient = new Patient(0L);
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(0L);
        final Doctor doctor = new Doctor(1000L);

        doctor.setReceptions(reception);
        doctor.setCoupon(patient);
        doctor.setPackage(reception, 2L);

        coordinator.setReception(reception);

        patient.buyPackage(coordinator);
        final Package actual = patient.getPackage();

        assertThat(actual).isNotEqualTo(Package.EMPTY);
    }
}
