package doctor.domain.business.members;

import static org.assertj.core.api.Assertions.assertThat;

import doctor.domain.business.members.Coordinator;
import doctor.domain.business.members.Reception;
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
