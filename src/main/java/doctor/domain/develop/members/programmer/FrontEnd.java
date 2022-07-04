package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Library;
import doctor.domain.develop.Program;
import doctor.domain.develop.paper.ProjectPaper;
import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.paper.TxRoomPaper;

public class FrontEnd implements Programmer {

    private Library library;
    private Language language;

    public Program makeProgram(final ProjectPaper projectPaper) {
        if (projectPaper instanceof TxPackagePaper) {
            final TxPackagePaper txPackagePaper = (TxPackagePaper) projectPaper;

            this.language = txPackagePaper.getFrontEndLanguage();
        }

        if (projectPaper instanceof TxRoomPaper) {
            final TxRoomPaper txRoomPaper = (TxRoomPaper) projectPaper;

            this.language = txRoomPaper.getLanguage();
            this.library = txRoomPaper.getLibrary();
        }
        return createFrontEndProgram();
    }

    private Program createFrontEndProgram() {
        return new Program();
    }
}
