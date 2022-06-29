package doctor_v2.vo;

public class Title {
    private final String title;

    public Title(final String title) {
        this.title = title;
    }

    public static Title of(final String title) {
        if (title.length() < 1) {
            throw new IllegalArgumentException("[ERROR] 제목은 2글자 이상이어야합니다.");
        }
        return new Title(title);
    }
}
