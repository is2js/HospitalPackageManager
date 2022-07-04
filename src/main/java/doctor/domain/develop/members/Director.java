package doctor.domain.develop.members;

import doctor.domain.develop.paper.ProjectPaper;
import java.util.HashMap;
import java.util.Map;

public class Director {

    private Map<String, ProjectPaper> projectPapers = new HashMap<>();

    public void addProjectPaper(final String paperName, final ProjectPaper projectPaper) {
        if (projectPapers.containsKey(paperName)) {
            throw new RuntimeException("[ERROR] 이미 수행에 포함된 paperName입니다.");
        }
        this.projectPapers.put(paperName, projectPaper);
    }

    public void runProjectPaper(final String paperName) {
        throw new UnsupportedOperationException("Director#runProjectPaper not write.");
    }
}
