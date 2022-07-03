package doctor_v2.vo;

import java.util.Objects;

public class Title {

    private final String title;

    public Title(final String title) {
        validateLength(title);
        this.title = title;
    }

    public static Title of(final String title) {
        return new Title(title);
    }

    private void validateLength(final String title) {
        if (title.length() < 2) {
            throw new IllegalArgumentException("[ERROR] 제목은 2글자 이상이어야합니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Title{" +
            "title='" + title + '\'' +
            '}';
    }
}
