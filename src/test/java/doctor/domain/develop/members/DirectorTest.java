package doctor.domain.develop.members;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.paper.TxRoomPaper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectorTest {

    @DisplayName("")
    @Test
    void addProjectPaper() {
        final Director director = new Director();
        director.addProjectPaper("기획서1", new TxPackagePaper());

        assertThatThrownBy(() -> director.addProjectPaper("기획서1", new TxRoomPaper()))
          .isInstanceOf(RuntimeException.class)
          .hasMessage("[ERROR] 이미 수행에 포함된 paperName입니다.");
    }

    @DisplayName("")
    @Test
    void runProjectPaper() {
        final Director director = new Director();
        director.addProjectPaper("기획서1", new TxPackagePaper());

        assertThatThrownBy(() -> director.runProjectPaper("기획서2"))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("[ERROR] 현재 수행에 포함된 paperName이 아닙니다.");
    }
}
