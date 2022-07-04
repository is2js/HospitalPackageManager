package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Program;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class Programmer<T extends ProjectPaper> {
    public Program makeProgram(final T projectPaper) {
        setData(projectPaper);

        return createProgram();
    }

    protected abstract void setData(final T projectPaper);

    protected abstract Program createProgram();
}
