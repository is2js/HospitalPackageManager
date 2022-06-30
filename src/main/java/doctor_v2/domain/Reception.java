package doctor_v2.domain;

import doctor_v2.Specialty;
import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reception {
    private Money amount;

    private Map<Doctor, CommissionRate> doctors = new HashMap<>();

    public Reception(final Money amount) {
        this.amount = amount;
    }

//    public void addPackage(final Package packageItem) {
//        this.packages.add(packageItem);
//    }

//    public List<Package> getPackages() {
//        return packages;
//    }

//    public Package getPackageWithNoFee() {
//        // 뭔가 저장된 것을 가져올 땐, 존재검사를 해야한다.
//        // READ - 존재 검증
//        if (packages.isEmpty()) {
//            return Package.EMPTY;
//        }
//
//        return packages.remove(0);
//    }

    public Money calculateFee(final Specialty specialty, final Treatment treatment, final Count count) {
        return specialty.calculateFee(treatment, count);
    }

//    public Package getPackageWithFee() {
//        if (packages.isEmpty()) {
//            return Package.EMPTY;
//        }
//
//        final Package packageItem = packages.remove(0);
////        fee += packageItem.getFee();
//
//        return packageItem;
//    }

    public boolean contract(final Doctor doctor, final CommissionRate commissionRate) {
        if (doctors.containsKey(doctor)) {
            return false;
        }

        doctors.put(doctor, commissionRate);
        return true;
    }

    public boolean cancelContract(final Doctor doctor) {
        if (!doctors.containsKey(doctor)) {
            return false;
        }

        doctors.remove(doctor);
        return true;
    }

    public Map<Doctor, CommissionRate> getDoctors() {
        return doctors;
    }

    public Package getPackageWithCoupon(final Doctor doctor, final Specialty specialty, final Treatment treatment,
                                        final Count count) {
        // C-1) 구매정보 검증은 똑같다.
        if (!doctors.containsKey(doctor)
            || !doctor.isValidMatching(specialty, treatment)
            || !treatment.hasCount(count)) {
            return Package.EMPTY;
        }
        // 돈계산 돈검증은 생략

        // C-2) doctor에게 생성된 물건 떼온다.(완전동일)
        //     -> doctor에선 돈계산은 원래 안하고 1)구매정보 검증 2) 구매가능갯수 차감 3) 물건반환 완전히 똑같다.
        //     -> doctor에서 물건가져오는 메서드를 똑같이 사용한다.

        // C-3) 물건검증 -> 성공시 돈가산 -> 생략
        return doctor.sellPackage(specialty, treatment, count);
    }

    public Package sellPackage(final Doctor doctor,
                               final Specialty specialty, final Treatment treatment,
                               final Count count) {
        // A-1) 구매정보 검증
        // A: 나와 계약된 doctor인가 &&
        // B: doctor에 명시된 specialty - treament 상-하위도메인 매칭되는 구매정보인가 &&
        // C: treamtment에 명시된 구매가능 갯수 > count로 (갯수가 모자라진 않나)
        // -> A && B && C 해야 판매수행 -> not A || not B || not C 이면 early return NULL객체
        if (!doctors.containsKey(doctor)
                || !doctor.isValidMatching(specialty, treatment)
                || !treatment.hasCount(count)) {
            return Package.EMPTY;
        }

        // A-2) 물건검증 전 doctor에게 생성된 물건 떼오기
        final Package packageItem = doctor.sellPackage(specialty, treatment, count);

        // A-3) 떼온 물건 검증
        if (packageItem != Package.EMPTY) {
            // A-4) 돈 가산 (reception, doctor)
            final Money sales = calculateFee(specialty, treatment, count);
            final Money commission = sales.multiRate(doctors.get(doctor));
            final Money salesWithNoCommission = sales.minus(commission);
            amount = amount.plus(commission); // reception의 커미션 돈가산
            doctor.plusAmount(salesWithNoCommission); // doctor의 나머지금액 가산
        }

        // A-4) NULL객체의 가능성을 가진 물건변수이며, 정상/NULL객체인지는 알아서 결정되므로
        //      성공if 내부가 아니라 밖에서 return한다.
        return packageItem;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Reception reception = (Reception) o;
        return Objects.equals(amount, reception.amount) && Objects.equals(getDoctors(),
            reception.getDoctors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, getDoctors());
    }
}
