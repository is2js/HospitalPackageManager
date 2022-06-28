package doctor_v1.domain;

public class Patient {
    private Long amount;
    private Coupon coupon = Coupon.EMPTY;
    private Package packageItem = Package.EMPTY;

    public Patient(final Long amount) {
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
        return this.amount >= fee;
    }

    public boolean minusAmount(final Long fee) {
        // 하한을 가지는 객체의 차감은, max로 0을 챙겨줘야한다?
        //this.amount = Math.max(this.amount - fee, 0L);

        // 다른 방법으로는 클 때만 뺄 수 있게...
        // 가진돈이 더 적을 때는(앞에 검증했지만..?) false를 응답하게 해서 minus기능이 실패했다고 알려준다.
        if (amount < fee){
            return false;
        }
        amount -= fee;
        return true;
    }

    public Package getPackage() {
        return packageItem;
    }
}
