package doctor.domain.vo;

import java.util.Objects;

public class Title {

    private final String value;

    public Title(final String value) {
        validateLength(value);
        this.value = value;
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
        return Objects.equals(value, title1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Title{" +
            "title='" + value + '\'' +
            '}';
    }
}
