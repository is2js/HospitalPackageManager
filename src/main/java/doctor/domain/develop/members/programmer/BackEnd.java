package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Program;
import doctor.domain.develop.Server;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class BackEnd<T extends ProjectPaper> extends Programmer<ProjectPaper> {

    protected Server server;
    protected Language language;

    public Program makeProgram(final T projectPaper) {
        setData(projectPaper);

        return createBackEndProgram();
    }

    protected abstract void setData(final T projectPaper);

    private Program createBackEndProgram() {
        // 추후  Server와 Language로 만든 백엔드프로그램을 내놔야함.
        return new Program();
    }
}
