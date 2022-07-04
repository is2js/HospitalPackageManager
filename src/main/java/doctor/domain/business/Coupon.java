package doctor.domain.business;

import doctor.domain.business.members.Doctor;

public class Coupon {

    public static final Coupon EMPTY = new Coupon(null);

    private final Doctor doctor;

    public Coupon(final Doctor doctor) {
        this.doctor = doctor;
    }
}
