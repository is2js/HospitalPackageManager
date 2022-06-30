package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.DOCTOR_0원;
import static doctor_v2.fixture.Fixture.RECEPTION_1;
import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_10개;
import static doctor_v2.fixture.Fixture.TREATMENT_첫번째_10개;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor_v2.fixture.Fixture;
import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Money;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoctorTest {

    @DisplayName("")
    @Test
    void setReception() {
        final Doctor doctor = DOCTOR_0원;
        
        doctor.setReception(RECEPTION_1);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setReceptions() {
        final Doctor doctor = DOCTOR_0원;
        final Reception reception1 = RECEPTION_1;
        final Reception reception2 = Fixture.RECEPTION_2;

        doctor.setReceptions(reception1, reception2);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setPackage() {
        final Doctor doctor = DOCTOR_0원;
        final Reception reception = RECEPTION_1;

        doctor.setReception(reception);

//        doctor.setPackage(reception, 3L);
//        final List<Package> actual = reception.getPackages();

//        assertThat(actual).hasSize(3);
    }

    @DisplayName("")
    @Test
    void setCoupon() {
        final Doctor doctor = DOCTOR_0원;
        final Patient patient = new Patient(Money.of(0.0));

        doctor.setCoupon(patient);
        final Coupon actual = patient.getCoupon();

        assertThat(actual).isNotEqualTo(Coupon.EMPTY);
    }

    @DisplayName("")
    @Test
    void validatePackage() {
        final Doctor doctor = new Doctor(Money.of(1000.0));

        final Patient 오천원_환자 = new Patient(Money.of(5000.0));
        final Patient 오백원_환자 = new Patient(Money.of(500.0));
        final Patient 영원_환자 = new Patient(Money.of(0.0));
        final Patient 영원_쿠폰_환자 = new Patient(Money.of(0.0));

        final Reception reception1 = RECEPTION_1;
        final Coordinator coordinator1 = new Coordinator();

        doctor.setReception(reception1);
//        doctor.setPackage(reception1, 4L);
        doctor.setCoupon(영원_쿠폰_환자);

        coordinator1.setReception(reception1);

//        오천원_환자.buyPackage(coordinator1, doctor, specialty, treatment, Count.of(2L));
//        오백원_환자.buyPackage(coordinator1, doctor, specialty, treatment, Count.of(2L));
//        영원_환자.buyPackage(coordinator1, doctor, specialty, treatment, Count.of(2L));
//        영원_쿠폰_환자.buyPackage(coordinator1, doctor, specialty, treatment, Count.of(2L));

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

    @DisplayName("")
    @Test
    void contract() {
        final Doctor doctor = DOCTOR_0원;
        final Reception reception1 = RECEPTION_1;
        final Reception reception2 = Fixture.RECEPTION_2;
        final CommissionRate commisionRate = CommissionRate.of(10.0);
        doctor.contract(reception1, commisionRate);
        doctor.contract(reception2, commisionRate);


        assertAll(
            () -> assertThat(doctor.getReceptions()).hasSize(2),
            () -> assertThat(reception1.getDoctors()).hasSize(1),
            () -> assertThat(reception2.getDoctors()).hasSize(1)
        );
    }

    @DisplayName("")
    @Test
    void cancelContract() {
        final Reception reception1 = RECEPTION_1;
        final Reception reception2 = Fixture.RECEPTION_2;
        final CommissionRate commisionRate = CommissionRate.of(10.0);
        DOCTOR_0원.contract(reception1, commisionRate);
        DOCTOR_0원.contract(reception2, commisionRate);
        DOCTOR_0원.cancelContract(reception1);


        assertAll(
            () -> assertThat(DOCTOR_0원.getReceptions()).hasSize(1),
            () -> assertThat(reception1.getDoctors()).isEmpty(),
            () -> assertThat(reception2.getDoctors()).hasSize(1)
        );
    }

    @DisplayName("")
    @Test
    void getTreatments() {

        DOCTOR_0원.addSpecialty(SPECIALTY_구안와사_5000원);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_첫번째_10개);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개);

        final Set<Treatment> actual = DOCTOR_0원.getTreatments(SPECIALTY_구안와사_5000원);

        assertThat(actual).hasSize(2);
    }

    @DisplayName("")
    @Test
    void isValidMatching() {
        DOCTOR_0원.addSpecialty(SPECIALTY_구안와사_5000원);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_첫번째_10개);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개);

        final boolean actual = DOCTOR_0원.isValidMatching(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개);

        assertThat(actual).isTrue();
    }
}
