package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Library;
import doctor.domain.develop.Program;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class FrontEnd<T extends ProjectPaper> implements Programmer {

    private Library library;
    private Language language;

    public Program makeProgram(final T projectPaper) {
        setData(projectPaper);

        return createFrontEndProgram();
    }

//        this.language =txPackagePaper.getFrontEndLanguage();
//
//        this.language =txRoomPaper.getLanguage();
//        this.library =txRoomPaper.getLibrary();
    protected abstract void setData(final T projectPaper);

    private Program createFrontEndProgram() {
        return new Program();
    }
}
