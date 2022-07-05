package doctor;

import doctor.domain.develop.Program;
import doctor.domain.develop.members.programmer.BackEnd;
import doctor.domain.develop.members.programmer.FrontEnd;
import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.members.Director;
import doctor.domain.develop.paper.TxRoomPaper;

public class DevelopApplication {
    public static void main(String[] args) {
        final Director director = new Director();

        director.addProjectPaper("구완와사", new TxPackagePaper("AWS", "java", "js") {
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

        director.addProjectPaper("백구한의원", new TxRoomPaper("VueJS", "js") {
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
                return new Program[]{client};
            }
        });

        director.runProjectPaper("구완와사");
    }
}
