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
        // doctor는 package발행에 필요한 데이터를 미리 받아저장하여 알고 있다.
        // -> 상위도메인이라서 먼저 단독key로 저장한다.
        doctor.addSpecialty(specialty);
//        // -> doctor는 specialty에 딸린 하위도메인인 treatment를 상위도메인과 함께 입력받아 저장한다.
//        //doctor.addTreatment(specialty, treatment);
//        // --> 하위도메인이 규칙적으로 여러개일 경우, for문을 돌려서 초기데이터를 생성과 동시에 add할 수 있다.
//        //     상수는 그냥 적으면 되고, 변하는 값을 for문에서 선언하여 생성하되,
//        //     for문에서 개당 생성되는 것을, for문위에 변수받아놓고 넣던지, 애초에 add메서드를 내부에서 써버려서 하나씩 생성된 것을 바로 넣어버린다.
//        //     여러가지가 돌아가면, 상위를 바깥/하위를 안쪽 for문의 변수로 선언해서 바깥쪽부터 돌려가며 만들면 된다.
//        doctor.addTreatment(specialty, treatment);
//
        for (Long seq = 1L; seq <6L; seq++) {
            doctor.addTreatment(
                specialty,
                new Treatment(Sequence.of(seq),
                    Description.of(String.format("%d번째 패키지", seq)),
                    Count.of(10L))
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
//
//            //patient의 구매
//            patient.buyPackage(coordinator,
//                doctor, specialty, treatment,
//                Count.of(2));
//
//            //doctor의 검증
//            final boolean isValidPackage = doctor.validatePackage(patient, Count.of(2));
//            System.out.println("isValidPackage = " + isValidPackage);
//            break;
        }
    }
}
