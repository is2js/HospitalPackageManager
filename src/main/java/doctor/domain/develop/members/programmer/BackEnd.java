package doctor.domain.develop.members.programmer;

import doctor.domain.develop.Language;
import doctor.domain.develop.Program;
import doctor.domain.develop.Server;
import doctor.domain.develop.paper.ProjectPaper;

public abstract class BackEnd<T extends ProjectPaper> extends Programmer<T> {

    protected Server server;
    protected Language language;

    @Override
    protected Program createProgram() {
        // 추후  Server와 Language로 만든 백엔드프로그램을 내놔야함.
        return new Program();
    }
}
