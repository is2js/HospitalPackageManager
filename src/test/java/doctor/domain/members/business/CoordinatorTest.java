package doctor.domain.members.business;

import static org.assertj.core.api.Assertions.assertThat;

import doctor.domain.members.business.Coordinator;
import doctor.domain.members.business.Reception;
import doctor.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinatorTest {

    @DisplayName("")
    @Test
    void setReception() {
        final Coordinator coordinator = new Coordinator();
        final Reception reception = new Reception(Money.of(0.0));

        coordinator.setReception(reception);
        final Reception actual = coordinator.getReception();

        assertThat(actual).isNotNull();
    }
}
