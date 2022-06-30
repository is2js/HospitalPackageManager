package doctor_v2;

import doctor_v2.domain.Coordinator;
import doctor_v2.domain.Doctor;
import doctor_v2.domain.Patient;
import doctor_v2.domain.Reception;
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
        // doctor가 package당 가격(Long)을 가지고 있다가
        // -> 상태값으로 자본금을 가지며, 값객체 Money with Double를 이용하도록 변경
        final Doctor doctor = new Doctor(Money.of(100.0));

        //package에 들어갈 필드로서 새로운 데이터객체 -> 미리 초기데이터 발행
        //할인정책을 가져 가격계산 책임도 가질 예정.
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
        // doctor가 먼저, 계약조건(수수료율)협력 계약을 제안한다.
        doctor.contract(reception, CommissionRate.of(10.0)); // doctor.cancelContract(reception);

        // Coordinator ===========================================
        final Coordinator coordinator = new Coordinator();
        // 코디네이터가, 소속기관을 결정한다.
        coordinator.setReception(reception);

        // Patient ===========================================
        final Patient patient = new Patient(Money.of(20000.0));


        // 비지니스 ===========================================
        // patient -> 선택한 doctor에 저장된 정보를 보고 선택 후 -> package 구매를 한다.
        // -> 선택을 ui에서 해주는 데, 현재는 없으므로 for + break로 첫번째 정보들만 선택한다고 가정한다.
        // --> 상위도메인을 통한 하위도메인들 전체조회만 하면, 정보가 다 나온다.
        for (Treatment treatment: doctor.getTreatments(specialty)) {
            //patient의 구매
            patient.buyPackage(coordinator, doctor, specialty, treatment, Count.of(2L));
//
//            //doctor의 검증
//            final boolean isValidPackage = doctor.validatePackage(patient, Count.of(2));
//            System.out.println("isValidPackage = " + isValidPackage);
//            break;
        }
    }
}
