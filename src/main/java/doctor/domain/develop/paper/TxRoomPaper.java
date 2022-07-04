package doctor.domain.develop.paper;

import doctor.domain.develop.Language;
import doctor.domain.develop.Library;
import doctor.domain.develop.members.programmer.Programmer;

public class TxRoomPaper implements ProjectPaper{

    private final Library library;
    private final Language language;
    private Programmer programmer;

    public TxRoomPaper(final String library, final String language) {
        this.library = new Library(library);
        this.language = new Language(language);
    }

    public void setProgrammer(final Programmer programmer) {
        this.programmer = programmer;
    }

    public Library getLibrary() {
        return library;
    }

    public Language getLanguage() {
        return language;
    }

    public Programmer getProgrammer() {
        return programmer;
    }
}
