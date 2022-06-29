package doctor_v2.domain;

public class Coupon {
    public static final Coupon EMPTY = new Coupon(null);
    private final Doctor doctor;

    public Coupon(final Doctor doctor) {
        this.doctor = doctor;
    }
}
