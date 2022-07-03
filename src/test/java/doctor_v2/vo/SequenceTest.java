package doctor_v2.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SequenceTest {

    @DisplayName("")
    @Test
    void create_fail____음수() {
        Assertions.assertThatThrownBy(() -> Sequence.of(-1L))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("[ERROR] 음수를 입력할 수 없습니다.");
    }
}
