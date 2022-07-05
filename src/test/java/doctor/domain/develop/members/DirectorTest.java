package doctor.domain.develop.members;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import doctor.domain.develop.Program;
import doctor.domain.develop.members.programmer.BackEnd;
import doctor.domain.develop.members.programmer.FrontEnd;
import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.paper.TxRoomPaper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectorTest {

    @DisplayName("")
    @Test
    void addProjectPaper() {
        final Director director = new Director();

        director.addProjectPaper("기획서1", new TxPackagePaper("AWS", "java", "js") {
            @Override
            public Program[] makeProgram() {
                final FrontEnd<TxPackagePaper> frontEnd = new FrontEnd<>() {
                    @Override
                    protected void setData(final TxPackagePaper projectPaper) {
                        this.language = projectPaper.getFrontEndLanguage();
                    }
                };

                final BackEnd<TxPackagePaper> backEnd = new BackEnd<>() {
                    @Override
                    protected void setData(final TxPackagePaper projectPaper) {
                        this.language = projectPaper.getBackEndLanguage();
                        this.server = projectPaper.getServer();
                    }
                };

                setFrontEndProgrammer(frontEnd);
                setBackEndProgrammer(backEnd);

                final Program client = frontEnd.makeProgram(this);
                final Program server = backEnd.makeProgram(this);

                return new Program[]{client, server};
            }
        });

        assertThatThrownBy(() -> director.addProjectPaper("기획서1", new TxRoomPaper("VueJS", "js") {
            @Override
            public Program[] makeProgram() {
                final FrontEnd<TxRoomPaper> frontEnd = new FrontEnd<>() {
                    @Override
                    protected void setData(final TxRoomPaper projectPaper) {
                        this.language = projectPaper.getLanguage();
                        this.library = projectPaper.getLibrary();
                    }
                };

                setProgrammer(frontEnd);

                final Program client = frontEnd.makeProgram(this);
                return new Program[]{client};            }
        }))
          .isInstanceOf(RuntimeException.class)
          .hasMessage("[ERROR] 이미 수행에 포함된 paperName입니다.");
    }

    @DisplayName("")
    @Test
    void runProjectPaper() {
        final Director director = new Director();

        director.addProjectPaper("기획서1", new TxPackagePaper("AWS", "java", "js") {
            @Override
            public Program[] makeProgram() {
                final FrontEnd<TxPackagePaper> frontEnd = new FrontEnd<>() {
                    @Override
                    protected void setData(final TxPackagePaper projectPaper) {
                        this.language = projectPaper.getFrontEndLanguage();
                    }
                };

                final BackEnd<TxPackagePaper> backEnd = new BackEnd<>() {
                    @Override
                    protected void setData(final TxPackagePaper projectPaper) {
                        this.language = projectPaper.getBackEndLanguage();
                        this.server = projectPaper.getServer();
                    }
                };

                setFrontEndProgrammer(frontEnd);
                setBackEndProgrammer(backEnd);

                final Program client = frontEnd.makeProgram(this);
                final Program server = backEnd.makeProgram(this);

                return new Program[]{client, server};
            }
        });

        assertThatThrownBy(() -> director.runProjectPaper("기획서2"))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("[ERROR] 현재 수행에 포함된 paperName이 아닙니다.");
    }
}
