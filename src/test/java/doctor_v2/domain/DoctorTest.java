package doctor_v2.domain;

import static doctor_v2.fixture.Fixture.DOCTOR_0원;
import static doctor_v2.fixture.Fixture.RECEPTION_1;
import static doctor_v2.fixture.Fixture.RECEPTION_2;
import static doctor_v2.fixture.Fixture.SPECIALTY_구안와사_5000원;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_10개;
import static doctor_v2.fixture.Fixture.TREATMENT_두번째_9개;
import static doctor_v2.fixture.Fixture.TREATMENT_첫번째_10개;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import doctor_v2.discountpolicy.amount.SequenceAmountDiscount;
import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoctorTest {

    @DisplayName("")
    @Test
    void setReception() {
        DOCTOR_0원.setReception(RECEPTION_1);
        final Set<Reception> actual = DOCTOR_0원.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setReceptions() {
        final Doctor doctor = DOCTOR_0원;
        final Reception reception1 = RECEPTION_1;
        final Reception reception2 = RECEPTION_2;

        doctor.setReceptions(reception1, reception2);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setCoupon() {
        final Doctor doctor = DOCTOR_0원;
        final Patient patient = new Patient(Money.of(0.0));

        doctor.setCoupons(patient, Count.of(1L));
        final List<Coupon> actual = patient.getCoupons();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void contract() {
        final Doctor doctor = new Doctor(Money.of(0.0));
        final Reception reception1 = new Reception(Money.of(0.0));
        final Reception reception2 = new Reception(Money.of(0.0));
        final CommissionRate commissionRate = CommissionRate.of(10.0);

        doctor.contract(reception1, commissionRate);
        doctor.contract(reception2, commissionRate);

        assertAll(
            () -> assertThat(doctor.getReceptions()).hasSize(2),
            () -> assertThat(reception1.getDoctors()).hasSize(1),
            () -> assertThat(reception2.getDoctors()).hasSize(1)
        );
    }

    @DisplayName("")
    @Test
    void cancelContract() {
        final Reception reception1 = new Reception(Money.of(0.0));
        final Reception reception2 = new Reception(Money.of(0.0));
        final CommissionRate commissionRate = CommissionRate.of(10.0);
        final Doctor doctor = new Doctor(Money.of(0.0));
        doctor.contract(reception1, commissionRate);
        doctor.contract(reception2, commissionRate);
        doctor.cancelContract(reception1);

        assertAll(
            () -> assertThat(doctor.getReceptions()).hasSize(1),
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

    @DisplayName("")
    @Test
    void plusAmount() {
        DOCTOR_0원.plusAmount(Money.of(5000.0));
        DOCTOR_0원.plusAmount(Money.of(5000.0));

        assertThat(DOCTOR_0원).extracting("amount")
            .isEqualTo(Money.of(10000.0));
    }

    @DisplayName("")
    @Test
    void sellPackage() {
        DOCTOR_0원.addSpecialty(SPECIALTY_구안와사_5000원);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개);
        DOCTOR_0원.addTreatment(SPECIALTY_구안와사_5000원, TREATMENT_두번째_9개);
        final Package actual = DOCTOR_0원.sellPackage(SPECIALTY_구안와사_5000원, TREATMENT_두번째_10개, Count.of(1L));
        final Package expected = new Package(DOCTOR_0원, SPECIALTY_구안와사_5000원, TREATMENT_두번째_9개, Count.of(1L));

        assertAll(
          () -> assertThat(actual).isEqualTo(expected),
          () -> TREATMENT_두번째_10개.hasCount(Count.of(9L))
        );
    }

    @Test
    void validatePackage_invalid____empty_package() {
        final Doctor contractedDoctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(5000.0 * 10));

        final Specialty registeredSpecialty = new Specialty(
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22),
            new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));

        final Treatment matchedTreatment = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        final Count availableCount = Count.of(1L);
        final Count notAvailableCount = Count.of(11L);

        contractedDoctor.addSpecialty(registeredSpecialty);
        contractedDoctor.addTreatment(registeredSpecialty, matchedTreatment);
        contractedDoctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        patient.buyPackage(coordinator, contractedDoctor, registeredSpecialty, matchedTreatment, notAvailableCount);

        assertThatThrownBy(() -> contractedDoctor.validatePackage(patient, availableCount))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("[ERROR] 소유하신 상품이 존재하지 않습니다.");
    }
    @Test
    void validatePackage_invalid____not_creator() {
        final Doctor contractedDoctor = new Doctor(Money.of(0.0));
        final Doctor notContractedDoctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(5000.0 * 10));

        final Specialty registeredSpecialty = new Specialty(
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22),
            new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));

        final Treatment matchedTreatment = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        final Count availableCount = Count.of(1L);

        contractedDoctor.addSpecialty(registeredSpecialty);
        contractedDoctor.addTreatment(registeredSpecialty, matchedTreatment);
        contractedDoctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        patient.buyPackage(coordinator, contractedDoctor, registeredSpecialty, matchedTreatment, availableCount);

        assertThatThrownBy(() -> notContractedDoctor.validatePackage(patient, availableCount))
            .isInstanceOf(RuntimeException.class)
            .hasMessage(String.format("[ERROR]  %s가 발행한 상품이 아닙니다.", notContractedDoctor));
    }

    @Test
    void validatePackage_invalid____not_matched() {
        final Doctor contractedDoctor = new Doctor(Money.of(0.0));
        final Reception reception = new Reception(Money.of(0.0));
        final Coordinator coordinator = new Coordinator();
        final Patient patient = new Patient(Money.of(5000.0 * 10));

        final Specialty registeredSpecialty = new Specialty(
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22),
            new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));

        final Treatment matchedTreatment = new Treatment(
            Sequence.of(1L),
            Title.of(String.format("%dth 제목", 1L)),
            Description.of(String.format("%d번째 패키지", 1L)),
            Count.of(10L), LocalDate.now());

        final Count availableCount = Count.of(1L);
        final Count notAvailableCount = Count.of(2L);

        contractedDoctor.addSpecialty(registeredSpecialty);
        contractedDoctor.addTreatment(registeredSpecialty, matchedTreatment);
        contractedDoctor.contract(reception, CommissionRate.of(10.0));
        coordinator.setReception(reception);

        patient.buyPackage(coordinator, contractedDoctor, registeredSpecialty, matchedTreatment, availableCount);

        assertThatThrownBy(() -> contractedDoctor.validatePackage(patient, notAvailableCount))
            .isInstanceOf(RuntimeException.class)
            .hasMessage(String.format("[ERROR] 남은 이용가능 횟수가 모자랍니다. 남은 횟수(%d) < 사용하려는 횟수(%d)",availableCount.getCount(), notAvailableCount.getCount()));
    }
}
