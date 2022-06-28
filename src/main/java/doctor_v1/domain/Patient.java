package doctor_v1.domain;

public class Patient {
    private final Long fee;
    private Coupon coupon = Coupon.EMPTY;

    public Patient(final Long fee) {
        this.fee = fee;
    }

    public void setCoupon(final Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }
}
