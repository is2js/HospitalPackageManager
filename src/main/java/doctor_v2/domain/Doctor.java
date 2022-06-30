package doctor_v2.domain;

import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Doctor {
    private Money amount;
    private final Set<Reception> receptions = new HashSet<>();
    private static final Set<Treatment> EMPTY = new HashSet<>();
    private Map<Specialty, Set<Treatment>> specialties = new HashMap<>();

    public Doctor(final Money amount) {
        this.amount = amount;
    }

    public void setReception(final Reception reception) {
        this.receptions.add(reception);
    }

    public void setReceptions(final Reception... receptions) {
        this.receptions.addAll(Arrays.asList(receptions));
    }

    public Set<Reception> getReceptions() {
        return receptions;
    }

//    public void setPackage(final Reception reception, Long number) {
//        if (!receptions.contains(reception)) {
//            throw new RuntimeException("협력관계의 원무과 직원이 아닙니다.");
//        }
//
//        while (number-- >0) {
//            reception.addPackage(new Package(this));
//        }
//    }

    public void setCoupons(final Patient patient, Count count) {
        while (count.isPositive()) {
            patient.addCoupon(new Coupon(this)); // 나중에 doctor or treament에서 쿠폰발급수량도 정해놔야할 듯.
            count = count.decrease();
        }
    }

//    public Long getFee() {
//        return fee;
//    }

//    public boolean validatePackage(final Patient patient) {
//        final Package packageItem = patient.getPackage();
//        return packageItem.isValid(this);
//    }

    public boolean addSpecialty(final Specialty specialty) {
        if (specialties.containsKey(specialty)) {
            return false;
        }
        specialties.put(specialty, new HashSet<>());
        return true;
    }

    public boolean addTreatment(final Specialty specialty, final Treatment treatment) {
        if (!specialties.containsKey(specialty)) {
            return false;
        }
        return specialties.get(specialty).add(treatment);
    }

    public boolean contract(final Reception reception, final CommissionRate commissionRate) {
        if (!reception.contract(this, commissionRate)) {
            return false;
        }

        return this.receptions.add(reception);
    }


    public boolean cancelContract(final Reception reception) {
        if (!reception.cancelContract(this) || !this.receptions.contains(reception)) {
            return false;
        }

        return receptions.remove(reception);
    }

    public Set<Treatment> getTreatments(final Specialty specialty) {
        //READ로서 조회 전 상위도메인 존재 && 하위도메인(조회데이터)의 존재검증
        // A && B -> not A  || not B early return
        if (!specialties.containsKey(specialty) || specialties.get(specialty).isEmpty()) {
            return EMPTY;
        }
        return specialties.get(specialty);
    }

    public boolean isValidMatching(final Specialty specialty, final Treatment treatment) {
        //A: key가 존재하고 && 그 key의 value값들 안에 포함되어야한다.
        if (!specialties.containsKey(specialty)) {
            throw new RuntimeException("[ERROR] 해당의사에게 등록되지 않은 진료과목을 가진 상품입니다.");
        }
        final Set<Treatment> treatments = specialties.get(specialty);
        if (!treatments.contains(treatment)) {
            throw new RuntimeException("[ERROR] 치료정보가 진료과목과 연결되지 않은 상품입니다.");
        }
        return true;
    }

    public Package sellPackage(final Specialty specialty, final Treatment treatment, final Count count) {
        // A-1: 구매정보 검증
        if (!isValidMatching(specialty, treatment)
            || !treatment.hasCount(count)) {
            return Package.EMPTY;
        }
        // A-2: 생성 전, 구매가능 갯수 검증 및 갯수 차감
        treatment.minusCount(count);

        // A-3: 생성하여 반환
        return new Package(this, specialty, treatment, count);
    }

    public void plusAmount(final Money money) {
        amount = amount.plus(money);
    }

    public boolean validatePackage(final Patient patient, final Count count) {
        //A-1: 환자에게서 구매물건을 받아와서
        final Package packageItem = patient.getPackage();

        //A-2: getter로 정보들 꺼내서 검증
//        return packageItem != Package.EMPTY
//            && packageItem.getDoctor() == this // 물건 생산자의 생산자 확인
//            && isValidMatching(packageItem.getSpecialty(), packageItem.getTreatment()) // 상-하위 매칭 검증
//            && packageItem.getCount().isGreaterThanOrEqualTo(count); // 구매한 갯수 vs 검증할 갯수 검증
        // A && B && C && D 시 true -> not A, not Bearly return 써도되며, early thr를 써서 어떤 것에 걸리는지 확인시켜준다.
        if (packageItem == Package.EMPTY) {
            throw new RuntimeException("[ERROR] 소유하신 상품이 존재하지 않습니다.");
        }

        if (packageItem.getDoctor() != this) {
            throw new RuntimeException(String.format("[ERROR]  %s가 발행한 상품이 아닙니다.", this));
        }

        isValidMatching(packageItem.getSpecialty(), packageItem.getTreatment());


        if (!packageItem.isCountGreaterThanOrEqualTo(count)) {
            throw new RuntimeException(String.format("[ERROR] 남은 이용가능 횟수가 모자랍니다. 남은 횟수(%d) < 사용하려는 횟수(%d)",packageItem.getCount().getCount(), count.getCount()));
        }
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Doctor doctor = (Doctor) o;
        return Objects.equals(amount, doctor.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
