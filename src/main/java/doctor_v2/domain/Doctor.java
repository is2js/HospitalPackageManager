package doctor_v2.domain;

import doctor_v2.Specialty;
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
    private static final Set<Treatment> EMPTY = new HashSet<>();
    private Money amount;
    private final Set<Reception> receptions = new HashSet<>();
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

    public void setCoupon(final Patient patient) {
        //몇번 이상 진료내역있는 회원인지 검증하고 줘도 될 듯?
        patient.addCoupon(new Coupon(this));
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
        if (!reception.cancelContract(this) || !this.receptions.contains(reception) ){
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
        return specialties.containsKey(specialty) && specialties.get(specialty).contains(treatment);
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
