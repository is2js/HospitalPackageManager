package doctor_v2.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TitleTest {

    @DisplayName("")
    @Test
    void create_fail____1글자이하_제목() {
        assertThatThrownBy(() -> Title.of("제"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("[ERROR] 제목은 2글자 이상이어야합니다.");
    }
}
