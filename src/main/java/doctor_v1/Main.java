package doctor_v1;

import doctor_v1.domain.Coordinator;
import doctor_v1.domain.Doctor;
import doctor_v1.domain.Patient;
import doctor_v1.domain.Reception;

public class Main {
    public static void main(String[] args) {
        // 주체 객체들 생성 ====================
        //1. 패키지를 만들 의사 생성 -> 상태값으로 돈을 가짐(Reception에게 떼어줄 커미션 제외 돈 벌어야함.ㄴㄴ)
        // ---> 상태값으로 가지는 값은, doctor의 돈이 아니라, doctor가 발행할 package의 1개당 가격이다.
        // ---> 돈은 일단 reception만 벌게 한다.
        final Doctor doctor = new Doctor(1000L);

        //2. 패키지를 구매할 환자 생성 -> 상태값으로 돈을 가짐(돈을 씀.)
        final Patient patient1 = new Patient(5000L);
        final Patient patient2 = new Patient(0L);

        //3. 의사와 협력할 N:N(상하위X)관계의 Reception 생성 -> 상태값으로 돈을 가짐(커미션 떼가기)
        final Reception reception1 = new Reception(0L);
        final Reception reception2 = new Reception(0L);
        final Reception reception3 = new Reception(0L);

        //4. Reception 하위로 들어갈 프리랜서 코디네이터 생성 -> 돈 필요없이 판매만, 나중에 챙겨줌.
        final Coordinator coordinator1 = new Coordinator();
        final Coordinator coordinator2 = new Coordinator();

        // 객체들끼리 협력 ====================
        // 1. doctor는 같이 일할 협력 원무과직원들을 내부에 저장한다. by 받기기능
        doctor.setReception(reception1);
        doctor.setReceptions(reception2, reception3);

        // 2. doctor는 협력대상 원무과 직원들에게, 내부에서 발행한 package를 준다. by 직접주기기능
        // -> 내부발행할 package 갯수를 정해놓고 직접준다.
        // -> 직접주기기능에 사용되려면, 원무과 직원들은 내부 받기기능이 있어야한다.
        doctor.setPackage(reception1, 10L);
        doctor.setPackage(reception2, 2L);
        // 3. doctor는 일부환자들에게 내부발행된 공짜쿠폰도 준다.
        doctor.setCoupon(patient2);
        // 4. 코디네이터는 원하는 원무과에 들어간다(상위 소속기관으로 박는다)
        coordinator1.setReception(reception1);
        coordinator2.setReception(reception2);
        // 5. 원무과는 직접적으로 다른 객체와 협력하진 않는다.
        // -> (1) doctor는 검증하기 위해 reception을 받아 저장만 한다. -> 협력완료
        // -> (2) codinator는 하위도메인으로서 상위소속기관인 reception을 정해서 받는다. -> 협력완료
        // -> reception은 중개자로서 실질적인 협력관계를 맺지 않는다.
        // --> doctor와는 동등한 입장이지만, [데이터객체 발행기관]으로서 갑으로서, 강제로 협력당하고 있음.
        // --> condinator와는 상위도메인(1)이 하위도메인 정보(N)를 저장하지 않고, 하위가 필드에 저장한 뒤 하위에서 활용한다.

        // 비지니스로직 ====================
        // 1. 거래에서 을인, 환자가 먼저 [구매기능]으로 판매자 코디네이터에게 거래를 건다.
        patient1.buyPackage(coordinator1);
        patient2.buyPackage(coordinator2);
//
//        // 2. doctor는 발행권자(정보전문가)로서 환자를 검증대상으로 받아, 산 패키지를 검증한다.
//        final boolean isValid1 = doctor.validatePackage(patient1);
//        final boolean isValid2 = doctor.validatePackage(patient2);
//
//        System.out.println("isValid1 = " + isValid1);
//        System.out.println("isValid2 = " + isValid2);
    }
}
