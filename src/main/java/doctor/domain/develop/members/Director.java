package doctor.domain.develop.members;

import doctor.domain.develop.Program;
import doctor.domain.develop.members.programmer.BackEnd;
import doctor.domain.develop.members.programmer.FrontEnd;
import doctor.domain.develop.paper.ProjectPaper;
import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.paper.TxRoomPaper;
import java.util.HashMap;
import java.util.Map;

public class Director {

    private Map<String, ProjectPaper> projectPapers = new HashMap<>();

    public void addProjectPaper(final String paperName, final ProjectPaper projectPaper) {
        validateDuplicatePaper(paperName);
        this.projectPapers.put(paperName, projectPaper);
    }

    private void validateDuplicatePaper(final String paperName) {
        if (projectPapers.containsKey(paperName)) {
            throw new RuntimeException("[ERROR] 이미 수행에 포함된 paperName입니다.");
        }
    }

    public void runProjectPaper(final String paperName) {
        validateExistPaper(paperName);

        final ProjectPaper projectPaper = projectPapers.get(paperName);

        if (projectPaper instanceof TxPackagePaper) {
            final TxPackagePaper txPackagePaper = (TxPackagePaper) projectPaper;

            final FrontEnd frontEnd = new FrontEnd<TxPackagePaper>() {
                @Override
                protected void setData(final TxPackagePaper projectPaper) {
                    this.language =txPackagePaper.getFrontEndLanguage();
                }
            };
            final BackEnd backEnd = new BackEnd();
            
            txPackagePaper.setFrontEndProgrammer(frontEnd);
            txPackagePaper.setBackEndProgrammer(backEnd);

            final Program client = frontEnd.makeProgram(txPackagePaper);
            final Program server = backEnd.makeProgram(txPackagePaper);

            deploy(paperName, client, server);
        }

        if (projectPaper instanceof TxRoomPaper) {
            final TxRoomPaper txRoomPaper = (TxRoomPaper) projectPaper;

            final FrontEnd<TxRoomPaper> frontEnd = new FrontEnd<>() {
                @Override
                protected void setData(final TxRoomPaper projectPaper) {
                    this.language =txRoomPaper.getLanguage();
                    this.library = txRoomPaper.getLibrary();
                }
            };

            txRoomPaper.setProgrammer(frontEnd);

            final Program client = frontEnd.makeProgram(txRoomPaper);

            deploy(paperName, client);
        }
    }

    private void validateExistPaper(final String paperName) {
        if (!projectPapers.containsKey(paperName)) {
            throw new RuntimeException("[ERROR] 현재 수행에 포함된 paperName이 아닙니다.");
        }
    }

    private void deploy(final String paperName, final Program... programs) {
        for (final Program program : programs) {
            System.out.printf("%s 프로그램(%s) 배포%n", paperName, program);
        }
    }
}
