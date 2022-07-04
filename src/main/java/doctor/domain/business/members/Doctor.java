package doctor.domain.business.members;

import doctor.domain.business.Coupon;
import doctor.domain.business.Package;
import doctor.domain.business.Specialty;
import doctor.domain.business.Treatment;
import doctor.domain.vo.CommissionRate;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
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

    public Package sellPackage(final Specialty specialty, final Treatment treatment, final Count count) {
        if (!isValidMatching(specialty, treatment)
            || !treatment.hasCount(count)) {

            return Package.EMPTY;
        }

        treatment.minusCount(count);

        return new Package(this, specialty, treatment, count);
    }

    public boolean isValidMatching(final Specialty specialty, final Treatment treatment) {
        validateSpecialty(specialty);
        validateMatchedSpecialtyAndTreatment(specialty, treatment);
        return true;
    }

    private void validateSpecialty(final Specialty specialty) {
        if (!specialties.containsKey(specialty)) {
            throw new RuntimeException("[ERROR] 해당의사에게 등록되지 않은 진료과목을 가진 상품입니다.");
        }
    }

    private void validateMatchedSpecialtyAndTreatment(final Specialty specialty, final Treatment treatment) {
        if (!specialties.get(specialty).contains(treatment)) {
            throw new RuntimeException("[ERROR] 치료정보가 진료과목과 연결되지 않은 상품입니다.");
        }
    }

    public void plusAmount(final Money money) {
        amount = amount.plus(money);
    }

    public boolean validatePackage(final Patient patient, final Count count) {
        final Package packageItem = patient.getPackage();

        if (packageItem == Package.EMPTY) {
            throw new RuntimeException("[ERROR] 소유하신 상품이 존재하지 않습니다.");
        }

        if (packageItem.getDoctor() != this) {
            throw new RuntimeException(String.format("[ERROR]  %s가 발행한 상품이 아닙니다.", this));
        }

        isValidMatching(packageItem.getSpecialty(), packageItem.getTreatment());


        if (!packageItem.isCountGreaterThanOrEqualTo(count)) {
            throw new RuntimeException(String.format("[ERROR] 남은 이용가능 횟수가 모자랍니다. 남은 횟수(%d) < 사용하려는 횟수(%d)",packageItem.getCount().getValue(), count.getValue()));
        }
        return true;
    }

    public Set<Reception> getReceptions() {
        return receptions;
    }

    public Set<Treatment> getTreatments(final Specialty specialty) {
        if (!specialties.containsKey(specialty) || specialties.get(specialty).isEmpty()) {
            return EMPTY;
        }
        return specialties.get(specialty);
    }

    public void setCoupons(final Patient patient, Count count) {
        while (count.isPositive()) {
            patient.addCoupon(new Coupon(this));
            count = count.decrease();
        }
    }

    public void setReception(final Reception reception) {
        this.receptions.add(reception);
    }

    public void setReceptions(final Reception... receptions) {
        this.receptions.addAll(Arrays.asList(receptions));
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
