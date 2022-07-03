package doctor_v2;

import doctor_v2.discountpolicy.DiscountPolicy;
import doctor_v2.discountpolicy.condition.SequenceCondition;
import doctor_v2.discountpolicy.strategy.AmountPolicyApplierFactory;
import doctor_v2.discountpolicy.strategy.PolicyApplier;
import doctor_v2.domain.Coordinator;
import doctor_v2.domain.Doctor;
import doctor_v2.domain.Patient;
import doctor_v2.domain.Reception;
import doctor_v2.domain.Specialty;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // doctor ===========================================
        final Doctor doctor = new Doctor(Money.of(100.0));

        ///add Specialty(MANY) to doctor(ONE)
        // A: 할인정책 action 3가지( 일정금액Amount, 일정비율Percent, 중복Overlapped, 없음None)
        // B: 할인조건 condition 3가지( Sequence선착순, Period기간, DayOfWeek요일(주말) )
        // -> discontPolicy는 2가지 전략객체를 사용하는 일반클래스가 됨.
        // --> discountPolicy는 전략객체 대신, 전략객체Factory를 주입하도록 변경
        //     전략객체는 factory내부에서 캐싱되어 생성 -> 사용된다.
        final PolicyApplier amountPolicyApplierFactory = new AmountPolicyApplierFactory(Money.of(0.0));
        final DiscountPolicy discountPolicy = new DiscountPolicy(amountPolicyApplierFactory);
        discountPolicy.addCondition(new SequenceCondition(Sequence.of(3L)));

        final Specialty specialty = new Specialty(
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22),
            discountPolicy
        );

        doctor.addSpecialty(specialty);

        for (Long seq = 1L; seq <6L; seq++) {
            doctor.addTreatment(
                specialty,
                new Treatment(Sequence.of(seq),
                    Title.of(String.format("%dth 제목", seq)),
                    Description.of(String.format("%d번째 패키지", seq)), Count.of(10L), LocalDate.now())
            );
        }

        // reception ===========================================
        final Reception reception = new Reception(Money.of(0.0));
        doctor.contract(reception, CommissionRate.of(10.0)); // doctor.cancelContract(reception);

        // Coordinator ===========================================
        final Coordinator coordinator = new Coordinator();
        coordinator.setReception(reception);

        // Patient ===========================================
        final Patient patient = new Patient(Money.of(20000.0));

        // 비지니스 ===========================================
        for (Treatment treatment: doctor.getTreatments(specialty)) {
            //patient의 구매
            patient.buyPackage(coordinator, doctor, specialty, treatment, Count.of(2L));
            //doctor의 검증
            final boolean isValidPackage = doctor.validatePackage(patient, Count.of(2L));
            System.out.println("isValidPackage = " + isValidPackage);
            break;
        }
    }
}
