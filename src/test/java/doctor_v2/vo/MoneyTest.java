package doctor_v2.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("")
    @Test
    void create_fail____음수() {
        assertThatThrownBy(() -> Money.of(-1.0))
          .isInstanceOf(RuntimeException.class)
          .hasMessage("[ERROR] 돈은 음수가 될 수 없습니다.");
    }

    @DisplayName("")
    @Test
    void multi() {
        final Money five = Money.of(5.0);
        final Money actual = five.multi(Count.of(3L));
        final Money expected = Money.of(15.0);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
