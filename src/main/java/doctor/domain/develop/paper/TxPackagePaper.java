package doctor.domain.develop.paper;

import doctor.domain.develop.Language;
import doctor.domain.develop.Server;
import doctor.domain.develop.members.programmer.BackEnd;
import doctor.domain.develop.members.programmer.FrontEnd;
import doctor.domain.develop.members.programmer.Programmer;

public abstract class TxPackagePaper implements ProjectPaper {

    private final Server server;
    private final Language backEndLanguage;
    private final Language frontEndLanguage;
    private Programmer frontEnd;
    private Programmer backEnd;

    public TxPackagePaper(final String server, final String backEndLanguage, final String frontEndLanguage) {
        this.server = new Server(server);
        this.backEndLanguage = new Language(backEndLanguage);
        this.frontEndLanguage = new Language(frontEndLanguage);
    }

    public void setFrontEndProgrammer(final FrontEnd frontEnd) {
        this.frontEnd = frontEnd;
    }

    public void setBackEndProgrammer(final BackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public Server getServer() {
        return server;
    }

    public Language getBackEndLanguage() {
        return backEndLanguage;
    }

    public Language getFrontEndLanguage() {
        return frontEndLanguage;
    }

    public Programmer getFrontEnd() {
        return frontEnd;
    }

    public Programmer getBackEnd() {
        return backEnd;
    }
}
