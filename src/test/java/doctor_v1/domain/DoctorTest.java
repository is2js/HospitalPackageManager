package doctor_v1.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoctorTest {

    @DisplayName("")
    @Test
    void setReception() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception = new Reception(0L);

        doctor.setReception(reception);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setReceptions() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception1 = new Reception(0L);
        final Reception reception2 = new Reception(0L);

        doctor.setReceptions(reception1, reception2);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setPackage() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception = new Reception(0L);

        doctor.setReception(reception);

        doctor.setPackage(reception, 3L);
        final List<Package> actual = reception.getPackages();

        assertThat(actual).hasSize(3);
    }

    @DisplayName("")
    @Test
    void setCoupon() {
        final Doctor doctor = new Doctor(0L);
        final Patient patient = new Patient(0L);

        doctor.setCoupon(patient);
        final Coupon actual = patient.getCoupon();

        assertThat(actual).isNotEqualTo(Coupon.EMPTY);
    }

    @DisplayName("")
    @Test
    void validatePackage() {
        final Doctor doctor = new Doctor(1000L);

        final Patient 오천원_환자 = new Patient(5000L);
        final Patient 오백원_환자 = new Patient(500L);
        final Patient 영원_환자 = new Patient(0L);
        final Patient 영원_쿠폰_환자 = new Patient(0L);

        final Reception reception1 = new Reception(0L);
        final Coordinator coordinator1 = new Coordinator();

        doctor.setReception(reception1);
        doctor.setPackage(reception1, 4L);
        doctor.setCoupon(영원_쿠폰_환자);

        coordinator1.setReception(reception1);

        오천원_환자.buyPackage(coordinator1);
        오백원_환자.buyPackage(coordinator1);
        영원_환자.buyPackage(coordinator1);
        영원_쿠폰_환자.buyPackage(coordinator1);

        final boolean isValid_오천원_환자 = doctor.validatePackage(오천원_환자);
        final boolean isValid_오백원_환자 = doctor.validatePackage(오백원_환자);
        final boolean isValid_영원_환자 = doctor.validatePackage(영원_환자);
        final boolean isValid_영원_쿠폰_환자 = doctor.validatePackage(영원_쿠폰_환자);

        assertAll(
            () -> assertThat(isValid_오천원_환자).isTrue(),
            () -> assertThat(isValid_오백원_환자).isFalse(),
            () -> assertThat(isValid_영원_환자).isFalse(),
            () -> assertThat(isValid_영원_쿠폰_환자).isTrue()
        );
    }
}
