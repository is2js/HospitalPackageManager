package doctor_v1.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoctorTest {

    @DisplayName("")
    @Test
    void setReception() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception = new Reception(0L);

        doctor.setReception(reception);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setReceptions() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception1 = new Reception(0L);
        final Reception reception2 = new Reception(0L);

        doctor.setReceptions(reception1, reception2);
        final Set<Reception> actual = doctor.getReceptions();

        assertThat(actual).isNotEmpty();
    }

    @DisplayName("")
    @Test
    void setPackage() {
        final Doctor doctor = new Doctor(0L);
        final Reception reception = new Reception(0L);

        doctor.setReception(reception);

        doctor.setPackage(reception, 3L);
        final List<Package> actual = reception.getPackages();

        assertThat(actual).hasSize(3);
    }
}
