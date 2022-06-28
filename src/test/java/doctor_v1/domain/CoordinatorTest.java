package doctor_v1.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinatorTest {

    @DisplayName("")
    @Test
    void setReception() {
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(0L);

        coordinator.setReception(reception);
        final Reception actual = coordinator.getReception();

        assertThat(actual).isNotNull();
    }
}
