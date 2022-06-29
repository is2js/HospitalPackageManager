package doctor_v2.domain;

import doctor_v2.Specialty;
import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Money;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Doctor {
    private final Money amount;
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

    public void setPackage(final Reception reception, Long number) {
        if (!receptions.contains(reception)) {
            throw new RuntimeException("협력관계의 원무과 직원이 아닙니다.");
        }

        while (number-- >0) {
            reception.addPackage(new Package(this));
        }
    }

    public void setCoupon(final Patient patient) {
        //몇번 이상 진료내역있는 회원인지 검증하고 줘도 될 듯?
        patient.setCoupon(new Coupon(this));
    }

//    public Long getFee() {
//        return fee;
//    }

    public boolean validatePackage(final Patient patient) {
        final Package packageItem = patient.getPackage();
        return packageItem.isValid(this);
    }

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
}
