package doctor_v2.vo;

import java.util.Objects;

public class Description {

    private final String description;

    private Description(final String description) {
        this.description = description;
    }

    public static Description of(final String description) {
        if (description.length() < 5) {
            throw new IllegalArgumentException("[ERROR] 제목은 5글자 이상이어야합니다.");
        }
        return new Description(description);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
