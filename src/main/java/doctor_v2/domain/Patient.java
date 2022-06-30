package doctor_v2.domain;

import doctor_v2.Specialty;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;

public class Patient {
    private Money amount;
    private Coupon coupon = Coupon.EMPTY;
    private Package packageItem = Package.EMPTY;

    public Patient(final Money amount) {
        this.amount = amount;
    }

    public void setCoupon(final Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void buyPackage(final Coordinator coordinator,
                           final Doctor doctor,
                           final Specialty specialty,
                           final Treatment treatment,
                           final Count count) {
        this.packageItem = coordinator.sellPackage(this, doctor, specialty, treatment, count);
    }

    public void removeCoupon() {
        coupon = Coupon.EMPTY;
    }

    public boolean hasAmount(final Money amount) {
        return this.amount.isGreaterThan(amount) || this.amount.isEqualTo(amount);
    }

    public boolean minusAmount(final Money fee) {
        //this.amount = Math.max(this.amount - fee, 0L);
        if (amount.isLessThan(fee)) {
            return false;
        }
        amount = amount.minus(fee);
        return true;
    }

    public Package getPackage() {
        return packageItem;
    }
}
