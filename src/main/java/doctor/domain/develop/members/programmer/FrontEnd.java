package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Library;
import doctor.domain.develop.Program;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class FrontEnd<T extends ProjectPaper> implements Programmer {

    protected Library library;
    protected Language language;

    public Program makeProgram(final T projectPaper) {
        setData(projectPaper);

        return createFrontEndProgram();
    }

    protected abstract void setData(final T projectPaper);

    private Program createFrontEndProgram() {
        return new Program();
    }
}
