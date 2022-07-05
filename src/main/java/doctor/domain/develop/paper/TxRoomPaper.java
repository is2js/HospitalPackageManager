package doctor.domain.develop.paper;

import doctor.domain.develop.Language;
import doctor.domain.develop.Library;
import doctor.domain.develop.Program;
import doctor.domain.develop.members.programmer.FrontEnd;
import doctor.domain.develop.members.programmer.Programmer;

public class TxRoomPaper implements ProjectPaper{

    private final Library library;
    private final Language language;
    private Programmer programmer;

    public TxRoomPaper(final String library, final String language) {
        this.library = new Library(library);
        this.language = new Language(language);
    }

    @Override
    public Program[] makeProgram() {
        final FrontEnd<TxRoomPaper> frontEnd = new FrontEnd<>() {
            @Override
            protected void setData(final TxRoomPaper projectPaper) {
                this.language = TxRoomPaper.this.language;
                this.library = TxRoomPaper.this.library;
            }
        };

        setProgrammer(frontEnd);

        final Program client = frontEnd.makeProgram(this);
        return new Program[]{client};
    }

    public void setProgrammer(final Programmer programmer) {
        this.programmer = programmer;
    }
}
