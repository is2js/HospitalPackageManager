package doctor.domain.members;

import doctor.domain.business.Package;
import doctor.domain.business.Specialty;
import doctor.domain.business.Treatment;
import doctor.domain.vo.CommissionRate;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
import java.util.HashMap;
import java.util.Map;

public class Reception {

    private Money amount;
    private Map<Doctor, CommissionRate> doctors = new HashMap<>();

    public Reception(final Money amount) {
        this.amount = amount;
    }

    public Money calculateFee(final Specialty specialty, final Treatment treatment, final Count count) {
        return specialty.calculateFee(treatment, count);
    }

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
        if (isValidPackage(doctor, specialty, treatment, count)) {
            return Package.EMPTY;
        }

        return doctor.sellPackage(specialty, treatment, count);
    }

    public Package sellPackage(final Doctor doctor,
                               final Specialty specialty, final Treatment treatment,
                               final Count count) {
        if (isValidPackage(doctor, specialty, treatment, count)) {
            return Package.EMPTY;
        }

        final Package packageItem = doctor.sellPackage(specialty, treatment, count);

        if (packageItem != Package.EMPTY) {
            final Money sales = calculateFee(specialty, treatment, count);
            final Money commission = sales.multiRate(doctors.get(doctor));
            final Money salesWithNoCommission = sales.minus(commission);
            amount = amount.plus(commission);
            doctor.plusAmount(salesWithNoCommission);
        }

        return packageItem;
    }

    private boolean isValidPackage(final Doctor doctor, final Specialty specialty, final Treatment treatment,
                                   final Count count) {
        return !doctors.containsKey(doctor)
            || !doctor.isValidMatching(specialty, treatment)
            || !treatment.hasCount(count);
    }
}
