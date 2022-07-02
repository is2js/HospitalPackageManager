package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.DOCTOR_0원;
import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PatientTest {

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
        final Patient patient_0 = new Patient(Money.of(0.0));
        patient_0.addCoupon(new Coupon(DOCTOR_0원));

        assertAll(
            () -> assertThat(patient_0.hasCoupons(Count.of(1L))).isTrue(),
            () -> assertThat(patient_0.hasCoupons(Count.of(2L))).isFalse()
        );
    }

    @DisplayName("")
    @Test
    void minusCoupon() {
        final Patient patient_0 = new Patient(Money.of(0.0));
        patient_0.addCoupon(new Coupon(DOCTOR_0원));
        patient_0.minusCoupon(Count.of(1L));

        final boolean actual = patient_0.hasCoupons(Count.of(1L));

        assertThat(actual).isFalse();
    }


    public static Stream<Arguments> buyPackage_with_no_coupon_provider() {
        return Stream.of(
            Arguments.of(5L, 3L),
            Arguments.of(5L, 5L)
        );
    }

    @ParameterizedTest
    @MethodSource("buyPackage_with_no_coupon_provider")
    void buyPackage_with_no_coupon(final long availableCount, final Long buyCount) {
        final Doctor doctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(5000.0 * availableCount));

        final Treatment treatment_10개 = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        doctor.addSpecialty(SPECIALTY_구안와사_5000원);
        doctor.addTreatment(SPECIALTY_구안와사_5000원, treatment_10개);
        doctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        patient.buyPackage(coordinator, doctor, SPECIALTY_구안와사_5000원, treatment_10개, Count.of(buyCount));
        final Package actual = patient.getPackage();

        assertAll(
            () -> assertThat(actual.getCount()).isEqualTo(Count.of(buyCount)), // 산 갯수 확인 from patient package
            // 상수객체의 상태변화는, parameterized 돌리는 동안 유지가 되버린다. -> 갯수가 변화하는 데이터객체 treatment는 상태객체로 변경
            () -> assertThat(treatment_10개.getCount()).isEqualTo(Count.of(10L - buyCount)), // 차감 갯수 확인 from treatment 객체
            () -> assertThat(actual).isNotEqualTo(Package.EMPTY)
        );
    }

    public static Stream<Arguments> buyPackage_with_coupon_provider() {
        return Stream.of(
            Arguments.of(1L, 1L),
            Arguments.of(2L, 1L),
            Arguments.of(2L, 2L),
            Arguments.of(11L, 10L)
        );
    }

    @ParameterizedTest
    @MethodSource("buyPackage_with_coupon_provider")
    void buyPackage_with_coupon_no_amount(final Long couponCount, final Long buyCount) {
        final Doctor doctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(0.0));

        final Treatment treatment_10개 = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        doctor.addSpecialty(SPECIALTY_구안와사_5000원);
        doctor.addTreatment(SPECIALTY_구안와사_5000원, treatment_10개);
        doctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        doctor.setCoupons(patient, Count.of(couponCount));

        patient.buyPackage(coordinator, doctor, SPECIALTY_구안와사_5000원, treatment_10개, Count.of(buyCount));
        final Package actual = patient.getPackage();

        assertAll(
            () -> assertThat(actual.getCount()).isEqualTo(Count.of(buyCount)), // 산 갯수 확인 from patient package
            () -> assertThat(treatment_10개.getCount()).isEqualTo(Count.of(10L - buyCount)), // 차감 갯수 확인 from treatment 객체
            () -> assertThat(actual).isNotEqualTo(Package.EMPTY)
        );
    }

    public static Stream<Arguments> buyPackage_with_coupon_fail____lack_of_quantity_provide() {
        return Stream.of(
            Arguments.of(1L, 2L),
            Arguments.of(2L, 10L)
        );
    }

    @ParameterizedTest
    @MethodSource("buyPackage_with_coupon_fail____lack_of_quantity_provide")
    void buyPackage_with_coupon_fail____lack_of_quantity(final Long couponCount, final Long buyCount) {
        final Doctor doctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(0.0));

        final Treatment treatment_10개 = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        doctor.addSpecialty(SPECIALTY_구안와사_5000원);
        doctor.addTreatment(SPECIALTY_구안와사_5000원, treatment_10개);
        doctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        doctor.setCoupons(patient, Count.of(couponCount));

        assertThatThrownBy(() -> patient.buyPackage(coordinator, doctor, SPECIALTY_구안와사_5000원, treatment_10개, Count.of(buyCount)))
          .isInstanceOf(RuntimeException.class)
          .hasMessage("[ERROR] 쿠폰의 갯수가 모자랍니다.");
    }
}
