package doctor.domain.develop.members;

import doctor.domain.develop.Program;
import doctor.domain.develop.paper.ProjectPaper;
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

        final Program[] programs = projectPaper.makeProgram();

        deploy(paperName, programs);
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
