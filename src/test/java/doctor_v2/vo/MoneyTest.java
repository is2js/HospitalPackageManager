package doctor_v2.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("")
    @Test
    void create_fail____음수() {
        assertThatThrownBy(() -> Money.of(-1.0))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("[ERROR] 음수를 입력할 수 없습니다.");
    }
}
