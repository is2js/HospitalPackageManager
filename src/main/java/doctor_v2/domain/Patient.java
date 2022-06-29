package doctor_v2.domain;

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

    public void buyPackage(final Coordinator coordinator) {
         this.packageItem = coordinator.sellPackage(this);
    }

    public void removeCoupon() {
        coupon = Coupon.EMPTY;
    }

    public boolean hasAmount(final Long fee) {
        return amount.isGreaterThan(fee) || amount.isEqualTo(fee);
    }

    public boolean minusAmount(final Long fee) {
        //this.amount = Math.max(this.amount - fee, 0L);
        if (amount.isLessThan(fee)){
            return false;
        }
        amount = amount.minus(fee);
        return true;
    }

    public Package getPackage() {
        return packageItem;
    }
}
