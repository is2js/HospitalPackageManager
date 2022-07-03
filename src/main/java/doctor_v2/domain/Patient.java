package doctor_v2.domain;

import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    private Money amount;
    private   Package packageItem = Package.EMPTY;
    private List<Coupon> coupons = new ArrayList<>();

    public Patient(final Money amount) {
        this.amount = amount;
    }

    public void addCoupon(final Coupon coupon) {
        this.coupons.add(coupon);
    }

    public void buyPackage(final Coordinator coordinator,
                           final Doctor doctor,
                           final Specialty specialty,
                           final Treatment treatment,
                           final Count count) {
        this.packageItem = coordinator.sellPackage(this, doctor, specialty, treatment, count);
    }

    public boolean hasAmount(final Money amount) {
        return this.amount.isGreaterThanOrEqualTo(amount) ;
    }

    public boolean minusAmount(final Money fee) {
        if (amount.isLessThan(fee)) {
            return false;
        }
        amount = amount.minus(fee);
        return true;
    }

    public boolean hasCoupons(final Count count) {
        return coupons.size() >= count.getCount();
    }

    public void minusCoupon(Count count) {
        while (count.isPositive()) {
            removeCoupon();

            count = count.decrease();
        }
    }

    public void removeCoupon() {
        coupons.remove(0);
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public Package getPackage() {
        return packageItem;
    }
}
