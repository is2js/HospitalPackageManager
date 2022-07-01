package doctor_v2;

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

        Specialty specialty = new Specialty( // <AmountDiscount>( // 일정금액할인 vs 퍼센트할인을 정한다.
            Title.of("구안와사"),
            Duration.ofDays(60),
            Money.of(5000.0),
            LocalDate.of(2022, 06, 22) // 패키지 생성일
        );
//            new SequenceAmountDiscount(Money.of(1000.0), Sequence.of(1L)) // 위에서 정해진 할인정책에 대한 정책조건을 앞에 명시한 구상체
        doctor.addSpecialty(specialty);

        for (Long seq = 1L; seq <6L; seq++) {
            doctor.addTreatment(
                specialty,
                new Treatment(Sequence.of(seq),
                    Title.of(String.format("%dth 제목", seq)),
                    Description.of(String.format("%d번째 패키지", seq)), Count.of(10L))
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
