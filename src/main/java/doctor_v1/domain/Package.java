package doctor_v1.domain;

public class Package {
    public static final Package EMPTY = new Package(null);
    private final Doctor doctor;

    public Package(final Doctor doctor) {

        this.doctor = doctor;
    }

    public Long getFee() {
        return this.doctor.getFee();
    }
}
