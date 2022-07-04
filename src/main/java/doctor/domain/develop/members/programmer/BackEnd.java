package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Program;
import doctor.domain.develop.Server;
import doctor.domain.develop.paper.ProjectPaper;
import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.paper.TxRoomPaper;

public class BackEnd implements Programmer {

    private Server server;
    private Language language;

    public Program makeProgram(final ProjectPaper projectPaper) {
        if (projectPaper instanceof TxPackagePaper) {
            final TxPackagePaper txPackagePaper = (TxPackagePaper) projectPaper;

            this.language = txPackagePaper.getBackEndLanguage();
            this.server = txPackagePaper.getServer();
        }

        if (projectPaper instanceof TxRoomPaper) {
            final TxRoomPaper txRoomPaper = (TxRoomPaper) projectPaper;
//            txRoomPaper.
        }
        return createBackEndProgram();
    }

    private Program createBackEndProgram() {
        return new Program();
    }
}
