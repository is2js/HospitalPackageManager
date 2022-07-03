package doctor.domain;

import doctor.domain.members.business.Doctor;
import doctor.domain.vo.Count;
import java.util.Objects;

public class Package {

    public static final Package EMPTY = new Package(null, null, null, Count.EMPTY);

    private final Doctor doctor;
    private final Specialty specialty;
    private final Treatment treatment;
    private final Count count;

    public Package(final Doctor doctor, final Specialty specialty, final Treatment treatment, final Count count) {
        this.doctor = doctor;
        this.specialty = specialty;
        this.treatment = treatment;
        this.count = count;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Count getCount() {
        return count;
    }

    public boolean isCountGreaterThanOrEqualTo(final Count count) {
        return this.count.isGreaterThanOrEqualTo(count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Package aPackage = (Package) o;
        return Objects.equals(doctor, aPackage.doctor) && Objects.equals(specialty, aPackage.specialty)
            && Objects.equals(treatment, aPackage.treatment) && Objects.equals(count, aPackage.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, specialty, treatment, count);
    }
}
