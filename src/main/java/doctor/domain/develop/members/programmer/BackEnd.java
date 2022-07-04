package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Program;
import doctor.domain.develop.Server;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class BackEnd<T extends ProjectPaper> implements Programmer {

    protected Server server;
    protected Language language;

    public Program makeProgram(final T projectPaper) {
        setData(projectPaper);

        return createBackEndProgram();
    }

    protected abstract void setData(final T projectPaper);

    private Program createBackEndProgram() {
        return new Program();
    }
}
